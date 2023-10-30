package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import model.HuffInternalNode;
import model.HuffLeafNode;
import model.HuffTree;

class test1 {

	HuffLeafNode leaf1 = new HuffLeafNode('b', 0);
	HuffLeafNode leaf2 = new HuffLeafNode('c', 0);
	HuffTree tree1 = new HuffTree(leaf1, leaf2, 2);

	@Test
	void test1() {
		assertEquals(tree1.weight(), 2);
		assertEquals(tree1.root().weight(), tree1.weight());
		assertEquals(tree1.root().isLeaf(), false);
		assertEquals(((HuffInternalNode) (tree1.root())).left(), leaf1);
		assertEquals(((HuffInternalNode) (tree1.root())).right(), leaf2);
	}

	@Test
	void test2() {
		assertEquals(leaf1.value(), 'b');
		assertEquals(leaf2.value(), 'c');
		assertEquals(leaf1.isLeaf(), true);
		assertEquals(tree1.root().isLeaf(), false);
		assertEquals(leaf2.weight(), 0);
	}

	HuffTree tree = new HuffTree('i', 3);
	HuffLeafNode leaf3 = new HuffLeafNode('b', 0);
	HuffLeafNode leaf4 = new HuffLeafNode('c', 0);
	HuffLeafNode leaf5 = new HuffLeafNode('d', 1);
	HuffTree tree3 = new HuffTree(leaf3, leaf4, 2);

	@Test
	void test3() {
		assertEquals(tree.root().isLeaf(), true);
		assertEquals(tree.compareTo(tree1), 1);
		assertEquals(tree3.compareTo(tree1), 0);
		assertEquals(tree3.compareTo(tree), -1);

	}

}
