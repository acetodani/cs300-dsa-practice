import java.util.NoSuchElementException;

/**
 * ArrayStack — a generic stack you build from scratch, backed by an array.
 *
 * A stack is a LIFO ("last in, first out") collection: the last thing you
 * `push` is the first thing you `pop` back out — like a stack of plates, where
 * you take from the top. The only end you ever touch is the "top".
 *
 *     push(a), push(b), push(c)   ->   top is c
 *     pop()  -> c                       top is b
 *     pop()  -> b                       top is a
 *
 * We store elements in a plain array and keep an int `size` that marks how many
 * slots are in use. The "top" of the stack is always at index `size - 1`. When
 * the array fills up, we grow it (double its capacity) so the stack can keep
 * accepting pushes — the caller never has to think about capacity.
 *
 * Fill in every // TODO. Work top to bottom and run the tester after each
 * milestone (see README.md Step 3). When stuck, read the Hints in README.md —
 * not an AI. You'll learn far more, and these are very much doable.
 *
 *     javac ArrayStack.java ArrayStackTester.java
 *     java ArrayStackTester
 *
 * @param <T> the type of element stored in this stack
 */
public class ArrayStack<T> {

    /** Capacity used by the no-arg constructor before any resize happens. */
    private static final int DEFAULT_CAPACITY = 4;

    /**
     * The backing array. Slots 0..size-1 hold the stack's elements (bottom to
     * top); slots size..length-1 are unused spare capacity.
     *
     * Note: Java does not let you write `new T[...]`, so we create an
     * Object[] and cast. This is the standard trick for a generic array-backed
     * collection. It is provided for you — do not change how `data` is created.
     */
    private T[] data;

    /** How many elements are currently in the stack (NOT data.length). */
    private int size;

    /** Create an empty stack with the default starting capacity. */
    @SuppressWarnings("unchecked")
    public ArrayStack() {
        this.data = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    // ===================================================================
    // M1 — push / pop / peek (the core stack operations)
    // ===================================================================

    /** @return the number of elements currently in the stack. */
    public int size() {
        // TODO 1: return the size field
        return -1; // replace this
    }

    /** @return true if the stack has no elements. */
    public boolean isEmpty() {
        // TODO 1: a stack is empty when size is 0 — return that boolean
        return false; // replace this
    }

    /**
     * Push a value onto the TOP of the stack. Amortized O(1).
     * @param value the value to add
     */
    public void push(T value) {
        // TODO 1: if the array is full (size == data.length), call resize()
        //         (you will write resize() in M2; for M1 you may start with a
        //          small stack that never fills, then come back and add this line)
        // TODO 2: store value at index `size` (that is the next free slot)
        // TODO 3: increment size
    }

    /**
     * Look at the top element WITHOUT removing it.
     * @return the value currently on top
     * @throws java.util.NoSuchElementException if the stack is empty
     */
    public T peek() {
        // TODO 1: if empty, throw new NoSuchElementException()
        // TODO 2: the top element is at index size - 1 — return it
        return null; // replace this
    }

    /**
     * Remove and return the top element. O(1).
     * @return the value that was on top
     * @throws java.util.NoSuchElementException if the stack is empty
     */
    public T pop() {
        // TODO 1: if empty, throw new NoSuchElementException()
        // TODO 2: remember the top value (index size - 1)
        // TODO 3: set data[size - 1] = null so we don't hold onto the object
        //         (this lets the garbage collector reclaim it — good habit)
        // TODO 4: decrement size
        // TODO 5: return the remembered value
        return null; // replace this
    }

    // ===================================================================
    // M2 — auto-resize (grow the backing array when it fills up)
    // ===================================================================

    /**
     * Double the capacity of the backing array, copying existing elements over.
     * Called by push() when the array is full. The visible contents and order
     * of the stack must NOT change — only the spare capacity grows.
     *
     * Time is O(n) for the copy, but it happens rarely, so push stays O(1) on
     * average ("amortized"). See README.md for why doubling is the right move.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        // TODO 1: compute the new capacity = data.length * 2
        //         (HINT: if data.length could ever be 0, use Math.max(1, ...);
        //          with DEFAULT_CAPACITY = 4 it never is, so plain *2 is fine.)
        // TODO 2: create a new array: T[] bigger = (T[]) new Object[newCapacity];
        // TODO 3: copy data[0..size-1] into bigger (a simple for loop, or
        //         System.arraycopy(data, 0, bigger, 0, size))
        // TODO 4: assign data = bigger  (size does NOT change)
    }

    // ===================================================================
    // Provided helper for the tester (do not edit) — lets a test inspect the
    // backing array's capacity so it can verify a resize happened.
    // ===================================================================

    /** Package helper: current capacity of the backing array (data.length). */
    int capacityInternal() {
        return data.length;
    }
}
