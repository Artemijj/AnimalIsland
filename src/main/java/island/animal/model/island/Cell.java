package island.animal.model.island;

import island.animal.model.animals.Animal;
import island.animal.model.animals.Species;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private double plantCount;
    private List<Animal> animals;
    private int index;
    private int width;
    private int height;

    public Cell(int index, int width, int height) {
        this.index = index;
        this.width = width;
        this.height = height;
        createList();
    }

    private synchronized void createList() {
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
        return new ArrayList<>(animals);
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

    public synchronized void addToCellAnimalList(Animal animal) {
        animals.add(animal);
    }

    public synchronized void removeFromCellAnimalList(Animal animal) {
        animals.remove(animal);
    }

    public synchronized void addPlant(double p) {
        plantCount += p;
    }

    public synchronized int animalsCount(Species species) {
        return (int) getAnimals().stream().filter((x) -> x.getSpecies() == species).count();
    }

    public synchronized int typeAnimalCount(String animalParentType) {
        return (int) getAnimals().stream().filter((x) -> x.getSpecies().parentType.equals(animalParentType)).count();
    }
}
