package main.InterpreterParts;

import util.annotations.Tags;
import main.SceneParts.Avatar;
@Tags("Animating Command")

public class AvatarAnimationCommand implements Runnable {
	private Animator animator;
	private BroadcastingClearanceManager clearanceManager;
	
	public AvatarAnimationCommand(BroadcastingClearanceManager theClearanceManager, Animator theAnimator){
		animator = theAnimator;
		clearanceManager = theClearanceManager;
		
	}
	public AvatarAnimationCommand(Animator theAnimator){
		animator = theAnimator;
	}

	public void run() {
		if (clearanceManager == null){
		animator.animate();}
		else {
		clearanceManager.waitForProceed();
		animator.animate();
		}
	}

}
