package main.AvatarParts;
import java.awt.Color;
import java.beans.PropertyChangeEvent;

import main.Atomics.MyLocatable;

import util.annotations.StructurePattern;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.STRING_PATTERN)
@PropertyNames({"x","y","text","color"})
@EditablePropertyNames({"x","y","text"})
public class AStringShape extends MyLocatable implements StringShape {//Creates string objects with an editable text property
	
	private String text;
	private Color color = Color.BLUE;
	
	public AStringShape(int initX, int initY, String initText){
		super(initX,initY);
		text = initText;
	}
	
	public Color getColor(){
		return color;
	}
	public void setText(String newText){
		String oldText = text;
		text = newText;
		if ((propertyListenerSupport != null) && (newText != oldText)){//second condition prevents unnecessary notifications
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "text", oldText, newText));
		}
	}
	public String getText(){
		return text;
	}
}
