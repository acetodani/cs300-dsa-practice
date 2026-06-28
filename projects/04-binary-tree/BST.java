import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * BST — a generic Binary Search Tree you build from scratch.
 *
 * A binary search tree is a tree of nodes. Each node holds a value and two
 * references: `left` (a subtree of SMALLER values) and `right` (a subtree of
 * LARGER values). That single ordering rule — smaller goes left, larger goes
 * right — is the "BST property", and it is what makes search fast and what makes
 * an in-order traversal come out SORTED.
 *
 * The whole tree is reached through one reference, `root` (or null when empty).
 * There are no parent pointers: you always work down from a node to its
 * children. That is why almost every method here uses RECURSION — a method that
 * calls itself on `node.left` and `node.right`.
 *
 * The pattern you will repeat for nearly every operation:
 *   - A short PUBLIC method that the tester calls. It kicks things off by
 *     calling a PRIVATE recursive helper, passing `root` as the starting node.
 *   - A PRIVATE recursive helper that takes a `Node` parameter and:
 *       1. handles the BASE CASE first (node == null — an empty subtree), then
 *       2. compares against node.value and RECURSES into node.left or node.right.
 *
 * Fill in every // TODO. Work top to bottom and run the tester after each
 * milestone (see README.md Step 3). When stuck, read the Hints in README.md —
 * not an AI. You'll learn far more, and these are very much doable.
 *
 *     javac BST.java BSTTester.java
 *     java BSTTester
 *
 * @param <T> the type of element stored; must be Comparable so we can order it
 */
public class BST<T extends Comparable<T>> {

    /** A node in the tree: a value plus links to the left and right subtrees. */
    private static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;
        Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node<T> root;
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    // ===================================================================
    // M1 — basics: insert, size, isEmpty, contains
    // ===================================================================

    /** @return the number of elements in the tree. */
    public int size() {
        // TODO 1: return the size field
        return -1; // replace this
    }

    /** @return true if the tree has no elements. */
    public boolean isEmpty() {
        // TODO 1: a tree is empty when size is 0 (or root is null) — return that boolean
        return false; // replace this
    }

    /**
     * Insert a value into the tree, keeping the BST property. Duplicates are
     * ignored (a BST holds a set of values, so inserting a value already present
     * changes nothing).
     *
     * The public method kicks off the recursion. The private helper does the
     * real work and RETURNS the (possibly new) subtree so the parent can re-link
     * it. We track whether a node was actually added so `size` stays correct.
     *
     * @param value the value to add (must not be null)
     */
    public void insert(T value) {
        // TODO 1: reset the `added` flag to false (see field below the helper)
        // TODO 2: call the helper: root = insertHelper(root, value);
        //         (the helper returns the subtree root, which may be a brand-new node)
        // TODO 3: if `added` is now true, increment size
    }

    /** Set to true by insertHelper when a brand-new node was created. */
    private boolean added;

    /**
     * Recursive helper for insert. Inserts `value` into the subtree rooted at
     * `node` and returns the root of the resulting subtree.
     *
     * @param node the root of the current subtree (may be null)
     * @param value the value to insert
     * @return the root of the subtree after inserting
     */
    private Node<T> insertHelper(Node<T> node, T value) {
        // TODO 1: BASE CASE — if node == null, we found the empty spot:
        //         set this.added = true; return new Node<>(value);
        // TODO 2: compare value to node.value with: int cmp = value.compareTo(node.value);
        // TODO 3: if cmp < 0, the value is smaller -> goes left:
        //         node.left = insertHelper(node.left, value);
        // TODO 4: else if cmp > 0, the value is larger -> goes right:
        //         node.right = insertHelper(node.right, value);
        // TODO 5: else (cmp == 0) it's a duplicate -> do nothing (leave node as is)
        // TODO 6: return node;  (so the parent's left/right gets re-linked to it)
        // HINT: re-linking with "node.left = insertHelper(node.left, value)" looks
        //       odd at first. For an existing node it just re-assigns the same
        //       child back. For a null child it attaches the new node. One line
        //       handles both — that is the elegance of the return-the-subtree style.
        return null; // replace this
    }

    /**
     * @param value the value to look for
     * @return true if the tree contains a node equal to value
     */
    public boolean contains(T value) {
        // TODO 1: return the result of the recursive helper starting at root:
        //         return containsHelper(root, value);
        return false; // replace this
    }

    /**
     * Recursive helper for contains. Searches the subtree rooted at `node`.
     *
     * @param node the root of the current subtree (may be null)
     * @param value the value to look for
     * @return true if found in this subtree
     */
    private boolean containsHelper(Node<T> node, T value) {
        // TODO 1: BASE CASE — if node == null, the value isn't here -> return false
        // TODO 2: int cmp = value.compareTo(node.value);
        // TODO 3: if cmp == 0 -> return true (found it)
        // TODO 4: if cmp < 0 -> search LEFT:  return containsHelper(node.left, value);
        // TODO 5: else        -> search RIGHT: return containsHelper(node.right, value);
        // HINT: notice you only recurse into ONE side, never both — that is why
        //       search in a balanced BST is fast (you halve the work each step).
        return false; // replace this
    }

    // ===================================================================
    // M2 — traversals (each returns a java.util.List<T>)
    // ===================================================================

    /**
     * In-order traversal: LEFT subtree, then this NODE, then RIGHT subtree.
     * For a BST this yields the values in SORTED order — that is the big "aha".
     *
     * @return a List of all values in ascending order
     */
    public List<T> inOrder() {
        // TODO 1: create a List<T> result = new ArrayList<>();
        // TODO 2: call the helper to fill it: inOrderHelper(root, result);
        // TODO 3: return result;
        return null; // replace this
    }

    /**
     * Recursive helper for inOrder. Appends the values of the subtree rooted at
     * `node` into `result` in (left, node, right) order.
     *
     * @param node the root of the current subtree (may be null)
     * @param result the list to append values to
     */
    private void inOrderHelper(Node<T> node, List<T> result) {
        // TODO 1: BASE CASE — if node == null, return (nothing to add)
        // TODO 2: recurse LEFT first:        inOrderHelper(node.left, result);
        // TODO 3: then VISIT this node:       result.add(node.value);
        // TODO 4: then recurse RIGHT:          inOrderHelper(node.right, result);
        // HINT: the ORDER of these three lines is the whole difference between
        //       in-order, pre-order, and post-order. Get this one right and the
        //       next two are tiny variations.
    }

    /**
     * Pre-order traversal: this NODE first, then LEFT subtree, then RIGHT subtree.
     * Useful for copying/serializing a tree (the root comes out first).
     *
     * @return a List of all values in pre-order
     */
    public List<T> preOrder() {
        // TODO 1: create a List<T> result = new ArrayList<>();
        // TODO 2: call preOrderHelper(root, result);
        // TODO 3: return result;
        return null; // replace this
    }

    /**
     * Recursive helper for preOrder: (node, left, right).
     *
     * @param node the root of the current subtree (may be null)
     * @param result the list to append values to
     */
    private void preOrderHelper(Node<T> node, List<T> result) {
        // TODO 1: BASE CASE — if node == null, return
        // TODO 2: VISIT this node first:  result.add(node.value);
        // TODO 3: recurse LEFT:            preOrderHelper(node.left, result);
        // TODO 4: recurse RIGHT:           preOrderHelper(node.right, result);
    }

    /**
     * Post-order traversal: LEFT subtree, then RIGHT subtree, then this NODE.
     * Useful for deleting/freeing a tree (children are handled before the node).
     *
     * @return a List of all values in post-order
     */
    public List<T> postOrder() {
        // TODO 1: create a List<T> result = new ArrayList<>();
        // TODO 2: call postOrderHelper(root, result);
        // TODO 3: return result;
        return null; // replace this
    }

    /**
     * Recursive helper for postOrder: (left, right, node).
     *
     * @param node the root of the current subtree (may be null)
     * @param result the list to append values to
     */
    private void postOrderHelper(Node<T> node, List<T> result) {
        // TODO 1: BASE CASE — if node == null, return
        // TODO 2: recurse LEFT:            postOrderHelper(node.left, result);
        // TODO 3: recurse RIGHT:           postOrderHelper(node.right, result);
        // TODO 4: VISIT this node LAST:    result.add(node.value);
    }

    // ===================================================================
    // M3 — min, max, height
    // ===================================================================

    /**
     * @return the smallest value in the tree
     * @throws java.util.NoSuchElementException if the tree is empty
     * The smallest value lives at the LEFTMOST node: keep going left until a node
     * has no left child. (This one is short enough to do with a simple loop —
     * no recursion required, though you may use it if you prefer.)
     */
    public T min() {
        // TODO 1: if empty (root == null), throw new java.util.NoSuchElementException()
        // TODO 2: start a Node<T> current at root
        // TODO 3: while current.left != null, move current = current.left
        // TODO 4: return current.value
        return null; // replace this
    }

    /**
     * @return the largest value in the tree
     * @throws java.util.NoSuchElementException if the tree is empty
     * The largest value lives at the RIGHTMOST node: keep going right.
     */
    public T max() {
        // TODO 1: if empty (root == null), throw new java.util.NoSuchElementException()
        // TODO 2: start a Node<T> current at root
        // TODO 3: while current.right != null, move current = current.right
        // TODO 4: return current.value
        return null; // replace this
    }

    /**
     * Height of the tree: the number of EDGES on the longest path from the root
     * down to a leaf. By convention an EMPTY tree has height -1, and a tree with
     * a single node (just the root) has height 0.
     *
     * @return the height of the tree
     */
    public int height() {
        // TODO 1: return heightHelper(root);
        return -2; // replace this
    }

    /**
     * Recursive helper for height. Returns the height of the subtree rooted at
     * `node`, in EDGES, with an empty subtree being -1.
     *
     * @param node the root of the current subtree (may be null)
     * @return the height of this subtree
     */
    private int heightHelper(Node<T> node) {
        // TODO 1: BASE CASE — if node == null, return -1 (empty subtree)
        // TODO 2: int leftHeight = heightHelper(node.left);
        // TODO 3: int rightHeight = heightHelper(node.right);
        // TODO 4: return 1 + the LARGER of leftHeight and rightHeight
        //         (use Math.max(leftHeight, rightHeight))
        // HINT: the "+1" counts the edge from `node` down into its taller child.
        //       For a single leaf, both children are null -> -1, so this returns
        //       1 + max(-1, -1) = 1 + (-1) = 0. That matches "single node = 0".
        return -2; // replace this
    }

    // ===================================================================
    // M4 — STRETCH: delete (the hard one — read the big Hint in README.md!)
    // ===================================================================

    /**
     * Remove `value` from the tree if present, keeping the BST property. There
     * are three cases to handle once you FIND the node to remove:
     *   (a) it is a LEAF (no children) — just detach it (return null).
     *   (b) it has ONE child — replace it with that child.
     *   (c) it has TWO children — this is the tricky one. Replace the node's
     *       value with its IN-ORDER SUCCESSOR (the smallest value in its RIGHT
     *       subtree), then delete that successor from the right subtree.
     *
     * Like insert, the helper RETURNS the new subtree root so the parent re-links.
     *
     * @param value the value to remove (does nothing if not present)
     */
    public void delete(T value) {
        // TODO 1: only adjust size if the value is actually present, so first:
        //         if (!contains(value)) return;
        // TODO 2: root = deleteHelper(root, value);
        // TODO 3: size--;  (safe now, because we returned early if it wasn't present)
        // HINT: checking contains() first is a small inefficiency (two traversals)
        //       but it keeps the size bookkeeping dead simple. Correctness over
        //       cleverness while you're learning.
    }

    /**
     * Recursive helper for delete. Removes `value` from the subtree rooted at
     * `node` and returns the new root of that subtree.
     *
     * @param node the root of the current subtree (may be null)
     * @param value the value to remove
     * @return the root of the subtree after removal
     */
    private Node<T> deleteHelper(Node<T> node, T value) {
        // TODO 1: BASE CASE — if node == null, return null (value not in this subtree)
        // TODO 2: int cmp = value.compareTo(node.value);
        // TODO 3: if cmp < 0 -> the target is to the LEFT:
        //           node.left = deleteHelper(node.left, value);  then return node;
        // TODO 4: else if cmp > 0 -> the target is to the RIGHT:
        //           node.right = deleteHelper(node.right, value); then return node;
        // TODO 5: else (cmp == 0) -> THIS is the node to remove. Handle the cases:
        //           - case (a)/(b) combined: if node.left == null, return node.right;
        //                                     if node.right == null, return node.left;
        //             (if BOTH are null, node.right is null too, so this returns null —
        //              that quietly handles the leaf case as well.)
        //           - case (c) TWO children: find the in-order successor's value
        //             (the MINIMUM value in the RIGHT subtree) using the
        //             minValue helper below:
        //                 T successor = minValue(node.right);
        //             copy it into this node:
        //                 node.value = successor;
        //             then delete that successor value from the right subtree:
        //                 node.right = deleteHelper(node.right, successor);
        //             finally: return node;
        // HINT: read the long delete walkthrough in README.md Hints before writing
        //       case (c). Drawing a 3-level tree on paper and deleting the root by
        //       hand makes this click.
        return null; // replace this
    }

    /**
     * Small helper: the minimum value in the subtree rooted at `node` (its
     * leftmost value). Assumes node != null. Used by delete's two-child case to
     * find the in-order successor.
     *
     * @param node the (non-null) root of a subtree
     * @return the smallest value in that subtree
     */
    private T minValue(Node<T> node) {
        // TODO 1: walk current = node, then while current.left != null move left
        // TODO 2: return current.value
        return null; // replace this
    }

    // ===================================================================
    // M5 — INTERVIEW: level-order (breadth-first) traversal
    // ===================================================================

    /**
     * Level-order (breadth-first) traversal: visit the root, then every node at
     * depth 1 left-to-right, then every node at depth 2, and so on. Unlike the
     * other traversals this one is NOT naturally recursive — it uses a QUEUE.
     *
     * This is the "BFS" pattern you will see again on graphs.
     *
     * @return a List of all values in level order (top to bottom, left to right)
     */
    public List<T> levelOrder() {
        // TODO 1: create List<T> result = new ArrayList<>();
        // TODO 2: if the tree is empty (root == null), return result (it's just empty)
        // TODO 3: create a queue: Queue<Node<T>> queue = new LinkedList<>();
        // TODO 4: enqueue the root: queue.add(root);
        // TODO 5: while the queue is NOT empty:
        //           - Node<T> current = queue.remove();   // take the front
        //           - result.add(current.value);          // visit it
        //           - if current.left  != null, queue.add(current.left);
        //           - if current.right != null, queue.add(current.right);
        // TODO 6: return result;
        // HINT: a node's children are added to the BACK of the queue, so they are
        //       only visited AFTER everything already waiting — that is exactly
        //       what makes the traversal go level by level.
        return null; // replace this
    }
}
