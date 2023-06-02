package island.animal.model;

import java.util.List;
import java.util.ArrayList;

public class Wolf extends Predator{
    public Wolf(Animals animals, Island island) {
        super(animals, island);
    }

    @Override
    public void eat() {
        super.eat();
        List<Animal> list =  new ArrayList<>(island.arrayCells[getPosition()].getAnimals());
        for (Animal animal : list) {
            if (animals.canEat.containsKey(animal.getAnimals())) {
                int potentialProbability = animals.canEat.get(animal.getAnimals());
                int probability = RandomValue.getIntRandom(101);
                double potentialFoodWeight = getMaxAnimalWeight() - getWeight();
                Integer counter = (int)island.arrayCells[getPosition()].getAnimals().stream().filter(getClass()::equals).count();
                if (probability > potentialProbability && getWeight() < getMaxAnimalWeight()) {
                    double foodWeight = animal.getWeight() <= potentialFoodWeight ? animal.getWeight() : potentialFoodWeight;
                    setWeight(getWeight() + foodWeight);
                    Logger.printLog(getClass().getSimpleName() + " (" + getUuid() + ") ate a " + animal.getClass().getSimpleName().toLowerCase() + ".");
                    Logger.printLog(animal.getClass().getSimpleName() + " (" + animal.getUuid() + ")" + " is eaten...");
                    if (counter > 1 && animal.getWeight() > getMaxFoodWeight()) {
                        double sharedFood = (animal.getWeight() - foodWeight) / counter;
                        for (Animal wolf : list) {
                            if (wolf.getClass().getSimpleName().equals(getClass().getSimpleName()) && wolf.getUuid() != getUuid()) {
                                wolf.setWeight(wolf.getWeight() + sharedFood);
                            }
                        }
                    }
                    animal.die();
                } else {
                    setWeight(getWeight() - 1);
                    if (getWeight() <= getNormalWeight() * 0.4) {
                        Logger.printLog(getClass().getSimpleName() + " (" + getUuid() + ") died of starvation.");
                        die();
                    }
                }
            }
        }
    }
}
