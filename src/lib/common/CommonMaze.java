package ija.ija2022.homework2.common.lib.common;

import ija.ija2022.homework2.common.game.PacmanObject;

import java.util.List;

/**
 * The interface Common maze.
 */
public interface CommonMaze {
    /**
     * Gets field.
     *
     * @param var1 the var 1
     * @param var2 the var 2
     * @return the field
     */
    CommonField getField(int var1, int var2);

    /**
     * Num rows int.
     *
     * @return the int
     */
    int numRows();

    /**
     * Num cols int.
     *
     * @return the int
     */
    int numCols();

    /**
     * Ghosts list.
     *
     * @return the list
     */
    List<CommonMazeObject> ghosts();

    /**
     * Gets pacman.
     *
     * @return the pacman
     */
    public PacmanObject getPacman();

    /**
     * No key boolean.
     *
     * @return the boolean
     */
    default boolean noKey() {
        return true;
    }

    /**
     * Dec counter.
     */
    public void decCounter();

    /**
     * Gets key counter.
     *
     * @return the key counter
     */
    public int getKeyCounter();

    /**
     * To text string.
     *
     * @return the string
     */
    public String toText();

    /**
     * Sets input.
     *
     * @param input the input
     */
    public void setInput(String input);

    /**
     * Gets input.
     *
     * @return the input
     */
    public String getInput();
}
