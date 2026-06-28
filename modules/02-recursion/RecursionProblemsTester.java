/**
 * Tester for RecursionProblems.
 *
 *     javac RecursionProblems.java RecursionProblemsTester.java
 *     java RecursionProblemsTester
 *
 * Prints PASS / FAIL (expected X, got Y) per test and a final N/M summary.
 * You do not need to edit this file — make the tests pass by editing
 * RecursionProblems.java.
 */
public class RecursionProblemsTester {

    private static int passed = 0;
    private static int total = 0;

    public static void main(String[] args) {
        // Each test runs in isolation: if a method you haven't finished yet throws
        // (e.g. returns a placeholder and crashes), it's reported as one FAIL and
        // the rest still run — so you always get a summary instead of a stack trace.
        guard("testFactorial", RecursionProblemsTester::testFactorial);
        guard("testSumToN", RecursionProblemsTester::testSumToN);
        guard("testPower", RecursionProblemsTester::testPower);
        guard("testReverseString", RecursionProblemsTester::testReverseString);
        guard("testIsPalindrome", RecursionProblemsTester::testIsPalindrome);
        guard("testFibonacci", RecursionProblemsTester::testFibonacci);
        guard("testSumArray", RecursionProblemsTester::testSumArray);
        System.out.println("\n" + passed + "/" + total + " tests passed");
        if (passed != total) {
            System.out.println("Keep going — see README.md Step 2 and the Hints section.");
        } else {
            System.out.println("All green! You now think in base case + recursive case — "
                + "exactly what the Linked List and Tree projects need next.");
        }
    }

    private static void testFactorial() {
        check("testFactorial (0! base)", 1, RecursionProblems.factorial(0));
        check("testFactorial (1! base)", 1, RecursionProblems.factorial(1));
        check("testFactorial (5!)", 120, RecursionProblems.factorial(5));
        check("testFactorial (7!)", 5040, RecursionProblems.factorial(7));
    }

    private static void testSumToN() {
        check("testSumToN (0 base)", 0, RecursionProblems.sumToN(0));
        check("testSumToN (1)", 1, RecursionProblems.sumToN(1));
        check("testSumToN (5 -> 15)", 15, RecursionProblems.sumToN(5));
        check("testSumToN (100 -> 5050)", 5050, RecursionProblems.sumToN(100));
    }

    private static void testPower() {
        check("testPower (x^0 base)", 1, RecursionProblems.power(2, 0));
        check("testPower (5^1)", 5, RecursionProblems.power(5, 1));
        check("testPower (2^10)", 1024, RecursionProblems.power(2, 10));
        check("testPower (3^4)", 81, RecursionProblems.power(3, 4));
    }

    private static void testReverseString() {
        check("testReverseString (empty base)", "", RecursionProblems.reverseString(""));
        check("testReverseString (length 1)", "a", RecursionProblems.reverseString("a"));
        check("testReverseString (abc)", "cba", RecursionProblems.reverseString("abc"));
        check("testReverseString (recursion)", "noisrucer", RecursionProblems.reverseString("recursion"));
    }

    private static void testIsPalindrome() {
        check("testIsPalindrome (empty base)", true, RecursionProblems.isPalindrome(""));
        check("testIsPalindrome (single char)", true, RecursionProblems.isPalindrome("z"));
        check("testIsPalindrome (racecar)", true, RecursionProblems.isPalindrome("racecar"));
        check("testIsPalindrome (abc -> false)", false, RecursionProblems.isPalindrome("abc"));
        check("testIsPalindrome (abba)", true, RecursionProblems.isPalindrome("abba"));
        check("testIsPalindrome (ab -> false)", false, RecursionProblems.isPalindrome("ab"));
    }

    private static void testFibonacci() {
        check("testFibonacci (fib 0 base)", 0, RecursionProblems.fibonacci(0));
        check("testFibonacci (fib 1 base)", 1, RecursionProblems.fibonacci(1));
        check("testFibonacci (fib 2)", 1, RecursionProblems.fibonacci(2));
        check("testFibonacci (fib 7)", 13, RecursionProblems.fibonacci(7));
        check("testFibonacci (fib 10 -> 55)", 55, RecursionProblems.fibonacci(10));
    }

    private static void testSumArray() {
        int[] sample = {4, 8, 15, 16, 23, 42};
        check("testSumArray (from index 0)", 108, RecursionProblems.sumArray(sample, 0));
        check("testSumArray (from index 3)", 81, RecursionProblems.sumArray(sample, 3));
        check("testSumArray (index at end -> 0)", 0, RecursionProblems.sumArray(sample, sample.length));
        check("testSumArray (overload whole array)", 108, RecursionProblems.sumArray(sample));
        check("testSumArray (overload empty -> 0)", 0, RecursionProblems.sumArray(new int[]{}));
        check("testSumArray (overload single)", 7, RecursionProblems.sumArray(new int[]{7}));
    }

    /** Helper: compare two ints. */
    private static void check(String name, int expected, int actual) {
        total++;
        if (expected == actual) {
            passed++;
            System.out.println(name + ": PASS");
        } else {
            System.out.println(name + ": FAIL (expected " + expected + ", got " + actual + ")");
        }
    }

    /** Helper: compare two booleans. */
    private static void check(String name, boolean expected, boolean actual) {
        total++;
        if (expected == actual) {
            passed++;
            System.out.println(name + ": PASS");
        } else {
            System.out.println(name + ": FAIL (expected " + expected + ", got " + actual + ")");
        }
    }

    /** Helper: compare two Strings. */
    private static void check(String name, String expected, String actual) {
        total++;
        if (expected == null ? actual == null : expected.equals(actual)) {
            passed++;
            System.out.println(name + ": PASS");
        } else {
            System.out.println(name + ": FAIL (expected \"" + expected + "\", got \"" + actual + "\")");
        }
    }

    /**
     * Run one group of checks; if it throws (e.g. an unfinished method returned
     * a placeholder and crashed), report it as a single FAIL instead of letting a
     * stack trace abort the whole run.
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
