package com.example.controller;
import com.example.entity.PlayerEntity;
import com.example.repository.GameRepository2;
import com.example.repository.PlayerRepository;
import game.Player;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/players")
@Tag(name = "Player", description = "The Player API")
public class PlayerController {


    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository2 gameRepository2;

    @GetMapping("")
    public String index() {
        return "index";
    }

    @PostMapping
    @Operation(summary = "Add a new player")
    public ResponseEntity<String> addPlayer(@RequestBody Player player) throws SQLException {
        Player newPlayer = new Player(player.getName());
        PlayerEntity newPlayerE = new PlayerEntity(newPlayer.getId(), newPlayer.getName());
        //playerRepository.save(newPlayerE);
        gameRepository2.createPlayer(newPlayerE.getPlayerId(), newPlayer.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body("Player added successfully.");
    }

    @PutMapping("/{playerId}")
    @Operation(summary = "Modify an existing player")
    public ResponseEntity<String> modifyPlayer(@PathVariable Long playerId, @RequestBody Player updatedPlayer) {
        Optional<PlayerEntity> playerOptional = getPlayerById(playerId);
        if (playerOptional.isPresent()) {
            PlayerEntity player = playerOptional.get();
            player.setName(updatedPlayer.getName());
            return ResponseEntity.ok("Player modified successfully. Player ID: " + playerId);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found with ID: " + playerId);
        }
    }

    @DeleteMapping("/{playerId}")
    @Operation(summary = "Delete an existing player")
    public ResponseEntity<String> deletePlayer(@PathVariable Long playerId) {
        Optional<PlayerEntity> playerOptional = getPlayerById(playerId);
        if (playerOptional.isPresent()) {
            playerRepository.deleteById(playerOptional.get().getId());
            return ResponseEntity.ok("Player deleted successfully. Player ID: " + playerId);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found with ID: " + playerId);
        }
    }

    @GetMapping("/all")
    @Operation(summary = "Get all players")
    public ResponseEntity<List<PlayerEntity>> listPlayers() {
        return ResponseEntity.ok(playerRepository.findAll());
    }

    private Optional<PlayerEntity> getPlayerById(Long id) {
        return playerRepository.findAll().stream()
                .filter(playerEntity -> playerEntity.getId().equals(id))
                .findFirst();
    }
}

