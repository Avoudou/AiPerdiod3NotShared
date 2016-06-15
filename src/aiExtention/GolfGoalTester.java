package aiExtention;

import searchTree.GoalAchived;

import com.badlogic.gdx.math.Vector3;
import components.Position;

public class GolfGoalTester extends GoalAchived<GolfState> {
	private final int diameterOfTarget = 15;

	@Override
	public boolean test(GolfState aState) {
		Vector3 difference = aState.getTarget().getComponent(Position.class).cpy()
				.sub(aState.getBall().getComponent(Position.class).cpy());
		return difference.len() < diameterOfTarget;
	}

}
