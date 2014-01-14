package main.RunnableCommands;

import util.annotations.Tags;
import main.SceneParts.Avatar;

@Tags("Move Command")

public class RunnableMoveCommand implements Runnable {
		private Avatar avatar;
		private int x,y;
	
	public RunnableMoveCommand(Avatar theAvatar, int xMove, int yMove){
		avatar = theAvatar;
		x = xMove;
		y = yMove;
	}

	public void run() {
		avatar.move(avatar.getAHead().getX()+x,avatar.getAHead().getY()+y);	
	}

}
