package main.SceneParts;

import java.awt.Color;
import util.annotations.Position;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;

import main.AvatarParts.Line;
import main.AvatarParts.RotatingLine;
@StructurePattern("Bean Pattern")
@PropertyNames({"gorgeBridge","gorgeNearRim","gorgeFarRim","gorgeWall1","gorgeWall2","gorgeWall3","gorgeWall4","gorgeWall5","gorgeWall6","gorgeBottom","width","x","height"})
public class BridgeGorge implements Gorge {
	
	private Rectangle gorgeWall1, gorgeWall2, gorgeWall3, gorgeWall4, gorgeWall5, gorgeWall6, gorgeBottom;
	private Line gorgeNearRim,gorgeFarRim;
	private Bridge gorgeBridge;
	private int width,x,height;
	
	
	public BridgeGorge(int initX,int initY, int initHeight, int initWidth, int bridgeY){
		x = initX;
		width = initWidth;
		height = initHeight;
		gorgeWall1 = new SceneRectangle(initX,initY,initHeight,initWidth*1/10, new Color (240,240,240));
		gorgeWall2 = new SceneRectangle(initX+initWidth*1/10,initY,initHeight,initWidth*1/10, new Color (200,200,200));
		gorgeWall3 = new SceneRectangle(initX+initWidth*2/10,initY,initHeight,initWidth*1/10, new Color (160,160,160));
		gorgeWall4 = new SceneRectangle(initX+initWidth*3/10,initY,initHeight,initWidth*1/10, new Color (120,120,120));
		gorgeWall5 = new SceneRectangle(initX+initWidth*4/10,initY,initHeight,initWidth*1/10, new Color (80,80,80));
		gorgeWall6 = new SceneRectangle(initX+initWidth*5/10,initY,initHeight,initWidth*1/10, new Color (40,40,40));
		gorgeBottom = new SceneRectangle(initX+3*initWidth/5,initY,initHeight,initWidth*2/5, Color.BLACK);
		gorgeNearRim = new RotatingLine(initX,initY,initHeight,0);
		gorgeFarRim = new RotatingLine(initX+initWidth,initY,initHeight,0);
		gorgeBridge = new GorgeBridge(initX,bridgeY,initHeight/10,initWidth);
	}
	public Rectangle getGorgeWall1(){
		return gorgeWall1;
	}
	public Rectangle getGorgeWall2(){
		return gorgeWall2;
	}
	public Rectangle getGorgeWall3(){
		return gorgeWall3;
	}
	public Rectangle getGorgeWall4(){
		return gorgeWall4;
	}
	public Rectangle getGorgeWall5(){
		return gorgeWall5;
	}
	public Rectangle getGorgeWall6(){
		return gorgeWall6;
	}
	public Rectangle getGorgeBottom(){
		return gorgeBottom;
	}
	public Line getGorgeNearRim(){
		return gorgeNearRim;
	}
	public Line getGorgeFarRim(){
		return gorgeFarRim;
	}
@Position(0)
	public Bridge getGorgeBridge(){
		return gorgeBridge;
	}
	public int getX(){
		return x;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
}
