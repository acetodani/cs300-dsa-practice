/**
 * TreeWarmup — tiny guided drills to get RECURSION ON A TREE into your fingers
 * BEFORE you build the full BST class.
 *
 * Every drill here works on a minimal Node type defined at the bottom of this
 * file. Each one follows the same recursive shape you'll use all over BST.java:
 *
 *     if (node == null) return <the base-case answer>;   // empty subtree
 *     ... combine results from node.left and node.right ...
 *
 * Fill in the TODOs, then run:
 *
 *     javac TreeWarmup.java TreeWarmupTester.java
 *     java TreeWarmupTester
 *
 * These are deliberately small. If one takes more than a few minutes, re-watch
 * the tree video linked in README.md (Step 1) — don't reach for an AI. Notice
 * how each answer is built from the answers for the two subtrees: that "trust
 * the recursion to solve the smaller problem" mindset is the whole game.
 */
public class TreeWarmup {

    /**
     * Drill 1 — Count the nodes.
     * Return how many nodes are in the tree rooted at `node`. An empty tree
     * (node == null) has 0 nodes.
     */
    public static int countNodes(Node node) {
        // TODO 1: BASE CASE — if node == null, return 0
        // TODO 2: otherwise return 1 (for this node)
        //         + countNodes(node.left) + countNodes(node.right)
        return -1; // replace this
    }

    /**
     * Drill 2 — Sum the values.
     * Return the sum of every node's value in the tree. An empty tree sums to 0.
     */
    public static int sumValues(Node node) {
        // TODO 1: BASE CASE — if node == null, return 0
        // TODO 2: otherwise return node.value
        //         + sumValues(node.left) + sumValues(node.right)
        return -1; // replace this
    }

    /**
     * Drill 3 — Maximum depth (height in edges).
     * Return the number of EDGES on the longest path from `node` down to a leaf.
     * An empty tree (node == null) has depth -1; a single node has depth 0.
     * (This is exactly the rule you'll reuse for BST.height().)
     */
    public static int maxDepth(Node node) {
        // TODO 1: BASE CASE — if node == null, return -1
        // TODO 2: int left = maxDepth(node.left);
        // TODO 3: int right = maxDepth(node.right);
        // TODO 4: return 1 + Math.max(left, right);
        return -2; // replace this
    }

    /**
     * A minimal binary-tree node for these drills. Do not change it.
     * (No ordering rule here — it's just a plain binary tree so you can focus on
     * the recursion. The BST property comes later, in BST.java.)
     */
    public static class Node {
        int value;
        Node left;
        Node right;
        Node(int value) { this.value = value; this.left = null; this.right = null; }
    }
}
