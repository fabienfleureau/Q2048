package fr.fabienfleureau.games.q2048.core;

/**
 * Created by Fabien on 20/03/2016.
 */
public class GameManager {

    private Grid grid;

    public GameManager(Grid grid) {
        this.grid = grid;
    }

    public void play(Direction direction) {
        grid.moveTiles(direction);
        grid.addTileRandom();
    }
}
