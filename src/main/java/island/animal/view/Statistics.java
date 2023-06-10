package island.animal.view;

import island.animal.model.Island;
import java.text.DecimalFormat;

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
        for (int j = 0; j < island.getWidth(); j++) {
            System.out.print("+--------------");
        }
        System.out.print("+");
        System.out.println();
        for (int j = 0; j < island.getHeight(); j++) {
            while (i < k) {
                System.out.printf("|Predator   %2d ", (int)island.arrayCells[i].getAnimals().stream().filter(x -> x.getSpecies().parentType.equals("Predator")).count());
                i++;
            }
            i -= island.getWidth();
            System.out.println("|");
            while (i < k) {
                System.out.printf("|Omnivorous %2d ", (int)island.arrayCells[i].getAnimals().stream().filter(x -> x.getSpecies().parentType.equals("Omnivorous")).count());
                i++;
            }
            i -= island.getWidth();
            System.out.println("|");
            while (i < k) {
                System.out.printf("|Herbivore  %2d ", (int)island.arrayCells[i].getAnimals().stream().filter(x -> x.getSpecies().parentType.equals("Herbivore")).count());
                i++;
            }
            System.out.print("|");
            i -= island.getWidth();
            System.out.println();
            while (i < k) {
                double plantCount = island.arrayCells[i].getPlantCount();
                if (plantCount == 0) {
                    System.out.printf("|              ", myFormat.format(plantCount));
                } else {
                    System.out.printf("|🌱         %2s ", myFormat.format(plantCount));
                }
                i++;
            }
            System.out.println("|");
            k += island.getWidth();
            for (int l = 0; l < island.getWidth(); l++) {
                System.out.print("+--------------");
            }
            System.out.println("+");
        }
    }
}
