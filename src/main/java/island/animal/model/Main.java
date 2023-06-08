package island.animal.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ModelParameter modelParameter = new ModelParameter();
        Island island = new Island(modelParameter);

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(5);
//        ses.scheduleWithFixedDelay(new Plant(island), 1, 2, TimeUnit.SECONDS);
//        ses.scheduleWithFixedDelay(new DefineAnimals(island), 1, 10, TimeUnit.SECONDS);
        ses.scheduleWithFixedDelay(new Statistics(island), 1, modelParameter.getDurationOfTact()  * 5, TimeUnit.MILLISECONDS);
        ses.scheduleWithFixedDelay(() -> island.startMove(), 1, modelParameter.getDurationOfTact() * 1, TimeUnit.MILLISECONDS);
        ses.scheduleWithFixedDelay(() -> island.startEat(), 1, modelParameter.getDurationOfTact() * 2, TimeUnit.MILLISECONDS);
        ses.scheduleWithFixedDelay(() -> island.startReproduction(), 1, modelParameter.getDurationOfTact() * 5, TimeUnit.MILLISECONDS);
        ses.scheduleWithFixedDelay(() -> {
            if (island.getAnimalCount() == 0) {
                System.out.println("Count of animal is " + island.getAnimalCount());
                System.exit(0);
            }
        }, 1, modelParameter.getDurationOfTact() *10, TimeUnit.MILLISECONDS);
//        ses.scheduleWithFixedDelay(() -> island.isAnimalZero(), 1, 10, TimeUnit.SECONDS);
    }
}
