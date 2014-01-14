package main.RunnableCommands;

import util.annotations.Tags;
import util.misc.ThreadSupport;
@Tags("Sleep")
public class RunnableSleepCommand implements Runnable {
	private int sleep;
	
	public RunnableSleepCommand(int length){
		sleep = length;
	}

	public void run() {
		ThreadSupport.sleep(sleep);
	}

}
