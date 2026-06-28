/**
 * MinHeap — a min-heap of ints you build from scratch, backed by an array.
 *
 * A binary heap is a COMPLETE binary tree (every level full except possibly the
 * last, which fills left-to-right) that obeys the HEAP-ORDER property: in a
 * min-heap every parent is <= its children. The smallest element therefore
 * always sits at the root.
 *
 * The "no gaps" rule of a complete tree lets us store the tree in a plain array,
 * level by level, left to right — no Node objects, no child pointers. For a
 * node at array index i:
 *
 *     parent(i)      = (i - 1) / 2     (integer division)
 *     leftChild(i)   = 2 * i + 1
 *     rightChild(i)  = 2 * i + 2
 *
 * The root lives at index 0, so peekMin() is just heap[0] (O(1)). insert and
 * extractMin each do at most ~log2(n) swaps (O(log n)) to restore the heap-order
 * property after a change.
 *
 * Fill in every // TODO. Work top to bottom and run the tester after each
 * milestone (see README.md Step 3). When stuck, read the Hints in README.md —
 * not an AI. The two tricky helpers, siftUp and siftDown, have their own
 * numbered steps and HINT comments below. These are very much doable.
 *
 *     javac MinHeap.java MinHeapTester.java
 *     java MinHeapTester
 */
public class MinHeap {

    /** Backing array. Heap elements live in indices 0 .. size-1. */
    private int[] heap;
    /** Number of elements currently in the heap (NOT heap.length). */
    private int size;

    /** Create an empty min-heap with a small starting capacity. */
    public MinHeap() {
        this.heap = new int[8];
        this.size = 0;
    }

    // ===================================================================
    // Tiny helpers — these are provided for you. Use them in your code.
    // ===================================================================

    /** @return the number of elements in the heap. */
    public int size() {
        return size;
    }

    /** @return true if the heap has no elements. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Swap the two array slots i and j. Use this in siftUp / siftDown. */
    private void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    /** Grow the backing array if it is full. Called for you by insert(). */
    private void ensureCapacity() {
        if (size == heap.length) {
            int[] bigger = new int[heap.length * 2];
            for (int i = 0; i < size; i++) bigger[i] = heap[i];
            heap = bigger;
        }
    }

    // ===================================================================
    // M1 — insert + siftUp
    // ===================================================================

    /**
     * Insert a value into the heap, keeping the heap-order property. O(log n).
     * Strategy: drop the new value into the next free slot (the end of the
     * array), then bubble it up until it is no longer smaller than its parent.
     * @param x the value to insert
     */
    public void insert(int x) {
        ensureCapacity(); // makes sure heap[size] is a valid slot to write into
        // TODO 1: place x at index `size` (the next free leaf at the end)
        // TODO 2: increment size
        // TODO 3: call siftUp on the index where you just placed x
        //         (that index is size - 1 now that you have incremented size)
    }

    /**
     * Bubble the value at index i UP toward the root until the heap-order
     * property holds. Called by insert() right after adding a new value at the
     * end. At most ~log2(n) iterations (one per level climbed).
     * @param i the index of the value to bubble up
     */
    private void siftUp(int i) {
        // HINT: a node at index i has its parent at index (i - 1) / 2.
        // TODO 1: loop while i > 0 (i.e. while you are NOT yet at the root):
        // TODO 2:   compute parent = (i - 1) / 2
        // TODO 3:   if heap[i] >= heap[parent] the value is in a legal spot — break
        // TODO 4:   otherwise swap(i, parent), then set i = parent and loop again
    }

    // ===================================================================
    // M2 — peekMin
    // ===================================================================

    /**
     * @return the smallest element (always at the root, index 0). O(1).
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public int peekMin() {
        // TODO 1: if the heap is empty, throw new java.util.NoSuchElementException()
        // TODO 2: return heap[0] (the root is always the minimum)
        return -1; // replace this
    }

    // ===================================================================
    // M3 — extractMin + siftDown
    // ===================================================================

    /**
     * Remove and return the smallest element (the root). O(log n).
     * Strategy: remember heap[0], move the LAST element up to the root, shrink
     * the heap by one, then bubble the new root down to a legal spot.
     * @return the removed minimum value
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public int extractMin() {
        // TODO 1: if the heap is empty, throw new java.util.NoSuchElementException()
        // TODO 2: remember the minimum: int min = heap[0]
        // TODO 3: special case — if size == 1, set size = 0 and return min
        //         (nothing left to sift)
        // TODO 4: move the last element to the root: heap[0] = heap[size - 1]
        // TODO 5: shrink the heap: size-- (the old last slot is no longer part of the heap)
        // TODO 6: call siftDown(0) to restore the heap-order property
        // TODO 7: return min
        return -1; // replace this
    }

    /**
     * Bubble the value at index i DOWN until the heap-order property holds.
     * Called by extractMin() right after moving the last element to the root.
     * Each step swaps with the SMALLER child (so both children end up >= parent).
     * At most ~log2(n) iterations (one per level descended).
     * @param i the index of the value to bubble down
     */
    private void siftDown(int i) {
        // HINT: a node at index i has children at left = 2*i + 1 and right = 2*i + 2.
        // HINT: the `< size` guards matter — a node may have 0, 1, or 2 children.
        // TODO 1: loop forever (you will break out from inside):
        // TODO 2:   compute left = 2*i + 1 and right = 2*i + 2
        // TODO 3:   start with smallest = i (assume current node is already smallest)
        // TODO 4:   if left  < size && heap[left]  < heap[smallest], set smallest = left
        // TODO 5:   if right < size && heap[right] < heap[smallest], set smallest = right
        // TODO 6:   if smallest == i, the value is <= both children — break
        // TODO 7:   otherwise swap(i, smallest), set i = smallest, and loop again
    }

    // ===================================================================
    // Provided helper for the tester (do not edit) — lets tests peek at the
    // raw array contents without exposing the backing store for mutation.
    // ===================================================================

    /** Package helper: a copy of the live heap region [0..size), for tests. */
    int[] toArrayInternal() {
        int[] out = new int[size];
        for (int i = 0; i < size; i++) out[i] = heap[i];
        return out;
    }
}
