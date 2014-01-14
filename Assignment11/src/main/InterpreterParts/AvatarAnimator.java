package main.InterpreterParts;

import main.SceneParts.Avatar;
import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags("animator")
public class AvatarAnimator implements Animator {
	private int delay;
	private Avatar avatar;
	
	public AvatarAnimator(Avatar theAvatar, int time){
		avatar = theAvatar;
		delay = time/2;
	}
	
	public synchronized void animate(){
		boolean bent=false;
		for (int i=0;i<14;i++){
		avatar.move(avatar.getAHead().getX()+10, avatar.getAHead().getY());
			if (bent){
			avatar.getTwoArms().rotate(-Math.PI/10);
			avatar.getTwoLegs().rotate(-Math.PI/10);
			avatar.connect();
			bent = false;
		}
			else{
			avatar.getTwoArms().rotate(Math.PI/10);
			avatar.getTwoLegs().rotate(Math.PI/10);
			avatar.connect();
			bent = true;
		}
		ThreadSupport.sleep(delay/2);}
	}

}
