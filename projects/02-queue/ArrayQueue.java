/**
 * ArrayQueue — a generic, circular array-backed queue you build from scratch
 * (the "Core" part of this project).
 *
 * A queue is FIFO: you enqueue at the back and dequeue from the front. We store
 * the elements in a plain array and treat that array as a CIRCLE. Two fields do
 * the bookkeeping:
 *
 *   - `front`  — the index of the element that will be dequeued next.
 *   - `count`  — how many elements are currently in the queue.
 *
 * The back of the queue is never stored directly; it is computed as
 * (front + count) % capacity. That `% capacity` is the wrap-around trick: when
 * an index passes the last slot it wraps back to slot 0, so the array behaves
 * like an endless conveyor belt and we never have to shift elements.
 *
 * When the array fills up (count == capacity) we double its size in resize().
 *
 * Java cannot create a `new T[...]`, so we allocate an Object[] and cast on the
 * way out — this is done for you in a couple of spots; follow the pattern.
 *
 * Fill in every // TODO. Work top to bottom and run the tester after each
 * milestone (see README.md Step 3). When stuck, read the Hints in README.md —
 * not an AI. You'll learn far more, and these are very much doable.
 *
 *     javac ArrayQueue.java ArrayQueueTester.java
 *     java ArrayQueueTester
 *
 * @param <T> the type of element stored in this queue
 */
public class ArrayQueue<T> {

    private Object[] array;   // backing storage; capacity == array.length
    private int front;        // index of the next element to dequeue
    private int count;        // number of elements currently in the queue

    /** Start with a small capacity so resize() actually gets exercised. */
    public ArrayQueue() {
        this.array = new Object[4];
        this.front = 0;
        this.count = 0;
    }

    // ===================================================================
    // M1 — basics (assume there is room; no wrap or resize needed yet)
    // ===================================================================

    /** @return the number of elements in the queue. */
    public int size() {
        // TODO 1: return the count field
        return -1; // replace this
    }

    /** @return true if the queue has no elements. */
    public boolean isEmpty() {
        // TODO 1: a queue is empty when count is 0 — return that boolean
        return false; // replace this
    }

    /**
     * Add a value to the BACK of the queue.
     * @param value the value to add
     */
    public void enqueue(T value) {
        // TODO 1: if count == array.length the array is full — call resize() first
        // TODO 2: compute the next free slot index: (front + count) % array.length
        //         HINT: the % is what makes the index wrap past the end back to 0
        // TODO 3: store value at that index in the array
        // TODO 4: increment count
        //         (note: front does NOT change on an enqueue)
    }

    /**
     * Remove and return the value at the FRONT of the queue.
     * @return the removed value
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    @SuppressWarnings("unchecked")
    public T dequeue() {
        // TODO 1: if empty, throw new java.util.NoSuchElementException()
        // TODO 2: remember the value at array[front] (cast it: (T) array[front])
        // TODO 3: optionally null out array[front] so we don't hold a stale reference
        // TODO 4: advance the front with wrap-around: front = (front + 1) % array.length
        // TODO 5: decrement count and return the remembered value
        return null; // replace this
    }

    /**
     * Look at the value at the FRONT without removing it.
     * @return the front value
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    @SuppressWarnings("unchecked")
    public T peek() {
        // TODO 1: if empty, throw new java.util.NoSuchElementException()
        // TODO 2: return the value at array[front], cast to (T)
        return null; // replace this
    }

    // ===================================================================
    // M2 — circular wrap + resize
    //
    // If you followed the modulo hints above, enqueue/dequeue already wrap.
    // The remaining piece is growing the array when it fills. resize() is
    // called by enqueue when count == array.length.
    // ===================================================================

    /**
     * Grow the backing array (double the capacity) and re-pack the elements so
     * the front sits at index 0 again. Called automatically by enqueue when the
     * array is full.
     *
     * The tricky part: when full, the elements may be WRAPPED — they are not
     * necessarily at indexes 0..count-1. So copy them out in logical order.
     */
    private void resize() {
        // TODO 1: make a new Object[] of size array.length * 2
        // TODO 2: for i from 0 to count - 1, copy the i-th logical element:
        //           newArray[i] = array[(front + i) % array.length]
        //         HINT: this "unwraps" the queue so element 0 lands at slot 0
        // TODO 3: set array = newArray
        // TODO 4: set front = 0  (the front is now at the start of the new array)
        //         (count does not change — you didn't add or remove elements)
    }

    /** @return the current capacity (array length). Provided for the tester. */
    public int capacity() {
        return array.length;
    }

    /**
     * @return a String like "[a, b, c]" in FRONT-to-BACK order ("[]" if empty).
     * Provided for you as a reference for how to walk a circular queue.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < count; i++) {
            sb.append(array[(front + i) % array.length]);
            if (i < count - 1) sb.append(", ");
        }
        return sb.append("]").toString();
    }
}
