package fr.fabienfleureau.games.q2048.core;

import fr.fabienfleureau.games.q2048.core.player.KeyboardPlayer;
import fr.fabienfleureau.games.q2048.core.player.Player;

/**
 * Created by Fabien on 20/03/2016.
 */
public class GameManager {

    private Grid grid;
    private Player player;
    private boolean printEnabled;

    public GameManager(Grid grid, Player player, boolean printEnabled) {
        this.grid = grid;
        this.player = player;
        this.printEnabled = printEnabled;
    }

    public void play() {
        while (!isFinish()) {
            print();
            grid.moveTiles(player.getMove());
            grid.addTileRandom();
        }
    }

    private void print() {
        if (printEnabled) {
            System.out.println(grid.toString());
        }
    }

    private boolean isFinish() {
        return false;
    }


    public static void main(String[] args) {
        Grid defaultGrid = GridBuilder.defaultBuild();
        Player humanPlayer = new KeyboardPlayer();
        new GameManager(defaultGrid, humanPlayer, true).play();
    }

}
