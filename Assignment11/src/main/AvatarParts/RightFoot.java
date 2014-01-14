package main.AvatarParts;
import main.Atomics.MyLocatable;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;

@StructurePattern("Bean Pattern")
@PropertyNames({"x","y","toeOne","toeTwo"})
@EditablePropertyNames({"x","y"})
public class RightFoot extends MyLocatable implements Appendage {//similar shape to the angles used for arms and legs
											//although smaller and directed horizontally to the right
		
		private Line toeOne,toeTwo;
		
		public RightFoot(int initX,int initY,int length){
			super(initX,initY);
			toeOne = new RotatingLine(initX,initY,length/2,length);
			toeTwo = new RotatingLine(initX,initY,-length/2,length);
		}
		public int getX(){
			return x;
		}
		public void setX(int newX){
			super.setX(newX);
			toeOne.setX(newX);
			toeTwo.setX(newX);
		}
		public void setY(int newY){
			super.setY(newY);
			toeOne.setY(newY);
			toeTwo.setY(newY);
		}
		public Line getToeOne(){
			return toeOne;
		}
		public Line getToeTwo(){
			return toeTwo;
		}
		public void rotate(double angle){
			toeOne.rotate(angle);
			toeTwo.rotate(angle);
		}

}

