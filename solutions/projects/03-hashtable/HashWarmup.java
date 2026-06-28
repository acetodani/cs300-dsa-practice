/** HashWarmup — reference solution. */
public class HashWarmup {

    public static int bucketIndex(int key, int tableLength) {
        return Math.floorMod(key, tableLength);
    }

    public static int bucketIndexOf(Object key, int tableLength) {
        return Math.floorMod(key.hashCode(), tableLength);
    }

    public static boolean collide(int a, int b, int tableLength) {
        return bucketIndex(a, tableLength) == bucketIndex(b, tableLength);
    }

    public static int countCollisions(int[] keys, int tableLength) {
        boolean[] occupied = new boolean[tableLength];
        int collisions = 0;
        for (int key : keys) {
            int index = bucketIndex(key, tableLength);
            if (occupied[index]) collisions++;
            occupied[index] = true;
        }
        return collisions;
    }
}
