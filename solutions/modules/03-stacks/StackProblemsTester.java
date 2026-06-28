/**
 * Tester for StackProblems.
 *
 *     javac StackProblems.java StackProblemsTester.java
 *     java StackProblemsTester
 *
 * Each test prints PASS or FAIL (expected X, got Y) and names its milestone so
 * you know what to fix. Make the tests pass by editing StackProblems.java — you
 * should not need to edit this file.
 */
public class StackProblemsTester {

    private static int passed = 0;
    private static int total = 0;

    public static void main(String[] args) {
        // Each test runs in isolation: if a method you haven't finished yet throws
        // (e.g. returns null and crashes), it's reported as one FAIL and the rest
        // still run — so you always get a summary instead of a stack trace.
        // M3 — isBalanced
        guard("M3 testBalancedSimple", StackProblemsTester::testBalancedSimple);
        guard("M3 testBalancedNested", StackProblemsTester::testBalancedNested);
        guard("M3 testUnbalancedMismatch", StackProblemsTester::testUnbalancedMismatch);
        guard("M3 testUnbalancedLeftover", StackProblemsTester::testUnbalancedLeftover);
        guard("M3 testUnbalancedStrayCloser", StackProblemsTester::testUnbalancedStrayCloser);
        guard("M3 testBalancedIgnoresOtherChars", StackProblemsTester::testBalancedIgnoresOtherChars);
        guard("M3 testBalancedEmpty", StackProblemsTester::testBalancedEmpty);
        // M4 — reverseString
        guard("M4 testReverseString", StackProblemsTester::testReverseString);
        // M5 — evalPostfix
        guard("M5 testEvalPostfixSingle", StackProblemsTester::testEvalPostfixSingle);
        guard("M5 testEvalPostfixAdd", StackProblemsTester::testEvalPostfixAdd);
        guard("M5 testEvalPostfixChained", StackProblemsTester::testEvalPostfixChained);
        guard("M5 testEvalPostfixSubtractOrder", StackProblemsTester::testEvalPostfixSubtractOrder);

        System.out.println("\n" + passed + "/" + total + " tests passed");
        if (passed != total) {
            System.out.println("A failing test names its milestone (M3..M5). "
                + "Re-read that part of README.md and the Hints section.");
        } else {
            System.out.println("All green! Now try the LeetCode problems in README.md Step 4.");
        }
    }

    // ---- M3 ----
    private static void testBalancedSimple() {
        checkBool("M3 testBalanced \"()\"", true, StackProblems.isBalanced("()"));
        checkBool("M3 testBalanced \"()[]{}\"", true, StackProblems.isBalanced("()[]{}"));
    }

    private static void testBalancedNested() {
        checkBool("M3 testBalanced \"([])\"", true, StackProblems.isBalanced("([])"));
        checkBool("M3 testBalanced \"{[()]}\"", true, StackProblems.isBalanced("{[()]}"));
    }

    private static void testUnbalancedMismatch() {
        checkBool("M3 testUnbalanced \"([)]\"", false, StackProblems.isBalanced("([)]"));
        checkBool("M3 testUnbalanced \"(]\"", false, StackProblems.isBalanced("(]"));
    }

    private static void testUnbalancedLeftover() {
        checkBool("M3 testUnbalanced \"((\"", false, StackProblems.isBalanced("(("));
        checkBool("M3 testUnbalanced \"([]\"", false, StackProblems.isBalanced("([]"));
    }

    private static void testUnbalancedStrayCloser() {
        checkBool("M3 testUnbalanced \")\"", false, StackProblems.isBalanced(")"));
        checkBool("M3 testUnbalanced \"())\"", false, StackProblems.isBalanced("())"));
    }

    private static void testBalancedIgnoresOtherChars() {
        checkBool("M3 testBalanced \"a(b)c\"", true, StackProblems.isBalanced("a(b)c"));
        checkBool("M3 testBalanced \"if (x[0]) {y}\"", true,
            StackProblems.isBalanced("if (x[0]) {y}"));
    }

    private static void testBalancedEmpty() {
        checkBool("M3 testBalanced empty string", true, StackProblems.isBalanced(""));
    }

    // ---- M4 ----
    private static void testReverseString() {
        checkStr("M4 testReverse \"abc\"", "cba", StackProblems.reverseString("abc"));
        checkStr("M4 testReverse \"\"", "", StackProblems.reverseString(""));
        checkStr("M4 testReverse \"x\"", "x", StackProblems.reverseString("x"));
        checkStr("M4 testReverse \"stack\"", "kcats", StackProblems.reverseString("stack"));
    }

    // ---- M5 ----
    private static void testEvalPostfixSingle() {
        checkInt("M5 testEvalPostfix \"5\"", 5, StackProblems.evalPostfix("5"));
    }

    private static void testEvalPostfixAdd() {
        checkInt("M5 testEvalPostfix \"3 4 +\"", 7, StackProblems.evalPostfix("3 4 +"));
    }

    private static void testEvalPostfixChained() {
        checkInt("M5 testEvalPostfix \"3 4 + 2 *\"", 14,
            StackProblems.evalPostfix("3 4 + 2 *"));
    }

    private static void testEvalPostfixSubtractOrder() {
        // "8 3 -" must be 8 - 3 = 5, NOT 3 - 8. Order of the two pops matters.
        checkInt("M5 testEvalPostfix \"8 3 -\" (order)", 5,
            StackProblems.evalPostfix("8 3 -"));
    }

    // ---- helpers ----
    private static void checkInt(String name, int expected, int actual) {
        record(name, expected == actual, String.valueOf(expected), String.valueOf(actual));
    }

    private static void checkBool(String name, boolean expected, boolean actual) {
        record(name, expected == actual, String.valueOf(expected), String.valueOf(actual));
    }

    private static void checkStr(String name, String expected, String actual) {
        record(name, expected.equals(actual), expected, String.valueOf(actual));
    }

    private static void record(String name, boolean ok, String expected, String actual) {
        total++;
        if (ok) {
            passed++;
            System.out.println(name + ": PASS");
        } else {
            System.out.println(name + ": FAIL (expected " + expected + ", got " + actual + ")");
        }
    }

    /**
     * Run one group of checks; if it throws (e.g. an unfinished method returned
     * null and crashed), report it as a single FAIL instead of letting a stack
     * trace abort the whole run.
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
