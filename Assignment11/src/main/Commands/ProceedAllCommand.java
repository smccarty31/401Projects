package main.Commands;
import main.Tokens.Token;
import main.Tokens.Word;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;
@Tags({"proceedAll"})
@PropertyNames({"scannedString", "lowercaseString"})
@EditablePropertyNames({"scannedString"})
@StructurePattern("Bean Pattern")
public class ProceedAllCommand extends Word implements Token {

	public ProceedAllCommand(String input) {
		super(input);
	}
}
