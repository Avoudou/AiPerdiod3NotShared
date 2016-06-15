package testForAi;

import systems.ForceApply;
import systems.Gravity;
import systems.Movement;

import com.badlogic.ashley.core.Engine;
import components.Force;
import components.GravityForce;
import components.Position;

import entities.Ball;

public class TestGrav {

	public static void main(String[] args) {
		Engine engine = new Engine();
		// engine.update();
		int radius = 1;
		int gravityConstant = 10;
		Ball ball = new Ball(radius, 1, new GravityForce.Builder(gravityConstant));
		ball.add(new Position(5, 5, 20));
		ball.add(new Force());
		
		engine.addEntity(ball);

		Position pos = ball.getComponent(Position.class);
		// Mass mass = ball.getComponent(Mass.class);
		// Force force = ball.getComponent(Force.class);
		
		// System.out.println("mass " + mass.mMass);
		System.out.println("ball mass " + ball.getComponent(Position.class));
		


		engine.addSystem(new Gravity());
		engine.addSystem(new ForceApply());
		engine.addSystem(new Movement());
		
		System.out.println("ball pos 1 " + pos);
		
//		force.add(mass.mMass, 0, 0);
		
		float deltaTime = 1;
		engine.update(deltaTime);
		System.out.println("ball pos 2 " + pos);
		engine.update(deltaTime);
		System.out.println("ball pos 3 " +pos);
		engine.update(deltaTime);
		System.out.println("ball pos 4 " +pos);
		
	}

}
