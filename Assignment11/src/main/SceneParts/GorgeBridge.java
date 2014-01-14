package main.SceneParts;

import java.awt.Color;

import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
@StructurePattern("Bean Pattern")
@PropertyNames({"bridgeSlat1","bridgeSlat2","bridgeSlat3","bridgeSlat4","bridgeSlat5","bridgeSlat6","bridgeSlat7","upperBridgeCrossbeam","lowerBridgeCrossbeam"})
public class GorgeBridge implements Bridge {
	
	private Rectangle bridgeSlat1, bridgeSlat2, bridgeSlat3, bridgeSlat4, bridgeSlat5, bridgeSlat6, bridgeSlat7;
	private Rectangle upperBridgeCrossbeam, lowerBridgeCrossbeam;
	
	public GorgeBridge(int initX,int initY, int initHeight, int initWidth){
		upperBridgeCrossbeam = new SceneRectangle(initX,initY,initHeight/4,initWidth, new Color(139,69,19));
		lowerBridgeCrossbeam = new SceneRectangle(initX,initY+(3*initHeight/4),initHeight/4,initWidth, new Color(139,69,19));
		bridgeSlat1 = new SceneRectangle(initX+(initWidth/15),initY,initHeight,initWidth/15,new Color(139,69,19));
		bridgeSlat2 = new SceneRectangle(initX+(3*initWidth/15),initY,initHeight,initWidth/15,new Color(139,69,19));
		bridgeSlat3 = new SceneRectangle(initX+(5*initWidth/15),initY,initHeight,initWidth/15,new Color(139,69,19));
		bridgeSlat4 = new SceneRectangle(initX+(7*initWidth/15),initY,initHeight,initWidth/15,new Color(139,69,19));
		bridgeSlat5 = new SceneRectangle(initX+(9*initWidth/15),initY,initHeight,initWidth/15,new Color(139,69,19));
		bridgeSlat6 = new SceneRectangle(initX+(11*initWidth/15),initY,initHeight,initWidth/15,new Color(139,69,19));
		bridgeSlat7 = new SceneRectangle(initX+(13*initWidth/15),initY,initHeight,initWidth/15,new Color(139,69,19));
	}
	public Rectangle getBridgeSlat1(){
		return bridgeSlat1;
	}
	public Rectangle getBridgeSlat2(){
		return bridgeSlat2;
	}
	public Rectangle getBridgeSlat3(){
		return bridgeSlat3;
	}
	public Rectangle getBridgeSlat4(){
		return bridgeSlat4;
	}
	public Rectangle getBridgeSlat5(){
		return bridgeSlat5;
	}
	public Rectangle getBridgeSlat6(){
		return bridgeSlat6;
	}
	public Rectangle getBridgeSlat7(){
		return bridgeSlat7;
	}
	public Rectangle getUpperBridgeCrossbeam(){
		return upperBridgeCrossbeam;
	}
	public Rectangle getLowerBridgeCrossbeam(){
		return lowerBridgeCrossbeam;
	}
}
