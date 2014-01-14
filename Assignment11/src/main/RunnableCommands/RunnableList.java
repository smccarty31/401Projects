package main.RunnableCommands;

public interface RunnableList extends Runnable {
	public void addCommand(Runnable command);
}
