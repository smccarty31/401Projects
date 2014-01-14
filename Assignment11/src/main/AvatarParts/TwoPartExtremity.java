package main.AvatarParts;

import main.Atomics.MyEditableBoundedShapeIntf;


public interface TwoPartExtremity extends MyEditableBoundedShapeIntf {//outlines the methods for angle type shapes such as the arms and legs
	
	public Line getLeftLine();
	public Line getRightLine();
	public void rotate(double angle);
	
}
