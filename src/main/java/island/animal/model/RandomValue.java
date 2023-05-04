package island.animal.model;

import java.util.Random;

public final class RandomValue {
    private static Random random = new Random();
    public static boolean getRandom() {
        return random.nextBoolean();
    }
}
