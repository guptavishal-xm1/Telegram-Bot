package org.telegram.bot.Entity;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class userService implements userRepo {




    @Override
    public Optional<user> findById(long username) {
        return Optional.empty();
    }

    @NotNull
    @Override
    public <S extends user> S save(@NotNull S entity) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends user> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends user> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<user> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public user getOne(Long aLong) {
        return null;
    }

    @Override
    public user getById(Long aLong) {
        return null;
    }

    @Override
    public user getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends user> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends user> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends user> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends user> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends user> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends user> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends user, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends user> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<user> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<user> findAll() {
        return List.of();
    }

    @Override
    public List<user> findAllById(Iterable<Long> longs) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(user entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends user> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<user> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<user> findAll(Pageable pageable) {
        return null;
    }


}
