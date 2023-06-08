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
        int islandSize = modelParameter.getWidthIsland() * modelParameter.getHeightIsland();
////        Map<Species, Integer> animalMap = new HashMap<>();
////        animalMap.put(Species.Wolf, 10);
////        animalMap.put(Species.Fox, 10);
////        animalMap.put(Species.Sheep, 10);
////
//        for (Map.Entry<Species, Integer> entry : modelParameter.getAnimalMap().entrySet()) {
//            for (int j = 0; j < entry.getValue(); j++) {
//                Animal newAnimal = entry.getKey().create();
//                island.arrayCells[RandomValue.getIntRandom(islandSize)].addToCellAnimalList(newAnimal);
//            }
//        }
//
//        for (int j = 0; j < islandSize * modelParameter.getPlantDensity(); j++) {
//            island.arrayCells[RandomValue.getIntRandom(islandSize)].addPlant(1);
//        }

        modelParameter.readParameters("/home/artemij/IdeaProjects/Island/src/main/java/parameter1.txt");

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(5);
//        ses.scheduleWithFixedDelay(new Plant(island), 1, 2, TimeUnit.SECONDS);
//        ses.scheduleWithFixedDelay(new DefineAnimals(island), 1, 10, TimeUnit.SECONDS);
        ses.scheduleWithFixedDelay(new Statistics(island), 1, modelParameter.getDurationOfTact() * 5, TimeUnit.MILLISECONDS);
        ses.scheduleWithFixedDelay(() -> island.startMove(), 1, modelParameter.getDurationOfTact() * 1, TimeUnit.MILLISECONDS);
        ses.scheduleWithFixedDelay(() -> island.startEat(), 1, modelParameter.getDurationOfTact() * 2, TimeUnit.MILLISECONDS);
        ses.scheduleWithFixedDelay(() -> island.startReproduction(), 1, modelParameter.getDurationOfTact() * 5, TimeUnit.MILLISECONDS);
        ses.scheduleWithFixedDelay(() -> {
            if (island.getAnimalCount() == 0) {
                System.out.println("Count of animal is " + island.getAnimalCount());
                System.exit(0);
            }
        }, 1, modelParameter.getDurationOfTact() * 10, TimeUnit.MILLISECONDS);
//        ses.scheduleWithFixedDelay(() -> island.isAnimalZero(), 1, 5, TimeUnit.SECONDS);
    }
}
