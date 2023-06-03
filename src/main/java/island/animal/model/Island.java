package island.animal.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Island {

    public Cell[] arrayCells;
    private int animalCount;

    private ModelParameter modelParameter;

    public Island(ModelParameter modelParameter) {
        this.modelParameter = modelParameter;
        arrayCells = new Cell[modelParameter.widthIsland * modelParameter.heightIsland];
        for (int i = 0; i < arrayCells.length; i++) {
            arrayCells[i] = new Cell(i, modelParameter.widthIsland,  modelParameter.heightIsland);
        }
        // create animals according to modelParameter
        for (Map.Entry<Species, Integer> entry : modelParameter.animalMap.entrySet()) {
            for (int j = 0; j < entry.getValue(); j++) {
                int position = RandomValue.getIntRandom(arrayCells.length);
                Animal animal = entry.getKey().create();
                arrayCells[position].addToCellAnimalList(animal);
                Logger.printLog(animal.getDescription() + " create in position:" + position);
            }
        }
        // create grass
        for (int j = 0; j < arrayCells.length * modelParameter.grassDensity; j++) {
            arrayCells[RandomValue.getIntRandom(arrayCells.length)].addPlant(1);
        }

    }

    public int getWidth() {
        return modelParameter.widthIsland;
    }

    public int getHeight() {
        return modelParameter.heightIsland;
    }

    public Cell[] getArrayCells() {
        return arrayCells;
    }

    public int getAnimalCount() {
        for (int i = 0; i < arrayCells.length; i++) {
            animalCount += arrayCells[i].getAnimals().size();
        }
        return animalCount;
    }

    public Cell getCell(int i) {
        return arrayCells[i];
    }

    public void printArray() {
        int i = 0;
        int k = getWidth();
        for (int j = 0; j < getWidth(); j++) {
            System.out.print("+----");
        }
        System.out.print("+");
        System.out.println();
        for (int j = 0; j < getHeight(); j++) {
            while (i < k) {
                System.out.printf("| %2d ", arrayCells[i].getAnimals().size());
                i++;
            }
            System.out.print("|");
            i -= getWidth();
            System.out.println();
            while (i < k) {
                int plantCount = arrayCells[i].getPlantCount();
                if (plantCount == 0) {
                    System.out.printf("| %2d ", plantCount);
                } else {
                    System.out.printf("|🌱%2d", plantCount);
                }
                 i++;
            }
            System.out.println("|");
            k += getWidth();
            for (int l = 0; l < getWidth(); l++) {
                System.out.print("+----");
            }
            System.out.println("+");
        }

    }



    ExecutorService executorService = Executors.newCachedThreadPool();

    public void startMove() {
        try {
            for (int j = 0; j < arrayCells.length; j++) {
                List<Animal> list = new ArrayList<>(arrayCells[j].getAnimals());
                for (Animal animal : list) {
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
                List<Animal> list = new ArrayList<>(arrayCells[j].getAnimals());
                for (Animal animal : list) {
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
                List<Animal> list = new ArrayList<>(arrayCells[j].getAnimals());
                for (Animal animal : list) {
                    int position = j;
                    executorService.execute(() -> animal.reproduction(this, position));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
