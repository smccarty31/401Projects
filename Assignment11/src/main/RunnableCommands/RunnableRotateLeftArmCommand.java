package main.RunnableCommands;

import util.annotations.Tags;
import main.SceneParts.Avatar;
@Tags("Rotate Left Arm")

public class RunnableRotateLeftArmCommand implements Runnable {
	private Avatar avatar;
	private int rotation;
	public RunnableRotateLeftArmCommand(Avatar theAvatar, int amount){
		avatar = theAvatar;
		rotation = amount;
	}
	public void run(){
		avatar.getTwoArms().getLeftLine().rotate(rotation);
		avatar.connect();
	}
}
