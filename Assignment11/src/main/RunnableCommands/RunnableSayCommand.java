package main.RunnableCommands;

import util.annotations.Tags;
import main.BridgeScene.Scene;
@Tags("Say Command")
public class RunnableSayCommand implements Runnable {
	private String text;
	private Scene scene;
	
	public RunnableSayCommand(Scene theScene, String speech){
		text = speech;
		scene = theScene;
	}
	
	public void run() {
		scene.say(text);
	}

}
