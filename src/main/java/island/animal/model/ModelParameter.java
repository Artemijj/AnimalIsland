package island.animal.model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ModelParameter {
//    private int widthIsland = 20;
//    private int heightIsland = 10;
//
//    private double plantDensity = 1;
//    private int durationOfTact = 1000;
//    private int numberWolf = 10;
//    private int numberFox = 10;
//    private int numberSheep = 10;
//
//    private int quantityWolf = 40;
//    private int quantityFox = 40;
//    private int quantitySheep = 150;
    private boolean wrongFile = false;

    private Map<String, String> parametersMap = new HashMap<>();

    private Map<Species, Integer> animalMap = new HashMap<>();
    private Map<Species, Integer> maxQuantity = new HashMap<>();

    public ModelParameter(String fileName) {
        if (fileName != null) {
            readParameters(fileName);
        }
        addParameters();
    }

    private void addParameters() {
        // Numbers of animals
        animalMap.put(Species.Wolf, 10);
        animalMap.put(Species.Fox, 10);
        animalMap.put(Species.Sheep, 10);

        // Quantity of animals
        maxQuantity.put(Species.Wolf, 40);
        maxQuantity.put(Species.Fox, 40);
        maxQuantity.put(Species.Sheep, 150);
    }

    public int getWidthIsland() {
        return getIntParameter("widthIsland", 20);
    }

    public int getHeightIsland() {
        return getIntParameter("heightIsland", 10);
    }

    public double getPlantDensity() {
        return getDoubleParameter("plantDensity", 1);
    }

    public int getDurationOfTact() {
        return getIntParameter("durationOfTact", 1000);
    }

    public boolean isWrongFile() {
        return wrongFile;
    }

    public void setWrongFile(boolean wrongFile) {
        this.wrongFile = wrongFile;
    }

    //    public int getNumberWolf() {
//        return numberWolf;
//    }
//
//    public int getNumberFox() {
//        return numberFox;
//    }
//
//    public int getNumberSheep() {
//        return numberSheep;
//    }
//
//    public int getWolfMaxQuantity() {
//        return wolfMaxQuantity;
//    }
//
//    public int getFoxMaxQuantity() {
//        return foxMaxQuantity;
//    }
//
//    public int getSeepMaxQuantity() {
//        return seepMaxQuantity;
//    }

    public Map<Species, Integer> getAnimalMap() {
        return animalMap;
    }

    public Map<Species, Integer> getMaxQuantity() {
        return maxQuantity;
    }

    public void readParameters(String fileName) {
        File file = new File(fileName);
        BufferedReader bufferedReader;
//        parametersMap = new HashMap<>();

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.startsWith("#")) {
                    String[] tempArr = line.split("=");
//                    System.out.println(tempArr.length);
//                    System.out.println("0" + tempArr[0]);
//                    System.out.println("1" + tempArr[1]);
                    parametersMap.put(tempArr[0].strip(), tempArr[1].strip());
                }
            }

            bufferedReader.close();
        } catch (IOException ex) {
            setWrongFile(true);
            throw new RuntimeException(ex);
        }


//        try {
//            widthIsland = Integer.parseInt(parametersMap.get("widthIsland"));
//            heightIsland = Integer.parseInt(parametersMap.get("heightIsland"));
//            plantDensity = Double.parseDouble(parametersMap.get("plantDensity"));
//            durationOfTact = Integer.parseInt(parametersMap.get("durationOfTact"));
//
//            numberWolf = Integer.parseInt(parametersMap.get("numberWolf"));
//            numberFox = Integer.parseInt(parametersMap.get("numberFox"));
//            numberSheep = Integer.parseInt(parametersMap.get("numberSheep"));
//
//            quantityWolf = Integer.parseInt(parametersMap.get("quantityWolf"));
//            quantityFox = Integer.parseInt(parametersMap.get("quantityFox"));
//            quantitySheep = Integer.parseInt(parametersMap.get("quantitySheep"));
//        } catch (NumberFormatException ex) {
//            throw new RuntimeException(ex);
//        }


        try {
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
        } catch (NumberFormatException ex) {
            setWrongFile(true);
            throw new RuntimeException(ex);
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
