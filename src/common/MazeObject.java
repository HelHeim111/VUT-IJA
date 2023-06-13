package ija.ija2022.homework2.common.common;

/**
 * The interface Maze object.
 */
public interface MazeObject {
    /**
     * Can move boolean.
     *
     * @param dir the dir
     * @return the boolean
     */
    boolean canMove(Field.Direction dir);

    /**
     * Move boolean.
     *
     * @param dir the dir
     * @return the boolean
     */
    boolean move(Field.Direction dir);
}
