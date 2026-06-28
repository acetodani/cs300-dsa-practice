/**
 * DoublyLinkedList — a generic doubly linked list (the "Stretch" part of this
 * project).
 *
 * In a doubly linked list every node holds TWO links: `next` (toward the tail)
 * and `prev` (toward the head). We also keep a `tail` reference, which makes
 * addLast and removeLast O(1) — the big payoff over a singly linked list.
 *
 *     null <- [A] <-> [B] <-> [C] -> null
 *     head ---^                 ^--- tail
 *
 * Fill in every // TODO. The key discipline: whenever you change a `next`, ask
 * "do I also need to fix the matching `prev`?" Run:
 *
 *     javac DoublyLinkedList.java DoublyLinkedListTester.java
 *     java DoublyLinkedListTester
 *
 * @param <T> the type of element stored in this list
 */
public class DoublyLinkedList<T> {

    private static class Node<T> {
        T value;
        Node<T> next;
        Node<T> prev;
        Node(T value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size() {
        // TODO 1: return size
        return -1; // replace this
    }

    public boolean isEmpty() {
        // TODO 1: return whether size == 0
        return false; // replace this
    }

    /**
     * Add to the FRONT. O(1).
     */
    public void addFirst(T value) {
        // TODO 1: make a new node
        // TODO 2: if the list is empty, head and tail both become the new node
        // TODO 3: otherwise: newNode.next = head; head.prev = newNode; head = newNode
        // TODO 4: increment size
    }

    /**
     * Add to the END. O(1) thanks to the tail reference.
     */
    public void addLast(T value) {
        // TODO 1: make a new node
        // TODO 2: if the list is empty, head and tail both become the new node
        // TODO 3: otherwise: newNode.prev = tail; tail.next = newNode; tail = newNode
        // TODO 4: increment size
    }

    /**
     * Remove and return the first element. O(1).
     * @throws java.util.NoSuchElementException if empty
     */
    public T removeFirst() {
        // TODO 1: if empty, throw new java.util.NoSuchElementException()
        // TODO 2: remember head.value
        // TODO 3: move head = head.next
        // TODO 4: if head is now null, the list is empty -> set tail = null too;
        //         otherwise set head.prev = null
        // TODO 5: decrement size and return the value
        return null; // replace this
    }

    /**
     * Remove and return the last element. O(1) thanks to the tail + prev links.
     * @throws java.util.NoSuchElementException if empty
     */
    public T removeLast() {
        // TODO 1: if empty, throw new java.util.NoSuchElementException()
        // TODO 2: remember tail.value
        // TODO 3: move tail = tail.prev
        // TODO 4: if tail is now null, the list is empty -> set head = null too;
        //         otherwise set tail.next = null
        // TODO 5: decrement size and return the value
        return null; // replace this
    }

    /** Forward string "[a, b, c]". Provided for you. */
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

    /**
     * Build a string by walking from TAIL to HEAD using prev links: "[c, b, a]".
     * This proves your prev links are correct. Implement it once addLast works.
     */
    public String toStringReverse() {
        // TODO 1: start a StringBuilder with "["
        // TODO 2: walk `current` from tail backward using current.prev
        // TODO 3: append values with ", " between them
        // TODO 4: close with "]" and return
        return null; // replace this
    }
}
