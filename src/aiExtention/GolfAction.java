package aiExtention;

import searchTree.AbstractAction;

import com.badlogic.gdx.math.Vector3;

public class GolfAction extends AbstractAction<GolfState> {
	private Vector3 force;

	public GolfAction(Vector3 force) {
		this.force = force;
	}

	public Vector3 getForce() {
		return force;
	}
}
