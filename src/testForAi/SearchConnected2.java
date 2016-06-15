package testForAi;

import searchTree.TreeNode;
import searchTree.TreeOperator;
import systems.ForceApply;
import systems.FricsionSystem;
import systems.Gravity;
import systems.Movement;
import aiExtention.GolfAction;
import aiExtention.GolfEvaluator;
import aiExtention.GolfGenerator;
import aiExtention.GolfGoalTester;
import aiExtention.GolfState;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector3;
import components.Force;
import components.GravityForce;
import components.Position;
import components.Velocity;

import entities.Ball;

public class SearchConnected2 {

	public static void main(String[] args) {

		Engine engine = new Engine();
		Engine engine2 = new Engine();
		int radius = 1;
		int gravityConstant = 0;
		Ball ball = new Ball(radius, 1, new GravityForce.Builder(gravityConstant));
		ball.add(new Position(0, 0, 0));
		ball.add(new Force());

		engine.addEntity(ball);

		Position pos = ball.getComponent(Position.class);

		engine.addSystem(new Gravity());
		engine.addSystem(new ForceApply());
		engine.addSystem(new Movement());
		engine.addSystem(new FricsionSystem());


		float deltaTime = 1f;

		// RUN ALGORITHM
		Entity target = new Entity();

		target.add(new Position(100, 0, 0));

		GolfState rootState = new GolfState(ball, target);

		TreeNode<GolfState, GolfAction> rootNode = new TreeNode<GolfState, GolfAction>(null);

		GolfEvaluator evaluator = new GolfEvaluator();

		rootNode.setState(rootState);

		rootNode.setNodeDeapth(0);

		rootNode.setValueOfNode(evaluator.evaluateNode(rootNode));

		System.out.println("value root " + rootNode.getValueOfNode());

		GolfGenerator generator = new GolfGenerator(engine2,evaluator);

		GolfGoalTester goalTester = new GolfGoalTester();

		TreeOperator<GolfState, GolfAction> treeOperator = new TreeOperator<GolfState, GolfAction>(
				rootNode, generator, goalTester);

		System.out.println("ball pos 1 " + pos);
		System.out.println("value root " + rootNode.getValueOfNode());

		TreeNode<GolfState, GolfAction> solutionNode = treeOperator.runSearch();



		TreeNode<GolfState, GolfAction> tempNode = solutionNode;
		System.out.println("final ball pos " + solutionNode.getState().getPosition());
		for (int i = 0; i < solutionNode.getNodeDeapth(); i++) {
			algStep(engine, ball, deltaTime, solutionNode, tempNode, i + 1);
			System.out.println("ball possition :" + (i+1) + " "
					+ ball.getComponent(Position.class));
			tempNode = solutionNode;
		}

	

	}

	public static void algStep(Engine engine, Ball ball, float deltaTime,
			TreeNode<GolfState, GolfAction> solutionNode, TreeNode<GolfState, GolfAction> tempNode,
			int counter) {
		while (tempNode.getNodeDeapth() != counter) {
			tempNode = tempNode.getParent();

		}
		// System.out.println("valueOFNode" + tempNode.getValueOfNode());

		Vector3 forceApply = tempNode.getAction().getForce();
		// System.out.println(forceApply);
		ball.getComponent(Force.class).add(forceApply.x, forceApply.y, forceApply.z);
		int i = 1;
		//engine.update(deltaTime);
		while (ball.getComponent(Velocity.class).len() > 0.1 || i == 1) {
			engine.update(deltaTime);

			i++;
		}
		ball.getComponent(Velocity.class).setZero();
		System.out.println("Solution Depth: " + tempNode.getNodeDeapth() + " ball pos " + tempNode.getState().getPosition());

	}

}
