/**
 * MyHashSet — a generic SET built on top of the hash map you already wrote.
 *
 * A set stores DISTINCT elements with no duplicates and answers "is this in the
 * set?" in (on average) O(1) time. Sound familiar? That is exactly what the
 * KEYS of a hash map already do. So the easiest, most reliable way to build a
 * set is to wrap a MyHashMap and ignore the values:
 *
 *     element present  <=>  the map has that element as a KEY
 *
 * We store each element as a key mapped to a single shared dummy value. (Java's
 * own java.util.HashSet is implemented this way on top of HashMap.)
 *
 * This is the M5 stretch. Fill in every // TODO. Run:
 *
 *     javac MyHashMap.java MyHashSet.java MyHashSetTester.java
 *     java MyHashSetTester
 *
 * When stuck, read the Hints in README.md — not an AI.
 *
 * @param <E> the type of elements held in this set
 */
public class MyHashSet<E> {

    /** A single placeholder value; every element-key maps to this. */
    private static final Object PRESENT = new Object();

    /** The backing map: element -> PRESENT. Membership == "is a key here". */
    private final MyHashMap<E, Object> map;

    /** Create an empty set. */
    public MyHashSet() {
        // TODO 1: initialize map = new MyHashMap<>()
        this.map = null; // replace this
    }

    /**
     * Add an element. If it is already present, the set is unchanged.
     * @param element the element to add
     * @return true if the element was newly added, false if it was already present
     */
    public boolean add(E element) {
        // TODO 1: if map.containsKey(element) is already true, return false
        //         (no duplicate added, set unchanged)
        // TODO 2: otherwise map.put(element, PRESENT) and return true
        return false; // replace this
    }

    /**
     * @param element the element to test
     * @return true if the element is in the set
     */
    public boolean contains(E element) {
        // TODO 1: return map.containsKey(element)
        return false; // replace this
    }

    /**
     * Remove an element if present.
     * @param element the element to remove
     * @return true if the element was present (and is now removed), false otherwise
     */
    public boolean remove(E element) {
        // TODO 1: if the element is not present (use contains), return false
        // TODO 2: otherwise map.remove(element) and return true
        // HINT: map.remove returns the old value (PRESENT) or null. You could
        //       use that directly, but the contains-then-remove version above is
        //       perfectly clear.
        return false; // replace this
    }

    /** @return the number of elements in the set. */
    public int size() {
        // TODO 1: return map.size()
        return -1; // replace this
    }

    /** @return true if the set has no elements. */
    public boolean isEmpty() {
        // TODO 1: return map.isEmpty()
        return false; // replace this
    }
}
