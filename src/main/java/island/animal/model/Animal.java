package island.animal.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Animal implements IAnimal{
    private double weight;
    private boolean sex;
    private boolean fullAnimal;
    Species species;

    private long uuid;

    static long lastUuid = 1;

    final double maxAnimalWeight;

    public Animal(Species species) {
        this.species = species;
        weight = species.weight;
        maxAnimalWeight = species.weight  + species.feed;
        sex = RandomValue.getBoolRandom();
        fullAnimal = false;
        synchronized (species) {
            uuid = lastUuid++;
        }
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

    public void setFullAnimal(boolean fullAnimal) {
        this.fullAnimal = fullAnimal;
    }

    public boolean isFullAnimal() {
        return fullAnimal;
    }

    public long getUuid() {
        return uuid;
    }

    @Override
    public int move(Island island, int initialPosition) {
        int step = RandomValue.getIntRandom(species.speed + 1);
        if (step == 0) return initialPosition;
        int stepPosition = initialPosition;
        for (int j = 0; j < step; j++) {
            int calculatedPosition = -1;
            while (calculatedPosition == -1) {
                int randomDirection = RandomValue.getIntRandom(Cell.Direction.values().length);
                Cell.Direction dir = Cell.Direction.values()[randomDirection];
                calculatedPosition = island.arrayCells[stepPosition].nextCell(dir);
                if (calculatedPosition == initialPosition) {
                    calculatedPosition = -1;
                }
            }
            stepPosition = calculatedPosition;
        }
        Integer counter = (int)island.arrayCells[stepPosition].animalsCount(getSpecies());
        if (counter < species.quantity) {
            island.arrayCells[initialPosition].removeFromCellAnimalList(this);
            island.arrayCells[stepPosition].addToCellAnimalList(this);
            Logger.printLog(getDescription() + " move to field " + stepPosition);
            return stepPosition;
        } else
            return initialPosition;
    }

    public String getDescription() {
        return "" + species + " " + species.icon + " (" + getUuid() + ")" + " weight " + getWeight();
    }

    @Override
    public void reproduction(Island island, int position) {
        if (! isSex())  return; // only for boys
        Integer counter = (int)island.arrayCells[position].animalsCount(getSpecies());
        if (counter >= species.quantity) return;
        String type = getClass().getSimpleName();
        List<Animal> list =  new ArrayList<>(island.arrayCells[position].getAnimals());
        for (Animal animal : list) {
            if (type == animal.getClass().getSimpleName() && isSex() != animal.isSex()) {
                Animal newAnimal = species.create();
                island.arrayCells[position].addToCellAnimalList(newAnimal);
                Logger.printLog(newAnimal.getDescription() + " was born, position:"+position);
                Logger.printLog(newAnimal.getDescription() +" parents: "+getDescription() + ":" + animal.getDescription());
            }
        }
    }

    @Override
    public void die(Island island, int position) {
            island.arrayCells[position].removeFromCellAnimalList(this);
    }
}
