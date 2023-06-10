package island.animal.model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ModelParameter {
    private int widthIsland = 20;
    private int heightIsland = 10;

    private double plantDensity = 1;
    private int durationOfTact = 1000;
    private int numberWolf = 10;
    private int numberFox = 10;
    private int numberSheep = 10;

    private int wolfMaxQuantity = 40;
    private int foxMaxQuantity = 40;
    private int seepMaxQuantity = 150;

    private Map<Species, Integer> animalMap = new HashMap<>();

    public ModelParameter(String fileName) {
        if (fileName != null) {
            readParameters(fileName);
        }
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

    public int getDurationOfTact() {
        return durationOfTact;
    }

    public int getNumberWolf() {
        return numberWolf;
    }

    public int getNumberFox() {
        return numberFox;
    }

    public int getNumberSheep() {
        return numberSheep;
    }

    public int getWolfMaxQuantity() {
        return wolfMaxQuantity;
    }

    public int getFoxMaxQuantity() {
        return foxMaxQuantity;
    }

    public int getSeepMaxQuantity() {
        return seepMaxQuantity;
    }

    public Map<Species, Integer> getAnimalMap() {
        return animalMap;
    }

    public void readParameters(String fileName) {
        File file = new File(fileName);
        BufferedReader bufferedReader;
        Map<String, String> parametersMap = new HashMap<>();

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.startsWith("#")) {
                    String[] tempArr = line.split("=");
                    System.out.println(tempArr.length);
                    System.out.println("0" + tempArr[0]);
                    System.out.println("1" + tempArr[1]);
                    parametersMap.put(tempArr[0].strip(), tempArr[1].strip());
                }
            }

            bufferedReader.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


        try {
            widthIsland = Integer.parseInt(parametersMap.get("widthIsland"));
            heightIsland = Integer.parseInt(parametersMap.get("heightIsland"));
            plantDensity = Double.parseDouble(parametersMap.get("plantDensity"));
            durationOfTact = Integer.parseInt(parametersMap.get("durationOfTact"));

            numberWolf = Integer.parseInt(parametersMap.get("numberWolf"));
            numberFox = Integer.parseInt(parametersMap.get("numberFox"));
            numberSheep = Integer.parseInt(parametersMap.get("numberSheep"));

            wolfMaxQuantity = Integer.parseInt(parametersMap.get("wolfMaxQuantity"));
            foxMaxQuantity = Integer.parseInt(parametersMap.get("foxMaxQuantity"));
            seepMaxQuantity = Integer.parseInt(parametersMap.get("seepMaxQuantity"));
        } catch (NumberFormatException ex) {
            throw new RuntimeException(ex);
        }
    }
}
