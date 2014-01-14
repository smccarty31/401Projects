package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeListener;

import main.AvatarParts.ComplicatedWeapon;
import main.AvatarParts.ImageShape;
import main.AvatarParts.Line;
import main.AvatarParts.StringShape;
import main.AvatarParts.Weapon;
import main.SceneParts.Avatar;
import main.SceneParts.Bridge;
import main.SceneParts.Gorge;
import main.SceneParts.Rectangle;

public interface ScenePainter extends PropertyChangeListener {
	
	public void paint(Graphics g);
	
    public void draw(Graphics2D g, StringShape aLabel); 
	public void draw(Graphics2D g, Gorge gorge);
    public void draw(Graphics2D g, Rectangle rectangle); 
    public void draw(Graphics2D g, Bridge bridge);
    public void draw(Graphics2D g, ImageShape anImage);
    public void draw(Graphics2D g, Line line);
    public void draw(Graphics2D g, ComplicatedWeapon shield) ;
    public void draw(Graphics2D g, Weapon sword) ;
    public void draw(Graphics2D g, Avatar avatar) ;
            }
