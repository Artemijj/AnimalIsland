package island.animal.model;

public abstract class Predator extends Animal{
    private Island island;
    public Predator(Animals animals, Island island) {
        super(animals, island);
    }

    @Override
    public Runnable eat() {

        return null;
    }
}
