package island.animal.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Omnivorous extends Animal{

    String type = "Omnivorous";
    public Omnivorous(Species species) {
        super(species);
//        this.animals = animals;
//        this.island = island;
    }

    @Override
    public void eat(Island island, int position) {
        int plantCount = island.arrayCells[position].getPlantCount();
        List<Animal> list =  new ArrayList<>(island.arrayCells[position].getAnimals());
        for (Animal animal : list) {
            if (species.canEat.containsKey(animal.getAnimals())) {
                int potentialProbability = species.canEat.get(animal.getAnimals());
                int probability = RandomValue.getIntRandom(101);
                double potentialFoodWeight = this.getMaxAnimalWeight() - this.getWeight();
                if (probability > potentialProbability && this.getWeight() < this.getMaxAnimalWeight()) {
                    double foodWeight = animal.getWeight() <= potentialFoodWeight ? animal.getWeight() : potentialFoodWeight;
                    this.setWeight(this.getWeight() + foodWeight);
                    Logger.printLog(this.getClass().getSimpleName() + " (" + this.getUuid() + ") ate a " + animal.getClass().getSimpleName().toLowerCase() + ".");
                    Logger.printLog(animal.getClass().getSimpleName() + " (" + animal.getUuid() + ")" + " is eaten...");
                    animal.die(island, position);
                } else if (plantCount > 0 && this.getWeight() < this.getMaxAnimalWeight()) {
                    this.setWeight(this.getWeight() + 1);
                    island.arrayCells[position].setPlantCount(plantCount - 1);
                    Logger.printLog(this.getClass().getSimpleName() + " (" + this.getUuid() + ") ate a plant.");
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
