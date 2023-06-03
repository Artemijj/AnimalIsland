package island.animal.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Animal implements IAnimal{
    private double weight;
    final double maxAnimalWeight;
    private boolean sex;
    Species species;
    Island island;
    private long uuid;
    static long lastUuid = 1;

    public Animal(Species species) {
        this.species = species;
        weight = species.weight;
        maxAnimalWeight = species.weight + species.feed;
        sex = RandomValue.getBoolRandom();
        uuid = lastUuid;
        lastUuid++;
    }

    public Species getSpecies() {
        return species;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getMaxAnimalWeight() {
        return maxAnimalWeight;
    }

    public boolean isSex() {
        return sex;
    }

    public long getUuid() {
        return uuid;
    }

    public String getDescription() {
        return species + " " + species.icon + " (" + getUuid() + ") weight " + getWeight();
    }

    @Override
    public int move(Island island, int initialPosition) {
        int step = RandomValue.getIntRandom(species.speed + 1);
        if (step == 0) {
            return initialPosition;
        }
        int stepPosition = initialPosition;
        for (int j = 0; j < step; j++) {
            int calculatePosition = -1;
            while (calculatePosition == -1) {
                int randomDirection = RandomValue.getIntRandom(Cell.Direction.values().length);
                Cell.Direction dir = Cell.Direction.values()[randomDirection];
                calculatePosition = island.arrayCells[stepPosition].nextCell(dir);
                if (calculatePosition == initialPosition) {
                    calculatePosition = -1;
                }
            }
            stepPosition = calculatePosition;
        }
        Integer counter = island.arrayCells[initialPosition].animalsCount(getSpecies());
        if (counter < species.quantity) {
            island.arrayCells[initialPosition].removeFromCellAnimalList(this);
            island.arrayCells[stepPosition].addToCellAnimalList(this);
            Logger.printLog(getDescription() + " move to field " + stepPosition);
            return stepPosition;
        } else {
            return initialPosition;
        }
    }

    public void reproduction(Island island, int position) {
        if (!isSex()) {
            return;
        }
        Integer counter = island.arrayCells[position].animalsCount(getSpecies());
        if (counter >= species.quantity) {
            return;
        }
        List<Animal> list =  new ArrayList<>(island.arrayCells[position].getAnimals());
        for (Animal animal : list) {
            if (species.equals(animal.species) && isSex() != animal.isSex()) {
                Animal newAnimal = species.create();
                island.arrayCells[position].addToCellAnimalList(newAnimal);
                Logger.printLog(newAnimal.getDescription() + " was born, at field " + position);
                Logger.printLog(newAnimal.getDescription() + "parents: " + getDescription() + " & " + animal.getDescription());
            }
        }
    }

    public void die(Island island, int position) {
            this.island.arrayCells[position].removeFromCellAnimalList(this);
    }
}
