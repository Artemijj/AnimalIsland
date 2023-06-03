package island.animal.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Island island = new Island(new ModelParameter());




////        island.printArray();


        ScheduledExecutorService ses = Executors.newScheduledThreadPool(4);
//        ses.scheduleWithFixedDelay(new Plant(island), 1, 2, TimeUnit.SECONDS);
//        ses.scheduleWithFixedDelay(new DefineAnimals(island), 1, 10, TimeUnit.SECONDS);
        ses.scheduleWithFixedDelay(new Statistics(island), 1, 5, TimeUnit.SECONDS);
        ses.scheduleWithFixedDelay(() -> island.startMove(), 1, 1, TimeUnit.SECONDS);
        ses.scheduleWithFixedDelay(() -> island.startEat(), 1, 2, TimeUnit.SECONDS);
        ses.scheduleWithFixedDelay(() -> island.startReproduction(), 1, 5, TimeUnit.SECONDS);
    }
}
