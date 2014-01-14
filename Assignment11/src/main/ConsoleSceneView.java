package main;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import util.annotations.Tags;

import main.BridgeScene.Scene;
@Tags("Console Scene View")
public class ConsoleSceneView implements PropertyChangeListener {
	
	public ConsoleSceneView(Scene scene){
		//scene.addListener(this); commented out older property changes to make precondition changes more visible
		scene.addPropertyChangeListener(this);
	}
	public void propertyChange(PropertyChangeEvent arg0) {
		System.out.print("Property Name: " + arg0.getPropertyName());
		System.out.print(" Method: " + arg0.getOldValue());
		System.out.println(" New Precondition Value: " + arg0.getNewValue());
	}
	

}
