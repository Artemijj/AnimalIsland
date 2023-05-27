package island.animal.model;

public abstract class Herbivore extends Animal{

    private Island island;
    public Herbivore(Animals animals, Island island) {
        super(animals, island);
    }

    @Override
    public void eat() {
        int plantCount = island.arrayCells[getPosition()].getPlantCount();
        if (plantCount > 0) {
            this.setWeight(this.getWeight() + 1);
            island.arrayCells[getPosition()].setPlantCount(plantCount - 1);
        }
    }
}
