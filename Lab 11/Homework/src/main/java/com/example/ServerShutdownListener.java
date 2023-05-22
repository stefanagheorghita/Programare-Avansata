package com.example;

import com.example.repository.GameRepository;
import com.example.repository.GameRepository2;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class ServerShutdownListener implements ApplicationListener<ContextClosedEvent> {

    private final GameRepository2 gameRepository2;

    public ServerShutdownListener(GameRepository2 gameRepository2) {
        this.gameRepository2 = gameRepository2;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        gameRepository2.deleteAll();
    }
}
