package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {
    static int i = 1;
    final MainFrame frame;
    JButton exitBtn = new JButton("Exit");
    //create all buttons (Load, Exit, etc.)
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton resetBtn=new JButton("Reset");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {

        setLayout(new GridLayout(1, 4));
        add(loadBtn);
        add(saveBtn);
        add(resetBtn);
        add(exitBtn);
       resetBtn.addActionListener(this::resetGame);
        exitBtn.addActionListener(this::exitGame);
        loadBtn.addActionListener(this::loadGame);
        saveBtn.addActionListener(this::saveGame);
    }

    private void resetGame(ActionEvent actionEvent) {
    }

    private void loadGame(ActionEvent actionEvent) {
    }

    private void saveGame(ActionEvent actionEvent) {
        try {
            File outputfile = new File("game" + i+".png");
            ImageIO.write(frame.canvas.image, "png", outputfile);
        } catch (IOException e) {
            System.out.println("Error saving image: " + e.getMessage());
        }
    }


    private void exitGame(ActionEvent e) {
        frame.dispose();
    }

}
