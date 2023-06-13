package ija.ija2022.homework2.common.game;

import ija.ija2022.homework2.common.common.Field;
import ija.ija2022.homework2.common.lib.common.CommonField;
import ija.ija2022.homework2.common.lib.common.CommonMaze;
import ija.ija2022.homework2.common.lib.common.CommonMazeObject;
import ija.ija2022.homework2.common.lib.common.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Path field.
 */
public class PathField implements Field {
    private int row, col;
    private CommonMazeObject obj = null, ghost = null;
    /**
     * The Maze.
     */
    public CommonMaze maze;
    private boolean target = false, key = false;
    private final List<Observable.Observer> observers = new ArrayList<>();

    /**
     * Instantiates a new Path field.
     *
     * @param row the row
     * @param col the col
     */
    public PathField(int row, int col) {
        this.row = row;
        this.col = col;
    }
    @Override
    public void setMaze(CommonMaze maze) {
        this.maze = maze;
    }

    @Override
    public CommonField nextField(Direction dirs) {
        switch (dirs) {
            case D:
                return this.maze.getField(row+1, col);
            case L:
                return this.maze.getField(row, col-1);
            case R:
                return this.maze.getField(row, col+1);
            case U:
                return this.maze.getField(row-1, col);
            default:
                return null;
        }
    }

    @Override
    public boolean put(CommonMazeObject object) {
        if (this.obj == null) {
            this.obj = object;
        } else {
            this.ghost = object;
        }
        notifyObservers();
        return true;
    }

    @Override
    public boolean remove(CommonMazeObject object) {
        if (this.obj != null) {
            if (this.ghost == null) {
                this.obj = null;
            } else {
                this.obj = this.ghost;
                this.ghost = null;
            }
            notifyObservers();
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        if (this.obj == null) {
            return true;
        }
        return false;
    }

    @Override
    public CommonMazeObject get() {
        return obj;
    }

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public boolean contains(CommonMazeObject commonMazeObject) {
        if (!this.isEmpty()) {
            return this.obj.equals(commonMazeObject);
        }
        return false;
    }

    @Override
    public void addObserver(Observable.Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observable.Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        this.observers.forEach((o) ->
                o.update(this));
    }
    public boolean isKey() {
        return key;
    }
    public boolean isTarget() {
        return target;
    }
    @Override
    public void setKey(boolean val){
        this.key = val;
    }
    @Override
    public void setTarget(boolean val){
        this.target = val;
    }
}
