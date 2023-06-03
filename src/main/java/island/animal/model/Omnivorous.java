package island.animal.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Omnivorous extends Animal{
    public Omnivorous(Species species) {
        super(species);
    }

    @Override
    public void eat(Island island, int position) {
        int plantCount = island.arrayCells[position].getPlantCount();
        List<Animal> list =  new ArrayList<>(island.arrayCells[position].getAnimals());
        for (Animal animal : list) {
            if (species.canEat.containsKey(animal.getSpecies())) {
                int potentialProbability = species.canEat.get(animal.getSpecies());
                int probability = RandomValue.getIntRandom(101);
                double potentialFoodWeight = getMaxAnimalWeight() - getWeight();
                if (probability > potentialProbability && getWeight() < getMaxAnimalWeight()) {
                    double foodWeight = animal.getWeight() <= potentialFoodWeight ? animal.getWeight() : potentialFoodWeight;
                    setWeight(getWeight() + foodWeight);
                    Logger.printLog(getDescription() + " ate a " + animal.getDescription() + ".");
                    Logger.printLog(animal.getDescription() + " is eaten, at field " + position);
                    animal.die(island, position);
                } else if (plantCount > 0 && getWeight() < getMaxAnimalWeight()) {
                    setWeight(getWeight() + 1);
                    island.arrayCells[position].setPlantCount(plantCount - 1);
                    Logger.printLog(getDescription() + " ate a plant, at field " + position);
                } else {
                    setWeight(getWeight() - 1);
                    if (getWeight() <= species.weight * 0.4) {
                        Logger.printLog(getDescription() + " died of starvation, at field " + position);
                        die(island, position);
                    }
                }
            }
        }

    }
}
