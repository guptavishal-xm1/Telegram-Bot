package org.telegram.bot.Entity;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface userRepo extends JpaRepository<user, Long> {
    Optional<user> findById(long username);
    @NotNull
    <S extends user> S save(@NotNull S entity);



}
