package island.animal.view;

import island.animal.model.island.Island;
import java.text.DecimalFormat;

public class IslandConsole implements Runnable{
    private Island island;

    public IslandConsole(Island island) {
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
                int predators = island.arrayCells[i].typeAnimalCount("Predator");
                System.out.printf("|Predators  %2d ", predators);
                allPredators += predators;
                i++;
            }
            i -= island.getWidth();
            System.out.println("|");
            while (i < k) {
                int omnivorous = island.arrayCells[i].typeAnimalCount("Omnivorous");
                System.out.printf("|Omnivorous %2d ", omnivorous);
                allOmnivorous += omnivorous;
                i++;
            }
            i -= island.getWidth();
            System.out.println("|");
            while (i < k) {
                int herbivores = island.arrayCells[i].typeAnimalCount("Herbivore");
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
                    System.out.print("|              ");
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

        System.out.println("All predators - " + allPredators);
        System.out.println("All omnivorous - " + allOmnivorous);
        System.out.println("All herbivores - " + allHerbivores);
        System.out.println("All plants - " + myFormat.format(allPlants));
        System.out.println();
    }
}
