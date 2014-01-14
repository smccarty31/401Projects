package main.AvatarParts;

import main.Atomics.MyLocatableIntf;


public interface ImageShape extends MyLocatableIntf {//Made subinterface of Locatable rather than BoundedShape because 
		public String getImageFileName();			//height/width determined by image not constructor or setters
		public int getHeight();
		public int getWidth();
		public void setImageFileName(String newImage);
		}
		

