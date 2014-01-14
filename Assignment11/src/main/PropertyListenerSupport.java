package main;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public interface PropertyListenerSupport {//code taken from lecture
    
	public int size();
    public PropertyChangeListener get(int index); 
    public void add(PropertyChangeListener l);
    public void notifyAllListeners(PropertyChangeEvent event);
    public void remove(PropertyChangeListener element) ;
    public int indexOf(PropertyChangeListener element);
    public boolean member(PropertyChangeListener element);
	}

