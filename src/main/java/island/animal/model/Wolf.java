package island.animal.model;

import java.util.List;
import java.util.ArrayList;

public class Wolf extends Predator{
    public Wolf(Species species) {
        super(species);
    }

    @Override
    public void eat(Island island, int position) {
        super.eat(island, position);
        List<Animal> list =  new ArrayList<>(island.arrayCells[position].getAnimals());
        for (Animal animal : list) {
            if (species.canEat.containsKey(animal.getSpecies())) {
                int potentialProbability = species.canEat.get(animal.getSpecies());
                int probability = RandomValue.getIntRandom(101);
                double potentialFoodWeight = getMaxAnimalWeight() - getWeight();
                Integer counter = (int)island.arrayCells[position].getAnimals().stream().filter(getClass()::equals).count();
                if (probability > potentialProbability && getWeight() < getMaxAnimalWeight()) {
                    double foodWeight = animal.getWeight() <= potentialFoodWeight ? animal.getWeight() : potentialFoodWeight;
                    setWeight(getWeight() + foodWeight);
                    Logger.printLog(getClass().getSimpleName() + " (" + getUuid() + ") ate a " + animal.getClass().getSimpleName().toLowerCase() + ".");
                    Logger.printLog(animal.getClass().getSimpleName() + " (" + animal.getUuid() + ")" + " is eaten...");
                    if (counter > 1 && animal.getWeight() > species.feed) {
                        double sharedFood = (animal.getWeight() - foodWeight) / counter;
                        for (Animal wolf : list) {
                            if (wolf.getClass().getSimpleName().equals(getClass().getSimpleName()) && wolf.getUuid() != getUuid()) {
                                wolf.setWeight(wolf.getWeight() + sharedFood);
                            }
                        }
                    }
                    animal.die(island, position);
                } else {
                    setWeight(getWeight() - 1);
                    if (getWeight() <= species.weight * 0.4) {
                        Logger.printLog(getClass().getSimpleName() + " (" + getUuid() + ") died of starvation.");
                        die(island, position);
                    }
                }
            }
        }
    }
}
