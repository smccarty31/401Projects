package main.InterpreterParts;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.annotations.Tags;
@Tags("Extra Action Components")


public class InterpreterView {
	static JFrame frame = new JFrame("Command Interpreter");
	static JTextField inputField = new JTextField();
	static JLabel inputLabel = new JLabel("Input:");
	static JPanel inputPanel = new JPanel();
	static JButton updateButton = new JButton("Set Command");
	static JPanel buttonPanel = new JPanel();
	static JButton XButton = new JButton("Move All +10 X");
	static JPanel XPanel = new JPanel();
	static JButton YButton = new JButton("Move All +10 Y");
	static JPanel YPanel = new JPanel();
	
	
 	public InterpreterView(Interpreter interpreter){
		composePanels();
		composeFrame();
		new InterpreterController(interpreter,inputField,updateButton,XButton,YButton);//passes the interpreter object to the Controller object, as well as the button (which it listens for it to be pressed) and the textfield (which it uses to update the view)
	}
	public void composePanels(){
		inputPanel.setLayout(new GridLayout(1,2));
		inputPanel.add(inputLabel);
		inputPanel.add((Component) inputField);
		buttonPanel.setLayout(new GridLayout(1,1));
		buttonPanel.add(updateButton);
		XPanel.setLayout(new GridLayout(1,1));
		XPanel.add(XButton);
		YPanel.setLayout(new GridLayout(1,1));
		YPanel.add(YButton);
		
	}
	public void composeFrame(){
		frame.setLayout(new GridLayout(4,1));
		frame.add(inputPanel);
		frame.add(buttonPanel);
		frame.add(XButton);
		frame.add(YButton);
		frame.setSize(400, 300);
		frame.setLocation(500, 0);
		frame.setVisible(true);
	}

}
