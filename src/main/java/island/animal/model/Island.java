package island.animal.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Island {
    public Cell[] arrayCells;
    private static int animalCount;
    private ModelParameter modelParameter;

    public Island(ModelParameter modelParameter) {
        this.modelParameter = modelParameter;
        arrayCells = new Cell[modelParameter.getWidthIsland() * modelParameter.getHeightIsland()];
        for (int i = 0; i < arrayCells.length; i++) {
            arrayCells[i] = new Cell(i, modelParameter.getWidthIsland(), modelParameter.getHeightIsland());
        }
        for (Map.Entry<Species, Integer> entry : modelParameter.getAnimalMap().entrySet()) {
            for (int j = 0; j < entry.getValue(); j++) {
                int position = RandomValue.getIntRandom(arrayCells.length);
                Animal animal = entry.getKey().create();
                arrayCells[position].addToCellAnimalList(animal);
                Logger.printLog(animal.getDescription() + " create in position:" + position);
            }
        }

        for (int j = 0; j < arrayCells.length * modelParameter.getPlantDensity(); j++) {
            plantedPlant();
        }
    }

    public int getWidth() {
        return modelParameter.getWidthIsland();
    }

    public int getHeight() {
        return modelParameter.getHeightIsland();
    }

    public Cell getArrayCell(int i) {
        return arrayCells[i];
    }

    public int getAnimalCount() {
        animalCount = 0;
        for (int i = 0; i < arrayCells.length; i++) {
            animalCount += arrayCells[i].getAnimals().size();
        }
        return animalCount;
    }

    public ModelParameter getModelParameter() {
        return modelParameter;
    }

    public Cell getCell(int i) {
        return arrayCells[i];
    }

    ScheduledExecutorService ses; // = Executors.newScheduledThreadPool(5);

    public void start() {
        ses = Executors.newScheduledThreadPool(5);
        ses.scheduleWithFixedDelay(() -> startMove(), 1, modelParameter.getDurationOfTact() * 1, TimeUnit.MILLISECONDS);
        ses.scheduleWithFixedDelay(() -> startEat(), 1, modelParameter.getDurationOfTact() * 2, TimeUnit.MILLISECONDS);
        ses.scheduleWithFixedDelay(() -> startReproduction(), 1, modelParameter.getDurationOfTact() * 5, TimeUnit.MILLISECONDS);
        ses.scheduleWithFixedDelay(() -> plantedPlant(), 5, modelParameter.getDurationOfTact() * 1, TimeUnit.MILLISECONDS);
//        ses.scheduleWithFixedDelay(() -> {
//            if (getAnimalCount() == 0) {
//                System.out.println("Count of animal is " + getAnimalCount());
//                System.exit(0);
//            }
//        }, 1, modelParameter.getDurationOfTact() * 10, TimeUnit.MILLISECONDS);
    }

    ExecutorService executorService = Executors.newCachedThreadPool();
    public void startMove() {
        try {
        for (int j = 0; j < arrayCells.length; j++) {
            List<Animal> list =  new ArrayList<>(arrayCells[j].getAnimals());
            for (Animal animal: list) {
                int initialPosition = j;
                executorService.execute(() -> animal.move(this, initialPosition));
            }
        }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void startEat() {
        try {
        for (int j = 0; j < arrayCells.length; j++) {
            List<Animal> list =  new ArrayList<>(arrayCells[j].getAnimals());
            for (Animal animal: list) {
                int position = j;
                executorService.execute(() -> animal.eat(this, position));
            }
        }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void startReproduction() {
        try {
        for (int j = 0; j < arrayCells.length; j++) {
            List<Animal> list =  new ArrayList<>(arrayCells[j].getAnimals());
            for (Animal animal: list) {
                int position = j;
                executorService.execute(() -> animal.reproduction(this, position));
            }
        }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void plantedPlant() {
        arrayCells[RandomValue.getIntRandom(arrayCells.length)].addPlant(1);
    }

    public void isAnimalZero() {
        System.out.println("Count of animal is " + getAnimalCount());
        if (getAnimalCount() == 0) {
            System.out.println("Count of animal is " + getAnimalCount());
            System.exit(0);
        }
    }

    public void stop() {
        ses.shutdown();
    }
}
