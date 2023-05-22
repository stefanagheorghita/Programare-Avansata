package com.example.controller;

import com.example.GameServer;
import com.example.entity.GameEntity;
import com.example.repository.GameRepository;
import game.Game;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/games")
@Tag(name = "Game", description = "The Game API")
public class GameController {

    @Autowired
    private GameRepository gameRepository;



    @GetMapping
    public ResponseEntity<List<GameEntity>> getAllGames() {
        List< GameEntity > gameEntities = gameRepository.findAll();
        return ResponseEntity.ok(gameEntities);
    }


}