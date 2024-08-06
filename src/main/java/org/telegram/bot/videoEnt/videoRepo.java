package org.telegram.bot.videoEnt;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface videoRepo extends JpaRepository<videoEntity,Long> {
    Optional<videoEntity> findById(Long id);
}
