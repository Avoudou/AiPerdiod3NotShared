package searchTree;



/** Main class that performs the search process */
public class TreeOperator<E extends SearchState, Action extends AbstractAction<E>> {

	private final int noOfGenerChild = 1000;
	private final int noOfParents = 3;
	private TreeNode<E, Action> rootNode;
	private SearchTree<E, Action> searchTree;
	private NodeGenerator<E, Action> generator;
	private GoalAchived<E> goalTester;


	public TreeOperator(TreeNode<E, Action> rootNode, NodeGenerator<E, Action> generator, GoalAchived<E> goalTester) {
		this.setRootNode(rootNode);
		this.searchTree = new SearchTree<E, Action>(rootNode);
		this.generator = generator;
		this.goalTester = goalTester;
		searchTree.addNode(rootNode);
	}

	/** Main search method */
	public TreeNode<E, Action> runSearch() {
		int depth = 0;
		System.out.println("Running search");
		while (!goalTester.test(searchTree.getListOfNodes(depth).peek().getState()) && depth < 1000) {
			expandDeapth(depth);
			depth++;
			// System.out.println(depth + " " + searchTree.getListOfNodes(depth).peek().getNodeDeapth());
		}
		return searchTree.getListOfNodes(depth).poll();

	}

	/**
	 * expands the nodes of the specified depth by adding their children to the a new priority queue in the SearchTree
	 * instance used
	 */
	public void expandDeapth(int deapth) {
		// System.out.println("Expanding");
		int nodesToExpand = Math.min(noOfParents, searchTree.getListOfNodes(deapth).size());
		for (int i = 1; i <= nodesToExpand; i++) {
			TreeNode<E, Action> tempParent = searchTree.getListOfNodes(deapth).poll();
			for (int j = 1; j <= noOfGenerChild; j++) {
				searchTree.addNode(generator.generateChildNode(tempParent));
			}
		}
		// System.out.println("Finished exp");

	}

	// TEST METHODS
	public TreeNode<E, Action> getRootNode() {
		return rootNode;
	}

	public void setRootNode(TreeNode<E, Action> rootNode) {
		this.rootNode = rootNode;
	}

	public SearchTree<E, Action> getTree() {
		return searchTree;
	}

}
