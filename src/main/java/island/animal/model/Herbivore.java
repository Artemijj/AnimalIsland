package island.animal.model;

public abstract class Herbivore extends Animal{

    public Herbivore(Species species) {
        super(species);
    }

    @Override
    public void eat(Island island, int position) {
//        System.out.println("Weight " + this.getWeight());
        int plantCount = island.arrayCells[position].getPlantCount();
        if (plantCount > 0 && this.getWeight() < this.getMaxAnimalWeight()) {
            this.setWeight(this.getWeight() + 1);
            island.arrayCells[position].setPlantCount(plantCount - 1);
            Logger.printLog(this.getClass().getSimpleName() + " (" + this.getUuid() + ") ate a plant.");
        } else {
            this.setWeight(this.getWeight() - 1);
            if (this.getWeight() <= species.weight * 0.4){
                Logger.printLog(this.getClass().getSimpleName() + " (" + this.getUuid() + ") died of starvation.");
                die(island, position);
            }
        }
//        System.out.println("Weight " + this.getWeight());
    }
}
