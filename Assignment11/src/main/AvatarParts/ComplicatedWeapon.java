package main.AvatarParts;

import java.awt.Color;

import main.Atomics.MyEditableBoundedShapeIntf;

public interface ComplicatedWeapon extends MyEditableBoundedShapeIntf {//Outlines methods for creating avatar tools that rotate
																		//around a point other than their ends, unfortunately does not work
																		//with OE yet
	public void rotate(double angle);
	public Line getRotationAxle();//used to determine the radius of the graphics' rotation
	public boolean getFilled();
	public void setColor(Color color);
	public Color getColor();
}
