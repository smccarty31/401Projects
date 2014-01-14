package main.AvatarParts;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.Stroke;

import main.Atomics.MyEditableBoundedShape;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
@PropertyNames({"x","y","height","width","location","color","stroke","location"})
@EditablePropertyNames({"x", "y","height","width","color"})
@StructurePattern(StructurePatternNames.LINE_PATTERN)
@Tags({"Rotating Line"})
public class RotatingLine extends MyEditableBoundedShape implements Line {//
	
	private double realHeight, realWidth;
	private Point location;
	private Color color = Color.BLACK;
	private Stroke stroke = new BasicStroke(3.5f);
	
public RotatingLine(int initX, int initY, int initHeight, int initWidth){
    	super(initX,initY,initHeight,initWidth);
    	realHeight = initHeight;//will be used to prevent rounding errors
    	realWidth = initWidth;//during rotation method
    	location = new Point(initX,initY);
    }
	
	public void setX(int newX) {
		super.setX(newX);
		location.setLocation(newX, y);
		}
	public void setY(int newY) {
		super.setY(newY);
		location.setLocation(x, newY);
	}
 	public Point getLocation(){//added to follow line structure pattern, OE still throws warning
 		return location;
 	}
 	public void setColor(Color newColor){
 		color = newColor;
 	}
 	public Color getColor(){
 		return color;
 	}
 	
 	public Stroke getStroke(){
 		return stroke;
 	}
@Tags("rotate")
  	public void rotate(double angle){
		double initAngle;
		double radius = (Math.sqrt((realHeight*realHeight + realWidth*realWidth)));
		if (realHeight == 0){//prevent dividing by zero
			if (realWidth > 0){initAngle = 0;}//positive width with zero height indicates zero angle on unit circle
			else {initAngle = Math.PI;}}//negative width indicates 180 degree angle (PI) around unit circle
		else if (realWidth == 0) {//prevent dividing by zero
			if (realHeight > 0){initAngle = Math.PI/2;}//positive height with zero width indicates right angle (PI/2) in unit circle
			else {initAngle = 3*Math.PI/2;}	//negative height indicates 270 deg (3PI/2)
		}
		else {
			initAngle = Math.atan2(realHeight, realWidth);//if no zeroes involved, calculate normally
		}
		
		realHeight = (radius*Math.sin(angle+initAngle));//calculate and save new exact
		realWidth =  (radius*Math.cos(angle+initAngle));//height and width to prevent rounding errors
		super.setHeight((int) realHeight);//cast exact height and width
		super.setWidth((int) realWidth);//to integers so that OE can use them to display line
		}

@Tags("change location")
	public void move(int newX, int newY){
		super.setX(newX);
		super.setY(newY);
		location.setLocation(newX,newY);
	}
@Tags("move")
	public void moveUpperLeft(int newX, int newY){//legacy code from previous assignment
		int endX = x+width; //define the endpoints as their own coordinates
		int endY = y+height;
		super.setX(newX);//change origin ("upper left")
		super.setY(newY);
		super.setWidth(endX - x);//adjust width and height according to new origin
		super.setHeight(endY - y);//by finding the X and Y distances between the endpoint and the new origin
		realHeight = super.getHeight();//saved to prevent
		realWidth = super.getWidth();//rounding errors
	}
	
}
