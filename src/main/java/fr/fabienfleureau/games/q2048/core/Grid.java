package fr.fabienfleureau.games.q2048.core;


import com.google.common.collect.Table;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Grid {

    private final Table<Integer, Integer, Tile> grid;
    private final Random rand;

    public Grid(Table<Integer, Integer, Tile> grid) {
        this.grid = grid;
        this.rand = new Random();

    }


    public void setTile(int x, int y, int value) {
        grid.get(x, y).setValue(value);
    }

    public Tile getTile(int x, int y) {
        return grid.get(x, y);
    }

    public Collection<Tile> getTiles() {
        return grid.values();
    }

    public List<Tile> getEmptyTiles() {
        return grid.values().parallelStream()
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
                        .map(tile -> StringUtils.center(tile.toString(), 7))
                        .collect(Collectors.joining("|")) + "|")
                .collect(Collectors.joining("\n" + StringUtils.repeat('-', 33) + "\n")) +
                "\n" + StringUtils.repeat('-', 33) + "\n";
    }

    public void moveTiles(Direction direction) {
        Collection<List<Tile>> tilesList;
        switch (direction) {
            case LEFT:
                tilesList = grid.rowMap().values().stream()
                        .map(map -> new ArrayList<>(map.values()))
                        .collect(Collectors.toList());
                break;
            case RIGHT:
                tilesList = grid.rowMap().values().stream()
                        .map(map -> {
                            List<Tile> tiles = new ArrayList<>(map.values());
                            Collections.reverse(tiles);
                            return tiles;
                        })
                        .collect(Collectors.toList());

                break;
            case UP:
                tilesList = grid.columnMap().values().stream()
                        .map(map -> new ArrayList<>(map.values()))
                        .collect(Collectors.toList());
                break;
            case DOWN:
                tilesList = grid.columnMap().values().stream()
                        .map(map -> {
                            List<Tile> tiles = new ArrayList<>(map.values());
                            Collections.reverse(tiles);
                            return tiles;
                        })
                        .collect(Collectors.toList());

                break;
            default:
                tilesList = new ArrayList<>();
                break;
        }

        move(tilesList, direction.opposite());

    }

    private void move(Collection<List<Tile>> tilesList, Direction direction) {
        tilesList.parallelStream().forEach(tiles -> move(tiles, direction));
    }


    private void move(List<Tile> tiles, Direction direction) {
        shift(tiles, direction);
        merge(tiles, direction);
        shift(tiles, direction);
    }

    private void merge(List<Tile> row, Direction direction) {
        row.stream().forEach(tile -> tile.getNeighbor(direction)
                    .filter(leftTile -> tile.getValue() == leftTile.getValue())
                    .ifPresent(leftTile -> {
                        tile.setValue(2 * tile.getValue());
                        leftTile.setValue(0);
                    }));
    }

    private void shift(List<Tile> row, Direction direction) {
        row.stream()
                .filter(Tile::isEmpty)
                .forEach(tile -> getNotEmptyTile(tile, direction).ifPresent(leftTile -> {
                    tile.setValue(leftTile.getValue());
                    leftTile.setValue(0);
                }));
    }

    private Optional<Tile> getNotEmptyTile(Tile start, Direction direction) {
        return start.getNeighbor(direction)
                .flatMap(neighbor -> !neighbor.isEmpty() ? Optional.of(neighbor) : getNotEmptyTile(neighbor, direction));
    }


    public boolean hasAvailableMoves() {
        return !availableMoves().isEmpty();
    }

    public Collection<Direction> availableMoves() {
        if (getTiles().parallelStream().anyMatch(Tile::isEmpty)) {
            return Arrays.asList(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT);
        }
        boolean leftRight = grid.rowMap().values().parallelStream()
                .anyMatch(tiles -> tiles.values().parallelStream()
                        .anyMatch(tile -> tile.getNeighbor(Direction.RIGHT).isPresent()
                                && tile.getValue() == tile.getNeighbor(Direction.RIGHT).get().getValue()));

        boolean upDown = grid.columnMap().values().parallelStream()
                .anyMatch(tiles -> tiles.values().parallelStream()
                        .anyMatch(tile -> tile.getNeighbor(Direction.UP).isPresent()
                                && tile.getValue() == tile.getNeighbor(Direction.UP).get().getValue()));
        if (leftRight && upDown) {
            return Arrays.asList(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT);
        } else if (leftRight) {
            return Arrays.asList(Direction.LEFT, Direction.RIGHT);
        } else if (upDown) {
            return Arrays.asList(Direction.UP, Direction.DOWN);
        }
        return Collections.emptyList();
    }


}
