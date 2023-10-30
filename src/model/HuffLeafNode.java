package model;

public class HuffLeafNode implements HuffBaseNode {

	private char element; // Element for this node
	private int weight; // Weight for this node

	/** Constructor */
	public HuffLeafNode(char el, int wt) {
		element = el;
		weight = wt;
	}

	/** @return The element value */
	public char value() {
		return element;
	}

	@Override
	/** @return true, node is external */
	public boolean isLeaf() {
		return true;
	}

	@Override
	/** @return weight of node */
	public int weight() {
		return weight;
	}

}
