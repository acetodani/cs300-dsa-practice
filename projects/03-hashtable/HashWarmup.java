/**
 * HashWarmup — tiny guided drills to get the "hashing arithmetic" into your
 * fingers BEFORE you build the full hash table.
 *
 * The whole trick behind a hash table is turning a key into an ARRAY INDEX:
 *
 *     bucket index = Math.floorMod(key.hashCode(), tableLength)
 *
 * These drills make that one line second nature, then have you reason about
 * COLLISIONS (two different keys landing in the same bucket). Fill in the
 * TODOs, then run:
 *
 *     javac HashWarmup.java HashWarmupTester.java
 *     java HashWarmupTester
 *
 * These are deliberately small. If one takes more than a few minutes, re-read
 * README.md (Step 1) and the Hints section — don't reach for an AI.
 */
public class HashWarmup {

    /**
     * Drill 1 — Bucket index for an int key.
     * Compute which bucket an integer key lands in for a table of `tableLength`
     * buckets. Use Math.floorMod so the answer is always in 0..tableLength-1,
     * even when the key is negative. (An int's hashCode() is the int itself,
     * so for this drill you can hash the int directly.)
     *
     * Example: bucketIndex(5, 4) == 1, because 5 % 4 == 1.
     * Example: bucketIndex(-1, 4) == 3, because floorMod(-1, 4) == 3.
     */
    public static int bucketIndex(int key, int tableLength) {
        // TODO 1: return Math.floorMod(key, tableLength)
        return -1; // replace this
    }

    /**
     * Drill 2 — Bucket index for any Object key (e.g. a String).
     * Real keys are objects, so first call key.hashCode() to get an int, THEN
     * floorMod it into range. This is exactly the line MyHashMap will use.
     *
     * Example: for a table of length 8, bucketIndexOf("cat", 8) is
     * Math.floorMod("cat".hashCode(), 8).
     */
    public static int bucketIndexOf(Object key, int tableLength) {
        // TODO 1: get the key's hash code with key.hashCode()
        // TODO 2: return Math.floorMod(thatHashCode, tableLength)
        return -1; // replace this
    }

    /**
     * Drill 3 — Do two int keys collide?
     * Two keys "collide" when they map to the SAME bucket. Return true if keys
     * `a` and `b` land in the same bucket of a table of `tableLength` buckets.
     *
     * Example: collide(1, 5, 4) is true (both land in bucket 1).
     * Example: collide(1, 2, 4) is false (buckets 1 and 2).
     */
    public static boolean collide(int a, int b, int tableLength) {
        // TODO 1: compute the bucket index of a (reuse bucketIndex)
        // TODO 2: compute the bucket index of b
        // TODO 3: return whether the two indices are equal
        return false; // replace this
    }

    /**
     * Drill 4 — Count collisions when filling a table.
     * Imagine inserting each int in `keys` into a table of `tableLength`
     * buckets, in order. A "collision" happens whenever a key lands in a bucket
     * that ALREADY has at least one key in it. Return the total number of such
     * collisions.
     *
     * Example: keys {1, 5, 2} with tableLength 4 ->
     *   1 lands in bucket 1 (empty, no collision),
     *   5 lands in bucket 1 (already occupied, +1 collision),
     *   2 lands in bucket 2 (empty, no collision).
     *   Total collisions = 1.
     *
     * Tip: keep a boolean[] of length tableLength to remember which buckets are
     * already occupied. Mark a bucket occupied AFTER you check it.
     */
    public static int countCollisions(int[] keys, int tableLength) {
        // TODO 1: create a boolean[] occupied of length tableLength (all false)
        // TODO 2: create an int counter starting at 0
        // TODO 3: for each key, compute its bucket index (reuse bucketIndex)
        // TODO 4:   if occupied[index] is already true, add 1 to your counter
        // TODO 5:   then set occupied[index] = true
        // TODO 6: return the counter
        return -1; // replace this
    }
}
