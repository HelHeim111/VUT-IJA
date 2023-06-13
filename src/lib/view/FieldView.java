package ija.ija2022.homework2.common.lib.view;

import ija.ija2022.homework2.common.lib.common.CommonField;
import ija.ija2022.homework2.common.lib.common.CommonMazeObject;
import ija.ija2022.homework2.common.lib.common.Observable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Field view.
 */
public class FieldView extends JPanel implements Observable.Observer {
    private final CommonField model;
    private final List<ComponentView> objects;
    private int changedModel = 0;

    /**
     * Instantiates a new Field view.
     *
     * @param model the model
     */
    public FieldView(CommonField model) {
        this.model = model;
        this.objects = new ArrayList();
        this.privUpdate();
        model.addObserver(this);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.objects.forEach((v) -> {
            v.paintComponent(g);
        });
    }

    private void privUpdate() {
        if (this.model.canMove()) {
            this.setBackground(Color.white);
            ComponentView v;
            if (!this.model.isEmpty()) {
                CommonMazeObject o = this.model.get();
                v = o.isPacman() ? new PacmanView(this, this.model.get()) : new GhostView(this, this.model.get());
                this.objects.add(v);
            } else if (this.model.isKey()) {
                v = new KeyView(this, this.model.get());
                this.objects.add(v);
            } else if (this.model.isTarget()) {
                v = new TargetView(this, this.model.get());
                this.objects.add(v);
            } else {
                this.objects.clear();
            }
        } else {
            this.setBackground(Color.lightGray);
        }

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public final void update(Observable field) {
        ++this.changedModel;
        this.privUpdate();
    }

    /**
     * Number updates int.
     *
     * @return the int
     */
    public int numberUpdates() {
        return this.changedModel;
    }

    /**
     * Clear changed.
     */
    public void clearChanged() {
        this.changedModel = 0;
    }

    /**
     * Gets field.
     *
     * @return the field
     */
    public CommonField getField() {
        return this.model;
    }
}
