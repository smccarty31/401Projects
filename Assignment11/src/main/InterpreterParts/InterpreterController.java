package main.InterpreterParts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;


public class InterpreterController implements ActionListener{
	JTextField input;
	Interpreter interpreter;
	JButton button1, button2, button3;
	public InterpreterController(Interpreter theInterpreter, JTextField theInput, JButton theButton1, JButton theButton2, JButton theButton3){
		input = theInput;
		interpreter = theInterpreter;
		button1 = theButton1;
		button1.addActionListener(this);//registers the controller as a listener for the GUI, being notified when the button is pushed		
		button2 = theButton2;
		button3 = theButton3;
		button2.addActionListener(this);
		button3.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent event) {//Controller calls the interpreter's setter if the
		JButton source = (JButton) event.getSource();//GUI button is pressed and updates it with the command
		String text = input.getText();//entered in the text field
		if (source == button1){
			interpreter.setCommand(text);
		}
		else if (source == button2){
			interpreter.getBridgeScene().getArthur().move(interpreter.getBridgeScene().getArthur().getAHead().getX()+10, interpreter.getBridgeScene().getArthur().getAHead().getY());
			interpreter.getBridgeScene().getLancelot().move(interpreter.getBridgeScene().getLancelot().getAHead().getX()+10, interpreter.getBridgeScene().getLancelot().getAHead().getY());
			interpreter.getBridgeScene().getRobin().move(interpreter.getBridgeScene().getRobin().getAHead().getX()+10, interpreter.getBridgeScene().getRobin().getAHead().getY());
			interpreter.getBridgeScene().getGalahad().move(interpreter.getBridgeScene().getGalahad().getAHead().getX()+10, interpreter.getBridgeScene().getGalahad().getAHead().getY());	
		}
		else if (source == button3){
			interpreter.getBridgeScene().getArthur().move(interpreter.getBridgeScene().getArthur().getAHead().getX(), interpreter.getBridgeScene().getArthur().getAHead().getY()+10);
			interpreter.getBridgeScene().getLancelot().move(interpreter.getBridgeScene().getLancelot().getAHead().getX(), interpreter.getBridgeScene().getLancelot().getAHead().getY()+10);
			interpreter.getBridgeScene().getRobin().move(interpreter.getBridgeScene().getRobin().getAHead().getX(), interpreter.getBridgeScene().getRobin().getAHead().getY()+10);
			interpreter.getBridgeScene().getGalahad().move(interpreter.getBridgeScene().getGalahad().getAHead().getX(), interpreter.getBridgeScene().getGalahad().getAHead().getY()+10);	
}
	}

}
