/**
 * LinkedListWarmup — tiny guided drills to get "pointer juggling" into your
 * fingers BEFORE you build the full list classes.
 *
 * Each method here works on a minimal Node type defined at the bottom of this
 * file. Fill in the TODOs, then run:
 *
 *     javac LinkedListWarmup.java LinkedListWarmupTester.java
 *     java LinkedListWarmupTester
 *
 * These are deliberately small. If one takes more than a few minutes, re-watch
 * the video linked in README.md (Step 1) — don't reach for an AI.
 */
public class LinkedListWarmup {

    /**
     * Drill 1 — Count the nodes.
     * Walk from `head` to the end, counting nodes. An empty list (head == null)
     * has length 0.
     */
    public static int length(Node head) {
        // TODO 1: create an int counter starting at 0
        // TODO 2: create a Node reference `current` starting at head
        // TODO 3: while current is not null, add 1 to your counter and move
        //         current to current.next
        // TODO 4: return the counter
        return -1; // replace this
    }

    /**
     * Drill 2 — Sum the values.
     * Return the sum of every node's value. Empty list sums to 0.
     */
    public static int sum(Node head) {
        // TODO 1: walk the list like in Drill 1, but add current.value each step
        return -1; // replace this
    }

    /**
     * Drill 3 — Find the last node's value.
     * Return the value stored in the final node. If the list is empty, return -1.
     * (The last node is the one whose `next` is null.)
     */
    public static int lastValue(Node head) {
        // TODO 1: handle the empty case (head == null) -> return -1
        // TODO 2: walk current forward while current.next is not null
        // TODO 3: return current.value
        return -1; // replace this
    }

    /**
     * Drill 4 — Build a chain.
     * Given an array of values, build a linked list and return its head.
     * The first array element should be the head. An empty array returns null.
     *
     * Tip: build it back-to-front. Start with head = null, then for each value
     * from the LAST to the FIRST, create a new node whose `next` is the current
     * head, and make that new node the head.
     */
    public static Node fromArray(int[] values) {
        // TODO 1: declare Node head = null
        // TODO 2: loop i from values.length - 1 down to 0
        // TODO 3: create `new Node(values[i])`, set its .next = head, then head = that node
        // TODO 4: return head
        return null; // replace this
    }

    /** A minimal node for these drills. Do not change it. */
    public static class Node {
        int value;
        Node next;
        Node(int value) { this.value = value; this.next = null; }
    }
}
