package main.Atomics;

import java.beans.PropertyChangeListener;

import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;
@Tags("Locatable")

public interface MyLocatableIntf extends PropertyListenerRegisterer {
	public int getX();
	public void setX(int newX);
	public int getY();
	public void setY(int newY);
	public void addPropertyChangeListener(PropertyChangeListener listener);
}
