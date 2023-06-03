package island.animal.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Predator extends Animal{
    public Predator(Species species) {
        super(species);
    }

    @Override
    public void eat(Island island, int position) {
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
