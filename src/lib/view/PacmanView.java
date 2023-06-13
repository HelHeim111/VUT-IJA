package ija.ija2022.homework2.common.lib.view;

import ija.ija2022.homework2.common.lib.common.CommonMazeObject;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * The type Pacman view.
 */
public class PacmanView implements ComponentView {
    private CommonMazeObject model;
    private FieldView parent;

    /**
     * Instantiates a new Pacman view.
     *
     * @param parent the parent
     * @param m      the m
     */
    public PacmanView(FieldView parent, CommonMazeObject m) {
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
        g2.setColor(Color.yellow);
        g2.fill(ellipse);
        g2.setColor(Color.black);
        g2.setFont(new Font("Serif", 1, 20));

    }
}
