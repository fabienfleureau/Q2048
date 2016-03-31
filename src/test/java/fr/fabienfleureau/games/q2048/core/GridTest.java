package fr.fabienfleureau.games.q2048.core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class GridTest {

    @Test
    public void moveRightOneTile() throws Exception {
        Grid grid = new GridBuilder().initGrid().build();
        grid.setTile(0, 0, 2);
        System.out.println(grid);
        grid.moveTiles(Direction.RIGHT);
        System.out.println(grid);
        assertEquals(2, grid.getTile(0, 3).getValue());
    }

    @Test
    public void moveRightTwoTiles() throws Exception {
        Grid grid = new GridBuilder().initGrid().build();
        grid.setTile(0, 0, 2);
        grid.setTile(0, 2, 2);
        System.out.println(grid);
        grid.moveTiles(Direction.RIGHT);
        System.out.println(grid);
        assertEquals(4, grid.getTile(0, 3).getValue());
    }

    @Test
    public void moveRightThreeTiles() throws Exception {
        Grid grid = new GridBuilder().initGrid().build();
        grid.setTile(0, 0, 4);
        grid.setTile(0, 1, 4);
        grid.setTile(0, 3, 4);
        System.out.println(grid);
        grid.moveTiles(Direction.RIGHT);
        System.out.println(grid);
        assertEquals(8, grid.getTile(0, 3).getValue());
        assertEquals(4, grid.getTile(0, 2).getValue());
    }

    @Test
    public void moveRightFourTiles() throws Exception {
        Grid grid = new GridBuilder().initGrid().build();
        grid.setTile(0, 0, 4);
        grid.setTile(0, 1, 4);
        grid.setTile(0, 2, 4);
        grid.setTile(0, 3, 4);
        System.out.println(grid);
        grid.moveTiles(Direction.RIGHT);
        System.out.println(grid);
        assertEquals(8, grid.getTile(0, 3).getValue());
        assertEquals(8, grid.getTile(0, 2).getValue());
    }

    @Test
         public void moveLeftThreeTiles() throws Exception {
        Grid grid = new GridBuilder().initGrid().build();
        grid.setTile(0, 0, 4);
        grid.setTile(0, 2, 4);
        grid.setTile(0, 3, 4);
        System.out.println(grid);
        grid.moveTiles(Direction.LEFT);
        System.out.println(grid);
        assertEquals(8, grid.getTile(0, 0).getValue());
        assertEquals(4, grid.getTile(0, 1).getValue());
    }

    @Test
    public void moveUpThreeTiles() throws Exception {
        Grid grid = new GridBuilder().initGrid().build();
        grid.setTile(0, 0, 4);
        grid.setTile(2, 0, 4);
        grid.setTile(3, 0, 4);
        System.out.println(grid);
        grid.moveTiles(Direction.UP);
        System.out.println(grid);
        assertEquals(8, grid.getTile(0, 0).getValue());
        assertEquals(4, grid.getTile(1, 0).getValue());
    }

    @Test
    public void moveDownThreeTiles() throws Exception {
        Grid grid = new GridBuilder().initGrid().build();
        grid.setTile(0, 0, 4);
        grid.setTile(1, 0, 4);
        grid.setTile(3, 0, 4);
        System.out.println(grid);
        grid.moveTiles(Direction.DOWN);
        System.out.println(grid);
        assertEquals(8, grid.getTile(3, 0).getValue());
        assertEquals(4, grid.getTile(2, 0).getValue());
    }

}