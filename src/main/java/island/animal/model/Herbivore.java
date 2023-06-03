package island.animal.model;

public abstract class Herbivore extends Animal{

//    Animals animals;
//    Island island;
//    String type;
    public Herbivore(Species species, Island island) {
        super(species, island);
//        type = "Herbivore";
//        this.animals = animals;
//        this.island = island;
    }

    @Override
    public void eat() {
//        System.out.println("Weight " + this.getWeight());
        int plantCount = island.arrayCells[getPosition()].getPlantCount();
        if (plantCount > 0 && this.getWeight() < this.getMaxAnimalWeight()) {
            this.setWeight(this.getWeight() + 1);
            island.arrayCells[getPosition()].setPlantCount(plantCount - 1);
            Logger.printLog(this.getClass().getSimpleName() + " (" + this.getUuid() + ") ate a plant.");
        } else {
            this.setWeight(this.getWeight() - 1);
            if (this.getWeight() <= this.getNormalWeight() * 0.4){
                Logger.printLog(this.getClass().getSimpleName() + " (" + this.getUuid() + ") died of starvation.");
                die();
            }
        }
//        System.out.println("Weight " + this.getWeight());
    }
}
