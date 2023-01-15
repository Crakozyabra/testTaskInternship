package com.game.service;

import com.game.entity.Player;
import com.game.repository.OtherPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OtherPlayerService {

    OtherPlayerRepository otherPlayerRepository;

    public OtherPlayerService(@Autowired OtherPlayerRepository otherPlayerRepository) {
        this.otherPlayerRepository = otherPlayerRepository;
    }

    public List<Player> getPlayers() {
        return otherPlayerRepository.findAll();
    }
}
