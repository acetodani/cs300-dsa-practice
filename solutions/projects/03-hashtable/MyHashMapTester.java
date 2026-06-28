/**
 * Tester for MyHashMap.
 *
 *     javac MyHashMap.java MyHashMapTester.java
 *     java MyHashMapTester
 *
 * Each test prints PASS or FAIL (expected X, got Y) and names its milestone so
 * you know what to fix. Make the tests pass by editing MyHashMap.java — you
 * should not need to edit this file.
 *
 * Collision note: several tests build the map with a SMALL capacity (4) on
 * purpose. With 4 buckets, Integer keys 1 and 5 both land in bucket
 * Math.floorMod(1,4) == Math.floorMod(5,4) == 1, so they COLLIDE and share a
 * chain. If your put() links onto the chain (instead of clobbering the bucket),
 * these pass.
 */
public class MyHashMapTester {

    private static int passed = 0;
    private static int total = 0;

    public static void main(String[] args) {
        // Each test runs in isolation: if a method you haven't finished yet throws
        // (e.g. returns null and crashes), it's reported as one FAIL and the rest
        // still run — so you always get a summary instead of a stack trace.
        // M1
        guard("M1 testSizeAndEmpty", MyHashMapTester::testSizeAndEmpty);
        guard("M1 testPutGet", MyHashMapTester::testPutGet);
        guard("M1 testPutUpdatesExisting", MyHashMapTester::testPutUpdatesExisting);
        // M2
        guard("M2 testContainsKey", MyHashMapTester::testContainsKey);
        guard("M2 testRemove", MyHashMapTester::testRemove);
        guard("M2 testRemoveAbsentAndGetAbsent", MyHashMapTester::testRemoveAbsentAndGetAbsent);
        // M3
        guard("M3 testCollisionsShareABucket", MyHashMapTester::testCollisionsShareABucket);
        guard("M3 testNegativeHashKeys", MyHashMapTester::testNegativeHashKeys);
        // M4
        guard("M4 testResizeGrowsCapacity", MyHashMapTester::testResizeGrowsCapacity);
        guard("M4 testResizeKeepsAllEntries", MyHashMapTester::testResizeKeepsAllEntries);

        System.out.println("\n" + passed + "/" + total + " tests passed");
        if (passed != total) {
            System.out.println("A failing test names its milestone (M1..M4). "
                + "Re-read that part of README.md and the Hints section.");
        } else {
            System.out.println("All green! Now build MyHashSet (M5), then try the LeetCode problems in README.md Step 4.");
        }
    }

    // ---- M1 ----
    private static void testSizeAndEmpty() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        checkBool("M1 testIsEmpty (new map)", true, map.isEmpty());
        checkInt("M1 testSize (new map)", 0, map.size());
        map.put("a", 1);
        checkBool("M1 testIsEmpty (after put)", false, map.isEmpty());
        checkInt("M1 testSize (after put)", 1, map.size());
    }

    private static void testPutGet() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        checkInt("M1 testGet one", 1, map.get("one"));
        checkInt("M1 testGet two", 2, map.get("two"));
        checkInt("M1 testGet three", 3, map.get("three"));
        checkInt("M1 testSize after 3 puts", 3, map.size());
    }

    private static void testPutUpdatesExisting() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("k", 100);
        map.put("k", 200); // update, NOT a new entry
        checkInt("M1 testPut updates value", 200, map.get("k"));
        checkInt("M1 testPut update keeps size 1", 1, map.size());
    }

    // ---- M2 ----
    private static void testContainsKey() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("present", 7);
        checkBool("M2 testContainsKey present", true, map.containsKey("present"));
        checkBool("M2 testContainsKey absent", false, map.containsKey("missing"));
    }

    private static void testRemove() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        checkInt("M2 testRemove returns old value", 2, map.remove("b"));
        checkBool("M2 testRemove gone", false, map.containsKey("b"));
        checkInt("M2 testRemove size", 2, map.size());
        // remaining keys still reachable
        checkInt("M2 testRemove keeps others (a)", 1, map.get("a"));
        checkInt("M2 testRemove keeps others (c)", 3, map.get("c"));
    }

    private static void testRemoveAbsentAndGetAbsent() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("x", 9);
        checkBool("M2 testRemove absent returns null", true, map.remove("nope") == null);
        checkInt("M2 testRemove absent keeps size", 1, map.size());
        checkBool("M2 testGet absent returns null", true, map.get("nope") == null);
    }

    // ---- M3: collisions ----
    private static void testCollisionsShareABucket() {
        // Capacity 4 -> Integer keys 1 and 5 both hash to bucket 1 (a collision).
        MyHashMap<Integer, String> map = new MyHashMap<>(4);
        map.put(1, "one");
        map.put(5, "five"); // collides with 1 in bucket 1
        checkInt("M3 testCollision both keys stored (size)", 2, map.size());
        checkStr("M3 testCollision get(1) survives", "one", map.get(1));
        checkStr("M3 testCollision get(5) survives", "five", map.get(5));
        // removing one colliding key must NOT disturb the other in the same chain
        checkStr("M3 testCollision remove(1) returns", "one", map.remove(1));
        checkStr("M3 testCollision get(5) still there", "five", map.get(5));
        checkBool("M3 testCollision get(1) now gone", true, map.get(1) == null);
        checkInt("M3 testCollision size after one remove", 1, map.size());
    }

    private static void testNegativeHashKeys() {
        // A negative Integer key would crash a plain % index; floorMod handles it.
        MyHashMap<Integer, String> map = new MyHashMap<>(4);
        map.put(-1, "neg-one");
        map.put(-5, "neg-five"); // -1 and -5 also collide in this size-4 table
        checkStr("M3 testNegativeKey get(-1)", "neg-one", map.get(-1));
        checkStr("M3 testNegativeKey get(-5)", "neg-five", map.get(-5));
        checkInt("M3 testNegativeKey size", 2, map.size());
    }

    // ---- M4: resize / rehash ----
    private static void testResizeGrowsCapacity() {
        // Start tiny (capacity 4). Load factor passes 0.75 once size > 3.
        MyHashMap<Integer, Integer> map = new MyHashMap<>(4);
        checkInt("M4 testResize starting capacity", 4, map.capacity());
        for (int i = 0; i < 4; i++) map.put(i, i * 10); // 4 entries -> lf 1.0 > 0.75
        checkBool("M4 testResize capacity grew past 4", true, map.capacity() > 4);
        checkBool("M4 testResize load factor under max", true, map.loadFactor() <= 0.75);
    }

    private static void testResizeKeepsAllEntries() {
        // Insert enough to force at least one resize, then confirm nothing was
        // lost or corrupted during the rehash.
        MyHashMap<Integer, Integer> map = new MyHashMap<>(4);
        int n = 20;
        for (int i = 0; i < n; i++) map.put(i, i * i);
        checkInt("M4 testResize size after many puts", n, map.size());
        boolean allPresent = true;
        for (int i = 0; i < n; i++) {
            Integer v = map.get(i);
            if (v == null || v != i * i) { allPresent = false; break; }
        }
        checkBool("M4 testResize all entries survive rehash", true, allPresent);
        checkBool("M4 testResize capacity grew", true, map.capacity() > 4);
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
