package main.RunnableCommands;

import main.Structures.Table;

public class RunnableThreadCommand implements Runnable {
	private String commandName;
	private Table<Runnable> environment; 

	public RunnableThreadCommand(String name, Table<Runnable> theEnvironment){
		commandName = name;
		environment = theEnvironment;
	}
	
	public void run() {
		Runnable command;
		command = environment.get(commandName);
		Thread thread = new Thread(command);
		thread.start();
	}

}
