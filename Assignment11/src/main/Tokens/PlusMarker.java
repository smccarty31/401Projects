package main.Tokens;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;
@Tags({"Plus Token"})
@PropertyNames({"scannedString"})
@EditablePropertyNames({"scannedString"})
@StructurePattern("Bean Pattern")
public class PlusMarker extends ScannedToken implements Token {

	public PlusMarker(String input){
			super(input);
	}

}