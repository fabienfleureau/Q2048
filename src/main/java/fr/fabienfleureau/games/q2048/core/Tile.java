package fr.fabienfleureau.games.q2048.core;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Tile {

    private int value;
    private int row;
    private int column;

    private Map<Direction, Tile> neighborTiles;

    public Tile(int row, int column) {
        this.row = row;
        this.column = column;
        neighborTiles = new HashMap<>();
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

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

    public Optional<Tile> getNeighbor(Direction direction) {
        return Optional.ofNullable(neighborTiles.get(direction));
    }

    public void setNeighbor(Direction direction, Tile tile) {
        neighborTiles.put(direction, tile);
    }

    @Override
    public String toString() {
        return isEmpty() ? "" : Integer.toString(value);
    }
}
