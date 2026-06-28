/**
 * SinglyLinkedList — a generic singly linked list you build from scratch.
 *
 * A singly linked list is a chain of nodes. Each node holds a value and a
 * reference to the NEXT node (or null at the end). We keep a reference to the
 * `head` (first node) and a running `size`.
 *
 *     head -> [A|*] -> [B|*] -> [C|null]
 *
 * Fill in every // TODO. Work top to bottom and run the tester after each
 * milestone (see README.md Step 3). When stuck, read the Hints in README.md —
 * not an AI. You'll learn far more, and these are very much doable.
 *
 *     javac SinglyLinkedList.java SinglyLinkedListTester.java
 *     java SinglyLinkedListTester
 *
 * @param <T> the type of element stored in this list
 */
public class SinglyLinkedList<T> {

    /** A node in the list: a value plus a link to the next node. */
    private static class Node<T> {
        T value;
        Node<T> next;
        Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node<T> head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // ===================================================================
    // M1 — basics
    // ===================================================================

    /** @return the number of elements in the list. */
    public int size() {
        // TODO 1: return the size field
        return -1; // replace this
    }

    /** @return true if the list has no elements. */
    public boolean isEmpty() {
        // TODO 1: a list is empty when size is 0 (or head is null) — return that boolean
        return false; // replace this
    }

    /**
     * Add a new value at the FRONT of the list. This is O(1).
     * @param value the value to add
     */
    public void addFirst(T value) {
        // TODO 1: create a new Node<T> holding value
        // TODO 2: set the new node's next to the current head
        // TODO 3: make head point to the new node
        // TODO 4: increment size
    }

    /**
     * Add a new value at the END of the list. This is O(n) because we must
     * walk to the last node first.
     * @param value the value to add
     */
    public void addLast(T value) {
        // TODO 1: create a new Node<T> holding value
        // TODO 2: if the list is empty (head == null), set head = new node, size++, return
        // TODO 3: otherwise walk a `current` reference from head while current.next != null
        // TODO 4: attach the new node: current.next = new node
        // TODO 5: increment size
    }

    /**
     * @return a String like "[a, b, c]" (empty list is "[]").
     * This is provided for you as a reference for how to traverse the list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.value);
            if (current.next != null) sb.append(", ");
            current = current.next;
        }
        return sb.append("]").toString();
    }

    // ===================================================================
    // M2 — access & search
    // ===================================================================

    /**
     * @param index position to read (0-based)
     * @return the value at that index
     * @throws IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        // TODO 1: if index < 0 || index >= size, throw new IndexOutOfBoundsException
        // TODO 2: walk `current` forward `index` times starting from head
        // TODO 3: return current.value
        return null; // replace this
    }

    /**
     * @param value the value to look for (may be null)
     * @return true if the list contains an element equal to value
     */
    public boolean contains(T value) {
        // TODO 1: walk the list; for each node compare using a null-safe equals:
        //         (value == null ? current.value == null : value.equals(current.value))
        // TODO 2: return true if you find a match, false if you reach the end
        return false; // replace this
    }

    /**
     * @param value the value to look for
     * @return the 0-based index of the first match, or -1 if not found
     */
    public int indexOf(T value) {
        // TODO 1: walk the list with both a `current` node and an int `index`
        // TODO 2: when current.value matches value (null-safe, like contains), return index
        // TODO 3: if you reach the end, return -1
        return -2; // replace this
    }

    // ===================================================================
    // M3 — removal
    // ===================================================================

    /**
     * Remove and return the first element. O(1).
     * @return the removed value
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFirst() {
        // TODO 1: if empty, throw new java.util.NoSuchElementException()
        // TODO 2: remember head.value
        // TODO 3: move head to head.next
        // TODO 4: decrement size and return the remembered value
        return null; // replace this
    }

    /**
     * Remove and return the last element. O(n).
     * @return the removed value
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeLast() {
        // TODO 1: if empty, throw new java.util.NoSuchElementException()
        // TODO 2: special case: if size == 1, grab head.value, set head = null, size = 0, return value
        // TODO 3: walk `current` until current.next.next == null (current is second-to-last)
        // TODO 4: remember current.next.value, set current.next = null
        // TODO 5: decrement size and return the value
        return null; // replace this
    }

    /**
     * Remove the element at the given index and return it.
     * @param index position to remove (0-based)
     * @return the removed value
     * @throws IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T remove(int index) {
        // TODO 1: bounds-check (throw IndexOutOfBoundsException) like get()
        // TODO 2: if index == 0, just return removeFirst()
        // TODO 3: walk to the node BEFORE index (the (index-1)th node)
        // TODO 4: remember prev.next.value, then "skip" the target: prev.next = prev.next.next
        // TODO 5: decrement size and return the value
        return null; // replace this
    }

    // ===================================================================
    // M4 — stretch: reverse (classic interview question, LeetCode #206)
    // ===================================================================

    /**
     * Reverse the list in place. After this call, the old last element is the
     * new head. Try to do it in O(n) time and O(1) extra space.
     * See the Hints section in README.md if the three-pointer dance is tricky.
     */
    public void reverse() {
        // TODO 1: create Node<T> prev = null, current = head
        // TODO 2: while current != null:
        //           - save next = current.next
        //           - flip current.next = prev
        //           - advance prev = current, current = next
        // TODO 3: set head = prev
    }

    // ===================================================================
    // M6 — interview patterns (fast/slow pointers)
    // ===================================================================

    /**
     * @return the value of the middle node. For even length, return the second
     * of the two middle nodes (LeetCode #876 convention). Use the fast/slow
     * pointer trick: move `slow` one step and `fast` two steps; when `fast`
     * reaches the end, `slow` is at the middle.
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T findMiddle() {
        // TODO 1: if empty, throw new java.util.NoSuchElementException()
        // TODO 2: slow = head, fast = head
        // TODO 3: while fast != null && fast.next != null: slow = slow.next; fast = fast.next.next
        // TODO 4: return slow.value
        return null; // replace this
    }

    // ===================================================================
    // Provided helpers for the tester (do not edit) — let tests build lists
    // and inspect them without exposing internals.
    // ===================================================================

    /** Package helper so the tester can detect a cycle you might create in tests. */
    boolean hasCycleInternal() {
        Node<T> slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }
}
