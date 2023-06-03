package island.animal.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Island island = new Island(20, 10);
        Map<Species, Integer> animalMap = new HashMap<>();
        animalMap.put(Species.Wolf, 10);
        animalMap.put(Species.Fox, 10);
        animalMap.put(Species.Sheep, 10);

        for (Map.Entry<Species, Integer> entry : animalMap.entrySet()) {
            for (int j = 0; j < entry.getValue(); j++) {
                DefineAnimals.createAnimal(island, entry.getKey(), RandomValue.getIntRandom(island.getN() * island.getM()));
                System.out.println("Create " + entry.getKey());
            }
        }

        for (int j = 0; j < island.getN() * island.getM() * 1.5; j++) {
            island.arrayCells[RandomValue.getIntRandom(island.getN() * island.getM())].addPlant(1);
        }

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
