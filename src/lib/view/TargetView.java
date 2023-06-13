package ija.ija2022.homework2.common.lib.view;

import ija.ija2022.homework2.common.lib.common.CommonMazeObject;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * The type Target view.
 */
public class TargetView implements ComponentView {
    private final CommonMazeObject model;
    private final FieldView parent;

    /**
     * Instantiates a new Target view.
     *
     * @param parent the parent
     * @param m      the m
     */
    public TargetView(FieldView parent, CommonMazeObject m) {
        this.model = m;
        this.parent = parent;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        Rectangle bounds = this.parent.getBounds();
        double w = bounds.getWidth();
        double h = bounds.getHeight();
        Math.max(h, w);
        double diameter = Math.min(h, w) - 10.0;
        double x = (w - diameter) / 2.0;
        double y = (h - diameter) / 2.0;
        Ellipse2D.Double ellipse = new Ellipse2D.Double(x, y, diameter, diameter);
        g2.setColor(Color.green);
        g2.fill(ellipse);
    }
}
