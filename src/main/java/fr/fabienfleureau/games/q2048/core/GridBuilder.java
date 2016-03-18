package fr.fabienfleureau.games.q2048.core;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.Table;

import java.util.Arrays;
import java.util.stream.IntStream;


public class GridBuilder {

    private final Table<Integer, Integer, Tile> grid;
    private int startTiles = 0;

    public GridBuilder() {
        this.grid = ArrayTable.create(Arrays.asList(0, 1, 2, 3), Arrays.asList(0, 1, 2, 3));
    }

    public GridBuilder initGrid() {
        grid.rowKeySet()
                .forEach(
                        row -> grid.columnKeySet()
                                .forEach(
                                        column -> grid.put(row, column, new Tile())));
        return this;
    }

    public GridBuilder setStartTilesNumber(int tilesNumber) {
        startTiles = tilesNumber;
        return this;
    }

    public Grid build() {
        Grid grid = new Grid(this.grid);
        IntStream.range(0, startTiles)
                .forEach(
                        i -> grid.addTileRandom()
                );
        return grid;
    }

    public Grid printAndBuild() {
        Grid grid = build();
        System.out.println(grid);
        return grid;
    }
}
