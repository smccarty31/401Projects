package main.AvatarParts;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import main.Atomics.MyLocatable;
import main.AvatarParts.Line;
@StructurePattern("Bean Pattern")
@EditablePropertyNames({"x","y"})
@PropertyNames({"x","y","blade","leftcross","rightcross"})


public class Sword extends MyLocatable implements Weapon  {
	
	private Line blade,leftcross,rightcross;//3 part objects, one shaft, two guards for the handle 
	
	public Sword(int initX, int initY, int length){
		super(initX,initY);
		blade = new RotatingLine(initX,initY,-length,0);
		leftcross = new RotatingLine(initX,initY-length/4, 0, -length/4);//crosses are set at a quarter
		rightcross = new RotatingLine(initX,initY-length/4, 0, length/4);//of the size of the shaft
	}
	public void setX(int newX) {
		super.setX(newX);//changing the x position of the origin of
		blade.setX(newX);//the sword requires that all threed parts
		leftcross.setX(newX+blade.getWidth()/4);//be readjusted
		rightcross.setX(newX+blade.getWidth()/4);
	}
	public void setY(int newY) {
		super.setY(newY);
		blade.setY(newY);//changing the y position of the sword requires all
		leftcross.setY(newY+blade.getHeight()/4);//three parts of it be 
		rightcross.setY(newY+blade.getHeight()/4);//readjusted
	}
	public Line getBlade(){
		return blade;
	}
	public Line getLeftCross(){
		return leftcross;
	}
	public Line getRightCross(){
		return rightcross;
	}
	public void rotate(double angle){
		blade.rotate(angle);//each part of the sword is rotated separately 
		leftcross.rotate(angle);
		rightcross.rotate(angle);
		leftcross.setX(blade.getX()+blade.getWidth()/4);//then the cross lines 
		rightcross.setX(blade.getX()+blade.getWidth()/4);//are repositioned to where
		leftcross.setY(blade.getY()+blade.getHeight()/4);//they belong on the sword
		rightcross.setY(blade.getY()+blade.getHeight()/4);
	}

}
