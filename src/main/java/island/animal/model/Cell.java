package island.animal.model;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private int plantCount;
    private List<Animal> animals;
    private int i;
    private int n;
    private int m;
//    private Island island;

    public Cell(int i, int n, int m) {
        this.i = i;
        this.n = n;
        this.m = m;
//        this.island = island;
        animals = new ArrayList<>();
    }

    public int getI() {
        return i;
    }

    public void setPlantCount(int plantCount) {
        this.plantCount = plantCount;
    }

    public int getPlantCount() {
        return plantCount;
    }

    public synchronized List<Animal> getAnimals() {
        return animals;
    }

    public enum Direction { UP, DOWN, LEFT, RIGHT }
    public int nextCell(Direction dir) {
        switch (dir) {
            case UP:
                return i - n < 0 ? -1 : i - n;
            case DOWN:
                return i + n >= m * n ? -1 : i + n;
            case LEFT:
                return i % n == 0 ? -1 : i - 1;
            case RIGHT:
                return (i + 1) % n == 0 ? -1 : i + 1;
        }
        return -1;
    }

    public synchronized int animalsCount(Species species) {
        return (int) getAnimals().stream().filter((x) -> { return x.getSpecies() == species; }).count();
    }
    public synchronized void addToCellAnimalList(Animal animal) {
        animals.add(animal);
    }

    public synchronized void removeFromCellAnimalList(Animal animal) {
        animals.remove(animal);
    }

    public synchronized void addPlant(int p) {
        plantCount += p;
    }
}
