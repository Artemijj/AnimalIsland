package island.animal.model;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private double plantCount;
    private List<Animal> animals;
    private int index;
    private int width;
    private int height;
//    private Island island;

    public Cell(int index, int width, int height) {
        this.index = index;
        this.width = width;
        this.height = height;
//        this.island = island;
        animals = new ArrayList<>();
    }

    public int getIndex() {
        return index;
    }

    public void setPlantCount(double plantCount) {
        this.plantCount = plantCount;
    }

    public double getPlantCount() {
        return plantCount;
    }

    public synchronized List<Animal> getAnimals() {
        return animals;
    }

    public enum Direction { UP, DOWN, LEFT, RIGHT }
    public int nextCell(Direction dir) {
        switch (dir) {
            case UP:
                return index - width < 0 ? -1 : index - width;
            case DOWN:
                return index + width >= height * width ? -1 : index + width;
            case LEFT:
                return index % width == 0 ? -1 : index - 1;
            case RIGHT:
                return (index + 1) % width == 0 ? -1 : index + 1;
        }
        return -1;
    }

    public int next(int i) {
        if (i ==0) {
            int[] arr = {i + 1, i + width};
            return arr[RandomValue.getIntRandom(2)];
        } else if (i == width - 1) {
            int[] arr = {i - 1, i + width};
            return arr[RandomValue.getIntRandom(2)];
        } else if (i == width * height - width) {
            int[] arr = {i + 1, i - width};
            return arr[RandomValue.getIntRandom(2)];
        } else if (i == width * height - 1) {
            int[] arr = {i - 1, i - width};
            return arr[RandomValue.getIntRandom(2)];
        } else if (i > 0 && i < width - 1) {
            int[] arr = {i + 1, i - 1, i + width};
            return arr[RandomValue.getIntRandom(3)];
        } else if (i > 0 && i < width * height - width && i % width == 0) {
            int[] arr = {i + 1, i + width, i - width};
            return arr[RandomValue.getIntRandom(3)];
        } else if (i > width * height - width && i < width * height) {
            int[] arr = {i + 1, i - 1, i - width};
            return arr[RandomValue.getIntRandom(3)];
        } else if (i > width - 1 && (i + 1) % width == 0 && i < width * height - 1) {
            int[] arr = {i - 1, i + width, i - width};
            return arr[RandomValue.getIntRandom(3)];
        } else {
            int[] arr = {i + 1, i - 1, i + width, i - width};
            return arr[RandomValue.getIntRandom(4)];
        }
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

    public synchronized int animalsCount(Species species) {
        return (int) getAnimals().stream().filter((x) -> x.getSpecies() == species).count();
    }
}
