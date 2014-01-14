package main.Tokens;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;
@Tags({"Minus Token"})
@PropertyNames({"scannedString"})
@EditablePropertyNames({"scannedString"})
@StructurePattern("Bean Pattern")
public class MinusMarker extends ScannedToken implements Token {

	public MinusMarker(String input) {
		super(input);
	}
}


