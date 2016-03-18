package fr.fabienfleureau.games.q2048.core;


public class Tile {


    private int value;


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return getValue() == 0;
    }


    public void init() {
        setValue(Math.random() < 0.9 ? 2 : 4);
    }

    @Override
    public String toString() {
        return isEmpty() ? "-" : Integer.toString(value);
    }
}
