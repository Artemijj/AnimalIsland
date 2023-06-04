package island.animal.model;

import java.util.HashMap;
import java.util.Map;

public class ModelParameter {
    private int widthIsland = 20;
    private int heightIsland = 10;

    private double plantDensity = 1.5;
    private int numberWolf = 10;
    private int numberFox = 10;
    private int numberSheep = 10;

    private Map<Species, Integer> animalMap = new HashMap<>();

    public ModelParameter() {
        animalMap.put(Species.Wolf, numberWolf);
        animalMap.put(Species.Fox, numberFox);
        animalMap.put(Species.Sheep, numberSheep);
    }

    public int getWidthIsland() {
        return widthIsland;
    }

    public int getHeightIsland() {
        return heightIsland;
    }

    public double getPlantDensity() {
        return plantDensity;
    }

    public Map<Species, Integer> getAnimalMap() {
        return animalMap;
    }
}
