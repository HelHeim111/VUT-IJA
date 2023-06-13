package ija.ija2022.homework2.common.common;

/**
 * The interface Maze.
 */
public interface Maze {
    /**
     * Gets field.
     *
     * @param row the row
     * @param col the col
     * @return the field
     */
    Field getField(int row, int col);

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
}
