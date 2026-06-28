import java.util.Arrays;

/**
 * Tester for PatternProblems.
 *
 *     javac PatternProblems.java PatternProblemsTester.java
 *     java PatternProblemsTester
 *
 * Prints PASS / FAIL (expected X, got Y) per test and a final N/M summary.
 * Test names are prefixed with the PATTERN they exercise so a failure points you
 * at the right section of README.md.
 *
 * You do not need to edit this file — make the tests pass by editing
 * PatternProblems.java.
 */
public class PatternProblemsTester {

    private static int passed = 0;
    private static int total = 0;

    public static void main(String[] args) {
        // Each test runs in isolation: if an unfinished method throws, it's
        // reported as one FAIL and the rest still run, so you always get a summary.
        // PATTERN: Two Pointers
        guard("[Two Pointers] twoSumSorted", PatternProblemsTester::testTwoSumSorted);
        guard("[Two Pointers] isPalindrome", PatternProblemsTester::testIsPalindrome);
        // PATTERN: Hash Map / Set
        guard("[Hash Map] twoSum", PatternProblemsTester::testTwoSum);
        guard("[Hash Set] containsDuplicate", PatternProblemsTester::testContainsDuplicate);
        guard("[Hash Map] firstUniqueChar", PatternProblemsTester::testFirstUniqueChar);
        // PATTERN: Sliding Window
        guard("[Sliding Window] maxSubarraySum", PatternProblemsTester::testMaxSubarraySum);
        guard("[Sliding Window] lengthOfLongestSubstring", PatternProblemsTester::testLengthOfLongestSubstring);
        // PATTERN: Two Pointers (reuse)
        guard("[Two Pointers] reverseArrayInPlace", PatternProblemsTester::testReverseArrayInPlace);

        System.out.println("\n" + passed + "/" + total + " tests passed");
        if (passed != total) {
            System.out.println("Keep going — see README.md (the pattern named in the failing test) and the Hints section.");
        }
    }

    // ---- PATTERN: Two Pointers ------------------------------------------

    private static void testTwoSumSorted() {
        checkArray("[Two Pointers] twoSumSorted (4+5=9)",
                new int[]{2, 3}, PatternProblems.twoSumSorted(new int[]{1, 3, 4, 5, 7, 11}, 9));
        checkArray("[Two Pointers] twoSumSorted (ends, 2+15=17)",
                new int[]{0, 4}, PatternProblems.twoSumSorted(new int[]{2, 7, 9, 11, 15}, 17));
        checkArray("[Two Pointers] twoSumSorted (first pair 2+7=9)",
                new int[]{0, 1}, PatternProblems.twoSumSorted(new int[]{2, 7, 11, 15}, 9));
        checkArray("[Two Pointers] twoSumSorted (no match)",
                new int[]{-1, -1}, PatternProblems.twoSumSorted(new int[]{1, 2, 3, 4}, 100));
    }

    private static void testIsPalindrome() {
        checkBool("[Two Pointers] isPalindrome (racecar)", true, PatternProblems.isPalindrome("racecar"));
        checkBool("[Two Pointers] isPalindrome (hello)", false, PatternProblems.isPalindrome("hello"));
        checkBool("[Two Pointers] isPalindrome (abba even)", true, PatternProblems.isPalindrome("abba"));
        checkBool("[Two Pointers] isPalindrome (single char)", true, PatternProblems.isPalindrome("z"));
        checkBool("[Two Pointers] isPalindrome (empty)", true, PatternProblems.isPalindrome(""));
    }

    // ---- PATTERN: Hash Map / Set ----------------------------------------

    private static void testTwoSum() {
        checkArray("[Hash Map] twoSum (2+4=6)",
                new int[]{1, 2}, PatternProblems.twoSum(new int[]{3, 2, 4}, 6));
        checkArray("[Hash Map] twoSum (2+7=9)",
                new int[]{0, 1}, PatternProblems.twoSum(new int[]{2, 7, 11, 15}, 9));
        checkArray("[Hash Map] twoSum (equal values 3+3=6)",
                new int[]{0, 1}, PatternProblems.twoSum(new int[]{3, 3}, 6));
        checkArray("[Hash Map] twoSum (no match)",
                new int[]{-1, -1}, PatternProblems.twoSum(new int[]{1, 2, 3}, 99));
    }

    private static void testContainsDuplicate() {
        checkBool("[Hash Set] containsDuplicate (has dup)", true,
                PatternProblems.containsDuplicate(new int[]{1, 2, 3, 1}));
        checkBool("[Hash Set] containsDuplicate (all unique)", false,
                PatternProblems.containsDuplicate(new int[]{1, 2, 3, 4}));
        checkBool("[Hash Set] containsDuplicate (empty)", false,
                PatternProblems.containsDuplicate(new int[]{}));
        checkBool("[Hash Set] containsDuplicate (single)", false,
                PatternProblems.containsDuplicate(new int[]{7}));
    }

    private static void testFirstUniqueChar() {
        checkInt("[Hash Map] firstUniqueChar (leetcode -> 0)", 0,
                PatternProblems.firstUniqueChar("leetcode"));
        checkInt("[Hash Map] firstUniqueChar (loveleetcode -> 2)", 2,
                PatternProblems.firstUniqueChar("loveleetcode"));
        checkInt("[Hash Map] firstUniqueChar (all repeat -> -1)", -1,
                PatternProblems.firstUniqueChar("aabb"));
        checkInt("[Hash Map] firstUniqueChar (empty -> -1)", -1,
                PatternProblems.firstUniqueChar(""));
    }

    // ---- PATTERN: Sliding Window ----------------------------------------

    private static void testMaxSubarraySum() {
        checkInt("[Sliding Window] maxSubarraySum (k=3 -> 9)", 9,
                PatternProblems.maxSubarraySum(new int[]{2, 1, 5, 1, 3, 2}, 3));
        checkInt("[Sliding Window] maxSubarraySum (k=2 -> 13)", 13,
                PatternProblems.maxSubarraySum(new int[]{1, 4, 2, 10, 3}, 2));
        checkInt("[Sliding Window] maxSubarraySum (k=length -> total)", 6,
                PatternProblems.maxSubarraySum(new int[]{1, 2, 3}, 3));
        checkInt("[Sliding Window] maxSubarraySum (k=1 -> max element)", 5,
                PatternProblems.maxSubarraySum(new int[]{2, 5, 1, 3}, 1));
    }

    private static void testLengthOfLongestSubstring() {
        checkInt("[Sliding Window] lengthOfLongestSubstring (abcabcbb -> 3)", 3,
                PatternProblems.lengthOfLongestSubstring("abcabcbb"));
        checkInt("[Sliding Window] lengthOfLongestSubstring (bbbbb -> 1)", 1,
                PatternProblems.lengthOfLongestSubstring("bbbbb"));
        checkInt("[Sliding Window] lengthOfLongestSubstring (pwwkew -> 3)", 3,
                PatternProblems.lengthOfLongestSubstring("pwwkew"));
        checkInt("[Sliding Window] lengthOfLongestSubstring (all unique -> 6)", 6,
                PatternProblems.lengthOfLongestSubstring("abcdef"));
        checkInt("[Sliding Window] lengthOfLongestSubstring (empty -> 0)", 0,
                PatternProblems.lengthOfLongestSubstring(""));
    }

    // ---- PATTERN: Two Pointers (reuse) ----------------------------------

    private static void testReverseArrayInPlace() {
        int[] even = {1, 2, 3, 4};
        PatternProblems.reverseArrayInPlace(even);
        checkArray("[Two Pointers] reverseArrayInPlace (even length)",
                new int[]{4, 3, 2, 1}, even);

        int[] odd = {1, 2, 3, 4, 5};
        PatternProblems.reverseArrayInPlace(odd);
        checkArray("[Two Pointers] reverseArrayInPlace (odd length, middle fixed)",
                new int[]{5, 4, 3, 2, 1}, odd);

        int[] single = {42};
        PatternProblems.reverseArrayInPlace(single);
        checkArray("[Two Pointers] reverseArrayInPlace (single unchanged)",
                new int[]{42}, single);

        int[] empty = {};
        PatternProblems.reverseArrayInPlace(empty);
        checkArray("[Two Pointers] reverseArrayInPlace (empty unchanged)",
                new int[]{}, empty);
    }

    // ---- check helpers --------------------------------------------------

    private static void checkArray(String name, int[] expected, int[] actual) {
        total++;
        if (Arrays.equals(expected, actual)) {
            passed++;
            System.out.println(name + ": PASS");
        } else {
            System.out.println(name + ": FAIL (expected " + Arrays.toString(expected)
                    + ", got " + Arrays.toString(actual) + ")");
        }
    }

    private static void checkInt(String name, int expected, int actual) {
        total++;
        if (expected == actual) {
            passed++;
            System.out.println(name + ": PASS");
        } else {
            System.out.println(name + ": FAIL (expected " + expected + ", got " + actual + ")");
        }
    }

    private static void checkBool(String name, boolean expected, boolean actual) {
        total++;
        if (expected == actual) {
            passed++;
            System.out.println(name + ": PASS");
        } else {
            System.out.println(name + ": FAIL (expected " + expected + ", got " + actual + ")");
        }
    }

    /**
     * Run one test; if it throws (e.g. an unfinished method returned null and
     * crashed), report it as a single FAIL instead of letting a stack trace
     * abort the whole run.
     */
    private static void guard(String group, Runnable test) {
        try {
            test.run();
        } catch (Throwable t) {
            total++;
            System.out.println(group + ": FAIL (threw " + t.getClass().getSimpleName()
                + " — likely an unfinished method. Keep going.)");
        }
    }
}
