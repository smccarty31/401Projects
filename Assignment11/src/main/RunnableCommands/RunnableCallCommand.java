package main.RunnableCommands;

import util.annotations.Tags;
import main.Structures.Table;
@Tags("Call")
public class RunnableCallCommand implements Runnable {
	private Table<Runnable> commandTable;
	private String commandName;
	
	public RunnableCallCommand(String name, Table<Runnable> environment){
		commandName = name;
		commandTable = environment;
	}
	
	public void run(){
		Runnable command;
		command = commandTable.get(commandName);
		command.run();
	}


}
