package main.RunnableCommands;

import util.annotations.Tags;
import main.BridgeScene.Scene;

@Tags("Pass Command")

public class RunnablePassCommand implements Runnable {
	private Scene scene;
	
	public RunnablePassCommand(Scene theScene){
		scene = theScene;
	}

	public void run() {
		scene.passed();
	}

}
