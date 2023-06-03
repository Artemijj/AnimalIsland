package island.animal.model;

public abstract class Herbivore extends Animal{

    public Herbivore(Species species) {
        super(species);
    }

    @Override
    public void eat(Island island, int position) {
        int plantCount = island.arrayCells[position].getPlantCount();
        if (plantCount > 0 && getWeight() < getMaxAnimalWeight()) {
            setWeight(getWeight() + 1);
            island.arrayCells[position].setPlantCount(plantCount - 1);
            Logger.printLog(getDescription() + " ate a plant, at field " + position);
        } else {
            setWeight(getWeight() - 1);
            if (getWeight() <= species.weight * 0.4){
                Logger.printLog(getDescription() + " died of starvation, at field " + position);
                die(island, position);
            }
        }
    }
}
