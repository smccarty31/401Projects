package main.RunnableCommands;

import util.annotations.Tags;

@Tags("Repeat")

public class RunnableRepeatCommand implements Runnable {
	private int repeatCount;
	private Runnable repeatable;
		
	public RunnableRepeatCommand(Runnable command, int repetitions){
		repeatCount = repetitions;
		repeatable = command;
	}
	
	public void run() {
		for (int i=0; i<repeatCount;i++){
			repeatable.run();
		}
	}

}
