package island.animal.model;

import java.util.List;
import java.util.ArrayList;

public class Wolf extends Predator{
    public Wolf(Species species) {
        super(species);
    }

    @Override
    public void eat(Island island, int position) {
        List<Animal> list = new ArrayList<>(island.arrayCells[position].getAnimals());
        for (Animal animal : list) {
            if (species.canEat.containsKey(animal.getSpecies())) {
                int potentialProbability = species.canEat.get(animal.getSpecies());
                int probability = RandomValue.getIntRandom(101);
                double potentialFoodWeight = getMaxAnimalWeight() - getWeight();
                Integer counter = (int) island.arrayCells[position].getAnimals().stream().filter(getClass()::equals).count();
                if (probability > potentialProbability && getWeight() < getMaxAnimalWeight()) {
                    double foodWeight = animal.getWeight() <= potentialFoodWeight ? animal.getWeight() : potentialFoodWeight;
                    setWeight(getWeight() + foodWeight);
                    Logger.printLog(getDescription() + " ate a " + animal.getDescription() + ".");
                    Logger.printLog(animal.getDescription() + " is eaten, at field " + position);
                    if (counter > 1 && animal.getWeight() > species.feed) {
                        double sharedFood = (animal.getWeight() - foodWeight) / counter;
                        for (Animal wolf : list) {
                            if (wolf.getClass().getSimpleName().equals(getClass().getSimpleName()) && wolf.getUuid() != getUuid()) {
                                wolf.setWeight(wolf.getWeight() + sharedFood);
                                Logger.printLog(getDescription() + " ate a " + animal.getDescription() + ".");
                            }
                        }
                    }
                    animal.die(island, position);
                }
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
