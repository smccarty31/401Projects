package main.InterpreterParts;

import util.annotations.ComponentWidth;
import util.annotations.Row;
import util.annotations.StructurePattern;
@StructurePattern("No Pattern")
public class ABroadcastingClearanceManager extends AClearanceManager 
                    implements BroadcastingClearanceManager {

    @Row(1)
    @ComponentWidth(100)
    public synchronized void proceedAll() {
    	clearance = true;
        notifyAll();        
    }
}