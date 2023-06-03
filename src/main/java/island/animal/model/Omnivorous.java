package island.animal.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Omnivorous extends Animal{
//    Animals animals;
//    Island island;
    String type = "Omnivorous";
    public Omnivorous(Species species, Island island) {
        super(species, island);
//        this.animals = animals;
//        this.island = island;
    }

    @Override
    public void eat() {
        int plantCount = island.arrayCells[getPosition()].getPlantCount();
        List<Animal> list =  new ArrayList<>(island.arrayCells[getPosition()].getAnimals());
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
                    animal.die();
                } else if (plantCount > 0 && this.getWeight() < this.getMaxAnimalWeight()) {
                    this.setWeight(this.getWeight() + 1);
                    island.arrayCells[getPosition()].setPlantCount(plantCount - 1);
                    Logger.printLog(this.getClass().getSimpleName() + " (" + this.getUuid() + ") ate a plant.");
                } else {
                    this.setWeight(this.getWeight() - 1);
                    if (this.getWeight() <= this.getNormalWeight() * 0.4) {
                        Logger.printLog(this.getClass().getSimpleName() + " (" + this.getUuid() + ") died of starvation.");
                        die();
                    }
                }
            }
        }

    }
}
