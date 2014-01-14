package main.InterpreterParts;
import util.annotations.ComponentHeight;
import util.annotations.ComponentWidth;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Row;
import util.annotations.StructurePattern;
import util.annotations.Tags;
import util.annotations.Visible;
import main.BridgeScene.Scene;
import main.RunnableCommands.CommandListCommand;
import main.RunnableCommands.RunnableApproachCommand;
import main.RunnableCommands.RunnableCallCommand;
import main.RunnableCommands.RunnableDefineCommand;
import main.RunnableCommands.RunnableFailCommand;
import main.RunnableCommands.RunnableList;
import main.RunnableCommands.RunnableMoveCommand;
import main.RunnableCommands.RunnablePassCommand;
import main.RunnableCommands.RunnableProceedAllCommand;
import main.RunnableCommands.RunnableRepeatCommand;
import main.RunnableCommands.RunnableRotateLeftArmCommand;
import main.RunnableCommands.RunnableRotateRightArmCommand;
import main.RunnableCommands.RunnableSayCommand;
import main.RunnableCommands.RunnableSleepCommand;
import main.RunnableCommands.RunnableThreadCommand;
import main.RunnableCommands.RunnableWaitCommand;
import main.SceneParts.Avatar;
import main.Structures.ProjectTable;
import main.Structures.Table;
import main.Tokens.EndMarker;
import main.Tokens.IntValue;
import main.Tokens.MinusMarker;
import main.Tokens.PlusMarker;
import main.Tokens.QuotedString;
import main.Tokens.ScannerIntf;
import main.Tokens.StartMarker;
import main.Tokens.Token;
import main.Tokens.Word;
import main.Commands.*;
@Tags({"Command Interpreter","Signed Move","Error Resilient"})
@StructurePattern("Bean Pattern")
@PropertyNames({"command","scanner","bridgeScene","avatarTable"})
@EditablePropertyNames({"command"})


public class CommandInterpreter implements Interpreter {
	
	private String command = "";
	private ScannerIntf scanner;
	private Scene bridgeScene;
	private Table<Avatar> avatarTable;
	private Token[] tokens;
	private int tokenIndex;
	private Animator arthurAnimator,lancelotAnimator,galahadAnimator,robinAnimator;
	private BroadcastingClearanceManager clearanceManager;
	private Table<Runnable> commandTable;
	
	public CommandInterpreter(BroadcastingClearanceManager theClearanceManager, ScannerIntf initScanner, Scene initBridgeScene){
		scanner = initScanner;
		bridgeScene = initBridgeScene;
		avatarTable = new ProjectTable<Avatar>();
		commandTable = new ProjectTable<Runnable>();
		avatarTable.put("arthur", bridgeScene.getArthur());//Builds ST for avatar names
		avatarTable.put("galahad", bridgeScene.getGalahad());
		avatarTable.put("lancelot", bridgeScene.getLancelot());
		avatarTable.put("robin", bridgeScene.getRobin());
		avatarTable.put("guard", bridgeScene.getGuard());	
		arthurAnimator = new AvatarAnimator(bridgeScene.getArthur(),500);
		galahadAnimator = new AvatarAnimator(bridgeScene.getGalahad(),500);
		lancelotAnimator = new AvatarAnimator(bridgeScene.getLancelot(),500);
		robinAnimator = new AvatarAnimator(bridgeScene.getRobin(),500);
		clearanceManager = theClearanceManager;
	}
@Visible(false)
	public Table<Avatar> getAvatarTable(){
		return avatarTable;
	}
@Row(1)
@ComponentWidth(200)
@ComponentHeight(50)
	public String getCommand() {
		return command;
	}

	public void setCommand(String newCommand) {
		tokenIndex = 0;
		Runnable runnableCommand;
		command = newCommand;
		scanner.setScannerInput(newCommand);
		tokens = scanner.getTokenArray();	
		while (tokenIndex<tokens.length){

		runnableCommand = parseCommand();
		clearanceManager.waitForProceed();
		runnableCommand.run();
	}}
@Tags("Command Parser")
	private Runnable parseCommand(){
		Runnable command;
		if (tokens[tokenIndex] instanceof SayCommand){
			command = parseSay();
			tokenIndex = tokenIndex+2;
			return command;
		}
		else if (tokens[tokenIndex] instanceof MoveCommand){
			command = parseMove();
			return command;
		}
		else if (tokens[tokenIndex] instanceof ApproachCommand){
			command = parseApproach();
			tokenIndex = tokenIndex+2;
			return command;
		}
		else if (tokens[tokenIndex] instanceof PassCommand){
			command = parsePass();
			tokenIndex++;
			return command;
		}
		else if (tokens[tokenIndex] instanceof FailCommand){
			command = parseFail();
			tokenIndex++;
			return command;
		}
		else if (tokens[tokenIndex] instanceof StartMarker){
			command = parseCommandList();
			return command;
		}
		else if (tokens[tokenIndex] instanceof RepeatCommand){
			command = parseRepeat();	
			return command;
		}
		else if (tokens[tokenIndex] instanceof RotateLeftArmCommand){
			command = parseRotateLeftArm();
			return command;
		}
		else if (tokens[tokenIndex] instanceof RotateRightArmCommand){
			command = parseRotateRightArm();
			return command;
		}
		else if (tokens[tokenIndex] instanceof SleepCommand){
			command = parseSleep();
			return command;
		}
		else if (tokens[tokenIndex] instanceof DefineCommand){
			command = parseDefine();
			return command;
		}
		else if (tokens[tokenIndex] instanceof CallCommand){
			command = parseCall();
			return command;
		}
		else if (tokens[tokenIndex] instanceof ThreadCommand){
			command = parseThread();
			return command;
		}
		else if (tokens[tokenIndex] instanceof ProceedAllCommand){
			command = parseProceedAll();
			return command;
		}
		else if (tokens[tokenIndex] instanceof WaitCommand){
			command = parseWait();
			return command;
		}
		command = new RunnableMoveCommand(bridgeScene.getArthur(),0,0);
		return command;
	}

@Tags("Proceed All Parser")
	private Runnable parseProceedAll(){
		tokenIndex++;
		Runnable proceedAllCommand = new RunnableProceedAllCommand(clearanceManager);
		return proceedAllCommand;
	}
@Tags("Wait Parser")
	private Runnable parseWait(){
		tokenIndex++;
		Runnable waitCommand = new RunnableWaitCommand(clearanceManager);
		return waitCommand;
	}
@Tags("Thread Parser")
	private Runnable parseThread(){
		tokenIndex++;
		Runnable threadCommand = new RunnableThreadCommand(tokens[tokenIndex].getScannedString(), commandTable);
		tokenIndex++;
		return threadCommand;
	}
@Tags("Call Parser")	
	private Runnable parseCall(){
		tokenIndex++;
		Runnable callCommand = new RunnableCallCommand(tokens[tokenIndex].getScannedString(),commandTable);
		tokenIndex++;
		return callCommand;
	}
@Tags("Define Parser")
	private Runnable parseDefine(){
		tokenIndex++;
		String definedName = tokens[tokenIndex].getScannedString();
		tokenIndex++;
		Runnable definedCommand = parseCommand();
		return new RunnableDefineCommand(definedName,definedCommand,commandTable);
	}
@Tags("Sleep Parser")	
	private Runnable parseSleep(){
		tokenIndex++;
		int sleepTime = ((IntValue) tokens[tokenIndex]).getIntegerValue();
		tokenIndex++;
		return new RunnableSleepCommand(sleepTime);
	}
@Tags("Rotate Right Arm Parser")
	private Runnable parseRotateRightArm(){
		tokenIndex++;
		Avatar rotatingAvatar = avatarTable.get(tokens[tokenIndex].getScannedString());
		tokenIndex++;
		int rotation = ((IntValue) tokens[tokenIndex]).getIntegerValue();
		tokenIndex++;
		return new RunnableRotateRightArmCommand(rotatingAvatar,rotation);
	}
@Tags("Rotate Left Arm Parser")	
	private Runnable parseRotateLeftArm(){
		tokenIndex++;
		Avatar rotatingAvatar = avatarTable.get(tokens[tokenIndex].getScannedString());
		tokenIndex++;
		int rotation = ((IntValue) tokens[tokenIndex]).getIntegerValue();
		tokenIndex++;
		return new RunnableRotateLeftArmCommand(rotatingAvatar,rotation);
	}
	
@Tags("Command List Parser")
	private Runnable parseCommandList(){
	tokenIndex++;
	RunnableList commandList = new CommandListCommand(parseCommand());
	while (!(tokens[tokenIndex] instanceof EndMarker)){
		commandList.addCommand(parseCommand());}
	tokenIndex++;
	return commandList;
	}

@Tags("Repeat Parser")		
	private Runnable parseRepeat(){
		tokenIndex++;
		int repetitions = ((IntValue) tokens[tokenIndex]).getIntegerValue();
		tokenIndex++;
		Runnable repeatedCommand = parseCommand();
		return new RunnableRepeatCommand(repeatedCommand,repetitions);
	}
	
@Tags("Fail Parser")
	private Runnable parseFail(){
	return new RunnableFailCommand(bridgeScene);
}

@Tags("Pass Parser")
	private Runnable parsePass(){
	return new RunnablePassCommand(bridgeScene);
}

@Tags("Approach Parser")
	private Runnable parseApproach(){
	Avatar approachingAvatar = avatarTable.get(tokens[tokenIndex+1].getScannedString());
	return new RunnableApproachCommand(bridgeScene,approachingAvatar);
}

@Tags("Say Parser")
	private Runnable parseSay(){
	return new RunnableSayCommand(bridgeScene, tokens[tokenIndex+1].getScannedString());
}

@Tags("Move Parser")
	private Runnable parseMove(){
	Avatar movingAvatar = avatarTable.get(tokens[tokenIndex+1].getScannedString());
	Runnable command = new RunnableMoveCommand(movingAvatar, ((IntValue) tokens[tokenIndex+2]).getIntegerValue(), ((IntValue) tokens[tokenIndex+3]).getIntegerValue());
	tokenIndex = tokenIndex+4;
	return command;
}

@Visible(false)
	public ScannerIntf getScanner() {
		return scanner;
	}
@Visible(false)
	public Scene getBridgeScene() {
		return bridgeScene;
	}

@Tags("Asynchronous Arthur")
	public void animateArthur(){
	Runnable arthurAnimation = new AvatarAnimationCommand(arthurAnimator);
	Thread arthurThread = new Thread(arthurAnimation);
	arthurThread.start();
	}
@Tags("Asynchronous Lancelot")
	public void animateLancelot(){
	Runnable lancelotAnimation = new AvatarAnimationCommand(lancelotAnimator);
	Thread lancelotThread = new Thread(lancelotAnimation);
	lancelotThread.start();	
}
@Tags("Asynchronous Galahad")
	public void animateGalahad(){
	Runnable galahadAnimation = new AvatarAnimationCommand(galahadAnimator);
	Thread galahadThread = new Thread(galahadAnimation);
	galahadThread.start();
}
@Tags("Asynchronous Robin")
	public void animateRobin(){
	Runnable robinAnimation = new AvatarAnimationCommand(robinAnimator);
	Thread robinThread = new Thread(robinAnimation);
	robinThread.start();
}
@Tags("Waiting Arthur")
	public void waitingAnimateArthur(){
	Runnable waitingArthurAnimation = new AvatarAnimationCommand(clearanceManager,arthurAnimator);
	Thread arthurThread = new Thread(waitingArthurAnimation);
	arthurThread.start();
	}
@Tags("Waiting Lancelot")
	public void waitingAnimateLancelot(){
	Runnable waitingLancelotAnimation = new AvatarAnimationCommand(clearanceManager,lancelotAnimator);
	Thread lancelotThread = new Thread(waitingLancelotAnimation);
	lancelotThread.start();		
	}
@Tags("Waiting Robin")
	public void waitingAnimateRobin(){
	Runnable waitingRobinAnimation = new AvatarAnimationCommand(clearanceManager,robinAnimator);
	Thread robinThread = new Thread(waitingRobinAnimation);
	robinThread.start();	
	}
@Tags("Waiting Galahad")
	public void waitingAnimateGalahad(){
	Runnable waitingGalahadAnimation = new AvatarAnimationCommand(clearanceManager,galahadAnimator);
	Thread galahadThread = new Thread(waitingGalahadAnimation);
	galahadThread.start();
	}
@Tags("Start Animation")
	public void startAnimate(){
	clearanceManager.proceedAll();
}

}
