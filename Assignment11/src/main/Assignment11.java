package main; 
import java.awt.Color;
import java.awt.Component;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import util.misc.ThreadSupport;

import main.BridgeScene.BridgeScene;
import main.BridgeScene.Scene;
import main.InterpreterParts.ABroadcastingClearanceManager;
import main.InterpreterParts.BroadcastingClearanceManager;
import main.InterpreterParts.CommandInterpreter;
import main.InterpreterParts.Interpreter;
import main.InterpreterParts.InterpreterView;
import main.Tokens.ScannerIntf;
import main.Tokens.TokenInput;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class Assignment11 { //Notes: Wait,ProceedAll,Sleep commands cause OE to freeze when entered in the interpreter
							
	
	
 
	public static void main(String[] args){

		Scene bridgeScene = new BridgeScene(5,5);
		PropertyChangeListener consoleSceneView = new ConsoleSceneView(bridgeScene);
		ScenePainter view = new BridgeScenePainter(bridgeScene); //scene is passed as an argument to register the view as a listener of the scene model
		ScannerIntf scanner = new TokenInput();
		BroadcastingClearanceManager clearanceManager = new ABroadcastingClearanceManager();
		OEFrame CMoeframe = ObjectEditor.edit(clearanceManager);		
		Interpreter interpreter = new CommandInterpreter(clearanceManager,scanner, bridgeScene);//instantiate a standard interpreter object that takes arguments to control the scene model
		OEFrame InterpOEframe = ObjectEditor.edit(interpreter);
		JFrame frame = new JFrame("A Scene View");
		frame.add((Component) view);//adds the scene view component to a JFrame
		frame.setSize(1000, 500);
		frame.setLocation(0, 0);
		frame.setVisible(true);
		
		
		interpreter.animateArthur();//displays synchronized animation
		interpreter.animateArthur();
		interpreter.animateLancelot();
		clearanceManager.waitForProceed();//waits for button press to reset avatars
		
		bridgeScene.getArthur().move(5, 5);
		bridgeScene.getLancelot().move(5, 150);
				
		interpreter.waitingAnimateArthur();//waits for proceedAll button press to display waiting animations
		interpreter.waitingAnimateGalahad();
		interpreter.waitingAnimateLancelot();
		interpreter.waitingAnimateRobin();
		
		interpreter.setCommand("define callList { move arthur 5 5 move galahad 5 5 move galahad 5 5 }");
		interpreter.setCommand(" repeat 5 call callList");//press proceed to move through commands
		interpreter.setCommand("approach arthur");
		interpreter.setCommand("say \"what is your quest\"");
		interpreter.setCommand("say \"To seek the holy grail\"");
		interpreter.setCommand("passed");
		interpreter.setCommand("{ repeat 3 { move lancelot 10 0 move robin 10 0 } move arthur 10 0 }"); 
		interpreter.setCommand("rotateLeftArm galahad 3 rotateRightArm lancelot 3");
		interpreter.setCommand("define moveTwo { move arthur 2 2 move galahad 2 2 }");
		interpreter.setCommand("repeat 5 call moveTwo");
		interpreter.setCommand("thread moveTwo");
	}

}