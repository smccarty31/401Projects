package main.RunnableCommands;

import util.annotations.Tags;
import main.InterpreterParts.BroadcastingClearanceManager;
@Tags("Wait")
public class RunnableWaitCommand implements Runnable {
	BroadcastingClearanceManager bcm;
	
	public RunnableWaitCommand(BroadcastingClearanceManager theBCM){
		bcm = theBCM;
	}
	
	public void run(){
		bcm.waitForProceed();
	}
}
