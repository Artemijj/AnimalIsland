package island.animal.model.island;

import island.animal.model.animals.Species;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ModelParameter {
    private boolean wrongFile = false;

    private Map<String, String> parametersMap = new HashMap<>();

    private Map<Species, Integer> animalMap = new HashMap<>();
    private Map<Species, Integer> maxQuantity = new HashMap<>();

    public ModelParameter(String fileName) {
        if (fileName != null) {
            readParameters(fileName);
        } else {
            addParameters();
        }
    }

    private void addParameters() {
        // Numbers of animals
        animalMap.put(Species.Wolf, 10);
        animalMap.put(Species.Boa, 10);
        animalMap.put(Species.Fox, 10);
        animalMap.put(Species.Bear, 5);
        animalMap.put(Species.Eagle, 20);
        animalMap.put(Species.Horse, 15);
        animalMap.put(Species.Deer, 15);
        animalMap.put(Species.Rabbit, 30);
        animalMap.put(Species.Mouse, 50);
        animalMap.put(Species.Goat, 15);
        animalMap.put(Species.Sheep, 15);
        animalMap.put(Species.Boar, 30);
        animalMap.put(Species.Buffalo, 10);
        animalMap.put(Species.Duck, 20);
        animalMap.put(Species.Caterpillar, 50);

        // Quantity of animals
        maxQuantity.put(Species.Wolf, 30);
        maxQuantity.put(Species.Boa, 30);
        maxQuantity.put(Species.Fox, 30);
        maxQuantity.put(Species.Bear, 5);
        maxQuantity.put(Species.Eagle, 20);
        maxQuantity.put(Species.Horse, 20);
        maxQuantity.put(Species.Deer, 20);
        maxQuantity.put(Species.Rabbit, 150);
        maxQuantity.put(Species.Mouse, 500);
        maxQuantity.put(Species.Goat, 140);
        maxQuantity.put(Species.Sheep, 140);
        maxQuantity.put(Species.Boar, 50);
        maxQuantity.put(Species.Buffalo, 10);
        maxQuantity.put(Species.Duck, 200);
        maxQuantity.put(Species.Caterpillar, 1000);
    }

    public int getWidthIsland() {
        return getIntParameter("widthIsland", 20);
    }

    public int getHeightIsland() {
        return getIntParameter("heightIsland", 10);
    }

    public double getPlantDensity() {
        return getDoubleParameter("plantDensity", 2);
    }

    public int getDurationOfTact() {
        return getIntParameter("durationOfTact", 1000);
    }

    public Map<Species, Integer> getAnimalMap() {
        return animalMap;
    }

    public int getMaxQuantity(Species species) {
        return maxQuantity.get(species);
    }

    public boolean readParameters(String fileName) {
        File file = new File(fileName);
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.startsWith("#")) {
                    String[] tempArr = line.split("=");
                    parametersMap.put(tempArr[0].strip(), tempArr[1].strip());
                }
            }

            bufferedReader.close();

            for (Map.Entry<Species, Integer> entry : animalMap.entrySet()) {
                String key = "quantity" + entry.getKey();
                Integer value = Integer.parseInt(parametersMap.get(key));
                maxQuantity.put(entry.getKey(), getIntParameter(key, value));
            }
            for (Map.Entry<Species, Integer> entry : maxQuantity.entrySet()) {
                String key = "number" + entry.getKey();
                Integer value = Integer.parseInt(parametersMap.get(key));
                animalMap.put(entry.getKey(), getIntParameter(key, value));
            }
            return true;
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            return false;
        }
    }

    private int getIntParameter(String name, int defaultValue) {
        if (parametersMap.get(name) != null) {
            return Integer.parseInt(parametersMap.get(name));
        } else {
            return defaultValue;
        }
    }

    private double getDoubleParameter(String name, double defaultValue) {
        if (parametersMap.get(name) != null) {
            return Double.parseDouble(parametersMap.get(name));
        } else {
            return defaultValue;
        }
    }
}
