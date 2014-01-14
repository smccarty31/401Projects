package main.Atomics;
import util.annotations.Tags;
@Tags("BoundedShape")

public abstract class MyBoundedShape extends MyLocatable implements MyBoundedShapeIntf {

	protected int height;
	protected int width;
	
	public MyBoundedShape(int initX, int initY, int initHeight, int initWidth){
		super(initX,initY);
		height = initHeight;
		width = initWidth;
	}

	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}

}
