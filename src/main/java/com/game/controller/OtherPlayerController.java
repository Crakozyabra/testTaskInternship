package com.game.controller;

import com.game.entity.Player;
import com.game.service.OtherPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/other_players")
public class OtherPlayerController {

    private OtherPlayerService otherPlayerService;

    public OtherPlayerController(@Autowired OtherPlayerService otherPlayerService) {
        this.otherPlayerService = otherPlayerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Player> getPlayers() {
        return otherPlayerService.getPlayers();
    }
}
