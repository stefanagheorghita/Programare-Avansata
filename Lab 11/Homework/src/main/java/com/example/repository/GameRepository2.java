package com.example.repository;


import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class GameRepository2 {

    public void createPlayer(String id, String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into players (player_id, name) values (?,?)")) {
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        }

    }

    public void createGame(String id) throws SQLException {
        Connection con = Database.getConnection();

        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into games (game_id) values (?)")) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }

    }

    public void deleteAll() {Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "delete from games")) {
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try (PreparedStatement pstmt = con.prepareStatement(
                "delete from players")) {
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}