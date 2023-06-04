package island.animal.model;

import java.util.HashMap;
import java.util.Map;

public class ModelParameter {
    private int widthIsland = 20;
    private int heightIsland = 10;

    private double plantDensity = 1.5;

    private Map<Species, Integer> animalMap = new HashMap<>();

    public ModelParameter() {
        animalMap.put(Species.Wolf, 10);
        animalMap.put(Species.Fox, 10);
        animalMap.put(Species.Sheep, 10);
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
