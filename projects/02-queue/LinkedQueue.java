/**
 * LinkedQueue — a generic queue backed by a singly linked list (the "Stretch"
 * part of this project).
 *
 * A queue is FIFO: enqueue at the back, dequeue from the front. With a singly
 * linked list we keep TWO references:
 *
 *   - `head` — the front node (the next one to dequeue).
 *   - `tail` — the back node (where the next enqueue attaches).
 *
 * Keeping a `tail` reference is the whole trick: it lets enqueue attach a new
 * node at the end in O(1) instead of walking the whole list (O(n)) every time.
 * Both enqueue and dequeue are O(1).
 *
 *     head -> [A|*] -> [B|*] -> [C|null]
 *                                  ^--- tail
 *
 * The discipline to watch: whenever an operation could change which node is
 * first or last, ask "do I need to update head, tail, or BOTH?" The classic bug
 * is dequeuing the last element and forgetting to also set tail = null.
 *
 * Fill in every // TODO. Work top to bottom and run the tester after each
 * milestone (see README.md Step 3). When stuck, read the Hints in README.md —
 * not an AI.
 *
 *     javac LinkedQueue.java LinkedQueueTester.java
 *     java LinkedQueueTester
 *
 * @param <T> the type of element stored in this queue
 */
public class LinkedQueue<T> {

    /** A node in the list: a value plus a link to the next node. */
    private static class Node<T> {
        T value;
        Node<T> next;
        Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node<T> head;   // front of the queue (dequeue from here)
    private Node<T> tail;   // back of the queue (enqueue to here)
    private int size;

    public LinkedQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // ===================================================================
    // M3 — LinkedQueue
    // ===================================================================

    /** @return the number of elements in the queue. */
    public int size() {
        // TODO 1: return the size field
        return -1; // replace this
    }

    /** @return true if the queue has no elements. */
    public boolean isEmpty() {
        // TODO 1: a queue is empty when size == 0 (or head == null) — return that
        return false; // replace this
    }

    /**
     * Add a value to the BACK of the queue. O(1) thanks to the tail reference.
     * @param value the value to add
     */
    public void enqueue(T value) {
        // TODO 1: create a new Node<T> holding value
        // TODO 2: if the queue is empty (tail == null), head and tail both
        //         become the new node
        // TODO 3: otherwise attach it after the current tail: tail.next = newNode,
        //         then move tail = newNode
        // TODO 4: increment size
    }

    /**
     * Remove and return the value at the FRONT of the queue. O(1).
     * @return the removed value
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    public T dequeue() {
        // TODO 1: if empty, throw new java.util.NoSuchElementException()
        // TODO 2: remember head.value
        // TODO 3: move head = head.next
        // TODO 4: if head is now null the queue is empty — set tail = null too
        //         HINT: this is the classic bug. Removing the last node must
        //         clear tail, or tail will dangle at a node no longer in the queue.
        // TODO 5: decrement size and return the remembered value
        return null; // replace this
    }

    /**
     * Look at the value at the FRONT without removing it.
     * @return the front value
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    public T peek() {
        // TODO 1: if empty, throw new java.util.NoSuchElementException()
        // TODO 2: return head.value
        return null; // replace this
    }

    /**
     * @return a String like "[a, b, c]" in FRONT-to-BACK order ("[]" if empty).
     * Provided for you as a reference for how to traverse the queue.
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
}
