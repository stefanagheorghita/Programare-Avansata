package org.example;


import org.game.Game;
import org.game.Player;

import javax.swing.*;

import java.awt.*;

import static java.awt.BorderLayout.*;

public class MainFrame extends JFrame {
    private Game game;
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;

    public MainFrame() {
        super("My Drawing Application");
        Player player1=new Player("one", Color.blue);
        Player player2=new Player("one", Color.magenta);
        game = new Game(800, 600,player1,player2);
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        //create the components
        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);
        canvas = new DrawingPanel(this);


        add(canvas, CENTER);
        add(configPanel, NORTH);
        add(controlPanel, SOUTH);

        pack();
    }

    public Game getGame() {

        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
