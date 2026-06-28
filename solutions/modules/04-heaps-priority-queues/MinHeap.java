/** MinHeap — reference solution. A min-heap of ints backed by an array. */
public class MinHeap {

    private int[] heap;
    private int size;

    public MinHeap() {
        this.heap = new int[8];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    private void ensureCapacity() {
        if (size == heap.length) {
            int[] bigger = new int[heap.length * 2];
            for (int i = 0; i < size; i++) bigger[i] = heap[i];
            heap = bigger;
        }
    }

    // ---- M1 ----
    public void insert(int x) {
        ensureCapacity();
        heap[size] = x;
        size++;
        siftUp(size - 1);
    }

    private void siftUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap[i] >= heap[parent]) break;
            swap(i, parent);
            i = parent;
        }
    }

    // ---- M2 ----
    public int peekMin() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        return heap[0];
    }

    // ---- M3 ----
    public int extractMin() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int min = heap[0];
        if (size == 1) {
            size = 0;
            return min;
        }
        heap[0] = heap[size - 1];
        size--;
        siftDown(0);
        return min;
    }

    private void siftDown(int i) {
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int smallest = i;
            if (left < size && heap[left] < heap[smallest]) smallest = left;
            if (right < size && heap[right] < heap[smallest]) smallest = right;
            if (smallest == i) break;
            swap(i, smallest);
            i = smallest;
        }
    }

    int[] toArrayInternal() {
        int[] out = new int[size];
        for (int i = 0; i < size; i++) out[i] = heap[i];
        return out;
    }
}
