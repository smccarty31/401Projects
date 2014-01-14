package main.Tokens;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;
@Tags({"Quote Token"})
@PropertyNames({"scannedString"})
@EditablePropertyNames({"scannedString"})
@StructurePattern("Bean Pattern")
public class QuotedString extends ScannedToken implements Token {

	public QuotedString(String input) {
		super(input);
	}

	
	
}
