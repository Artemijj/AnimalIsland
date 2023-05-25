package island.animal.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Island island = new Island(20, 10);
        Map<String, Integer> animalMap = new HashMap<>();
        animalMap.put("fox", 10);
        animalMap.put("sheep", 10);

        for (Map.Entry<String, Integer> entry : animalMap.entrySet()) {
            for (int j = 0; j < entry.getValue(); j++) {
                DefineAnimals.createAnimal(entry.getKey(), RandomValue.getIntRandom(island.getN() * island.getM()));
            }
        }

        for (int j = 0; j < island.getN() * island.getM() * 0.5; j++) {
            island.arrayCells[RandomValue.getIntRandom(island.getN() * island.getM())].addPlant(1);
        }


        ScheduledExecutorService ses = new ScheduledThreadPoolExecutor(3);
        ses.scheduleWithFixedDelay(new Plant(island), 1, 2, TimeUnit.SECONDS);
        ses.scheduleWithFixedDelay(new DefineAnimals(island), 1, 10, TimeUnit.SECONDS);
        ses.scheduleWithFixedDelay(new Statistics(island), 1, 5, TimeUnit.SECONDS);
    }
}
