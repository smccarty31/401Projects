package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;

import main.Atomics.MyBoundedShapeIntf;
import main.AvatarParts.ComplicatedWeapon;
import main.AvatarParts.ImageShape;
import main.AvatarParts.Line;
import main.AvatarParts.StringShape;
import main.AvatarParts.Weapon;
import main.BridgeScene.Scene;
import main.SceneParts.Avatar;
import main.SceneParts.Bridge;
import main.SceneParts.BridgeStandingArea;
import main.SceneParts.Gorge;
import main.SceneParts.Rectangle;
import main.SceneParts.StandingArea;
@Tags("Inheriting Bridge Scene Painter")
public class BridgeScenePainter extends Component implements ScenePainter {
	Scene aScene;
    
    public BridgeScenePainter(Scene theScene){
    	aScene = theScene;
    	aScene.addListener(this);//register as listener of scene-->avatars-->composite shapes-->atomic shapes
    }
   
    public void paint(Graphics g) {
	  	super.paint(g);
	  	Graphics2D g2 = (Graphics2D) g;     
	 	g.setColor(new Color(0,128,0));
    	g.fillRect(0, 0, 2000, 2000);
      	draw(g2, aScene.getGuardStandingArea());
      	draw(g2, aScene.getKnightStandingArea());
    	draw(g2, aScene.getBridgeGorge());
    	draw(g2, aScene.getBridgeGorge().getGorgeBridge());
    	draw(g2, aScene.getGuard());
    	draw(g2, aScene.getGalahad());
    	draw(g2, aScene.getArthur());
    	draw(g2, aScene.getRobin());
      	draw(g2, aScene.getLancelot());

    	
    }
    
    public void draw(Graphics2D g, StringShape aLabel) {
    	g.setColor(Color.BLACK);
        String s = aLabel.getText();
        g.drawString(s, aLabel.getX(), aLabel.getY());      
    }
    public void draw(Graphics2D g, Gorge gorge){
    	GradientPaint blacktowhite = new GradientPaint(gorge.getX(),0,Color.WHITE,gorge.getX()+gorge.getWidth(), 0,Color.BLACK);
    	((Graphics2D) g).setPaint(blacktowhite);
    	g.fillRect(gorge.getX(), 0, gorge.getWidth(), gorge.getHeight());
    }
    public void draw(Graphics2D g, Rectangle rectangle){
    	g.drawRect(rectangle.getX(),rectangle.getY(), rectangle.getWidth(),rectangle.getHeight());
    }
    public void draw(Graphics2D g, Bridge bridge){
    	g.setColor(new Color(139,69,19));
    	g.fillRect(bridge.getLowerBridgeCrossbeam().getX(), bridge.getLowerBridgeCrossbeam().getY(), bridge.getLowerBridgeCrossbeam().getWidth(), bridge.getLowerBridgeCrossbeam().getHeight());
    	g.fillRect(bridge.getUpperBridgeCrossbeam().getX(), bridge.getUpperBridgeCrossbeam().getY(), bridge.getUpperBridgeCrossbeam().getWidth(), bridge.getUpperBridgeCrossbeam().getHeight());
    	g.fillRect(bridge.getBridgeSlat1().getX(), bridge.getBridgeSlat1().getY(), bridge.getBridgeSlat1().getWidth(), bridge.getBridgeSlat1().getHeight());
    	g.fillRect(bridge.getBridgeSlat2().getX(), bridge.getBridgeSlat2().getY(), bridge.getBridgeSlat2().getWidth(), bridge.getBridgeSlat2().getHeight());
    	g.fillRect(bridge.getBridgeSlat3().getX(), bridge.getBridgeSlat3().getY(), bridge.getBridgeSlat3().getWidth(), bridge.getBridgeSlat3().getHeight());
    	g.fillRect(bridge.getBridgeSlat4().getX(), bridge.getBridgeSlat4().getY(), bridge.getBridgeSlat4().getWidth(), bridge.getBridgeSlat4().getHeight());
    	g.fillRect(bridge.getBridgeSlat5().getX(), bridge.getBridgeSlat5().getY(), bridge.getBridgeSlat5().getWidth(), bridge.getBridgeSlat5().getHeight());
    	g.fillRect(bridge.getBridgeSlat6().getX(), bridge.getBridgeSlat6().getY(), bridge.getBridgeSlat6().getWidth(), bridge.getBridgeSlat6().getHeight());
    	g.fillRect(bridge.getBridgeSlat7().getX(), bridge.getBridgeSlat7().getY(), bridge.getBridgeSlat7().getWidth(), bridge.getBridgeSlat7().getHeight());
    	
    }
    public void draw(Graphics2D g, ImageShape anImage) {
        Image img = Toolkit.getDefaultToolkit().getImage(anImage.getImageFileName());
        g.drawImage(img, anImage.getX(), anImage.getY(), this);     
    }
    public void draw(Graphics2D g, Line line){
    	g.drawLine(line.getX(), line.getY(), line.getX()+line.getWidth(), line.getY()+line.getHeight());
    }
    public void draw(Graphics2D g, ComplicatedWeapon shield){
    	g.setStroke(new BasicStroke(0.5f));
    	g.setColor(new Color(205,133,63));
    	g.fillOval(shield.getX(), shield.getY(), shield.getWidth(), shield.getHeight());
    }
    public void draw(Graphics2D g, Weapon sword){
    	g.setColor(Color.GRAY);
    	g.setStroke(new  BasicStroke(4.0f, BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER));
    	g.drawLine(sword.getBlade().getX(), sword.getBlade().getY(), sword.getBlade().getX()+sword.getBlade().getWidth(), sword.getBlade().getY()+sword.getBlade().getHeight());
    	g.drawLine(sword.getLeftCross().getX(), sword.getLeftCross().getY(), sword.getLeftCross().getX()+sword.getLeftCross().getWidth(), sword.getLeftCross().getY()+sword.getLeftCross().getHeight());
    	g.drawLine(sword.getRightCross().getX(), sword.getRightCross().getY(), sword.getRightCross().getX()+sword.getRightCross().getWidth(), sword.getRightCross().getY()+sword.getRightCross().getHeight());
    }
    public void draw(Graphics2D g, Avatar avatar){
    	g.setColor(Color.BLACK);
    	g.setStroke(new BasicStroke(3.0f));
    	draw(g, avatar.getAHead());
    	draw(g, avatar.getATorso());
    	draw(g, avatar.getTwoArms().getLeftLine());
    	draw(g, avatar.getTwoArms().getRightLine());
    	draw(g, avatar.getTwoLegs().getLeftLine());
    	draw(g, avatar.getTwoLegs().getRightLine());
    	draw(g, avatar.getAShield());
    	draw(g, avatar.getASword()); 
    	draw(g, avatar.getASpeech());
    }
    public void draw(Graphics2D g, StandingArea area){
    	g.setColor(Color.BLACK);
    	g.setStroke(new BasicStroke(6.0f));
    	g.drawOval(area.getX(), area.getY(), area.getWidth(), area.getHeight());
    }
	public void propertyChange(PropertyChangeEvent arg0) {//if anything changes in the scene, the view is repainted
		repaint();
	}
	
}
