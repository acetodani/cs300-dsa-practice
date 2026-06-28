/**
 * RecursionProblems — guided drills for thinking in BASE CASE + RECURSIVE CASE.
 *
 * This module sits right before the Linked List and Tree projects, both of which
 * lean heavily on recursion. The whole point here is to build the reflex:
 *
 *     1. What is the SMALLEST input I can answer immediately, with no recursion?
 *        (the BASE CASE — it stops the recursion)
 *     2. How do I solve the problem by ASSUMING a slightly smaller version is
 *        already solved? (the RECURSIVE CASE — trust the recursive call)
 *
 * EVERY method below MUST be solved RECURSIVELY. Do NOT use any loops
 * (no for, no while, no do-while). If you reach for a loop, stop and ask
 * "what smaller subproblem can I hand to a recursive call instead?"
 *
 * Fill in the TODOs, then run:
 *
 *     javac RecursionProblems.java RecursionProblemsTester.java
 *     java RecursionProblemsTester
 *
 * Each Javadoc states the BASE CASE and the RECURSIVE CASE. If a drill takes
 * more than a few minutes, re-watch the video linked in README.md (Step 1) and
 * read the matching hint at the bottom of the README — don't reach for an AI.
 */
public class RecursionProblems {

    /**
     * Drill 1 — factorial.
     * Return n! = n * (n-1) * ... * 2 * 1. Assume n >= 0.
     *
     * BASE CASE:      n <= 1  ->  return 1  (0! and 1! are both 1)
     * RECURSIVE CASE: n * factorial(n - 1)
     *
     * Solve RECURSIVELY — no loops.
     */
    public static int factorial(int n) {
        // TODO 1 (base case): if n <= 1, return 1
        // TODO 2 (recursive case): otherwise return n * factorial(n - 1)
        // HINT: factorial(4) should "unfold" to 4 * factorial(3), which becomes
        //       4 * 3 * factorial(2), and so on until the base case stops it.
        return -1; // replace this
    }

    /**
     * Drill 2 — sumToN.
     * Return 1 + 2 + 3 + ... + n. Assume n >= 0. sumToN(0) is 0.
     *
     * BASE CASE:      n == 0  ->  return 0  (nothing left to add)
     * RECURSIVE CASE: n + sumToN(n - 1)
     *
     * Solve RECURSIVELY — no loops.
     */
    public static int sumToN(int n) {
        // TODO 1 (base case): if n == 0, return 0
        // TODO 2 (recursive case): otherwise return n + sumToN(n - 1)
        // HINT: this is almost identical in shape to factorial — only the
        //       operation (+ instead of *) and the base value (0 instead of 1) change.
        return -1; // replace this
    }

    /**
     * Drill 3 — power.
     * Return base raised to exp (base^exp). Assume exp >= 0. power(x, 0) is 1.
     *
     * BASE CASE:      exp == 0  ->  return 1  (anything to the 0th power is 1)
     * RECURSIVE CASE: base * power(base, exp - 1)
     *
     * Solve RECURSIVELY — no loops.
     */
    public static int power(int base, int exp) {
        // TODO 1 (base case): if exp == 0, return 1
        // TODO 2 (recursive case): otherwise return base * power(base, exp - 1)
        // HINT: each recursive step peels off one factor of `base` and reduces
        //       the exponent by 1, marching exp down toward the base case.
        return -1; // replace this
    }

    /**
     * Drill 4 — reverseString.
     * Return s reversed, e.g. reverseString("abc") -> "cba".
     *
     * BASE CASE:      s is empty (length 0) -> return "" (an empty string is its
     *                 own reverse). A length-1 string also returns itself, which
     *                 the same logic handles for free.
     * RECURSIVE CASE: reverse the substring AFTER the first character, then put
     *                 the first character on the END:
     *                 reverseString(s.substring(1)) + s.charAt(0)
     *
     * Solve RECURSIVELY — no loops, and do NOT use StringBuilder.reverse().
     */
    public static String reverseString(String s) {
        // TODO 1 (base case): if s is empty (s.isEmpty() or s.length() == 0), return s
        // TODO 2 (recursive case): return reverseString(s.substring(1)) + s.charAt(0)
        // HINT: s.substring(1) is "everything except the first character".
        //       s.charAt(0) is the first character. Reverse the rest, then tack
        //       the old first character onto the end.
        return null; // replace this
    }

    /**
     * Drill 5 — isPalindrome.
     * Return true if s reads the same forwards and backwards. Assume s is all
     * lowercase with no spaces. The empty string and any single character are
     * palindromes.
     *
     * BASE CASE:      length 0 or length 1 -> return true (nothing left to
     *                 contradict it)
     * RECURSIVE CASE: compare the FIRST and LAST characters. If they differ,
     *                 return false immediately. If they match, recurse on the
     *                 MIDDLE (everything between them): s.substring(1, length - 1)
     *
     * Solve RECURSIVELY — no loops.
     */
    public static boolean isPalindrome(String s) {
        // TODO 1 (base case): if s.length() <= 1, return true
        // TODO 2 (recursive case, first half): if first and last chars differ,
        //        return false. First char is s.charAt(0); last is
        //        s.charAt(s.length() - 1).
        // TODO 3 (recursive case, second half): otherwise return
        //        isPalindrome(s.substring(1, s.length() - 1))
        // HINT: substring(1, s.length() - 1) chops off BOTH ends at once, leaving
        //       the middle. Each call shrinks the string by 2 until the base case.
        return false; // replace this
    }

    /**
     * Drill 6 — fibonacci.
     * Return the nth Fibonacci number, where fib(0) = 0, fib(1) = 1, and every
     * later number is the sum of the two before it: 0, 1, 1, 2, 3, 5, 8, 13, ...
     * Assume n >= 0.
     *
     * BASE CASE:      n == 0 -> return 0;  n == 1 -> return 1 (two base cases!)
     * RECURSIVE CASE: fibonacci(n - 1) + fibonacci(n - 2)
     *
     * BIG-O TEACHING MOMENT: this naive version is O(2^n) — exponential. Each
     * call spawns TWO more calls, so the number of calls roughly DOUBLES every
     * time n grows by 1. Worse, it recomputes the same values over and over:
     * fibonacci(5) computes fibonacci(3) twice, fibonacci(2) three times, and so
     * on. That repeated work is what makes it explode. (In a later course you'll
     * fix this with "memoization" — caching results — to bring it down to O(n).
     * For now, keep it simple and recursive; just know WHY it is slow.)
     *
     * Solve RECURSIVELY — no loops.
     */
    public static int fibonacci(int n) {
        // TODO 1 (base case): if n == 0, return 0
        // TODO 2 (base case): if n == 1, return 1
        // TODO 3 (recursive case): return fibonacci(n - 1) + fibonacci(n - 2)
        // HINT: you need TWO base cases here, not one. Without both, the
        //       recursion would never stop (n - 2 could skip past 0 into negatives).
        return -1; // replace this
    }

    /**
     * Drill 7 — sumArray (with an index).
     * Return the sum of a[index] + a[index + 1] + ... + a[a.length - 1], i.e. the
     * sum of the array from position `index` to the end.
     *
     * BASE CASE:      index == a.length -> return 0 (we've walked past the last
     *                 element; there is nothing left to add)
     * RECURSIVE CASE: a[index] + sumArray(a, index + 1)
     *
     * This "carry an index that creeps toward the end" pattern is exactly how you
     * will recurse over arrays and (soon) linked lists.
     *
     * Solve RECURSIVELY — no loops.
     */
    public static int sumArray(int[] a, int index) {
        // TODO 1 (base case): if index == a.length, return 0
        // TODO 2 (recursive case): return a[index] + sumArray(a, index + 1)
        // HINT: each call adds ONE element (the one at `index`) and hands the
        //       rest of the array to the next call by passing index + 1.
        return -1; // replace this
    }

    /**
     * Drill 7 (overload) — sumArray of the whole array.
     * A convenience method so callers don't have to remember to start at 0.
     * Return the sum of every element in `a`. An empty array sums to 0.
     *
     * This is NOT recursive itself — it just kicks off the recursion by calling
     * the index version with index = 0. (This "public starter that calls a
     * private/helper recursive method" is a very common recursion pattern.)
     */
    public static int sumArray(int[] a) {
        // TODO 1: return sumArray(a, 0)
        // HINT: do not re-implement the summing here — delegate to the version
        //       above, starting the index at 0.
        return -1; // replace this
    }
}
