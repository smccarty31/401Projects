package main.RunnableCommands;

import java.util.ArrayList;

import util.annotations.Tags;

@Tags("Command List")

public class CommandListCommand implements RunnableList {
	private ArrayList<Runnable> commands = new ArrayList<Runnable>();
	
	public CommandListCommand(Runnable command){
		commands.add(command);						
	}
	public void addCommand(Runnable command){
		commands.add(command);
	}
	public void run() {
		for (int i=0; i<commands.size();i++){
			commands.get(i).run();			
		}
	}

}
