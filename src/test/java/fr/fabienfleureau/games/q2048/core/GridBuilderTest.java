package fr.fabienfleureau.games.q2048.core;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;


public class GridBuilderTest {

    @Test
    public void builtGridShouldNotHaveNullTile() throws Exception {
        Grid grid = new GridBuilder().initGrid().build();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertNotNull(grid.getTile(i, j));
            }
        }
    }

    @Test
    public void countTiles() throws Exception {
        Grid grid = new GridBuilder().initGrid().printAndBuild();
        assertEquals(16, grid.getTiles().stream().count());
        assertEquals(16, grid.getEmptyTiles().stream().count());

        grid = new GridBuilder().initGrid().setStartTilesNumber(1).printAndBuild();
        assertEquals(16, grid.getTiles().stream().count());
        assertEquals(15, grid.getEmptyTiles().stream().count());

        grid = new GridBuilder().initGrid().setStartTilesNumber(16).printAndBuild();
        assertEquals(16, grid.getTiles().stream().count());
        assertEquals(0, grid.getEmptyTiles().stream().count());
    }

    @Test
    public void neighborsTraversal() {
        Grid grid = new GridBuilder().initGrid().printAndBuild();
        Tile tile = grid.getTile(0, 0);
        Optional<Tile> neighbor = tile.getNeighbor(Direction.RIGHT);
        assertTrue(neighbor.isPresent());
        neighbor = neighbor.get().getNeighbor(Direction.RIGHT);
        assertTrue(neighbor.isPresent());
        neighbor = neighbor.get().getNeighbor(Direction.RIGHT);
        assertTrue(neighbor.isPresent());
        assertFalse(neighbor.get().getNeighbor(Direction.RIGHT).isPresent());

        neighbor = neighbor.get().getNeighbor(Direction.DOWN);
        assertTrue(neighbor.isPresent());
        neighbor = neighbor.get().getNeighbor(Direction.DOWN);
        assertTrue(neighbor.isPresent());
        neighbor = neighbor.get().getNeighbor(Direction.DOWN);
        assertTrue(neighbor.isPresent());
        assertFalse(neighbor.get().getNeighbor(Direction.DOWN).isPresent());


        neighbor = neighbor.get().getNeighbor(Direction.LEFT);
        assertTrue(neighbor.isPresent());
        neighbor = neighbor.get().getNeighbor(Direction.LEFT);
        assertTrue(neighbor.isPresent());
        neighbor = neighbor.get().getNeighbor(Direction.LEFT);
        assertTrue(neighbor.isPresent());
        assertFalse(neighbor.get().getNeighbor(Direction.LEFT).isPresent());

        neighbor = neighbor.get().getNeighbor(Direction.UP);
        assertTrue(neighbor.isPresent());
        neighbor = neighbor.get().getNeighbor(Direction.UP);
        assertTrue(neighbor.isPresent());
        neighbor = neighbor.get().getNeighbor(Direction.UP);
        assertTrue(neighbor.isPresent());
        assertFalse(neighbor.get().getNeighbor(Direction.UP).isPresent());

        assertEquals(tile, neighbor.get());


    }
}