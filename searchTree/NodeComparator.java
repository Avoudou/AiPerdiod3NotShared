package searchTree;

import java.util.Comparator;

//Class used for the priority queue implementation in the SearchTree class
public class NodeComparator<E extends SearchState, Action extends AbstractAction<E>> implements
		Comparator<TreeNode<E, ?>> {


	@Override
	public int compare(TreeNode<E, ?> o1, TreeNode<E, ?> o2) {
		return (int) (o1.getValueOfNode() - o2.getValueOfNode());
	}


}
