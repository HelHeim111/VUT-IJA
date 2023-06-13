package ija.ija2022.homework2.common.common;

import ija.ija2022.homework2.common.lib.common.CommonField;
import ija.ija2022.homework2.common.lib.common.CommonMaze;
import ija.ija2022.homework2.common.lib.common.CommonMazeObject;

/**
 * This is Field class.
 */
public interface Field extends CommonField {
    /**
     * Sets maze.
     *
     * @param maze the maze
     */
    void setMaze(CommonMaze maze);

    /**
     * Put boolean.
     *
     * @param object the object
     * @return the boolean
     */
    boolean put(CommonMazeObject object);

    /**
     * Remove boolean.
     *
     * @param object the object
     * @return the boolean
     */
    boolean remove(CommonMazeObject object);
    public boolean isKey();
    public boolean isTarget();

    /**
     * Sets key.
     *
     * @param val the val
     */
    public void setKey(boolean val);

    /**
     * Sets target.
     *
     * @param val the val
     */
    public void setTarget(boolean val);
}