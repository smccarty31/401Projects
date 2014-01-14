package main.RunnableCommands;

import util.annotations.Tags;
import main.InterpreterParts.BroadcastingClearanceManager;
@Tags("Proceed")
public class RunnableProceedCommand implements Runnable {
	BroadcastingClearanceManager bcm;
	
	public RunnableProceedCommand(BroadcastingClearanceManager theBCM){
		bcm = theBCM;
	}
	public void run() {
		bcm.proceed();
	}

}
