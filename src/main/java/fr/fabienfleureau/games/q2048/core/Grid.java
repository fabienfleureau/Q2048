package fr.fabienfleureau.games.q2048.core;


import com.google.common.collect.Table;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Grid {

    private final Table<Integer, Integer, Tile> grid;
    private final Random rand;

    public Grid(Table<Integer, Integer, Tile> grid) {
        this.grid = grid;
        this.rand = new Random();

    }


    public void setTile(int x, int y, int value) {

    }

    public Tile getTile(int x, int y) {
        return grid.get(x, y);
    }

    public Collection<Tile> getTiles() {
        return grid.values();
    }

    public List<Tile> getEmptyTiles() {
        return grid.values().stream()
                .filter(Tile::isEmpty)
                .collect(Collectors.toList());
    }

    public void addTileRandom() {
        List<Tile> emptyTiles = getEmptyTiles();
        emptyTiles.get(rand.nextInt(emptyTiles.size())).init();
    }

    @Override
    public String toString() {
        return StringUtils.repeat('-', 33) + "\n" +
                grid.rowMap().values().stream()
                .map(row -> "|" + row.values().stream()
                        .map(tile -> StringUtils.center(Integer.toString(tile.getValue()), 7))
                        .collect(Collectors.joining("|")) + "|")
                .collect(Collectors.joining("\n" + StringUtils.repeat('-', 33) + "\n")) +
                "\n" + StringUtils.repeat('-', 33) + "\n";
    }
}
