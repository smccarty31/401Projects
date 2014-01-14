package main.SceneParts;

import java.awt.Color;
import java.awt.Point;
import java.beans.PropertyChangeListener;

import main.AvatarParts.ComplicatedWeapon;
import main.AvatarParts.ImageShape;
import main.AvatarParts.Line;
import main.AvatarParts.StringShape;
import main.AvatarParts.TwoPartExtremity;
import main.AvatarParts.Weapon;

public interface Avatar {

	public void move(int newX, int newY);
	public Line getATorso();
	public TwoPartExtremity getTwoArms();
	public TwoPartExtremity getTwoLegs();
	public ImageShape getAHead();
	public StringShape getASpeech();
	public void resize(double factor);
	public Point getLocation();
	public void changeColor(Color color);
	public Weapon getASword();
	public void connect();
	public ComplicatedWeapon getAShield();
	//public Appendage getLeftFoot();
	//public Appendage getRightFoot();
	public void addListener(PropertyChangeListener listener);
}
