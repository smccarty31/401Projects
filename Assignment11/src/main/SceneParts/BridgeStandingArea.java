package main.SceneParts;

import java.awt.BasicStroke;
import java.awt.Stroke;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.OVAL_PATTERN)
@PropertyNames({"x","y","height","width","stroke"})


public class BridgeStandingArea implements StandingArea {
		
		private int x,y,height,width;
		private Stroke stroke = new BasicStroke(4.5f);
		
		public BridgeStandingArea(int initX,int initY,int initHeight, int initWidth){
			x = initX;
			y = initY;
			height = initHeight;
			width = initWidth;
		}
		
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	public Stroke getStroke() {
		return stroke;
	}

}
