import java.util.PriorityQueue;

/**
 * HeapProblems — two classic interview problems solved with a heap.
 *
 * You may use the MinHeap you just built, but the RECOMMENDED tool here is
 * java.util.PriorityQueue. Remember: a PriorityQueue IS a heap underneath. By
 * default it is a MIN-heap (smallest element comes out first), so:
 *
 *     PriorityQueue<Integer> pq = new PriorityQueue<>();
 *     pq.offer(x);          // insert  (also called add)
 *     int min  = pq.peek(); // look at the smallest without removing
 *     int gone = pq.poll(); // remove and return the smallest
 *     int n    = pq.size(); // how many elements are in the heap
 *
 * To get a MAX-heap (largest first), pass a reversed comparator:
 *
 *     PriorityQueue<Integer> maxPq =
 *         new PriorityQueue<>(java.util.Collections.reverseOrder());
 *
 * Fill in every // TODO. Work top to bottom and run the tester after each
 * milestone (see README.md Step 3). When stuck, read the Hints in README.md.
 *
 *     javac MinHeap.java HeapProblems.java HeapProblemsTester.java
 *     java HeapProblemsTester
 */
public class HeapProblems {

    // ===================================================================
    // M4 — kthSmallest
    // ===================================================================

    /**
     * Return the kth SMALLEST element of the array (k is 1-based: k = 1 means
     * the single smallest, k = 2 the second smallest, and so on).
     *
     * Simple heap approach: push every element into a min-heap, then poll()
     * k times. The kth poll is your answer because a min-heap hands elements
     * back in ascending order.
     *
     * @param a the input array (assume it has at least k elements, k >= 1)
     * @param k which smallest to return (1-based)
     * @return the kth smallest value
     */
    public static int kthSmallest(int[] a, int k) {
        // TODO 1: create a min-heap: PriorityQueue<Integer> pq = new PriorityQueue<>();
        // TODO 2: loop over every value in `a` and pq.offer(value)
        // TODO 3: poll() exactly (k - 1) times to discard the k-1 smallest
        // TODO 4: return pq.poll() — that is the kth smallest
        // HINT: an int auto-unboxes from the Integer that poll() returns.
        return -1; // replace this
    }

    // ===================================================================
    // M5 — topKLargest
    // ===================================================================

    /**
     * Return the k LARGEST values of the array, as a NEW int[] sorted in
     * ASCENDING order. This is the classic "top K" heap pattern: keep a
     * min-heap of size at most k. The smallest of your current best k sits at
     * the top, ready to be evicted the moment a bigger candidate appears.
     *
     * Example: topKLargest({3, 1, 5, 12, 2, 11}, 3) returns {5, 11, 12}.
     *
     * @param a the input array (assume it has at least k elements, k >= 1)
     * @param k how many of the largest values to return
     * @return a new int[] of length k holding the k largest values, ascending
     */
    public static int[] topKLargest(int[] a, int k) {
        // TODO 1: create a min-heap: PriorityQueue<Integer> pq = new PriorityQueue<>();
        // TODO 2: for each value in `a`:
        //           - pq.offer(value)
        //           - if pq.size() > k, pq.poll() to drop the smallest so far
        //         (after the loop the heap holds exactly the k largest values)
        // TODO 3: make an int[] result of length k
        // TODO 4: fill result by polling the heap. A min-heap polls smallest-first,
        //         which is exactly ascending order — so fill result[0], result[1], ...
        //         in poll order.
        // TODO 5: return result
        // HINT: java.util.Arrays.sort(result) also works if you prefer to dump the
        //       heap in any order and sort at the end.
        return new int[0]; // replace this
    }
}
