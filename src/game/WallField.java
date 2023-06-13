package ija.ija2022.homework2.common.game;

import ija.ija2022.homework2.common.common.Field;
import ija.ija2022.homework2.common.lib.common.CommonMaze;
import ija.ija2022.homework2.common.lib.common.CommonMazeObject;

/**
 * The type Wall field.
 */
public class WallField implements Field {
    private int row, col;

    /**
     * The Maze.
     */
    public CommonMaze maze;

    /**
     * Instantiates a new Wall field.
     *
     * @param row the row
     * @param col the col
     */
    public WallField(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public void setMaze(CommonMaze maze) {
        this.maze = maze;
    }

    @Override
    public Field nextField(Direction dirs) {
        throw new UnsupportedOperationException();
    }

    public boolean put(CommonMazeObject object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(CommonMazeObject object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public CommonMazeObject get() {
        return null;
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public boolean contains(CommonMazeObject commonMazeObject) {
        return false;
    }

    @Override
    public void addObserver(Observer observer) {

    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObservers() {

    }
    @Override
    public boolean isKey() {
        return false;
    }

    @Override
    public boolean isTarget() {
        return false;
    }

    @Override
    public void setKey(boolean val) {

    }

    @Override
    public void setTarget(boolean val) {

    }

    @Override
    public boolean isWall() {
        return true;
    }
}
