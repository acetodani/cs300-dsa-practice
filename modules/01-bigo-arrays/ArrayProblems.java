/**
 * ArrayProblems — guided drills for Big-O reasoning and core 1-D array skills.
 *
 * These are the kinds of array moves you will reuse in EVERY later module:
 * walking an array, searching it, swapping elements in place, and building a
 * new array from an old one. As you fill in each method, read the Javadoc line
 * that states its time complexity (e.g. "O(n) time, O(1) extra space") and make
 * sure you can see WHY the code has that shape.
 *
 * Fill in the TODOs, then run:
 *
 *     javac ArrayProblems.java ArrayProblemsTester.java
 *     java ArrayProblemsTester
 *
 * Work top to bottom — the drills go easy to hard. If one stalls you for more
 * than a few minutes, re-read README.md (Step 1) and the Hints section — don't
 * reach for an AI.
 */
import java.util.NoSuchElementException;

public class ArrayProblems {

    /**
     * Drill 1 — Sum the array.
     * Return the sum of every element. An empty array sums to 0.
     *
     * Complexity: O(n) time, O(1) extra space.
     * (You touch each of the n elements once; you only keep one running total.)
     */
    public static int sum(int[] a) {
        // TODO 1: create an int `total` starting at 0
        // TODO 2: loop over every element of `a` and add it to `total`
        // TODO 3: return `total`
        // HINT: a for-each loop reads nicely here: for (int value : a) { ... }
        return -1; // replace this
    }

    /**
     * Drill 2 — Largest element.
     * Return the largest value in the array.
     *
     * If the array is empty there is no largest element, so throw a
     * java.util.NoSuchElementException (it is already imported above).
     *
     * Complexity: O(n) time, O(1) extra space.
     */
    public static int max(int[] a) {
        // TODO 1: if a.length == 0, throw new NoSuchElementException("empty array")
        // TODO 2: create an int `best` and set it to the FIRST element a[0]
        //         (do NOT start it at 0 — the array could be all negatives)
        // TODO 3: loop over the remaining elements; whenever you see a bigger
        //         value, update `best`
        // TODO 4: return `best`
        // HINT: starting `best` at a[0] is the classic "seed with the first
        //       element" trick. Starting at 0 is the most common max() bug.
        return -1; // replace this
    }

    /**
     * Drill 3 — Reverse in place (two pointers).
     * Reverse the order of elements WITHOUT creating a new array. You modify the
     * array the caller gave you.
     *
     * The two-pointer idea: put one index `front` at the start and one index
     * `back` at the end. Swap those two elements, then step `front` forward and
     * `back` backward. Stop when they meet in the middle.
     *
     * Complexity: O(n) time, O(1) extra space.
     */
    public static void reverseInPlace(int[] a) {
        // TODO 1: create int front = 0 and int back = a.length - 1
        // TODO 2: while front < back:
        //         - save a[front] in a temp variable
        //         - copy a[back] into a[front]
        //         - copy the temp into a[back]
        //         - then front++ and back--
        // HINT: you need the temp variable because once you overwrite a[front]
        //       you have lost its old value. Trace {1,2,3,4} on paper:
        //       swap ends -> {4,2,3,1}, then swap middle -> {4,3,2,1}.
        // (no return — this method is void and edits the array directly)
    }

    /**
     * Drill 4 — Linear search.
     * Return true if `target` appears anywhere in the array, false otherwise.
     *
     * Complexity: O(n) time, O(1) extra space.
     * (In the worst case the target is absent and you scan all n elements.)
     */
    public static boolean contains(int[] a, int target) {
        // TODO 1: loop over every element of `a`
        // TODO 2: if the current element equals `target`, return true immediately
        // TODO 3: if the loop finishes without finding it, return false
        // HINT: returning true as soon as you find a match is an "early return".
        //       You do not need to keep scanning once you have your answer.
        return false; // replace this
    }

    /**
     * Drill 5 — Second largest DISTINCT value.
     * Return the second-largest value, counting each distinct value once. For
     * {5, 9, 9, 7} the largest distinct value is 9 and the second largest is 7.
     *
     * If there is NO distinct second largest — because the array is empty, has
     * one element, or every element is the same (e.g. {4, 4, 4}) — throw a
     * java.util.NoSuchElementException.
     *
     * Complexity: O(n) time, O(1) extra space.
     * (One pass while remembering the best two distinct values — no sorting.)
     */
    public static int secondLargest(int[] a) {
        // TODO 1: track two values: `largest` and `second`. A clean way is to
        //         start both at Integer.MIN_VALUE and use two booleans (or a
        //         count) to know whether you have actually seen real values yet.
        // TODO 2: loop over each element `v`:
        //         - if `v` is greater than `largest`: `second` becomes the old
        //           `largest`, and `largest` becomes `v`
        //         - else if `v` is less than `largest` AND greater than `second`:
        //           `second` becomes `v`
        //         - if `v` equals `largest`, skip it (duplicates of the max must
        //           NOT count as the second largest)
        // TODO 3: if you never found a valid distinct second value, throw
        //         new NoSuchElementException("no distinct second largest")
        // TODO 4: otherwise return `second`
        // HINT: the trap is duplicates. {5, 5, 3} should return 3, not 5. That
        //       is why the "else if" requires v to be strictly LESS than largest.
        return -1; // replace this
    }

    /**
     * Drill 6 — Remove duplicates from a SORTED array.
     * The input is already sorted ascending (e.g. {1, 1, 2, 3, 3, 3, 5}). Return
     * a NEW array containing each value once, in order: {1, 2, 3, 5}.
     * An empty input returns an empty array.
     *
     * This is the array flavor of LeetCode #26.
     *
     * Complexity: O(n) time, O(n) extra space (for the new array you return).
     */
    public static int[] removeDuplicatesSorted(int[] sorted) {
        // TODO 1: handle the empty case: if sorted.length == 0 return new int[0]
        // TODO 2: FIRST PASS — count how many distinct values there are. Because
        //         the array is sorted, duplicates sit next to each other: a value
        //         is "new" if it is the first element OR it differs from the
        //         element just before it (sorted[i] != sorted[i - 1]).
        // TODO 3: allocate the result array with that exact count:
        //         int[] result = new int[count];
        // TODO 4: SECOND PASS — copy each "new" value into the next open slot of
        //         result (keep a separate write index that you advance only when
        //         you copy).
        // TODO 5: return result
        // HINT: being sorted is the whole trick — equal values are neighbors, so
        //       you only ever compare an element with the one immediately before
        //       it. On an UNSORTED array this approach would not work.
        return null; // replace this
    }

    /**
     * Drill 7 — Rotate left by k (into a new array).
     * Shift every element k positions toward the front, wrapping the ones that
     * fall off the front around to the back. Return a NEW array (do not modify
     * the input).
     *
     * Example: rotateLeft({1, 2, 3, 4, 5}, 2) -> {3, 4, 5, 1, 2}.
     *
     * `k` may be larger than the array length, so reduce it with modulo first
     * (rotating a length-5 array by 7 is the same as rotating by 2). An empty
     * array returns a new empty array.
     *
     * Complexity: O(n) time, O(n) extra space (for the new array you return).
     */
    public static int[] rotateLeft(int[] a, int k) {
        // TODO 1: if a.length == 0, return new int[0] (avoid dividing by zero in
        //         the next step — % by 0 throws)
        // TODO 2: reduce k with modulo: k = k % a.length;
        //         now 0 <= k < a.length
        // TODO 3: create int[] result = new int[a.length]
        // TODO 4: for each index i in `a`, the element a[i] lands at index
        //         (i - k + a.length) % a.length in result. (Adding a.length
        //         before the % keeps the index from going negative.)
        // TODO 5: return result
        // HINT: if the modular index math is confusing, try the other direction:
        //       for each destination index j, it should receive a[(j + k) % a.length].
        //       Pick whichever you can explain to yourself, and trace the example.
        return null; // replace this
    }
}
