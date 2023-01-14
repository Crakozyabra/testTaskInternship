package com.game.repository;

import com.game.entity.Player;

import java.util.List;
import java.util.Optional;

public interface IPlayerRepository {
    List<Player> getAll();

    Player save(Player player);

    Player updateById(Player player);

    Optional<Player> findById(long id);

    void deleteById(long id);
}
