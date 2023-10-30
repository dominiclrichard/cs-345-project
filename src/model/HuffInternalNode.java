package model;

public class HuffInternalNode implements HuffBaseNode {

	private int weight;
	private HuffBaseNode left;
	private HuffBaseNode right;

	/** Constructor */
	public HuffInternalNode(HuffBaseNode l, HuffBaseNode r, int wt) {
		left = l;
		right = r;
		weight = wt;
	}

	/** @return The left child */
	public HuffBaseNode left() {
		return left;
	}

	/** @return The right child */
	public HuffBaseNode right() {
		return right;
	}

	@Override
	/** @return false, node is internal */
	public boolean isLeaf() {
		return false;
	}

	@Override
	/** @return weight of node */
	public int weight() {
		return weight;
	}

}
