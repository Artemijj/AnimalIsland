package island.animal.model;

public abstract class Herbivore extends Animal{

    private Animals animals;
    private Island island;
    public Herbivore(Animals animals, Island island) {
        super(animals, island);
        this.animals = animals;
        this.island = island;
    }

    @Override
    public void eat() {
        System.out.println("Weight " + this.getWeight());
        int plantCount = island.arrayCells[getPosition()].getPlantCount();
        if (plantCount > 0 && this.getWeight() <= this.getNormalWeight() + this.getMaxFoodWeight()) {
            this.setWeight(this.getWeight() + 1);
            island.arrayCells[getPosition()].setPlantCount(plantCount - 1);
        } else {
            this.setWeight(this.getWeight() - 1);
            if (this.getWeight() <= this.getNormalWeight() * 0.4){
                die();
            }
        }
        System.out.println("Weight " + this.getWeight());
    }
}
