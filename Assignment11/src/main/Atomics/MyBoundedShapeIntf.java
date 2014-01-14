package main.Atomics;
import util.annotations.Tags;
@Tags("BoundedShape")

public interface MyBoundedShapeIntf extends MyLocatableIntf {
	public int getHeight();
	public int getWidth();
}
