package model;

public interface HuffBaseNode {

	boolean isLeaf();
	int weight();
	HuffBaseNode left();
	HuffBaseNode right();

}
