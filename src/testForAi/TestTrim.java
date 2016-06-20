package testForAi;

import searchTree.TreeNode;
import systems.ForceApply;
import systems.FricsionSystem;
import systems.Gravity;
import systems.Movement;
import aiExtention.GolfAction;
import aiExtention.GolfState;
import aiExtention.Utils.GolfSearchData;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector3;

import components.Force;
import components.GravityForce;

import components.Position;
import components.Velocity;
import entities.Ball;

public class TestTrim {

	public static void main(String[] args) {
		Engine engine = new Engine();
		float deltaTime = 1f;
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
		target.add(new Position(1000, 0, 0));
		
		
		GolfSearchData searchPerformer= new GolfSearchData(ball, target);
		
		TreeNode<GolfState, GolfAction> solutionNode= searchPerformer.aStarSolution();
		
		displaySolution(engine, deltaTime, ball, solutionNode);

	}
	
	/**runs and displays the possition of the ball to the  game-fake engine*/
	private static void displaySolution(Engine engine, float deltaTime,
			Ball ball, TreeNode<GolfState, GolfAction> solutionNode) {
		TreeNode<GolfState, GolfAction> tempNode = solutionNode;
		
		System.out.println("final ball pos " + solutionNode.getState().getPosition());
		for (int i = 0; i < solutionNode.getNodeDeapth(); i++) {
			algStep(engine, ball, deltaTime, solutionNode, tempNode, i + 1);
			System.out.println("ball possition :" + (i+1) + " "+ ball.getComponent(Position.class));
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
	//	System.out.println("apply force: "+forceApply);
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
