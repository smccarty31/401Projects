package main.Tokens;
import main.Commands.ApproachCommand;
import main.Commands.CallCommand;
import main.Commands.DefineCommand;
import main.Commands.FailCommand;
import main.Commands.MoveCommand;
import main.Commands.PassCommand;
import main.Commands.ProceedAllCommand;
import main.Commands.RedoCommand;
import main.Commands.RepeatCommand;
import main.Commands.RotateLeftArmCommand;
import main.Commands.RotateRightArmCommand;
import main.Commands.SayCommand;
import main.Commands.SleepCommand;
import main.Commands.ThreadCommand;
import main.Commands.UndoCommand;
import main.Commands.WaitCommand;
import util.annotations.ComponentWidth;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;
@Tags({"Scanner Bean"})
@PropertyNames({"scannerInput", "tokenArray", "errorArray"})
@EditablePropertyNames({"scannerInput"})
@StructurePattern("Bean Pattern")
public class TokenInput implements ScannerIntf {

	private String scannerInput;
	private Token[] largeTokenArray = new Token[100];
	private Token[] tokenArray;
	private String[] largeErrorArray = new String[100];
	private String[] errorArray;
@ComponentWidth(400)	
	public String getScannerInput(){return scannerInput;}
	public void setScannerInput(String input){ //separate the input into Token objects and print their respective outputs
		
		scannerInput = input;
		
		int k = 0;
		while ((scannerInput.charAt(k) == ' ')) { //trim off any leading blank space
			scannerInput = scannerInput.substring(k+1);
			k++;}
		
		k = scannerInput.length();
		
		while (scannerInput.charAt(k-1) == ' '){//trim off blanks at the end of the input
			scannerInput = scannerInput.substring(0,k-1);
			k--;}
		
		
		int tokencount = 0;
		int errorcount = 0;
		for (int i=0; i<scannerInput.length(); i++){

			
			if (scannerInput.charAt(i) == '{'){//the first four conditions initialize a Token object of a single character string
				Token start1 = new StartMarker(scannerInput.substring(i,i+1));//then print the necessary outputs
				largeTokenArray[tokencount] = start1;
				tokencount++;
			}
			
			else if (scannerInput.charAt(i) == '}'){
				Token end1 = new EndMarker(scannerInput.substring(i,i+1));
				largeTokenArray[tokencount] = end1;
				tokencount++;
			}
			
			else if (scannerInput.charAt(i) == '+'){
				Token plus1 = new PlusMarker(scannerInput.substring(i,i+1));
				largeTokenArray[tokencount] = plus1;
				tokencount++;
			} 
			
			else if (scannerInput.charAt(i) == '-'){
				Token minus1 = new MinusMarker(scannerInput.substring(i,i+1));
				largeTokenArray[tokencount] = minus1;
				tokencount++;
			}
			
			else if (scannerInput.charAt(i) == '"'){//the quotation condition scans for a closing quotation
				int quotecharcount = 0;//then initializes a Token object with the substring of the contained text
				int j = i+1;
				while (scannerInput.charAt(j) != '"') {
					quotecharcount++;
					j++;
			    }
				Token quotedstring1 = new QuotedString(scannerInput.substring(i+1,i+quotecharcount+1));//selects substring inside quotations
				largeTokenArray[tokencount] = quotedstring1;
				tokencount++;
				i = i+quotecharcount+1;//i skips over the characters inside the quotation to avoid repetition
			}
			
			else if (Character.isDigit(scannerInput.charAt(i))){//scans for digits, operating based on length and location of the number
				
				if (i == scannerInput.length()-1){//single number final token:assigns last digit to Token if index is length-1
					Token number1 = new Number(scannerInput.substring(i,i+1));
					largeTokenArray[tokencount] = number1;
					tokencount++;
				}
				
				else {
					int j = i+1;
					if (j == scannerInput.length()-1){//double number final token: assigns last two digits to Token if index is length-2
						if (Character.isDigit(scannerInput.charAt(j))){
							Token number1 = new Number(scannerInput.substring(i,j+1));
							largeTokenArray[tokencount] = number1;
							tokencount++;
							i = j;
						}
						else if (scannerInput.charAt(j)=='}'){
							Token number1 = new Number(scannerInput.substring(i,j));
							largeTokenArray[tokencount] = number1;
							tokencount++;
							largeTokenArray[tokencount] = new EndMarker(scannerInput.substring(j, j+1));
							tokencount++;
							i = j;
						}
					}
					
					else{//non-final number tokens and 3+ digit final tokens
						
					if (Character.isDigit(scannerInput.charAt(j)) == false){//single digit non final: if no digits follow the digit
						Token number1 = new Number(scannerInput.substring(i,j));//digit at i is assigned to Token
						largeTokenArray[tokencount] = number1;
						tokencount++;
					}
					
					else {//multiple digits final and non final:counts the number of digits then assigns a substring of that length starting at i
					while (Character.isDigit(scannerInput.charAt(j)) && Character.isDigit(scannerInput.charAt(j+1))){//to the Token
							j++;
							if (j == scannerInput.length()-1){//stop counting if the final character has been reached
								break;}
							}
					
					Token number1 = new Number(scannerInput.substring(i,j+1));
					largeTokenArray[tokencount] = number1;
					tokencount++;
					i = j;}
					
			}}}
			
			else if (Character.isLetter(scannerInput.charAt(i))){ //creates Word tokens if letters are detected, operate in same manner as number token scanning
				
				if (i == scannerInput.length()-1){//single letter final token
					Token word1 = checkWord(scannerInput.substring(i,i+1));
					largeTokenArray[tokencount] = word1;
					tokencount++;
				}
				else {
				int j = i+1;
				if (j ==scannerInput.length()-1){//double letter final token
					Token word1 = checkWord(scannerInput.substring(i,j));
					largeTokenArray[tokencount] = word1;
					tokencount++;
					i = j;
				}
				
				else{//non final tokens and 3+ letter final tokens
					
					if (Character.isLetter(scannerInput.charAt(j)) == false){//single letter token
						Token word1 = checkWord(scannerInput.substring(i,j));
						largeTokenArray[tokencount] = word1;
						tokencount++;
						} 
					
					else {
						while (Character.isLetter(scannerInput.charAt(j)) && Character.isLetter(scannerInput.charAt(j+1))){//operates in the same manner as the identical operation 
								j++;																						//used to scan for number Tokens
								if (j == scannerInput.length()-1){
									break;
									}
								}
								Token word1 = checkWord(scannerInput.substring(i,j+1));
								largeTokenArray[tokencount] = word1;
								tokencount++;
								i = j;}

								}
				}}
			else if (scannerInput.charAt(i) == ' '){//skips over blanks outside of quotations
			}
			else {//stores any errors found as single character strings
				largeErrorArray[errorcount] = scannerInput.substring(i, i+1);
				errorcount++;
			}
		}
		tokenArray = new Token[tokencount];//Compacts the large arrays 
		for (int i=0; i<tokencount; i++){
		tokenArray[i] = largeTokenArray[i];}
		errorArray = new String[errorcount];
		for (int i=0; i<errorcount; i++){
			errorArray[i] = largeErrorArray[i];}
		
	}
	public Token[] getTokenArray() {
		return tokenArray;
	}
	public String[] getErrorArray() {
		return errorArray;
	}
	private Token checkWord(String word){
		if (word.equalsIgnoreCase("call")) {
			Token checkedWord = new CallCommand(word);	
			return checkedWord;}
		else if (word.equalsIgnoreCase("define")){
			Token checkedWord = new DefineCommand(word);
			return checkedWord;}
		else if (word.equalsIgnoreCase("move")){
			Token checkedWord = new MoveCommand(word);
			return checkedWord;}
		else if (word.equalsIgnoreCase("proceedAll")){
			Token checkedWord = new ProceedAllCommand(word);
			return checkedWord;}
		else if (word.equalsIgnoreCase("redo")){
			Token checkedWord = new RedoCommand(word);
			return checkedWord;}
		else if (word.equalsIgnoreCase("repeat")){
			Token checkedWord = new RepeatCommand(word);
			return checkedWord;}
		else if (word.equalsIgnoreCase("rotateLeftArm")){
			Token checkedWord = new RotateLeftArmCommand(word);
			return checkedWord;}
		else if (word.equalsIgnoreCase("rotateRightArm")){
			Token checkedWord = new RotateRightArmCommand(word);
			return checkedWord;}
		else if (word.equalsIgnoreCase("say")){
			Token checkedWord = new SayCommand(word);
			return checkedWord;}
		else if (word.equalsIgnoreCase("sleep")){
			Token checkedWord = new SleepCommand(word);
			return checkedWord;}
		else if (word.equalsIgnoreCase("thread")){
			Token checkedWord = new ThreadCommand(word);
			return checkedWord;}
		else if (word.equalsIgnoreCase("undo")){
			Token checkedWord = new UndoCommand(word);
			return checkedWord;}
		else if (word.equalsIgnoreCase("wait")){
			Token checkedWord = new WaitCommand(word);
			return checkedWord;}
		else if (word.equalsIgnoreCase("approach")){
			Token checkedWord = new ApproachCommand(word);
			return checkedWord;}
		else if (word.equalsIgnoreCase("passed")){
			Token checkedWord = new PassCommand(word);
			return checkedWord;}
		else if (word.equalsIgnoreCase("failed")){
			Token checkedWord = new FailCommand(word);
			return checkedWord;
		}
		else {
			Token checkedWord = new Word(word);
			return checkedWord;
		}
		
	}
}
		
		
		

		