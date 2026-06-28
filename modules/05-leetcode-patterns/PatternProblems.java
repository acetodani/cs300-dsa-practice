import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * PatternProblems — the CAPSTONE drills.
 *
 * Most interview / LeetCode questions are NOT new data structures. They are a
 * handful of repeating PATTERNS layered on top of the structures you already
 * built (lists, arrays, maps, sets). Learn the pattern once and you can solve a
 * whole family of problems.
 *
 * This file groups drills by PATTERN. Each method's Javadoc names the pattern it
 * teaches. Fill in the numbered // TODO steps, lean on the // HINT comments when
 * stuck, then run:
 *
 *     javac PatternProblems.java PatternProblemsTester.java
 *     java PatternProblemsTester
 *
 * You may use java.util collections here (HashMap, HashSet, ArrayDeque, ...).
 * That is the point — you understand how they work internally now, so use them.
 *
 * Get all tests green, then go solve the same patterns ON leetcode.com. See
 * README.md Step 4.
 */
public class PatternProblems {

    // =====================================================================
    // PATTERN: Two Pointers
    // Two indices walk the data — often from opposite ends toward the middle,
    // or one chasing the other. Lets you scan in O(n) without a nested loop.
    // =====================================================================

    /**
     * Drill 1 — Two Sum II (input is SORTED). PATTERN: Two Pointers.
     *
     * Given an array sorted in ascending order, return the two indices
     * {i, j} (with i &lt; j) whose values add up to `target`. If no pair
     * works, return {-1, -1}.
     *
     * Because the array is sorted, you do NOT need a hash map: start one pointer
     * at the far left and one at the far right and let the sum guide you.
     *
     * Example: sorted = {1, 3, 4, 5, 7, 11}, target = 9 -> {2, 3} (4 + 5).
     *
     * LeetCode #167.
     */
    public static int[] twoSumSorted(int[] sorted, int target) {
        // TODO 1: create `left` = 0 and `right` = sorted.length - 1
        // TODO 2: while left < right:
        //           - compute sum = sorted[left] + sorted[right]
        //           - if sum == target, return new int[]{left, right}
        //           - HINT: if the sum is TOO SMALL you need a bigger value,
        //             so move `left` rightward (left++). If the sum is TOO BIG
        //             you need a smaller value, so move `right` leftward (right--).
        // TODO 3: if the loop ends with no match, return new int[]{-1, -1}
        return new int[]{-1, -1}; // replace this
    }

    /**
     * Drill 2 — Valid Palindrome. PATTERN: Two Pointers.
     *
     * Return true if `s` reads the same forwards and backwards. Assume `s`
     * contains only lowercase letters (no spaces / punctuation to clean up).
     *
     * Example: "racecar" -> true, "hello" -> false, "" -> true.
     *
     * LeetCode #125 (simplified).
     */
    public static boolean isPalindrome(String s) {
        // TODO 1: create `left` = 0 and `right` = s.length() - 1
        // TODO 2: while left < right:
        //           - if s.charAt(left) != s.charAt(right), return false
        //           - HINT: otherwise step both pointers inward (left++, right--)
        // TODO 3: if you make it through the loop, every pair matched -> return true
        return false; // replace this
    }

    // =====================================================================
    // PATTERN: Hash Map (and Hash Set)
    // Trade memory for speed: remember what you've seen so a future lookup is
    // O(1) instead of re-scanning. Turns many O(n^2) brute forces into O(n).
    // =====================================================================

    /**
     * Drill 3 — Two Sum (the classic #1, UNSORTED). PATTERN: Hash Map.
     *
     * Given an array (NOT sorted) and a target, return the indices {i, j} of the
     * two values that sum to `target`, found in ONE pass. Return {-1, -1} if none.
     * Assume at most one valid answer; i &lt; j in the returned pair.
     *
     * The trick: for each value x at index i, the partner you need is
     * (target - x). Keep a map of {value seen -> its index}. Before storing the
     * current value, check whether its partner is already in the map.
     *
     * Example: a = {3, 2, 4}, target = 6 -> {1, 2} (2 + 4).
     *
     * LeetCode #1.
     */
    public static int[] twoSum(int[] a, int target) {
        // TODO 1: create a Map<Integer, Integer> seen   (value -> index)
        // TODO 2: loop i from 0 to a.length - 1:
        //           - compute need = target - a[i]
        //           - HINT: if `seen` already CONTAINS `need`, then seen.get(need)
        //             is the earlier index. Return {seen.get(need), i}.
        //           - otherwise record the current value: seen.put(a[i], i)
        // TODO 3: if the loop finishes with no match, return new int[]{-1, -1}
        return new int[]{-1, -1}; // replace this
    }

    /**
     * Drill 4 — Contains Duplicate. PATTERN: Hash Set.
     *
     * Return true if ANY value appears more than once in `a`, otherwise false.
     *
     * A HashSet only stores values it hasn't seen. As you walk the array, try to
     * add each value; the FIRST time an add fails (the value was already there)
     * you've found a duplicate.
     *
     * Example: {1, 2, 3, 1} -> true, {1, 2, 3} -> false.
     *
     * LeetCode #217.
     */
    public static boolean containsDuplicate(int[] a) {
        // TODO 1: create a Set<Integer> seen
        // TODO 2: loop over each value v in a:
        //           - HINT: seen.add(v) returns FALSE when v is already present.
        //             If it returns false, you found a duplicate -> return true.
        // TODO 3: if you finish the loop, there were no duplicates -> return false
        return false; // replace this
    }

    /**
     * Drill 5 — First Unique Character. PATTERN: Hash Map (frequency count).
     *
     * Return the index of the FIRST character in `s` that appears exactly once.
     * If every character repeats (or `s` is empty), return -1.
     * Assume `s` contains only lowercase letters.
     *
     * Two passes: first build a frequency map {char -> count}. Then walk the
     * string again left-to-right and return the index of the first char whose
     * count is 1.
     *
     * Example: "leetcode" -> 0 ('l'), "aabb" -> -1, "loveleetcode" -> 2 ('v').
     *
     * LeetCode #387.
     */
    public static int firstUniqueChar(String s) {
        // TODO 1: create a Map<Character, Integer> counts
        // TODO 2: first pass — for each char c in s, increase its count.
        //         HINT: counts.put(c, counts.getOrDefault(c, 0) + 1);
        // TODO 3: second pass — loop i from 0 to s.length() - 1:
        //           - if counts.get(s.charAt(i)) == 1, return i
        // TODO 4: if no character had count 1, return -1
        return -1; // replace this
    }

    // =====================================================================
    // PATTERN: Sliding Window
    // A "window" is a contiguous run of elements. Instead of recomputing each
    // window from scratch, slide it: add the element entering on the right and
    // drop the element leaving on the left. Whole array scanned in O(n).
    // =====================================================================

    /**
     * Drill 6 — Maximum Sum Subarray of Size K. PATTERN: Sliding Window (fixed).
     *
     * Return the largest sum among ALL contiguous subarrays of length exactly `k`.
     * Assume 1 &lt;= k &lt;= a.length.
     *
     * Naive way recomputes every window (O(n*k)). The sliding-window way is O(n):
     * sum the first `k` elements once, then for each step add the new right
     * element and subtract the element that just fell out of the window.
     *
     * Example: a = {2, 1, 5, 1, 3, 2}, k = 3 -> 9 (the window {5, 1, 3}).
     */
    public static int maxSubarraySum(int[] a, int k) {
        // TODO 1: compute the sum of the FIRST window: indices 0..k-1.
        //         Store it in `windowSum`, and start `best` equal to windowSum.
        // TODO 2: slide the window from index k to the end:
        //           for (int right = k; right < a.length; right++) {
        //             - HINT: the element LEAVING the window is a[right - k].
        //               windowSum += a[right] - a[right - k];
        //             - update best = Math.max(best, windowSum);
        //           }
        // TODO 3: return best
        return 0; // replace this
    }

    /**
     * Drill 7 — Longest Substring Without Repeating Characters.
     * PATTERN: Sliding Window (variable size) + Hash Set.  (STRETCH)
     *
     * Return the LENGTH of the longest substring of `s` that contains no
     * repeated character. The empty string has length 0.
     *
     * Idea: grow a window to the right one character at a time. Keep a set of the
     * characters CURRENTLY inside the window. If the new character is already in
     * the set, the window has a repeat — shrink from the LEFT (removing characters
     * from the set) until the repeat is gone. After each step the window is valid,
     * so record its length.
     *
     * Walk through "abcabcbb" on paper: the answer is 3 ("abc"). "bbbbb" -> 1.
     *
     * LeetCode #3.
     */
    public static int lengthOfLongestSubstring(String s) {
        // TODO 1: create a Set<Character> window (chars currently in the window),
        //         an int `left` = 0 (the window's left edge), and `best` = 0.
        // TODO 2: loop `right` from 0 to s.length() - 1, letting c = s.charAt(right):
        //           - HINT: while `window` already CONTAINS c, the window has a
        //             duplicate. Remove s.charAt(left) from the set and do left++.
        //             Repeat until c is no longer in the set. (A `while` loop, not
        //             a single `if` — you may need to shrink several times.)
        //           - now it is safe to add c: window.add(c);
        //           - the current window spans left..right, so its length is
        //             (right - left + 1). Update best = Math.max(best, that length).
        // TODO 3: return best
        return 0; // replace this
    }

    // =====================================================================
    // PATTERN: Two Pointers (reuse) — converging from both ends to mutate
    // in place. Same two-index idea as Drill 1, now used to REWRITE the array.
    // =====================================================================

    /**
     * Drill 8 — Reverse an Array In Place. PATTERN: Two Pointers.
     *
     * Reverse `a` IN PLACE (no second array): the first element swaps with the
     * last, the second with the second-to-last, and so on, meeting in the middle.
     * This is the exact pointer dance behind reversing a string or linked list.
     *
     * Example: {1, 2, 3, 4} becomes {4, 3, 2, 1}. A length-0 or length-1 array is
     * unchanged. The method mutates `a` directly (returns void).
     *
     * Related: LeetCode #344 (Reverse String).
     */
    public static void reverseArrayInPlace(int[] a) {
        // TODO 1: create `left` = 0 and `right` = a.length - 1
        // TODO 2: while left < right:
        //           - swap a[left] and a[right] using a temp variable:
        //               int temp = a[left];
        //               a[left] = a[right];
        //               a[right] = temp;
        //           - HINT: then step inward — left++ and right--
        // TODO 3: nothing to return (void). The loop stops on its own when the
        //         pointers meet, so a length-0 or length-1 array is left alone.
    }
}
