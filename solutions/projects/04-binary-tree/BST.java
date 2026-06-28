import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/** BST — reference solution. */
public class BST<T extends Comparable<T>> {

    private static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;
        Node(T value) { this.value = value; this.left = null; this.right = null; }
    }

    private Node<T> root;
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    // ---- M1 ----
    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void insert(T value) {
        added = false;
        root = insertHelper(root, value);
        if (added) size++;
    }

    private boolean added;

    private Node<T> insertHelper(Node<T> node, T value) {
        if (node == null) {
            added = true;
            return new Node<>(value);
        }
        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            node.left = insertHelper(node.left, value);
        } else if (cmp > 0) {
            node.right = insertHelper(node.right, value);
        }
        // cmp == 0 -> duplicate, ignore
        return node;
    }

    public boolean contains(T value) {
        return containsHelper(root, value);
    }

    private boolean containsHelper(Node<T> node, T value) {
        if (node == null) return false;
        int cmp = value.compareTo(node.value);
        if (cmp == 0) return true;
        if (cmp < 0) return containsHelper(node.left, value);
        return containsHelper(node.right, value);
    }

    // ---- M2 ----
    public List<T> inOrder() {
        List<T> result = new ArrayList<>();
        inOrderHelper(root, result);
        return result;
    }

    private void inOrderHelper(Node<T> node, List<T> result) {
        if (node == null) return;
        inOrderHelper(node.left, result);
        result.add(node.value);
        inOrderHelper(node.right, result);
    }

    public List<T> preOrder() {
        List<T> result = new ArrayList<>();
        preOrderHelper(root, result);
        return result;
    }

    private void preOrderHelper(Node<T> node, List<T> result) {
        if (node == null) return;
        result.add(node.value);
        preOrderHelper(node.left, result);
        preOrderHelper(node.right, result);
    }

    public List<T> postOrder() {
        List<T> result = new ArrayList<>();
        postOrderHelper(root, result);
        return result;
    }

    private void postOrderHelper(Node<T> node, List<T> result) {
        if (node == null) return;
        postOrderHelper(node.left, result);
        postOrderHelper(node.right, result);
        result.add(node.value);
    }

    // ---- M3 ----
    public T min() {
        if (root == null) throw new java.util.NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) current = current.left;
        return current.value;
    }

    public T max() {
        if (root == null) throw new java.util.NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) current = current.right;
        return current.value;
    }

    public int height() {
        return heightHelper(root);
    }

    private int heightHelper(Node<T> node) {
        if (node == null) return -1;
        int leftHeight = heightHelper(node.left);
        int rightHeight = heightHelper(node.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // ---- M4 (stretch) ----
    public void delete(T value) {
        if (!contains(value)) return;
        root = deleteHelper(root, value);
        size--;
    }

    private Node<T> deleteHelper(Node<T> node, T value) {
        if (node == null) return null;
        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            node.left = deleteHelper(node.left, value);
            return node;
        } else if (cmp > 0) {
            node.right = deleteHelper(node.right, value);
            return node;
        }
        // found it
        if (node.left == null) return node.right;   // leaf or right-only
        if (node.right == null) return node.left;   // left-only
        // two children: replace with in-order successor (min of right subtree)
        T successor = minValue(node.right);
        node.value = successor;
        node.right = deleteHelper(node.right, successor);
        return node;
    }

    private T minValue(Node<T> node) {
        Node<T> current = node;
        while (current.left != null) current = current.left;
        return current.value;
    }

    // ---- M5 (interview) ----
    public List<T> levelOrder() {
        List<T> result = new ArrayList<>();
        if (root == null) return result;
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> current = queue.remove();
            result.add(current.value);
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        return result;
    }
}
