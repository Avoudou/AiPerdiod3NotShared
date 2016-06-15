package aiExtention;


import searchTree.SearchState;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector3;

import components.Force;
import components.GravityForce;
import components.Position;
import entities.Ball;

public class GolfState extends SearchState {
	private Ball ball;
	private Entity target;

	public GolfState(Ball ball, Entity target) {
		this.ball = ball;
		this.target = target;
	}
	
	public Vector3 getPosition() {
		return ball.getComponent(Position.class);
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	public Entity getTarget() {
		return target;
	}

	public void setTarget(Entity target) {
		this.target = target;
	}

	public GolfState cloneState() {
		Ball newBall = new Ball(1,1,  new GravityForce.Builder(0));
		newBall.add(new Position(ball.getComponent(Position.class).x, ball.getComponent(Position.class).y, ball
				.getComponent(Position.class).z));
		newBall.add(new Force());
		GolfState cloneState = new GolfState(newBall, target);
		return cloneState;
	}

}
