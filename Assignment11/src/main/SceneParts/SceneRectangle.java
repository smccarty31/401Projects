package main.SceneParts;
import java.awt.Color;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.RECTANGLE_PATTERN)
@PropertyNames({"x","y","height","width","Filled","color"})

public class SceneRectangle implements Rectangle {
	
	private int x,y,height,width;
	private Color color;
	private boolean Filled = true;
	
	public SceneRectangle(int initX, int initY, int initHeight, int initWidth, Color initColor ){
		x=initX;
		y=initY;
		height=initHeight;
		width=initWidth;
		color = initColor;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getHeight(){
		return height;
	}
	public int getWidth(){
		return width;
	}
	public Color getColor(){
		return color;
	}
	public boolean getFilled(){
		return Filled;
	}
}
