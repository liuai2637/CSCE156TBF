package unl.cse.bst;

import java.util.Comparator;

public class BSTDemo {

    public static final Comparator<Integer> INTEGER_COMPARATOR = new Comparator<Integer>() {
        @Override
        public int compare(Integer a, Integer b) {
            return a.compareTo(b);
        }
    };

    public static void main(String args[]) {

        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>(INTEGER_COMPARATOR);

        bst.addElement(8);
        bst.addElement(10);
        bst.addElement(3);
        bst.addElement(5);
        bst.addElement(7);
        
        System.out.println(bst);
        System.out.printf("Depth:      %d\n", bst.getMaxDepth());
        System.out.printf("Size:       %d\n", bst.getNumNodes());
        System.out.printf("Num Leaves: %d\n", bst.getNumLeaves());
        System.out.println("Pre Order Traversal:  ");
        System.out.println(bst.preOrderTraverse());
        System.out.println("In Order Traversal:   ");
        System.out.println(bst.inOrderTraverse());
        System.out.println("Post Order Traversal: ");
        System.out.println(bst.postOrderTraverse());
    }
}
