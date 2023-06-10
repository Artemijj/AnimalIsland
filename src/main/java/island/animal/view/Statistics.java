package island.animal.view;

import island.animal.model.Cell;
import island.animal.model.Island;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Statistics implements Runnable{
    private Island island;

    public Statistics(Island island) {
        this.island = island;
    }

    @Override
    public void run() {
        printArray();
    }

    DecimalFormat myFormat = new DecimalFormat("#.##");

    public void printArray() {
        int i = 0;
        int k = island.getWidth();
        int allPredators = 0;
        int allOmnivorous = 0;
        int allHerbivores = 0;
        double allPlants = 0;
        for (int j = 0; j < island.getWidth(); j++) {
            System.out.print("+--------------");
        }
        System.out.print("+");
        System.out.println();
        for (int j = 0; j < island.getHeight(); j++) {
            while (i < k) {
                int predators = (int)island.arrayCells[i].getAnimals().stream().filter(x -> x.getSpecies().parentType.equals("Predator")).count();
                System.out.printf("|Predators  %2d ", predators);
                allPredators += predators;
                i++;
            }
            i -= island.getWidth();
            System.out.println("|");
            while (i < k) {
                int omnivorous = (int)island.arrayCells[i].getAnimals().stream().filter(x -> x.getSpecies().parentType.equals("Omnivorous")).count();
                System.out.printf("|Omnivorous %2d ", omnivorous);
                allOmnivorous += omnivorous;
                i++;
            }
            i -= island.getWidth();
            System.out.println("|");
            while (i < k) {
                int herbivores = (int)island.arrayCells[i].getAnimals().stream().filter(x -> x.getSpecies().parentType.equals("Herbivore")).count();
                System.out.printf("|Herbivores %2d ", herbivores);
                allHerbivores += herbivores;
                i++;
            }
            System.out.print("|");
            i -= island.getWidth();
            System.out.println();
            while (i < k) {
                double plantCount = island.arrayCells[i].getPlantCount();
                if (plantCount == 0) {
                    System.out.print("|              "); //, myFormat.format(plantCount));
                } else {
                    System.out.printf("|🌱         %2s ", myFormat.format(plantCount));
                }
                i++;
                allPlants += plantCount;
            }
            System.out.println("|");
            k += island.getWidth();
            for (int l = 0; l < island.getWidth(); l++) {
                System.out.print("+--------------");
            }
            System.out.println("+");
        }
//        System.out.println();
//        for (Cell cell : island.arrayCells) {
//            allPredators += cell.getAnimals().stream().filter(x -> x.getSpecies().parentType.equals("Predator")).count();
//            allOmnivorous += cell.getAnimals().stream().filter(x -> x.getSpecies().parentType.equals("Herbivore")).count();
//            allHerbivores += cell.getAnimals().stream().filter(x -> x.getSpecies().parentType.equals("Herbivore")).count();
//            allPlants += cell.getPlantCount();
//        }
        System.out.println("All predators - " + allPredators);
        System.out.println("All omnivorous - " + allOmnivorous);
        System.out.println("All herbivores - " + allHerbivores);
        System.out.println("All plants - " + myFormat.format(allPlants));
        System.out.println();
    }
}
