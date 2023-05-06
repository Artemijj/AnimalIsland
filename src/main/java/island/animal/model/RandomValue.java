package island.animal.model;

import java.util.Random;

public final class RandomValue {
    private static Random random = new Random();
    public static boolean getBoolRandom() {
        return random.nextBoolean();
    }
    public static int getIntRandom(int x) {
        return random.nextInt(x);
    }
}
