package ija.ija2022.homework2.common.lib.common;

/**
 * The interface Common field.
 */
public interface CommonField extends Observable {
    /**
     * Next field common field.
     *
     * @param var1 the var 1
     * @return the common field
     */
    CommonField nextField(Direction var1);

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */
    boolean isEmpty();

    /**
     * Get common maze object.
     *
     * @return the common maze object
     */
    CommonMazeObject get();

    /**
     * Can move boolean.
     *
     * @return the boolean
     */
    boolean canMove();

    /**
     * Contains boolean.
     *
     * @param var1 the var 1
     * @return the boolean
     */
    boolean contains(CommonMazeObject var1);

    /**
     * Is key boolean.
     *
     * @return the boolean
     */
    boolean isKey();

    /**
     * Is target boolean.
     *
     * @return the boolean
     */
    boolean isTarget();

    /**
     * The enum Direction.
     */
    public static enum Direction {
        /**
         * L direction.
         */
        L(0, -1),
        /**
         * U direction.
         */
        U(-1, 0),
        /**
         * R direction.
         */
        R(0, 1),
        /**
         * D direction.
         */
        D(1, 0);

        private final int r;
        private final int c;

        private Direction(int dr, int dc) {
            this.r = dr;
            this.c = dc;
        }

        /**
         * Delta row int.
         *
         * @return the int
         */
        public int deltaRow() {
            return this.r;
        }

        /**
         * Delta col int.
         *
         * @return the int
         */
        public int deltaCol() {
            return this.c;
        }
    }

    /**
     * Is wall boolean.
     *
     * @return the boolean
     */
    default boolean isWall() {
        return false;
    }
}
