package org.telegram.bot;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.bot.Entity.user;
import org.telegram.bot.Entity.userRepo;
import org.telegram.bot.videoEnt.videoEntity;
import org.telegram.bot.videoEnt.videoRepo;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.BotSession;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.AfterBotRegistration;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.time.LocalDate;


@Component
public class myBot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {

    private final org.telegram.bot.Entity.userRepo userRepo;
    private final org.telegram.bot.videoEnt.videoRepo videoRepo;
    private long chatId;
    private String text;
    private String userName;
    private final TelegramClient telegramClient = new OkHttpTelegramClient(getBotToken());

    public myBot(@Qualifier("userRepo") userRepo userRepo, videoRepo videoRepo) {
        this.userRepo = userRepo;
        this.videoRepo = videoRepo;
    }
    public boolean chekUser(long id){
        return userRepo.findById(id).isPresent();
    }
    public user getUser(){
        return userRepo.findById(chatId).get();
    }
    public videoEntity getVideo(long id){
        return videoRepo.findById(id).get();
    }

    @Override
    public String getBotToken() {
        return "7029609334:AAHwEeHJeQGxhl6MNgsczr56wfR4ybMTEUg";
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }

    @Override
    public void consume(Update update) {
        chatId = update.getMessage().getChatId();
        text = update.getMessage().getText();
        userName = update.getMessage().getFrom().getUserName();
        if (update.hasMessage() && update.getMessage().hasText()) {
            System.out.println(update.getMessage().getText());
            if (text.equals("/start")){
                // check user exist or not
                System.out.println(chekUser(chatId));
                if (!chekUser(chatId)){
                    user tgUser = new user(chatId,userName,false,LocalDate.now(),0);
                    userRepo.save(tgUser);
                    System.out.println("user saved");
                } else {
                    System.out.println("usee already exist"); // redundant for testing
                }
            } else if (text.equals("/getvideo")) {
                if (!chekUser(chatId)){
                    SendMessage msg = SendMessage
                            .builder()
                            .chatId(chatId)
                            .text("You didn't registered to our service yet. please use /start to registered")
                            .build();
                    try {
                        telegramClient.execute(msg);
                    } catch (TelegramApiException e) {
                        e.fillInStackTrace();
                    }
                } else {
                    boolean checkSubscription = getUser().isIs_paid();
                    System.out.println(getUser().isIs_paid());
                    if (checkSubscription) {
                        sendVideoPaid();
                    }else sendVideoFree();
                }


            }
        } else if (update.getMessage().hasVideo() && chatId==6585662351L) {
            System.out.println("Shiki sending");
            System.out.println(update.getMessage().getVideo().getFileId());
            videoEntity video = new videoEntity();
            video.setF_id(update.getMessage().getVideo().getFileId());
            videoRepo.save(video);
        }
    }
    public void sendVideoPaid(){
        //sending videos to paid user
        int totalreq = getUser().getTotalRequest();

        SendVideo videoMsg = SendVideo
                .builder()
                .chatId(chatId)
                .video(new InputFile(getVideo(totalreq+1).getF_id()))
                .build();
        System.out.println(getVideo(totalreq+1).getF_id());
        totalreq++;
        user vidUser = getUser();
        vidUser.setTotalRequest(totalreq);
        userRepo.save(vidUser);

        try {
            telegramClient.execute(videoMsg);
            System.out.println("video sent");
        } catch (TelegramApiException e){
            System.out.println("something went wrong");
            System.out.println(e);
            e.fillInStackTrace();
        }
    }
    public void sendVideoFree(){// sending video to free user without premium

        int totalreq = getUser().getTotalRequest();

        if (getUser().getTodayLimit()<3) {
            SendVideo videoMsg = SendVideo
                    .builder()
                    .chatId(chatId)
                    .video(new InputFile(getVideo(totalreq + 1).getF_id()))
                    .build();
            System.out.println(getVideo(totalreq + 1).getF_id());
            totalreq++;
            user vidUser = getUser();
            vidUser.setTotalRequest(totalreq);
            vidUser.setTodayLimit(getUser().getTodayLimit()+1);
            userRepo.save(vidUser);

            try {
                telegramClient.execute(videoMsg);
                System.out.println("video sent");
            } catch (TelegramApiException e) {
                System.out.println("something went wrong");
                e.fillInStackTrace();
            }
        } else {
            boolean flag = checkDate();
            System.out.println("check date"+flag);
            if (flag){
                SendMessage msg = SendMessage
                        .builder()
                        .chatId(chatId)
                        .text("Today 3 limit exhausted")
                        .build();
                try {
                    telegramClient.execute(msg);
                } catch (TelegramApiException e){
                    e.fillInStackTrace();
                }
            } else {
                user vidUser = getUser();
                vidUser.setRegdate(LocalDate.now());
                vidUser.setTodayLimit(0);
                userRepo.save(vidUser);
            }

        }


    }

    public boolean checkDate(){
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        LocalDate userDate = getUser().getRegdate();
        System.out.println(userDate);
        return localDate.isEqual(userDate);
    }
    @AfterBotRegistration
    public void register(BotSession session){
        System.out.println("bot started"+" " + session.isRunning());
    }
}
