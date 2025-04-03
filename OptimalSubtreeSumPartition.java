import java.util.Arrays;

/**
 * Problem Name:  OptimalSubtreeSumPartition
 *
 * Problem Description: Given a binary tree where each node contains a positive integer,
 * partition the tree into two subtrees such that the absolute difference between the sum of
 * nodes in each subtree is minimized.  Return the minimum absolute difference.
 *
 * Note: A subtree is a connected component of nodes.  The partition must result in two non-empty subtrees.
 */
public class OptimalSubtreeSumPartition {

    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    public static int minSubtreeSumDifference(Node root) {
        if (root == null) {
            return 0; // Handle empty tree case
        }

        int totalSum = calculateTreeSum(root);
        return minDiffRecursive(root, totalSum);
    }


    private static int calculateTreeSum(Node node) {
        if (node == null) return 0;
        return node.data + calculateTreeSum(node.left) + calculateTreeSum(node.right);
    }


    private static int minDiffRecursive(Node node, int totalSum) {
        if (node == null) return totalSum; //Base case: reached a leaf, return total sum

        int leftSum = calculateTreeSum(node.left);
        int rightSum = calculateTreeSum(node.right);
        int currentSum = leftSum + rightSum + node.data;
        int minDiff = Math.abs(totalSum - 2 * currentSum); //Try splitting at this node

        //Explore splitting from left and right subtrees recursively
        minDiff = Math.min(minDiff, minDiffRecursive(node.left, totalSum));
        minDiff = Math.min(minDiff, minDiffRecursive(node.right, totalSum));
        return minDiff;
    }


    public static void main(String[] args) {
        Node root = new Node(5);
        root.left = new Node(3);
        root.right = new Node(7);
        root.left.left = new Node(1);
        root.left.right = new Node(4);
        root.right.left = new Node(6);
        root.right.right = new Node(9);

        int minDiff = minSubtreeSumDifference(root);
        System.out.println("Minimum absolute difference between subtree sums: " + minDiff);


        //Test case 2: Unbalanced tree
        Node root2 = new Node(10);
        root2.left = new Node(5);
        root2.right = new Node(15);
        root2.right.right = new Node(20);

        minDiff = minSubtreeSumDifference(root2);
        System.out.println("Minimum absolute difference between subtree sums: " + minDiff);

        // Test case 3: Empty Tree
        Node root3 = null;
        minDiff = minSubtreeSumDifference(root3);
        System.out.println("Minimum absolute difference between subtree sums: " + minDiff);
    }
}