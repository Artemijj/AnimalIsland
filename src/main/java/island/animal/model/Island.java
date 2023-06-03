package island.animal.model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Island {
    private int n;
    private int m;
    public Cell[] arrayCells;
    private int animalCount;

    public Island(int n, int m) {
        this.n = n;
        this.m = m;
        arrayCells = new Cell[n * m];
        for (int i = 0; i < n * m; i++) {
            arrayCells[i] = new Cell(i, n, m);
        }
    }

    public int getN() {
        return n;
    }

    public int getM() {
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

    public Cell getCell(int i) {
        return arrayCells[i];
    }

//    public void setCell(int i) {
//        arrayCells[i] =
//    }

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

    public void printArray() {
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
                int plantCount = arrayCells[i].getPlantCount();
                if (plantCount == 0) {
                    System.out.printf("| %2d ", plantCount);
                } else {
                    System.out.printf("|🌱%2d ", plantCount);
                }
                 i++;
            }
            System.out.println("|");
//            System.out.println();
            k += n;
            for (int l = 0; l < n; l++) {
                System.out.print("+----");
            }
            System.out.println("+");
//            System.out.println();
        }

    }

//    public void start() {
//        for (int j = 0; j < n * m; j++) {
//            for (Animal animal: arrayCells[j].getAnimals()) {
//                animal.move();
//            }
//        }
//    }

    ExecutorService executorService = Executors.newCachedThreadPool();
    public void startMove() {
        try {
//        System.out.println("startMove");
        for (int j = 0; j < n * m; j++) {
            List<Animal> list =  new ArrayList<>(arrayCells[j].getAnimals());
            for (Animal animal: list) {
//                System.out.println(animal.getClass().getSimpleName() + " " + animal.isSex());
//                System.out.println(animal.getClass().getSimpleName() + " move for cell:"+j+" animal position:"+animal.getPosition());
//                    animal.move();
                executorService.execute(() -> animal.move(this));
//                System.out.println(animal.getClass().getSimpleName() + " move for cell end:"+j+" animal position:"+animal.getPosition());
            }
        }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void startEat() {
        try {
//        System.out.println("startEat");
        for (int j = 0; j < n * m; j++) {
            List<Animal> list =  new ArrayList<>(arrayCells[j].getAnimals());
            for (Animal animal: list) {
//                System.out.println("move for cell:"+j+" animal position:"+animal.getPosition());
                executorService.execute(() -> animal.eat(this));
//                System.out.println("move for cell end:"+j+" animal position:"+animal.getPosition());
            }
        }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void startReproduction() {
        try {
//        System.out.println("startReproduction");
        for (int j = 0; j < n * m; j++) {
            List<Animal> list =  new ArrayList<>(arrayCells[j].getAnimals());
            for (Animal animal: list) {
//                System.out.println("move for cell:"+j+" animal position:"+animal.getPosition());
                executorService.execute(() -> animal.reproduction(this));
//                System.out.println("move for cell end:"+j+" animal position:"+animal.getPosition());
            }
        }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
