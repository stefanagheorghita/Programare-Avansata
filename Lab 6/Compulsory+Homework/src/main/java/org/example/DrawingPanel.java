package org.example;

import org.game.Dot;
import org.game.Game;
import org.game.Line;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;

public class DrawingPanel extends JPanel {
    int noPress = 0;
    final MainFrame frame;
    final static int W = 800, H = 600;
    private int numVertices;
    private double edgeProbability;
    private int[] x, y;

    BufferedImage image;
    Graphics2D graphics; //the tools needed to draw in the image

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        initPanel();
        createBoard();
    }

    private void initPanel() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                verifyPressedDot(x, y);
                repaint();
            }
        });
    }

    private boolean stillActive(Dot dot) {
        if (!dot.freeDots()) {

//            graphics.setColor(Color.red);
//            graphics.fillOval(dot.getX() - 5, dot.getY() - 5, 10, 10);
//            repaint();

            graphics.setColor(Color.white);
            graphics.fillOval(dot.getX() - 5, dot.getY() - 5, 10, 10);
            repaint();
            return false;
        }
        return true;
    }

    private void verifyPressedDot(int x, int y) {
        Dot dot = frame.getGame().getBoard().getDotObj(x, y);
        if (dot != null) {
            if (stillActive(dot)) //verificam ca nodul ales mai are laturi libere
                if (noPress == 0) {  //daca este primul nod ales
                    graphics.setColor(Color.yellow);
                    graphics.fillOval(dot.getX() - 5, dot.getY() - 5, 10, 10);
                    repaint();
                    frame.getGame().getBoard().setSelected(dot);
                    dot.setSelected(true);
                    noPress = 1;
                } else { //daca e al doilea nod
                    int index = frame.getGame().getBoard().getDots().indexOf(dot);
                    if (frame.getGame().getBoard().getDots().get(index).verifyAdj(frame.getGame().getBoard().getSelected())) //verificam ca primul si al doilea nod sunt vecine
                        if (!dot.getAdjDots().get(frame.getGame().getBoard().getSelected()).equals(Color.cyan)) { //daca latura este deja luata
//                            graphics.setColor(Color.RED);
//                            graphics.fillOval(dot.getX() - 5, dot.getY() - 5, 10, 10);
//                            repaint();
                            graphics.setColor(Color.white);
                            graphics.fillOval(dot.getX() - 5, dot.getY() - 5, 10, 10);
                            repaint();
                        } else { //daca latura este libera
                            dot.getAdjDots().put(frame.getGame().getBoard().getSelected(), frame.getGame().getCurrentPlayer().getColor()); //adaugam latura
                            frame.getGame().getBoard().getSelected().getAdjDots().put(dot, frame.getGame().getCurrentPlayer().getColor()); //adaugam
                            graphics.setColor(frame.getGame().getCurrentPlayer().getColor());
                            graphics.drawLine(dot.getX(), dot.getY(), frame.getGame().getBoard().getSelected().getX(), frame.getGame().getBoard().getSelected().getY()); //coloram
                            dot.full++; //adaugam ca am ocupat latura
                            frame.getGame().getBoard().getSelected().full++; //adaugam ca am ocupat latura
                            graphics.setColor(Color.white);
                            graphics.fillOval(frame.getGame().getBoard().getSelected().getX() - 5, frame.getGame().getBoard().getSelected().getY() - 5, 10, 10);
                            repaint();
                            if (frame.getGame().triangle(dot, frame.getGame().getBoard().getSelected())) { //verificam triunghi
                                frame.getGame().setWinner(frame.getGame().getCurrentPlayer());
                                paintWinner();
                            } else if (frame.getGame().finish()) //verificam final fara castigator
                                paintEq();
                            noPress = 0;
                            if (frame.getGame().getPlayer1().equals(frame.getGame().getCurrentPlayer())) //schimbam jucatorul
                                frame.getGame().setCurrentPlayer(frame.getGame().getPlayer2());
                            else
                                frame.getGame().setCurrentPlayer(frame.getGame().getPlayer1());


                        }


                }


        }
    }

    private void paintEq() {
        graphics.setColor(Color.black);
        graphics.clearRect(0, 0, getWidth(), getHeight());
        String message = "Game over! Nobody won.";
        Font font = new Font("Arial", Font.BOLD, 24);
        graphics.setFont(font);
        graphics.setColor(Color.yellow);
        graphics.drawString(message, getWidth() / 2 - graphics.getFontMetrics().stringWidth(message) / 2, getHeight() / 2);
        repaint();
    }

    private void paintWinner() {
        graphics.setColor(Color.black);
        graphics.clearRect(0, 0, getWidth(), getHeight());
        String message = "Game over! " + frame.getGame().getWinner() + " wins.";
        Font font = new Font("Arial", Font.BOLD, 24);
        graphics.setFont(font);
        graphics.setColor(Color.green); // set the color to red
        graphics.drawString(message, getWidth() / 2 - graphics.getFontMetrics().stringWidth(message) / 2, getHeight() / 2);
        repaint();
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, 800, 600);
    }

    final void createBoard() {
        noPress = 0;
        frame.getGame().getBoard().resetDots();
        frame.getGame().getBoard().resetLines();
        numVertices = (Integer) frame.configPanel.dotsSpinner.getValue();
        edgeProbability = (Double) frame.configPanel.linesCombo.getSelectedItem();
        createOffscreenImage();
        createVertices();
        drawLines();
        drawVertices();
        repaint();

    }

    private void createVertices() {
        int x0 = W / 2;
        int y0 = H / 2;
        int radius = H / 2 - 10;
        double alpha = 2 * Math.PI / numVertices;
        x = new int[numVertices];
        y = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            x[i] = x0 + (int) (radius * Math.cos(alpha * i));
            y[i] = y0 + (int) (radius * Math.sin(alpha * i));
            Dot d = new Dot(x[i], y[i]);
            frame.getGame().getBoard().addDot(d);
        }

    }

    private void drawLines() {
        graphics.setColor(Color.cyan);


        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {

                int dx = x[i] - x[j];
                int dy = y[i] - y[j];
                double distance = Math.sqrt(dx * dx + dy * dy);


                if (Math.random() < edgeProbability) {
                    graphics.drawLine(x[i], y[i], x[j], y[j]);
                    Line l = new Line(frame.getGame().getBoard().getDots().get(i), frame.getGame().getBoard().getDots().get(j));
                    frame.getGame().getBoard().addLine(l);

                    frame.getGame().getBoard().getDots().get(i).addAdjDot(frame.getGame().getBoard().getDots().get(j));
                    frame.getGame().getBoard().getDots().get(j).addAdjDot(frame.getGame().getBoard().getDots().get(i));
                }
            }
        }
    }


    private void drawVertices() {

        int radius = 5;
        graphics.setColor(Color.white);

        for (int i = 0; i < numVertices; i++) {

            graphics.fillOval(x[i] - radius, y[i] - radius, 2 * radius, 2 * radius);
        }


    }


    @Override
    public void update(Graphics g) {

    }


    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, this);
    }

    public int getNumVertices() {
        return numVertices;
    }

    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }

    public double getEdgeProbability() {
        return edgeProbability;
    }

    public void setEdgeProbability(double edgeProbability) {
        this.edgeProbability = edgeProbability;
    }

    public void remake(List<Dot> dots){
        graphics.setColor(Color.black);
        graphics.clearRect(0, 0, getWidth(), getHeight());
        repaint();
        for(int i=0;i<numVertices;i++)
        {
            x[i]=dots.get(i).getX();
            y[i]=dots.get(i).getY();
        }

        drawVertices();
        repaint();
        for(Dot dot:dots){
            for(Dot d: dot.getAdjDots().keySet()) {
                graphics.setColor(dot.getAdjDots().get(d));
                graphics.drawLine(dot.getX(), dot.getY(), d.getX(), d.getY());
                repaint();
            }
        }
    }

    public void reset() {
        for(Dot d:frame.getGame().getBoard().getDots())
            for(Dot dot: d.getAdjDots().keySet())
            {
                graphics.setColor(Color.cyan);
                graphics.drawLine(dot.getX(), dot.getY(), d.getX(), d.getY());
                repaint();
                dot.getAdjDots().put(d,Color.cyan);
                d.getAdjDots().put(dot,Color.cyan);
                dot.full=0;
                dot.setSelected(false);
            }

    }
}

