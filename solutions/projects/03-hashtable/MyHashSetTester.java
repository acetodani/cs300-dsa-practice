/**
 * Tester for MyHashSet.
 *
 *     javac MyHashMap.java MyHashSet.java MyHashSetTester.java
 *     java MyHashSetTester
 *
 * Each test prints PASS or FAIL (expected X, got Y) and names its milestone
 * (all M5 here). Make the tests pass by editing MyHashSet.java — you should not
 * need to edit this file. (MyHashSet is built on top of MyHashMap, so make sure
 * your map tests are green first.)
 */
public class MyHashSetTester {

    private static int passed = 0;
    private static int total = 0;

    public static void main(String[] args) {
        // Each test runs in isolation: if a method you haven't finished yet throws
        // (e.g. returns null and crashes), it's reported as one FAIL and the rest
        // still run — so you always get a summary instead of a stack trace.
        guard("M5 testSizeAndEmpty", MyHashSetTester::testSizeAndEmpty);
        guard("M5 testAddAndContains", MyHashSetTester::testAddAndContains);
        guard("M5 testNoDuplicates", MyHashSetTester::testNoDuplicates);
        guard("M5 testRemove", MyHashSetTester::testRemove);
        guard("M5 testStringsAndManyElements", MyHashSetTester::testStringsAndManyElements);

        System.out.println("\n" + passed + "/" + total + " tests passed");
        if (passed != total) {
            System.out.println("A failing test names its milestone (M5). "
                + "Re-read that part of README.md and the Hints section. "
                + "If MyHashMap tests fail too, fix those first.");
        } else {
            System.out.println("All green! Now try the LeetCode problems in README.md Step 4.");
        }
    }

    private static void testSizeAndEmpty() {
        MyHashSet<String> set = new MyHashSet<>();
        checkBool("M5 testIsEmpty (new set)", true, set.isEmpty());
        checkInt("M5 testSize (new set)", 0, set.size());
        set.add("a");
        checkBool("M5 testIsEmpty (after add)", false, set.isEmpty());
        checkInt("M5 testSize (after add)", 1, set.size());
    }

    private static void testAddAndContains() {
        MyHashSet<Integer> set = new MyHashSet<>();
        checkBool("M5 testAdd returns true when new", true, set.add(42));
        checkBool("M5 testContains present", true, set.contains(42));
        checkBool("M5 testContains absent", false, set.contains(99));
    }

    private static void testNoDuplicates() {
        MyHashSet<String> set = new MyHashSet<>();
        set.add("dog");
        checkBool("M5 testAdd duplicate returns false", false, set.add("dog"));
        checkInt("M5 testNoDuplicates size stays 1", 1, set.size());
    }

    private static void testRemove() {
        MyHashSet<Integer> set = new MyHashSet<>();
        set.add(1);
        set.add(2);
        checkBool("M5 testRemove present returns true", true, set.remove(1));
        checkBool("M5 testRemove gone", false, set.contains(1));
        checkInt("M5 testRemove size", 1, set.size());
        checkBool("M5 testRemove absent returns false", false, set.remove(999));
        checkInt("M5 testRemove absent keeps size", 1, set.size());
    }

    private static void testStringsAndManyElements() {
        MyHashSet<String> set = new MyHashSet<>();
        String[] words = {"apple", "banana", "cherry", "apple", "banana", "date"};
        for (String w : words) set.add(w);
        // 6 added but only 4 distinct
        checkInt("M5 testManyElements distinct count", 4, set.size());
        checkBool("M5 testManyElements contains apple", true, set.contains("apple"));
        checkBool("M5 testManyElements contains date", true, set.contains("date"));
        checkBool("M5 testManyElements absent", false, set.contains("fig"));
    }

    private static void checkInt(String name, int expected, int actual) {
        record(name, expected == actual, String.valueOf(expected), String.valueOf(actual));
    }

    private static void checkBool(String name, boolean expected, boolean actual) {
        record(name, expected == actual, String.valueOf(expected), String.valueOf(actual));
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
