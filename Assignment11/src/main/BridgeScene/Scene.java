package main.BridgeScene;

import java.beans.PropertyChangeListener;

import util.models.PropertyListenerRegisterer;

import bus.uigen.OEFrame;
import main.SceneParts.Avatar;
import main.SceneParts.Gorge;
import main.SceneParts.StandingArea;

public interface Scene extends PropertyListenerRegisterer {
	
	public void setOEFrame(OEFrame oeframe);
	public boolean getOccupied();
	public boolean getKnightTurn();
	public OEFrame getOEFrame();
	public Avatar getApproachedAvatar();
	public int getPassedLevel();
	public int getFailedLevel();
	public Avatar getArthur();
	public Avatar getGalahad();
	public Avatar getLancelot();
	public Avatar getRobin();
	public Avatar getGuard();
	public Gorge getBridgeGorge();
	public StandingArea getKnightStandingArea();
	public StandingArea getGuardStandingArea();
	public boolean preApproach();
	public void approach(Avatar avatar);
	public boolean preSay();
	public void say(String string);
	public boolean prePassed();
	public void passed();
	public boolean preFailed();
	public void failed();
	public void addListener(PropertyChangeListener listener);
}
