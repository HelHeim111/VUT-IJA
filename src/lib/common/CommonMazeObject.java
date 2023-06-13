package ija.ija2022.homework2.common.lib.common;

/**
 * The interface Common maze object.
 */
public interface CommonMazeObject {
    /**
     * Can move boolean.
     *
     * @param var1 the var 1
     * @return the boolean
     */
    boolean canMove(CommonField.Direction var1);

    /**
     * Move boolean.
     *
     * @param var1 the var 1
     * @return the boolean
     */
    boolean move(CommonField.Direction var1);

    /**
     * Is pacman boolean.
     *
     * @return the boolean
     */
    default boolean isPacman() {
        return false;
    }

    /**
     * Gets field.
     *
     * @return the field
     */
    CommonField getField();

    /**
     * Is dead boolean.
     *
     * @return the boolean
     */
    default boolean isDead() {
        return false;
    }

    /**
     * Random move boolean.
     *
     * @return the boolean
     */
    public boolean randomMove();
}
