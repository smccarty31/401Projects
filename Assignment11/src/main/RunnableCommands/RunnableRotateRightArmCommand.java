package main.RunnableCommands;

import util.annotations.Tags;
import main.SceneParts.Avatar;
@Tags("Rotate Right Arm")

public class RunnableRotateRightArmCommand implements Runnable {
	private Avatar avatar;
	private int rotation;
	public RunnableRotateRightArmCommand(Avatar theAvatar, int amount){
		avatar = theAvatar;
		rotation = amount;
	}
	public void run(){
		avatar.getTwoArms().getRightLine().rotate(rotation);
		avatar.connect();
	}
}
