package com.example.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.processing.Generated;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "players")
public class PlayerEntity {
    @GeneratedValue
    @Id
    private Long id;

    @Column(name = "player_id")
    private String playerId;


    @Column(name = "name")
    private String name;

    public PlayerEntity(String playerId, String name) {
        this.playerId = playerId;
        this.name = name;
    }


}
