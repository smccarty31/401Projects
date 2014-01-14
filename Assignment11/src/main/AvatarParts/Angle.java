package main.AvatarParts;
import main.Atomics.MyEditableBoundedShape;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;
@StructurePattern("Bean Pattern")
@EditablePropertyNames({"x","y","height","width"})
@PropertyNames({"x","y","leftLine","rightLine","height","width"})
@Tags({"Angle"})
public class Angle extends MyEditableBoundedShape implements TwoPartExtremity {
	
		private Line leftLine;
		private Line rightLine;
	
	public Angle(int initX, int initY, int initHeight, int initWidth){//creates two RotatingLine objects at the same point
		super(initX,initY,initHeight,initWidth);//in opposite X directions
		leftLine = new RotatingLine(initX,initY,initHeight,-initWidth);
		rightLine = new RotatingLine(initX, initY,initHeight, initWidth);		
	}
	public void setX(int newX) {
		super.setX(newX);	
		leftLine.setX(newX);
		rightLine.setX(newX);
	}
	public void setHeight(int newHeight){
		super.setHeight(newHeight);
		leftLine.setHeight(newHeight);
		rightLine.setHeight(newHeight);
	}
	public void setWidth(int newWidth){
		super.setWidth(newWidth);
		leftLine.setWidth(newWidth);
		rightLine.setWidth(newWidth);
	}
	public void setY(int newY){
		super.setY(newY);
		leftLine.setY(newY);
		rightLine.setY(newY);
	}
	public Line getLeftLine(){
		return leftLine;
	}
	public Line getRightLine(){
		return rightLine;
	}
	public void rotate(double angle){//rotates the two lines together by 
		leftLine.rotate(-angle);//rotating each line in the opposite direction
		rightLine.rotate(angle);
		super.setHeight(rightLine.getHeight());
		super.setWidth(rightLine.getWidth());
	}
}
