package com.example.controller;

import java.util.List;

import com.example.GameServer;
import com.example.TakePlayers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerController {


    public PlayerController(){

        System.out.println("PlayerController constructor called");
    }

    @GetMapping
    public String getRegisteredPlayers() {
        TakePlayers takePlayers = new TakePlayers();
        return takePlayers.start();
    }
}