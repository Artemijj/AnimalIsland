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
//                    System.out.println(animal.getClass().getSimpleName() + " is eaten..."); //!!!!!!!!!!!!!!!!!
                    Logger.printLog(this.getClass().getSimpleName() + " (" + this.getUuid() + ") ate a " + animal.getClass().getSimpleName().toLowerCase() + ".");
                    Logger.printLog(animal.getClass().getSimpleName() + " (" + animal.getUuid() + ")" + " is eaten...");
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
