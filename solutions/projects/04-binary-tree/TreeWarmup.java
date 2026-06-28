/** TreeWarmup — reference solution. */
public class TreeWarmup {

    public static int countNodes(Node node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    public static int sumValues(Node node) {
        if (node == null) return 0;
        return node.value + sumValues(node.left) + sumValues(node.right);
    }

    public static int maxDepth(Node node) {
        if (node == null) return -1;
        int left = maxDepth(node.left);
        int right = maxDepth(node.right);
        return 1 + Math.max(left, right);
    }

    public static class Node {
        int value;
        Node left;
        Node right;
        Node(int value) { this.value = value; this.left = null; this.right = null; }
    }
}
