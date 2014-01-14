package main.Commands;
import main.Tokens.Token;
import main.Tokens.Word;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;
@Tags({"repeat"})
@PropertyNames({"scannedString", "lowercaseString"})
@EditablePropertyNames({"scannedString"})
@StructurePattern("Bean Pattern")
public class RepeatCommand extends Word implements Token {

	public RepeatCommand(String input) {
		super(input);
	}

}
