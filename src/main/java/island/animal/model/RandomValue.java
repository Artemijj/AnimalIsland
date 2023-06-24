package island.animal.model;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class RandomValue {
    private static ThreadLocalRandom random = ThreadLocalRandom.current();
    public static boolean getBoolRandom() {
        return random.nextBoolean();
    }
    public static int getIntRandom(int x) {
        return random.nextInt(x);
    }
}
