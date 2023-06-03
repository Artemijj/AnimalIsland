package island.animal.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Predator extends Animal{
//    Island island;
    String type = "Predator";
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
                double potentialFoodWeight = this.getMaxAnimalWeight() - this.getWeight();
                if (probability > potentialProbability && this.getWeight() < this.getMaxAnimalWeight()) {
                    double foodWeight = animal.getWeight() <= potentialFoodWeight ? animal.getWeight() : potentialFoodWeight;
                    this.setWeight(this.getWeight() + foodWeight);
                    Logger.printLog(getDescription()+ " ate a " + animal.getDescription() + ".");
                    Logger.printLog(animal.getDescription() +" is eaten... field:" + position);
                    animal.die(island, position);
                } else {
                    this.setWeight(this.getWeight() - 1);
                    if (this.getWeight() <= species.weight * 0.4) {
                        Logger.printLog(this.getClass().getSimpleName() + " (" + this.getUuid() + ") died of starvation.");
                        die(island, position);
                    }
                }
            }
        }

    }
}
