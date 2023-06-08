package island.animal.model;

public abstract class Herbivore extends Animal{

    public Herbivore(Species species) {
        super(species);
    }

    @Override
    public void eat(Island island, int position) {
        double plantCount = island.arrayCells[position].getPlantCount();
        double potentialFoodWeight = getMaxAnimalWeight() - getWeight();
        if (plantCount > 0 && getWeight() < getMaxAnimalWeight()) {
            double plantFoodWeight = plantCount <= potentialFoodWeight ? plantCount : potentialFoodWeight;
            setWeight(getWeight() + plantFoodWeight);
            island.arrayCells[position].setPlantCount(plantCount - plantFoodWeight);
            Logger.printLog(getDescription() + " ate a plant, at field " + position);
        } else {
            setWeight(getWeight() * 0.95);
            if (getWeight() <= species.weight * 0.4){
                Logger.printLog(getDescription() + " died of starvation, at field " + position);
                die(island, position);
            }
        }
    }
}
