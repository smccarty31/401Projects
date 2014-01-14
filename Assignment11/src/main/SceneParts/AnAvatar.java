package main.SceneParts;
import java.awt.Color;
import java.awt.Point;
import java.beans.PropertyChangeListener;


import util.annotations.Position;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;
import main.AvatarParts.AStringShape;
import main.AvatarParts.Angle;
import main.AvatarParts.ComplicatedWeapon;
import main.AvatarParts.HeadShape;
import main.AvatarParts.ImageShape;
import main.AvatarParts.Line;
import main.AvatarParts.RotatingLine;
import main.AvatarParts.Shield;
import main.AvatarParts.StringShape;
import main.AvatarParts.Sword;
import main.AvatarParts.TwoPartExtremity;
import main.AvatarParts.Weapon;
@PropertyNames({"aTorso","twoArms","twoLegs","aSpeech","aSword","location","aShield","aHead"})
@Tags({"Avatar"})
@StructurePattern("Bean Pattern")
public class AnAvatar implements Avatar {
	
	private Line aTorso;
	private TwoPartExtremity twoArms, twoLegs;
	private ImageShape aHead;
	private StringShape aSpeech;
	private Point location;
	private Weapon aSword;
	private ComplicatedWeapon aShield;
//	private Appendage leftFoot,rightFoot;
	
	public AnAvatar(int initX, int initY, String fileName, String speech){
		
		location = new Point(initX,initY);//initialize "location" of avatar
		aHead = new HeadShape(fileName, initX, initY);//initialize the head with the given character's image and position
		aSpeech = new AStringShape(initX+aHead.getWidth()+5, initY+5, speech);//using the position/dimensions of the head, produce the rest of the parts of
		aTorso = new RotatingLine(initX+(aHead.getWidth()/2), initY+aHead.getHeight(), 50, 0);//the avatar relative to the position/dimensions of the head
		twoArms = new Angle(aTorso.getX(), aTorso.getY()+(aTorso.getHeight())/2, (aTorso.getHeight())/3, (aTorso.getHeight())/2);//arms given smaller height/width
		twoLegs = new Angle(aTorso.getX(), aTorso.getY()+aTorso.getHeight(),3*(aTorso.getHeight())/4, 3*(aTorso.getHeight())/4);//than legs
		aSword = new Sword(twoArms.getRightLine().getX()+twoArms.getRightLine().getWidth(),twoArms.getRightLine().getY()+twoArms.getRightLine().getHeight(),twoArms.getRightLine().getHeight()*2);
		aSword.rotate(Math.PI/5);//sword placed at end of right arm and given dimensions relative to arms and rotated to a natural position
		aShield = new Shield(twoArms.getLeftLine().getX()+twoArms.getLeftLine().getWidth(),twoArms.getLeftLine().getY()+twoArms.getLeftLine().getHeight(),twoArms.getLeftLine().getHeight()*2);
		//sheild placed at end or left arm and given dimensions relative to arms
		//leftFoot = new LeftFoot(twoLegs.getLeftLine().getX()+twoLegs.getLeftLine().getWidth(),twoLegs.getLeftLine().getY()+twoLegs.getLeftLine().getHeight(),twoLegs.getLeftLine().getWidth()/2);
		//rightFoot = new RightFoot(twoLegs.getRightLine().getX()+twoLegs.getRightLine().getWidth(),twoLegs.getRightLine().getY()+twoLegs.getRightLine().getHeight(),twoLegs.getRightLine().getWidth()/2);
		//feet placed at end of legs and given dimensions relative to legs
	}
	
	@Position(0)
	public ComplicatedWeapon getAShield(){
		return aShield;
	}
	public void connect(){
		aHead.setX((int) location.getX());//adjusts head to avatar's "location"
		aHead.setY((int) location.getY());
		aTorso.setX(aHead.getX()+(aHead.getWidth()/2));//determines torso's position
		aTorso.setY(aHead.getY()+aHead.getHeight());//based on head's new position
		twoArms.setX(aTorso.getX()+(aTorso.getWidth()/2));//determines arms' position
		twoArms.setY(aTorso.getY()+(aTorso.getHeight()/2));
		twoLegs.setX(aTorso.getX()+aTorso.getWidth());//based on head's new position
		twoLegs.setY(aTorso.getY()+aTorso.getHeight());
		aSpeech.setX(aHead.getX()+aHead.getWidth());//continues for all parts of the avatar
		aSpeech.setY(aHead.getY()+10);//basing the new positions on changes to the "location" of the avatar and any
		aSword.setX(twoArms.getX()+twoArms.getRightLine().getWidth());//changes to widths or heights
		aSword.setY(twoArms.getY()+twoArms.getRightLine().getHeight());
		aShield.setX(twoArms.getX()+twoArms.getLeftLine().getWidth());
		aShield.setY(twoArms.getY()+twoArms.getLeftLine().getHeight());
	//	leftFoot.setX(aHead.getX()+(aHead.getWidth()/2)+twoLegs.getLeftLine().getWidth());
		//leftFoot.setY(aHead.getY()+aHead.getHeight()+aTorso.getHeight()+twoLegs.getLeftLine().getHeight());
		//rightFoot.setX(aHead.getX()+(aHead.getWidth()/2)+twoLegs.getRightLine().getWidth());
		//rightFoot.setY(aHead.getY()+aHead.getHeight()+aTorso.getHeight()+twoLegs.getRightLine().getHeight());
	}
@Tags({"resize"})	
	public void resize(double factor){
		aTorso.setHeight((int) (aTorso.getHeight()*factor));//multiplies all widths and heights by 
		aTorso.setWidth((int) (aTorso.getWidth()*factor));//an input factor, then casts these new values
		twoArms.getLeftLine().setHeight((int) (twoArms.getLeftLine().getHeight()*factor));//to an integer so they can
		twoArms.getRightLine().setHeight((int) (twoArms.getRightLine().getHeight()*factor));//be used by OE to adjust 
		twoLegs.getLeftLine().setHeight((int) (twoLegs.getLeftLine().getHeight()*factor));//the shapes to their new size
		twoLegs.getRightLine().setHeight((int) (twoLegs.getRightLine().getHeight()*factor));
		twoArms.getLeftLine().setWidth((int) (twoArms.getLeftLine().getWidth()*factor));
		twoArms.getRightLine().setWidth((int) (twoArms.getRightLine().getWidth()*factor));
		twoLegs.getLeftLine().setWidth((int) (twoLegs.getLeftLine().getWidth()*factor));
		twoLegs.getRightLine().setWidth((int) (twoLegs.getRightLine().getWidth()*factor));
		aSword.getBlade().setHeight((int) (aSword.getBlade().getHeight()*factor));
		aSword.getBlade().setWidth((int) (aSword.getBlade().getWidth()*factor));
		aSword.getLeftCross().setHeight((int) (aSword.getLeftCross().getHeight()*factor));
		aSword.getLeftCross().setWidth((int) (aSword.getLeftCross().getWidth()*factor));
		aSword.getRightCross().setHeight((int) (aSword.getRightCross().getHeight()*factor));
		aSword.getRightCross().setWidth((int) (aSword.getRightCross().getWidth()*factor));
		aShield.setHeight((int) (aShield.getHeight()*factor));
		aShield.setWidth((int) (aShield.getWidth()*factor));
		connect();//once the heights and widths of the avatar parts have been adjusted the connect
	}//method adjusts their positions to account for these changes
@Tags({"move"})
	public void move(int newX, int newY){
		location.setLocation(newX, newY);//adjusts the "location" of the avatar	
		connect();//adjusts positions of all parts of the avatar according to the new "location" 
	}
	public Line getATorso(){
		return aTorso;
	}
	public Point getLocation(){
		return location;
	}
	public TwoPartExtremity getTwoArms(){
		return twoArms;
	}
	public TwoPartExtremity getTwoLegs(){
		return twoLegs;
	}
	public ImageShape getAHead(){
		return aHead;
	}
//@Visible(false)
	public StringShape getASpeech(){
		return aSpeech;
	}
	@Position(1)
	public Weapon getASword(){
		return aSword;
	}
	public void changeColor(Color color){
		aTorso.setColor(color);
		twoArms.getLeftLine().setColor(color);
		twoArms.getRightLine().setColor(color);
		twoLegs.getLeftLine().setColor(color);
		twoLegs.getRightLine().setColor(color);
		aSword.getBlade().setColor(color);
		aSword.getLeftCross().setColor(color);
		aSword.getRightCross().setColor(color);
		//rightFoot.getToeOne().setColor(color);
		//rightFoot.getToeTwo().setColor(color);
		//leftFoot.getToeOne().setColor(color);
		//leftFoot.getToeTwo().setColor(color);
	}
	//@Position(2)
	//public Appendage getLeftFoot(){
		//return leftFoot;
	//}
	//@Position(3)
	//public Appendage getRightFoot(){
		//return rightFoot;
	//}
	public void addListener(PropertyChangeListener listener){
		aTorso.addPropertyChangeListener(listener);
		twoLegs.getLeftLine().addPropertyChangeListener(listener);
		twoLegs.getRightLine().addPropertyChangeListener(listener);
		twoArms.getRightLine().addPropertyChangeListener(listener);
		twoArms.getLeftLine().addPropertyChangeListener(listener);
		aHead.addPropertyChangeListener(listener);
		aSpeech.addPropertyChangeListener(listener);
		aShield.addPropertyChangeListener(listener);
		aSword.getBlade().addPropertyChangeListener(listener);
		aSword.getLeftCross().addPropertyChangeListener(listener);
		aSword.getRightCross().addPropertyChangeListener(listener);
	}
}
