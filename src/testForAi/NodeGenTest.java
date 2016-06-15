package testForAi;

import searchTree.TreeNode;
import systems.ForceApply;
import systems.FricsionSystem;
import systems.Gravity;
import systems.Movement;
import aiExtention.GolfAction;
import aiExtention.GolfEvaluator;
import aiExtention.GolfGenerator;
import aiExtention.GolfState;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

import components.Force;
import components.GravityForce;
import components.Position;
import entities.Ball;

public class NodeGenTest {

	public static void main(String[] args) {

		Engine engine = new Engine();
		int radius = 1;
		int gravityConstant = 0;
		Ball ball = new Ball(radius, 1, new GravityForce.Builder(gravityConstant));
		ball.add(new Position(0, 0, 0));
		ball.add(new Force());

		engine.addEntity(ball);


		engine.addSystem(new Gravity());
		engine.addSystem(new ForceApply());
		engine.addSystem(new Movement());
		engine.addSystem(new FricsionSystem());



		
		Entity target = new Entity();
		target.add(new Position(50, 0, 0));


		GolfState rootState = new GolfState(ball, target);
		TreeNode<GolfState, GolfAction> rootNode = new TreeNode<GolfState, GolfAction>(null);
		rootNode.setState(rootState);
		rootNode.setNodeDeapth(0);
		// System.out.println(ball);
		GolfGenerator generator = new GolfGenerator(engine,new GolfEvaluator());

		TreeNode<GolfState, GolfAction> testNode = generator.generateChildNode(rootNode);

		System.out.println(testNode.getState().getBall().getComponent(Position.class));
		System.out.println("Force " + testNode.getAction().getForce());

		// TreeNode<GolfTestState, GolfTestAction> testNode2 = generator.generateChildNode(rootNode);

	}

}
