package main.AvatarParts;

import java.awt.Color;

import main.Atomics.MyLocatableIntf;


public interface StringShape extends MyLocatableIntf {//Simple outline for String shapes such as the one the Avatars have

	public Color getColor();
	public void setText(String newText);
	public String getText();
}
