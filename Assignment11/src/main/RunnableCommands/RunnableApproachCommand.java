package main.RunnableCommands;

import util.annotations.Tags;
import main.BridgeScene.Scene;
import main.SceneParts.Avatar;

@Tags("Approach Command")

public class RunnableApproachCommand implements Runnable {
	private Avatar avatar;
	private Scene scene;
	
	public RunnableApproachCommand(Scene theScene, Avatar theAvatar){
		avatar = theAvatar;
		scene = theScene;
	}
	
	public void run() {
		scene.approach(avatar);		
	}

}
