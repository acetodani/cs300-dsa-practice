/**
 * MyHashMap — a generic hash map you build from scratch using SEPARATE CHAINING.
 *
 * THE BIG IDEA
 * ------------
 * A hash map stores key -> value pairs and looks them up in (on average) O(1)
 * time. How? It keeps an ARRAY of "buckets". To find where a key belongs:
 *
 *   1. Ask the key for its hash code:        int h = key.hashCode();
 *   2. Squeeze that into a valid array index: int i = Math.floorMod(h, buckets.length);
 *
 * `hashCode()` is just an int the object computes from its contents (every Java
 * object has one). Modding by the array length wraps that potentially-huge (or
 * negative!) number into a real index, 0 .. buckets.length-1.
 *
 * WHY Math.floorMod AND NOT % ?
 * Java's `%` can return a NEGATIVE result when the hash code is negative
 * (e.g. -7 % 4 == -3), and a negative array index crashes with
 * ArrayIndexOutOfBoundsException. Math.floorMod always returns a value with the
 * same sign as the divisor (a positive length), so the index is always valid.
 *
 * COLLISIONS
 * Two different keys can produce the same bucket index — that's a COLLISION.
 * We handle it with "separate chaining": each bucket is the head of a small
 * linked list (a chain) of Entry nodes. A collision just appends to that chain.
 * To find/update/remove a key we walk its bucket's chain comparing with equals().
 *
 *     buckets[0] -> null
 *     buckets[1] -> [k1|v1] -> [k5|v5] -> null   (k1 and k5 collided here)
 *     buckets[2] -> [k2|v2] -> null
 *     buckets[3] -> null
 *
 * Fill in every // TODO. Work top to bottom and run the tester after each
 * milestone (see README.md Step 3). When stuck, read the Hints in README.md —
 * not an AI. You'll learn far more, and these are very much doable.
 *
 *     javac MyHashMap.java MyHashMapTester.java
 *     java MyHashMapTester
 *
 * @param <K> the type of keys
 * @param <V> the type of values
 */
public class MyHashMap<K, V> {

    /**
     * One key->value pair living in a bucket's chain. `next` links to the next
     * entry in the SAME bucket (or null at the end of the chain).
     */
    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    /** Default number of buckets when none is given. */
    private static final int DEFAULT_CAPACITY = 16;

    /** When size/capacity exceeds this, we grow (M4). */
    private static final double MAX_LOAD_FACTOR = 0.75;

    /** The array of buckets. buckets[i] is the head of bucket i's chain (or null). */
    private Entry<K, V>[] buckets;

    /** How many key->value pairs are currently stored. */
    private int size;

    /** Create a map with the default number of buckets. */
    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Create a map with a specific starting number of buckets. The tester uses
     * a SMALL capacity (like 4) on purpose so that different keys collide and
     * you can see chaining work.
     * @param capacity the initial number of buckets (must be >= 1)
     */
    @SuppressWarnings("unchecked")
    public MyHashMap(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException("capacity must be >= 1");
        // We can't write `new Entry<K,V>[capacity]` (Java generics + arrays),
        // so we make a raw Entry[] and cast. This is provided for you.
        this.buckets = (Entry<K, V>[]) new Entry[capacity];
        this.size = 0;
    }

    /**
     * Compute the bucket index for a key. This is THE core hashing step.
     * Provided for you — call it from put/get/containsKey/remove so they all
     * agree on where a key lives.
     * @param key the key to locate
     * @return an index in 0 .. buckets.length-1
     */
    private int bucketIndexFor(K key) {
        return Math.floorMod(key.hashCode(), buckets.length);
    }

    // ===================================================================
    // M1 — put / get / size  (the heart of a hash map)
    // ===================================================================

    /** @return the number of key->value pairs stored. */
    public int size() {
        // TODO 1: return the size field
        return -1; // replace this
    }

    /** @return true if the map has no entries. */
    public boolean isEmpty() {
        // TODO 1: a map is empty when size is 0 — return that boolean
        return false; // replace this
    }

    /**
     * Insert a key->value pair. If the key is ALREADY present, update its value
     * (and do NOT change size). Otherwise add a new entry to the front of the
     * key's bucket chain and increment size.
     *
     * @param key   the key (must not be null for this simple map)
     * @param value the value to associate with key
     */
    public void put(K key, V value) {
        // TODO 1: find the bucket index: int i = bucketIndexFor(key)
        // TODO 2: walk the chain starting at buckets[i]; for each entry, if its
        //         key equals the given key (use key.equals(entry.key)), set
        //         entry.value = value and RETURN (this is the "update" case —
        //         size does not change)
        // TODO 3: if you reached the end of the chain, the key is new. Create
        //         a new Entry<>(key, value), set its next = buckets[i], and set
        //         buckets[i] = the new entry (insert at the front of the chain)
        // TODO 4: increment size
        // TODO 5 (come back here in M4): after inserting, if the load factor
        //         (size / (double) buckets.length) exceeds MAX_LOAD_FACTOR,
        //         call resize(). Skip this until you reach M4.
    }

    /**
     * Look up the value for a key.
     * @param key the key to find
     * @return the associated value, or null if the key is not present
     */
    public V get(K key) {
        // TODO 1: find the bucket index with bucketIndexFor(key)
        // TODO 2: walk that bucket's chain; if an entry's key equals the given
        //         key, return entry.value
        // TODO 3: if you reach the end without a match, return null
        return null; // replace this
    }

    // ===================================================================
    // M2 — containsKey / remove
    // ===================================================================

    /**
     * @param key the key to test
     * @return true if the map contains an entry for this key
     */
    public boolean containsKey(K key) {
        // TODO 1: find the bucket index with bucketIndexFor(key)
        // TODO 2: walk that bucket's chain; return true if some entry's key
        //         equals the given key
        // TODO 3: return false if you reach the end with no match
        // HINT: this is almost identical to get(); you may even reason about it
        //       the same way. (Note: get returning null is ambiguous if a value
        //       can itself be null, which is why containsKey exists separately.)
        return false; // replace this
    }

    /**
     * Remove the entry for a key, if present.
     * @param key the key to remove
     * @return the value that was associated with key, or null if key was absent
     */
    public V remove(K key) {
        // TODO 1: find the bucket index with bucketIndexFor(key)
        // TODO 2: this is a single-linked-list removal inside the chain. Keep a
        //         `prev` reference (starts null) and a `current` reference
        //         (starts at buckets[i]). Walk forward.
        // TODO 3: when current's key equals the given key, you found it:
        //           - if prev == null, the match is the head of the chain, so
        //             set buckets[i] = current.next
        //           - otherwise, splice it out: prev.next = current.next
        //           - decrement size and return current.value
        // TODO 4: advance: prev = current; current = current.next
        // TODO 5: if you reach the end with no match, return null
        return null; // replace this
    }

    // ===================================================================
    // M3 — collisions are already handled!
    //
    // Notice you did NOT write any special "collision" code. Separate chaining
    // means a collision is just "the bucket's chain has more than one entry".
    // Because put/get/containsKey/remove all WALK the chain comparing with
    // equals(), colliding keys coexist correctly. The M3 tests use a tiny
    // capacity so multiple keys share a bucket — if M1/M2 are correct, M3
    // passes for free. (If it doesn't, you probably overwrote buckets[i]
    // instead of linking onto the existing chain in put.)
    // ===================================================================

    // ===================================================================
    // M4 — STRETCH: resize / rehash when the table gets crowded
    //
    // LOAD FACTOR = size / number_of_buckets. As it climbs past ~0.75, chains
    // get long and lookups slow toward O(n). The fix: allocate a bigger bucket
    // array and RE-INSERT every entry (its bucket index changes because the
    // array length changed — that's why we must rehash, not just copy).
    // ===================================================================

    /** @return the current number of buckets (capacity). Useful for tests/insight. */
    public int capacity() {
        // TODO 1: return buckets.length
        return -1; // replace this
    }

    /** @return the current load factor, size / capacity, as a double. */
    public double loadFactor() {
        // TODO 1: return size / (double) buckets.length
        return -1; // replace this
    }

    /**
     * Double the number of buckets and re-insert every existing entry into the
     * new, larger table. Call this from put() when loadFactor() exceeds
     * MAX_LOAD_FACTOR.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        // TODO 1: remember the old array: Entry<K,V>[] old = buckets
        // TODO 2: allocate a new array twice as long:
        //         buckets = (Entry<K,V>[]) new Entry[old.length * 2];
        // TODO 3: reset size to 0 (you will re-count as you re-insert)
        // TODO 4: for each chain head in `old`, walk the chain and for every
        //         entry call put(entry.key, entry.value). Because buckets now
        //         points at the NEW bigger array, bucketIndexFor recomputes the
        //         correct new index for each key (this is the "rehash").
        // HINT: it is safe to reuse put() here. put() will recompute the index
        //       against the new array and increment size back up. Just make sure
        //       you set `buckets` to the new array (TODO 2) BEFORE calling put.
        // HINT: put() also checks the load factor and could call resize() again,
        //       but right after doubling the table the load factor is well under
        //       0.75, so it won't recurse. No special guard needed.
    }
}
