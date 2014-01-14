package main.AvatarParts;
import java.awt.Color;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Visible;
import main.Atomics.MyEditableBoundedShape;
import main.AvatarParts.Line;
@StructurePattern(StructurePatternNames.OVAL_PATTERN)
@EditablePropertyNames({"x","y","height","width","color"})
@PropertyNames({"x","y","height","width","rotationAxle","color","filled"})

public class Shield extends MyEditableBoundedShape implements ComplicatedWeapon {
	
	private Line rotationAxle;
	private boolean filled = true;
	private Color color = Color.GRAY;
	
	public Shield(int initX, int initY, int length){
		super(0,0,0,0);
		rotationAxle = new RotatingLine(initX,initY,-length,-length/2);
		super.setX(rotationAxle.getX()+rotationAxle.getWidth());
		super.setY(rotationAxle.getY()+rotationAxle.getHeight());
		super.setHeight(rotationAxle.getHeight()*-2);
		super.setWidth(rotationAxle.getWidth()*-2);
				
	}
	
	public boolean getFilled(){
		return filled;
	}
	public void setColor(Color newColor){
		color = newColor;
	}
	public Color getColor(){
		return color;
	}
	public void rotate(double angle){
		rotationAxle.rotate(angle);
		x = rotationAxle.getX()+rotationAxle.getWidth();
		y = rotationAxle.getY()+rotationAxle.getHeight();
		height = rotationAxle.getHeight()*-2;
		width = rotationAxle.getWidth()*-2;
	}
@Visible(false)
	public Line getRotationAxle(){//the relation to height and width and the way they produce shapes 
		return rotationAxle;//in OE made it impossible to use this to produce central (vs endpoint) rotation
	}//but I am leaving the code concerning this property to tinker with later
	public void setX(int newX) {
		rotationAxle.setX(newX);	
		super.setX(rotationAxle.getX()+rotationAxle.getWidth());
	}
	public void setY(int newY) {
		rotationAxle.setY(newY);
		super.setY(rotationAxle.getY()+rotationAxle.getHeight());
	}
	public void setHeight(int h){
		rotationAxle.setHeight(-h/2);
		super.setY(rotationAxle.getY()+rotationAxle.getHeight());
		super.setHeight(h);
	}
	public void setWidth(int w){
		rotationAxle.setWidth(-w/2);
		super.setX(rotationAxle.getX()+rotationAxle.getWidth());
		super.setWidth(w);
	}
}
