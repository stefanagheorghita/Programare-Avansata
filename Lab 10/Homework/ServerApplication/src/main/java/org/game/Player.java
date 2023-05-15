package org.game;

public class Player {
    private String name;
    private char symbol;
    static int x=1;

    private String id;


    public Player(String name) {
        this.name = name;
        id=String.valueOf("player"+x);
        x++;
    }

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;

    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
