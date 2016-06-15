package testForAi;

import aiExtention.GolfState;
import aiExtention.GolfStateComparator;

import com.badlogic.ashley.core.Entity;
import components.Force;
import components.GravityForce;
import components.Position;

import entities.Ball;

public class StateComTest {

	public static void main(String[] args) {
		GolfStateComparator stateComparator = new GolfStateComparator(1000, 1000, 2);
		int radius = 1;
		int gravityConstant = 0;
		Ball ball = new Ball(radius, 1, new GravityForce.Builder(gravityConstant));
		ball.add(new Position(0, 0, 0));
		ball.add(new Force());
		Position pos = ball.getComponent(Position.class);
		Entity target = new Entity();
		target.add(new Position(100, 0, 0));
		GolfState state = new GolfState(ball, target);
		//stateComparator.isStateExplored(state);
		System.out.println(stateComparator.isStateExplored(state));
		
	}

}
