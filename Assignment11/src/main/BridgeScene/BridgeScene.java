package main.BridgeScene;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import bus.uigen.OEFrame;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;
import util.annotations.Visible;
import util.misc.ThreadSupport;
import main.APropertyListenerSupport;
import main.PropertyListenerSupport;
import main.SceneParts.AnAvatar;
import main.SceneParts.Avatar;
import main.SceneParts.BridgeGorge;
import main.SceneParts.BridgeStandingArea;
import main.SceneParts.Gorge;
import main.SceneParts.StandingArea;
@Tags({"Bridge Scene"})
@PropertyNames({"guard","arthur","galahad","lancelot","robin","knightStandingArea","guardStandingArea","bridgegorge","approachedAvatar","passedLevel","failedLevel","oeframe","occupied","knightTurn"})
@EditablePropertyNames({"oeframe"})
@StructurePattern("Bean Pattern")

public class BridgeScene implements Scene {
	
	private Avatar arthur, galahad, lancelot, guard, robin;
	private Gorge bridgegorge;
	private StandingArea knightStandingArea, guardStandingArea;
	private boolean occupied=false;
	private boolean knightTurn=false;
	private Avatar approachedAvatar;
	private int passedLevel=5;
	private int failedLevel=5;
	private OEFrame oeframe;
	private PropertyListenerSupport apls = new APropertyListenerSupport();

	public BridgeScene(int initX, int initY){
		arthur = new AnAvatar(initX, initY,"arthur.jpg", "");
		galahad = new AnAvatar(initX+75, initY,"galahad.jpg", "");
		lancelot = new AnAvatar(initX, initY+150,"lancelot.jpg", "");
		robin = new AnAvatar(initX+75,initY+150,"robin.jpg", "");
		guard = new AnAvatar(initX+300, initY+150,"guard.jpg", "");
		bridgegorge = new BridgeGorge(guard.getAHead().getX()+guard.getAHead().getWidth()+20, 0,800, 150, guard.getTwoArms().getRightLine().getY());
		guardStandingArea = new BridgeStandingArea(guard.getAHead().getX()-10,guard.getAHead().getY()+guard.getAHead().getHeight()+guard.getATorso().getHeight()-10,guard.getAHead().getHeight()+20,guard.getAHead().getWidth()+20);
		knightStandingArea = new BridgeStandingArea(guardStandingArea.getX()-150,guardStandingArea.getY(),guardStandingArea.getHeight(),guardStandingArea.getWidth());
				}
	
 	public void setOEFrame(OEFrame newOEFrame){//allows oeframe from main method to be accessed by the scene methods
		oeframe = newOEFrame;
	}
@Visible(false)
	public OEFrame getOEFrame(){
		return oeframe;
	}
 	public boolean getOccupied(){
		return occupied;
	}
	public boolean getKnightTurn(){
		return knightTurn;
	}
@Visible(false)
	public Avatar getApproachedAvatar(){//allows the avatar that has approached the bridge to be abstracted in the scene methods
		return approachedAvatar;
	}
@Visible(false)
	public int getPassedLevel(){
		return passedLevel;
	}
@Visible(false)
	public int getFailedLevel(){
		return failedLevel;
	}
	public Avatar getArthur(){
		return arthur;
	}
	public Avatar getGalahad(){
		return galahad;
	}
	public Avatar getLancelot(){
		return lancelot;
	}
	public Avatar getRobin(){
		return robin;
	}
	public Avatar getGuard(){
		return guard;
	}
	public Gorge getBridgeGorge(){
		return bridgegorge;
	}
	public StandingArea getKnightStandingArea(){
		return knightStandingArea;
	}
	public StandingArea getGuardStandingArea(){
		return guardStandingArea;
	}
	public boolean preSay(){
		return occupied;
	}
@Tags("Say")
	public void say(String string){
		assert preSay():"No Knight has approached the bridge";
			if (knightTurn == false){//if it is the guards turn, the guard is assigned 
				guard.getASpeech().setText(string);//the string and the knight's speech is emptied
				approachedAvatar.getASpeech().setText("");
				knightTurn = true;
				if (apls != null){//second condition prevents unnecessary notifications
					apls.notifyAllListeners(new PropertyChangeEvent(this, "this", "passed", false));
					System.out.println();
				}
			}
			else {
				guard.getASpeech().setText("");//if it is not the guards turn, the opposite happens
				approachedAvatar.getASpeech().setText(string);
				knightTurn = false;
				if (apls != null){//second condition prevents unnecessary notifications
					apls.notifyAllListeners(new PropertyChangeEvent(this, "this", "passed", true));
					System.out.println();
				}
			}
		}
	public boolean preApproach(){
		return !occupied; 
	}				
@Tags("Approach")
	public void approach(Avatar avatar){
		if (avatar == guard){}//makes it impossible for the guard to approach himself
		else {			
			assert preApproach():"Standing area already occupied";
				occupied = true;
				if (apls != null){//second condition prevents unnecessary notifications
					apls.notifyAllListeners(new PropertyChangeEvent(this, "this", "failed", true));
					apls.notifyAllListeners(new PropertyChangeEvent(this, "this", "passed", true));
					apls.notifyAllListeners(new PropertyChangeEvent(this, "this", "approach", false));
					apls.notifyAllListeners(new PropertyChangeEvent(this, "this", "say", true));
					System.out.println();
				}
				approachedAvatar = avatar;//allows other methods to access the avatar that has approached the guard
				int distX = knightStandingArea.getX()+5-avatar.getAHead().getX();//measures the distances to the standing area for animation
				int distY = knightStandingArea.getY()+(knightStandingArea.getHeight()/2)-(avatar.getAHead().getHeight()+avatar.getATorso().getHeight()+avatar.getTwoLegs().getLeftLine().getHeight())-avatar.getAHead().getY();
				for (int i=1;i<=9;i++){
				avatar.move(avatar.getAHead().getX()+distX/10, avatar.getAHead().getY()+distY/10);
				ThreadSupport.sleep(100);
				}
				avatar.move(knightStandingArea.getX()+7,  knightStandingArea.getY()+(knightStandingArea.getHeight()/2)-(avatar.getAHead().getHeight()+avatar.getATorso().getHeight()+avatar.getTwoLegs().getLeftLine().getHeight()));
				//final move aligns the avatar to deal with rounding errors that occur over the loop
				ThreadSupport.sleep(100);
				}
	}
	public boolean prePassed(){
		return !knightTurn && occupied;
	}	
@Tags("Passed")
	public void passed(){
		assert prePassed():"Not the guard's turn or standing area unoccupied";//only acts if a knight has approached and it is the guard's turn to speak
			approachedAvatar.getASpeech().setText("");
			int xDist = bridgegorge.getX()+bridgegorge.getWidth()-approachedAvatar.getAHead().getX()+10;
			int yDist = approachedAvatar.getAHead().getY()-passedLevel;
			for (int i = 1;i<=10;i++){//crosses the bridge
			approachedAvatar.move(approachedAvatar.getAHead().getX()+xDist/10,approachedAvatar.getAHead().getY());
			ThreadSupport.sleep(100);
			}
			for (int i = 1;i<=10;i++){//moves to empty space on the other side
			approachedAvatar.move(approachedAvatar.getAHead().getX(), approachedAvatar.getAHead().getY()-yDist/10);
			ThreadSupport.sleep(100);
			}			
			passedLevel = passedLevel+approachedAvatar.getAHead().getHeight()+approachedAvatar.getATorso().getHeight()+approachedAvatar.getTwoLegs().getLeftLine().getHeight()+10;
			occupied = false;//adjusts the properties representing the space 
			if (apls != null){
				apls.notifyAllListeners(new PropertyChangeEvent(this, "this", "failed", false));
				apls.notifyAllListeners(new PropertyChangeEvent(this, "this", "passed", false));
				apls.notifyAllListeners(new PropertyChangeEvent(this, "this", "approach", true));
				apls.notifyAllListeners(new PropertyChangeEvent(this, "this", "say", false));
				System.out.println();
			}
			approachedAvatar = null;
	
	}
	public boolean preFailed(){
		return occupied;
	}
@Tags("Failed")
	public void failed(){
		assert preFailed():"Standing area unoccupied";
		if (knightTurn==false){//only fails when knight area is occupied and fails the knight if it is the guard's turn to speak
			approachedAvatar.getASpeech().setText("");//empties the knight's speech
			int xDist=bridgegorge.getX()+bridgegorge.getWidth()-(approachedAvatar.getTwoLegs().getRightLine().getWidth())-(approachedAvatar.getAHead().getX());
			int yDist=approachedAvatar.getAHead().getY()-failedLevel;
			for (int i=1;i<=6;i++){//animates the knight falling to an open space in the gorge
				approachedAvatar.move(approachedAvatar.getAHead().getX()+xDist/7, approachedAvatar.getAHead().getY()-yDist/7);
				approachedAvatar.resize(0.9);//slowly shrinks the avatar to simulate depth
				ThreadSupport.sleep(150);
			}
			approachedAvatar.getTwoLegs().getRightLine().setColor(Color.WHITE);//as the avatar reaches the 
			approachedAvatar.getTwoLegs().getLeftLine().setColor(Color.WHITE);//bottom of the gorge, he turns
			approachedAvatar.getTwoArms().getLeftLine().setColor(Color.WHITE);//white and has his head transformed
			approachedAvatar.getTwoArms().getRightLine().setColor(Color.WHITE);//into a skull to imply his death
			approachedAvatar.getATorso().setColor(Color.WHITE);
			approachedAvatar.getASword().getBlade().setColor(Color.WHITE);
			approachedAvatar.getASword().getLeftCross().setColor(Color.WHITE);
			approachedAvatar.getASword().getRightCross().setColor(Color.WHITE);
			approachedAvatar.getAShield().setColor(Color.WHITE);
			approachedAvatar.getAHead().setImageFileName("skull.jpg");//changes the HeadShape image into a skull image
			approachedAvatar.connect();
			approachedAvatar.move(approachedAvatar.getAHead().getX()+xDist/7, approachedAvatar.getAHead().getY()-yDist/7);
			approachedAvatar.resize(0.9);//makes final movement/resizing based on the new HeadShape width/height
			ThreadSupport.sleep(150);
			failedLevel = failedLevel+approachedAvatar.getAHead().getHeight()+approachedAvatar.getATorso().getHeight()+approachedAvatar.getTwoLegs().getLeftLine().getHeight()+5;
			approachedAvatar = null;//adjusts target for next failed avatar and adjusts
			occupied = false;//assignments from previous approach
			if (apls != null){//second condition prevents unnecessary notifications
				apls.notifyAllListeners(new PropertyChangeEvent(this, "this", "failed", false));
				apls.notifyAllListeners(new PropertyChangeEvent(this, "this", "passed", false));
				apls.notifyAllListeners(new PropertyChangeEvent(this, "this", "approach", true));
				apls.notifyAllListeners(new PropertyChangeEvent(this, "this", "say", false));
				System.out.println();
			}
		}
		else {//if it is the knight's turn, then the guard is failed
			guard.getASpeech().setText("");
			int xDist=bridgegorge.getX()+bridgegorge.getWidth()-(guard.getTwoLegs().getRightLine().getWidth())-(guard.getAHead().getX());
			int yDist=guard.getAHead().getY()-failedLevel;
			for (int i=1;i<=6;i++){//follows the same process as the knights, except at the end
				guard.move(guard.getAHead().getX()+xDist/7, guard.getAHead().getY()-yDist/7);
				guard.resize(0.9);
				ThreadSupport.sleep(150);
			}
			guard.getTwoLegs().getRightLine().setColor(Color.WHITE);
			guard.getTwoLegs().getLeftLine().setColor(Color.WHITE);
			guard.getTwoArms().getLeftLine().setColor(Color.WHITE);
			guard.getTwoArms().getRightLine().setColor(Color.WHITE);
			guard.getATorso().setColor(Color.WHITE);
			guard.getASword().getBlade().setColor(Color.WHITE);
			guard.getASword().getLeftCross().setColor(Color.WHITE);
			guard.getASword().getRightCross().setColor(Color.WHITE);
			guard.getAShield().setColor(Color.WHITE);
			guard.getAHead().setImageFileName("skull.jpg");
			guard.connect();
			guard.move(guard.getAHead().getX()+xDist/7, guard.getAHead().getY()-yDist/7);
			guard.resize(0.9);
			ThreadSupport.sleep(150);
			failedLevel = failedLevel+guard.getAHead().getHeight()+guard.getATorso().getHeight()+guard.getTwoLegs().getLeftLine().getHeight()+5;
			knightTurn = false;//defaults knightTurn to false to allow any remaining avatars to pass the unguarded bridge
			if (apls != null){
				apls.notifyAllListeners(new PropertyChangeEvent(this, "this", "passed", true));
				System.out.println();
			}
		}
	}
	public void addListener(PropertyChangeListener listener){
		lancelot.addListener(listener);
		arthur.addListener(listener);
		galahad.addListener(listener);
		robin.addListener(listener);
		guard.addListener(listener);
	}
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		apls.add(arg0);
	}
}
	

