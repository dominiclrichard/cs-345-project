package model;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static HuffTree buildTree(HuffTree[] trees) {
   		 MinHeap treeHeap = new MinHeap();
    		for (HuffTree tree: trees) {
        		treeHeap.insert(tree);
    		}

    		HuffTree tmp1, tmp2, tmp3 = null;
    		while (treeHeap.size() > 1) { // While two items left
      			tmp1 = treeheap.removeMin();
      			tmp2 = treeHeap.removeMin();
      			tmp3 = new HuffTree(tmp1.root(), tmp2.root(),
                               tmp1.weight() + tmp2.weight());
     		 	treeHeap.insert(tmp3);   // Return new tree to heap
   	 	}
    		return tmp3;            // Return the tree
  	}

}
