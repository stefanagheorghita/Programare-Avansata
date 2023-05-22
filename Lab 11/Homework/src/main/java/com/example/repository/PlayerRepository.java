package com.example.repository;
import com.example.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import game.Player;
@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long>{

}
