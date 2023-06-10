package island.animal.model;

public class Statistics implements Runnable{
    private Island island;

    public Statistics(Island island) {
        this.island = island;
    }

    @Override
    public void run() {
        printArray();
    }

    public void printArray() {
        int i = 0;
        int k = island.getWidth();
        for (int j = 0; j < island.getWidth(); j++) {
            System.out.print("+-----------");
        }
        System.out.print("+");
        System.out.println();
        for (int j = 0; j < island.getHeight(); j++) {
            while (i < k) {
                System.out.printf("|        %2d ", island.arrayCells[i].getAnimals().size());
                i++;
            }
            System.out.print("|");
            i -= island.getWidth();
            System.out.println();
            while (i < k) {
                double plantCount = island.arrayCells[i].getPlantCount();
                if (plantCount == 0) {
                    System.out.printf("| %2f ", plantCount);
                } else {
                    System.out.printf("|🌱%2f ", plantCount);
                }
                i++;
            }
            System.out.println("|");
            k += island.getWidth();
            for (int l = 0; l < island.getWidth(); l++) {
                System.out.print("+-----------");
            }
            System.out.println("+");
        }
    }
}
