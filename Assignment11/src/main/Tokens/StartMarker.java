package main.Tokens;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;
@Tags({"Start Token"})
@PropertyNames({"scannedString"})
@EditablePropertyNames({"scannedString"})
@StructurePattern("Bean Pattern")
public class StartMarker extends ScannedToken implements Token {

	public StartMarker(String input) {
		super(input);
	}
	
}
