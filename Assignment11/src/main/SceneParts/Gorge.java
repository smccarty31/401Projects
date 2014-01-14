package main.SceneParts;

import main.AvatarParts.Line;

public interface Gorge {
	public Rectangle getGorgeWall1();
	public Rectangle getGorgeWall2();
	public Rectangle getGorgeWall3();
	public Rectangle getGorgeWall4();
	public Rectangle getGorgeWall5();
	public Rectangle getGorgeWall6();
	public Rectangle getGorgeBottom();
	public Line getGorgeNearRim();
	public Line getGorgeFarRim();
	public int getX();
	public int getWidth();
	public int getHeight();
	public Bridge getGorgeBridge();
}
