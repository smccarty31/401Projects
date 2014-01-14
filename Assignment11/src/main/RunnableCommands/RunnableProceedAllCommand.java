package main.RunnableCommands;

import util.annotations.Tags;
import main.InterpreterParts.BroadcastingClearanceManager;
@Tags("Proceed All")
public class RunnableProceedAllCommand implements Runnable {
	BroadcastingClearanceManager bcm;
	
	public RunnableProceedAllCommand(BroadcastingClearanceManager theBCM){
		bcm = theBCM;
	}
	public void run() {
		bcm.proceedAll();
	}

}
