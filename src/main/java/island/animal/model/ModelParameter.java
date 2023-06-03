package island.animal.model;

import java.util.HashMap;
import java.util.Map;

public class ModelParameter {
    public int widthIsland = 20;
    public int heightIsland = 10;

    public double grassDensity = 1.5;

    public Map<Species, Integer> animalMap = new HashMap<>();

    public ModelParameter() {
        animalMap.put(Species.Wolf, 10);
        animalMap.put(Species.Fox, 10);
        animalMap.put(Species.Sheep, 10);
    }
}
