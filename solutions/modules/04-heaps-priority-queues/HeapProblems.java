import java.util.PriorityQueue;

/** HeapProblems — reference solution. Two "top K" problems solved with a heap. */
public class HeapProblems {

    // ---- M4 ----
    public static int kthSmallest(int[] a, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int x : a) pq.offer(x);
        for (int i = 0; i < k - 1; i++) pq.poll();
        return pq.poll();
    }

    // ---- M5 ----
    public static int[] topKLargest(int[] a, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int x : a) {
            pq.offer(x);
            if (pq.size() > k) pq.poll();
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) result[i] = pq.poll(); // min-heap polls ascending
        return result;
    }
}
