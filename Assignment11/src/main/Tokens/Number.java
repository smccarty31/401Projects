package main.Tokens;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;
import util.annotations.Visible;
@Tags({"Number Token"})
@PropertyNames({"scannedString", "integerValue"})
@EditablePropertyNames({"scannedString"})
@StructurePattern("Bean Pattern")
public class Number extends ScannedToken implements IntValue {

	private int integerValue;
	
	public Number(String input){
		super(input);
	}
@Visible(false)
	public int getIntegerValue(){
			integerValue = Integer.parseInt(scannedString);
			return integerValue;		
	}
}