package main.AvatarParts;

import main.Atomics.MyLocatableIntf;

public interface Weapon extends MyLocatableIntf {//outlines the methods for simple 3 part 
												//tools for the avatars that rotate from the endpoint of the tool
	public void rotate(double angle);
	public Line getBlade();
	public Line getLeftCross();
	public Line getRightCross();
}
