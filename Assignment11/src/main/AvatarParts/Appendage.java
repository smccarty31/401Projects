package main.AvatarParts;

import main.Atomics.MyLocatableIntf;

public interface Appendage extends MyLocatableIntf {//outlines methods for extraneous limbs (ie feet)

	public Line getToeOne();
	public Line getToeTwo();
	public void rotate(double angle);
}
