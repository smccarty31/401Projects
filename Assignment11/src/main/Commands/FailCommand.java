package main.Commands;
import main.Tokens.Token;
import main.Tokens.Word;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;
@Tags({"fail"})
@PropertyNames({"scannedString", "lowercaseString"})
@EditablePropertyNames({"scannedString"})
@StructurePattern("Bean Pattern")
public class FailCommand extends Word implements Token {
	
	public FailCommand(String input){
		super(input);
	}

}
