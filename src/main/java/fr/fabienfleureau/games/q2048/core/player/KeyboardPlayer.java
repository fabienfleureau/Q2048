package fr.fabienfleureau.games.q2048.core.player;

import fr.fabienfleureau.games.q2048.core.Direction;

import java.io.IOException;

/**
 * Created by Fabien on 31/03/2016.
 */
public class KeyboardPlayer implements Player {

    @Override
    public Direction getMove() {
        Direction direction = null;
        try {
            while (direction == null) {
                int input = System.in.read();
                if (input == 'z') {
                    direction = Direction.UP;
                } else if (input == 'q') {
                    direction = Direction.LEFT;
                } else if (input == 's') {
                    direction = Direction.DOWN;
                } else if (input == 'd') {
                    direction = Direction.RIGHT;
                }
            }
            return direction;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Direction.UP;
    }
}
