package island.animal.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Omnivorous extends Animal{
    Animals animals;
    Island island;
    public Omnivorous(Animals animals, Island island) {
        super(animals, island);
        this.animals = animals;
        this.island = island;
    }

    @Override
    public void eat() {
        int plantCount = island.arrayCells[getPosition()].getPlantCount();
        List<Animal> list =  new ArrayList<>(island.arrayCells[getPosition()].getAnimals());
        for (Animal animal : list) {
            if (animals.canEat.containsKey(animal.getAnimals())) {
                int potentialProbability = animals.canEat.get(animal.getAnimals());
                int probability = RandomValue.getIntRandom(101);
                double potentialFoodWeight = this.getMaxAnimalWeight() - this.getWeight();
                if (probability > potentialProbability && this.getWeight() < this.getMaxAnimalWeight()) {
                    double foodWeight = animal.getWeight() <= potentialFoodWeight ? animal.getWeight() : potentialFoodWeight;
                    this.setWeight(this.getWeight() + foodWeight);
                    animal.die();
                } else if (plantCount > 0 && this.getWeight() < this.getMaxAnimalWeight()) {
                    this.setWeight(this.getWeight() + 1);
                    island.arrayCells[getPosition()].setPlantCount(plantCount - 1);
                } else {
                    this.setWeight(this.getWeight() - 1);
                    if (this.getWeight() <= this.getNormalWeight() * 0.4) {
                        die();
                    }
                }
            }
        }

    }
}
