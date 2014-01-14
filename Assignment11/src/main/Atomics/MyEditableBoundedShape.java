package main.Atomics;

import java.beans.PropertyChangeEvent;


public abstract class MyEditableBoundedShape extends MyBoundedShape implements
		MyBoundedShapeIntf {
	
	public MyEditableBoundedShape(int initX, int initY, int initHeight,
			int initWidth) {
		super(initX, initY, initHeight, initWidth);
			}
	public void setHeight(int newHeight){
		int oldHeight = height;
		height = newHeight;
		if ((propertyListenerSupport != null) && (oldHeight != newHeight)){//second condition prevents unnecessary notifications
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "height", oldHeight, newHeight));
		}
	}
	public void setWidth(int newWidth){
		int oldWidth = width;
		width = newWidth;
		if ((propertyListenerSupport != null) && (oldWidth != newWidth)){//second condition prevents unnecessary notifications
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "width", oldWidth, newWidth));
		}
	}

}
