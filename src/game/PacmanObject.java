package ija.ija2022.homework2.common.game;

import ija.ija2022.homework2.common.common.Field;
import ija.ija2022.homework2.common.lib.common.CommonField;
import ija.ija2022.homework2.common.lib.common.CommonMazeObject;

/**
 * The type Pacman object.
 */
public class PacmanObject implements CommonMazeObject {
    private CommonField field;
    private boolean isDead;
    private int steps;
    private boolean victory;
    private PacmanObject(){}

    /**
     * Instantiates a new Pacman object.
     *
     * @param field the field
     */
    public PacmanObject(CommonField field) {
        PacmanObject pacman = new PacmanObject();

        this.field = field;
        this.isDead = false;
        this.victory = false;
        this.steps = 0;
        pacman.steps = 0;
        pacman.field = field;
        pacman.isDead = false;
        pacman.victory = true;
    }

    @Override
    public boolean isPacman() {
        return true;
    }

    @Override
    public boolean canMove(CommonField.Direction dir) {
        return this.field.nextField(dir).canMove();
    }

    @Override
    public boolean move(CommonField.Direction dir) {
        this.steps += 1;
        if (canMove(dir)) {
            if (this.field.nextField(dir).get() != null) {
                if (!this.field.nextField(dir).get().isPacman()) {
                    this.isDead = true;
                }
            }
            ((Field) this.field.nextField(dir)).put(this.field.get());
            ((Field) this.field).remove(this.field.get());
            this.field = this.field.nextField(dir);
            if (((Field) this.field).isKey()) {
                ((PathField)this.field).maze.decCounter();
                ((Field) this.field).setKey(false);
            }
            if (((Field) this.field).isTarget()) {
                if(((PathField)this.field).maze.noKey()) {
                    this.victory = true;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public CommonField getField() {
        return this.field;
    }

    @Override
    public boolean isDead() {
        return this.isDead;
    }

    /**
     * Kill.
     */
    public void kill() {
        this.isDead = true;
    }

    @Override
    public boolean randomMove() {
        return false;
    }

    /**
     * Is victory boolean.
     *
     * @return the boolean
     */
    public boolean isVictory() {
        return this.victory;
    }

    /**
     * Gets steps.
     *
     * @return the steps
     */
    public int getSteps() {
        return this.steps;
    }
}
