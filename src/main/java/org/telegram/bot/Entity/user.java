package org.telegram.bot.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class user {
    @Id
    private long id;
    private String username;
    private boolean is_paid;
    private LocalDate date;
    private int todayLimit;
    private int totalRequest;

    public user(long id, String username, boolean is_paid, LocalDate date, int todayLimit) {
        this.id = id;
        this.username = username;
        this.is_paid = is_paid;
        this.date = date;
        this.todayLimit = todayLimit;
    }

    public user() {

    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", is_paid=" + is_paid +
                ", Regdate=" + date +
                ", todayLimit=" + todayLimit +
                '}';
    }

    public int getTotalRequest() {
        return totalRequest;
    }

    public void setTotalRequest(int totalRequest) {
        this.totalRequest = totalRequest;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isIs_paid() {
        return is_paid;
    }

    public void setIs_paid(boolean is_paid) {
        this.is_paid = is_paid;
    }

    public LocalDate getRegdate() {
        return date;
    }

    public void setRegdate(LocalDate date) {
        this.date = date;
    }

    public int getTodayLimit() {
        return todayLimit;
    }

    public void setTodayLimit(int todayLimit) {
        this.todayLimit = todayLimit;
    }
}
