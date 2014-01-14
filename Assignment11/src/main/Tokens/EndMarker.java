package main.Tokens;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;
@Tags({"End Token"})
@PropertyNames({"scannedString"})
@EditablePropertyNames({"scannedString"})
@StructurePattern("Bean Pattern")

public class EndMarker extends ScannedToken implements Token {
	
	public EndMarker(String input){
		super(input);
	}

}
