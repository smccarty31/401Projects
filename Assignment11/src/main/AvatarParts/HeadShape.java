package main.AvatarParts;
import java.beans.PropertyChangeEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import main.Atomics.MyLocatable;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.IMAGE_PATTERN)
@PropertyNames({"x","y","height","width","imageFileName"})
@EditablePropertyNames({"x","y","imageFileName"})

public class HeadShape extends MyLocatable implements ImageShape {//made a subclass of Locatable rather than BoundedShape because
																//height/width are determined by image not by constructor/setters
	private int height, width;
	private String imageFileName;
	
	public HeadShape(String anImageFileName, int initX, int initY){
		super(initX,initY);
		imageFileName = anImageFileName;
		Icon icon = new ImageIcon(imageFileName);
		height = icon.getIconHeight();
		width = icon.getIconWidth();
	}

	public int getHeight(){
		return height;
	}
	public int getWidth(){
		return width;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String newImage){
		imageFileName = newImage;
		Icon icon = new ImageIcon(imageFileName);//uses the Icon library shown in the lecture
		int oldHeight = height;
		height = icon.getIconHeight();//PPT to calculate the height and width of the image used
		if (propertyListenerSupport != null && (oldHeight != height)){//second condition prevents unnecessary notifications
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "height", oldHeight, height));
		}
		int oldWidth = width;
		width = icon.getIconWidth();//for the head of the avatar
		if (propertyListenerSupport != null && (oldWidth != width)){//second condition prevents unnecessary notifications
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "width", oldWidth, width));
		}
	}
}
