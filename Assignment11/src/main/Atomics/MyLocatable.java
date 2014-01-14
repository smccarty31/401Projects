package main.Atomics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import main.APropertyListenerSupport;
import main.PropertyListenerSupport;

import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.Tags;
@Tags("Locatable")

public abstract class MyLocatable implements MyLocatableIntf {
	
	protected int x,y;
	protected PropertyListenerSupport propertyListenerSupport = new APropertyListenerSupport();
	
	public MyLocatable(int initX,int initY){
		x=initX;
		y=initY;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int newX) {
		int oldX = x;
			x = newX;
		if ((propertyListenerSupport != null) && (oldX != newX)){//second condition prevents unnecessary notifications
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "x", oldX, newX));
		}
	}
			
	public int getY() {
		return y;
	}
	public void setY(int newY) {
		int oldY = y;
			y = newY;
			if ((propertyListenerSupport != null) && (oldY != newY)){//second condition prevents unnecessary notifications
				propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "y", oldY, newY));
			}
		}

@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyListenerSupport.add(listener);
	}
}
