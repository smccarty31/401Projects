package main.RunnableCommands;

import util.annotations.Tags;
import main.Structures.Table;
@Tags("Define")
public class RunnableDefineCommand implements Runnable {
	private Table<Runnable> commandTable;
	private String commandName;
	private Runnable commandBody;
	
	public RunnableDefineCommand(String name, Runnable body, Table<Runnable> environment){
		commandName = name;
		commandBody = body;
		commandTable = environment;
	}
	
	public void run(){
		commandTable.put(commandName, commandBody);
	}


}


