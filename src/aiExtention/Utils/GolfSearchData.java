package aiExtention.Utils;

import java.util.Arrays;
import java.util.List;

import searchTree.AStar;
import searchTree.GoalAchived;
import searchTree.NodeEvaluator;
import searchTree.NodeGenerator;
import searchTree.TreeNode;
import searchTree.TreeOperator;
import systems.EntitySystem;
import systems.ForceApply;
import systems.FricsionSystem;
import systems.Gravity;
import systems.Movement;
import aiExtention.AStarEvaluator;
import aiExtention.GolfAction;
import aiExtention.GolfEvaluator;
import aiExtention.GolfGenerator;
import aiExtention.GolfGoalTester;
import aiExtention.GolfState;
import aiExtention.GolfStateComparator;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

import components.Mass;
import entities.Ball;

public class GolfSearchData {
	private Engine aiEngine;
	private float deltaTime = 1f;
	private NodeEvaluator<GolfState> evaluator;
	private NodeGenerator<GolfState,GolfAction> generator;
	private GoalAchived<GolfState> goalTester;
	private TreeNode<GolfState,GolfAction> rootNode;
	
	public GolfSearchData(Ball ball,Entity target){
		aiEngine= new Engine();
		List<EntitySystem> systems = Arrays.asList(new Gravity(), new ForceApply(), new Movement(),
				new FricsionSystem());
		for (int i = 0; i < systems.size(); i++) {
			aiEngine.addSystem(systems.get(i));
			aiEngine.addEntityListener(systems.get(i).getNewEntitiesListener());
		}
		constructRoot(ball, target);		

		
	}
	
	public void constructRoot(Ball ball,Entity target){
		GolfState rootState = new GolfState(ball, target);

		TreeNode<GolfState, GolfAction> rootNode = new TreeNode<GolfState, GolfAction>(null);

		GolfEvaluator evaluator = new GolfEvaluator();

		rootNode.setState(rootState);

		rootNode.setNodeDeapth(0);

		rootNode.setValueOfNode(evaluator.evaluateNode(rootNode));
		System.out.println("rootvalue"+rootNode.getValueOfNode());
		
		this.rootNode=rootNode;		
	}
	public TreeNode<GolfState, GolfAction> aStarSolution(){
		this.evaluator= new AStarEvaluator();
		this.generator= new GolfGenerator(aiEngine, evaluator);
		this.goalTester=new GolfGoalTester();
		AStar<GolfState, GolfAction> astar = new AStar<GolfState, GolfAction>(
				 generator, goalTester,new GolfStateComparator(20000, 20000, 30));
		System.out.println("RUN aStar");
		return astar.runAStar(rootNode);
		
	}
	public TreeNode<GolfState, GolfAction> greedySolution(){
		this.evaluator= new GolfEvaluator();
		this.generator= new GolfGenerator(aiEngine, evaluator);
		this.goalTester=new GolfGoalTester();
		TreeOperator<GolfState, GolfAction> treeOperator = new TreeOperator<GolfState, GolfAction>(
				rootNode, generator, goalTester);
		System.out.println("RUN greedy");
		return treeOperator.runSearch();
		
	}
	

}
