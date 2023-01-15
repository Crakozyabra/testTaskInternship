package com.game.repository;

import com.game.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtherPlayerRepository extends JpaRepository<Player, Long> {
}
