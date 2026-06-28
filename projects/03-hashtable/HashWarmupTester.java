/**
 * Tester for HashWarmup.
 *
 *     javac HashWarmup.java HashWarmupTester.java
 *     java HashWarmupTester
 *
 * Prints PASS / FAIL (expected X, got Y) per test and a final N/M summary.
 * You do not need to edit this file — make the warm-up tests pass by editing
 * HashWarmup.java.
 */
public class HashWarmupTester {

    private static int passed = 0;
    private static int total = 0;

    public static void main(String[] args) {
        // Each test runs in isolation: if a method you haven't finished yet throws
        // (e.g. returns null and crashes), it's reported as one FAIL and the rest
        // still run — so you always get a summary instead of a stack trace.
        guard("testBucketIndex", HashWarmupTester::testBucketIndex);
        guard("testBucketIndexOf", HashWarmupTester::testBucketIndexOf);
        guard("testCollide", HashWarmupTester::testCollide);
        guard("testCountCollisions", HashWarmupTester::testCountCollisions);
        System.out.println("\n" + passed + "/" + total + " tests passed");
        if (passed != total) {
            System.out.println("Keep going — see README.md Step 2 and the Hints section.");
        }
    }

    private static void testBucketIndex() {
        checkInt("testBucketIndex (5, 4)", 1, HashWarmup.bucketIndex(5, 4));
        checkInt("testBucketIndex (1, 4)", 1, HashWarmup.bucketIndex(1, 4));
        checkInt("testBucketIndex (8, 4)", 0, HashWarmup.bucketIndex(8, 4));
        // floorMod keeps negatives in range; plain % would give -1 here.
        checkInt("testBucketIndex (-1, 4) stays in range", 3, HashWarmup.bucketIndex(-1, 4));
    }

    private static void testBucketIndexOf() {
        // We don't hardcode hash codes; we just confirm it matches the formula
        // the hash table itself uses, and that it is always a valid index.
        String key = "cat";
        int expected = Math.floorMod(key.hashCode(), 8);
        checkInt("testBucketIndexOf matches floorMod formula", expected, HashWarmup.bucketIndexOf(key, 8));
        int idx = HashWarmup.bucketIndexOf("anything", 16);
        checkBool("testBucketIndexOf is in range 0..15", true, idx >= 0 && idx < 16);
    }

    private static void testCollide() {
        checkBool("testCollide (1, 5, 4) collide", true, HashWarmup.collide(1, 5, 4));
        checkBool("testCollide (1, 2, 4) do not", false, HashWarmup.collide(1, 2, 4));
    }

    private static void testCountCollisions() {
        checkInt("testCountCollisions {1,5,2} len 4", 1,
            HashWarmup.countCollisions(new int[]{1, 5, 2}, 4));
        // 1,5,9 all land in bucket 1 -> 2 collisions (the 2nd and 3rd arrivals).
        checkInt("testCountCollisions {1,5,9} len 4", 2,
            HashWarmup.countCollisions(new int[]{1, 5, 9}, 4));
        checkInt("testCountCollisions {0,1,2,3} len 4 none", 0,
            HashWarmup.countCollisions(new int[]{0, 1, 2, 3}, 4));
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
