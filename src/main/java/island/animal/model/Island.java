package island.animal.model;

import java.sql.Array;

public class Island {
    private static int n;
    private static int m;
    public static Cell[] arrayCells;
    private int animalCount;

    public Island(int n, int m) {
        this.n = n;
        this.m = m;
        arrayCells = new Cell[n * m];
        for (int i = 0; i < n * m; i++) {
            arrayCells[i] = new Cell(i, n, m);
        }
    }

    public static int getN() {
        return n;
    }

    public static int getM() {
        return m;
    }

    public Cell[] getArrayCells() {
        return arrayCells;
    }

    public int getAnimalCount() {
        for (int i = 0; i < n * m; i++) {
            animalCount += arrayCells[i].getAnimals().size();
        }
        return animalCount;
    }

//    public void printArray() {
//        for (int i = 0; i < n * m; i++) {
//            if (i % n == 0) {
//                System.out.println();
//                for (int j = 0; j < n; j++) {
//                    System.out.print("+----");
//                }
//                System.out.println();
//            }
//            System.out.printf("| %2d ", arrayCells[i].getAnimals().size());
//            if (i + 1 % n == 0) {
//                System.out.print("|");
//            }
//        }
//        System.out.println();
//        for (int j = 0; j < n; j++) {
//            System.out.printf("+----");
//        }
//    }

    public static void printArray() {
        int i = 0;
        int k = n;
        for (int j = 0; j < n; j++) {
            System.out.print("+----");
        }
        System.out.print("+");
        System.out.println();
        for (int j = 0; j < m; j++) {
            while (i < k) {
                System.out.printf("| %2d ", arrayCells[i].getAnimals().size());
                i++;
            }
            System.out.print("|");
            i -= n;
            System.out.println();
            while (i < k) {
                System.out.printf("| %2d ", arrayCells[i].getPlantCount());
                 i++;
            }
            System.out.print("|");
            System.out.println();
            k += n;
            for (int l = 0; l < n; l++) {
                System.out.print("+----");
            }
            System.out.print("+");
            System.out.println();
        }

    }
}
