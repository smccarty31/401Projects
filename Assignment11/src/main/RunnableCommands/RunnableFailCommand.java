package main.RunnableCommands;

import util.annotations.Tags;
import main.BridgeScene.Scene;

@Tags("Fail Command")

public class RunnableFailCommand implements Runnable {
	private Scene scene;
	
	public RunnableFailCommand(Scene theScene){
		scene = theScene;
	}

	public void run() {
		scene.failed();
	}

}
