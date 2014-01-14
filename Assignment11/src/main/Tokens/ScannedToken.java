package main.Tokens;

public abstract class ScannedToken implements Token {

	protected String scannedString;
	
	public ScannedToken(){
		scannedString = "default";
	}
	
	public ScannedToken(String input){
		scannedString = input;
	}
	
	public String getScannedString() {
		return scannedString;
	}
	public void setScannedString(String input) {
		scannedString = input;		
	}
	
}
