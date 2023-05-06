package island.animal.model;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ScheduledExecutorService ses = new ScheduledThreadPoolExecutor(3);
        ses.scheduleWithFixedDelay(new Plant(), 1, 2, TimeUnit.SECONDS);
        ses.scheduleWithFixedDelay(new DefineAnimals(), 1, 2, TimeUnit.SECONDS);
        ses.scheduleWithFixedDelay(new Statistics(), 1, 2, TimeUnit.SECONDS);
    }
}
