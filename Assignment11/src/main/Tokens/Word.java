package main.Tokens;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;
import util.annotations.Visible;
@Tags({"Word Token"})
@PropertyNames({"scannedString", "lowercaseString"})
@EditablePropertyNames({"scannedString"})
@StructurePattern("Bean Pattern")
public class Word extends ScannedToken implements LowerCase{
	
	private String lowercaseString;
	
	public Word(String input) {
		super(input);
	}
@Visible(false)
	public String getLowerCaseString(){
		lowercaseString = scannedString.toLowerCase();
		return lowercaseString;
	}
}
