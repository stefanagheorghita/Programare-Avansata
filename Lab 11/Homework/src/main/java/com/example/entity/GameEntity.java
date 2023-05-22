package com.example.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "games")
public class GameEntity {
    @GeneratedValue
    @Id
    private Long id;

    @Column(name = "game_id")
    private String gameId;

    public GameEntity(String gameId) {
        this.gameId = gameId;
    }
}