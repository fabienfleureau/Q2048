package fr.fabienfleureau.games.q2048.core;

public enum Direction {
    LEFT, RIGHT, UP, DOWN;

    public Direction opposite() {
        switch (this) {
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            case UP:
                return DOWN;
            case DOWN:
                return UP;
        }
        return null;
    }
}
