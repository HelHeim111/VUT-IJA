package ija.ija2022.homework2.common.game;

import ija.ija2022.homework2.common.common.Field;
import ija.ija2022.homework2.common.lib.common.CommonField;
import ija.ija2022.homework2.common.lib.common.CommonMazeObject;

import java.util.Random;

/**
 * The type Ghost object.
 */
public class GhostObject implements CommonMazeObject {
    private CommonField field;
    private GhostObject(){}

    /**
     * Instantiates a new Ghost object.
     *
     * @param field the field
     */
    public GhostObject(CommonField field) {
        GhostObject ghost = new GhostObject();

        this.field = field;
        ghost.field = field;
    }

    @Override
    public boolean canMove(CommonField.Direction dir) {
        return this.field.nextField(dir).canMove();
    }

    @Override
    public boolean move(CommonField.Direction dir) {
        if (canMove(dir)) {
            if (this.field.nextField(dir).get() != null) {
                if (this.field.nextField(dir).get().isPacman()) {
                    ((PacmanObject) this.field.nextField(dir).get()).kill();
                }
            }
            ((Field) this.field.nextField(dir)).put(this.field.get());
            ((Field) this.field).remove(this.field.get());
            this.field = this.field.nextField(dir);
            return true;
        }
        return false;
    }

    @Override
    public CommonField getField() {
        return this.field;
    }

    public boolean randomMove() {
        CommonField.Direction[] directions = CommonField.Direction.values();
        Random random = new Random();
        CommonField.Direction randomDirection = directions[random.nextInt(directions.length)];
        while (!this.canMove(randomDirection)) {
            randomDirection = directions[random.nextInt(directions.length)];
        }
        return this.move(randomDirection);
    }
    
}
