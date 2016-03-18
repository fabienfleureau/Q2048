package fr.fabienfleureau.games.q2048.core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


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
}