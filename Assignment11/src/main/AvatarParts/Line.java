package main.AvatarParts;

import java.awt.Color;
import java.awt.Point;
import java.awt.Stroke;

import main.Atomics.MyEditableBoundedShapeIntf;


public interface Line extends MyEditableBoundedShapeIntf {//outlines appropriate properties for a Line object, essential to many parts of the avatar
    public Point getLocation();
    public Color getColor();
    public void setColor(Color color);
    public Stroke getStroke();
    public void rotate(double angle);
    public void move(int NewX, int NewY);
    public void moveUpperLeft(int NewX, int NewY);
}
