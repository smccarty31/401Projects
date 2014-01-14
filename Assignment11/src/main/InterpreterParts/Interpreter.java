package main.InterpreterParts;

import main.BridgeScene.Scene;
import main.SceneParts.Avatar;
import main.Structures.Table;
import main.Tokens.ScannerIntf;

public interface Interpreter {
	public String getCommand();
	public Table<Avatar> getAvatarTable();
	public void setCommand(String newCommand);
	public ScannerIntf getScanner();
	public Scene getBridgeScene();
	public void animateArthur();
	public void animateLancelot();
	public void animateRobin();
	public void animateGalahad();
	public void waitingAnimateArthur();
	public void waitingAnimateLancelot();
	public void waitingAnimateGalahad();
	public void waitingAnimateRobin();
	public void startAnimate();
}
