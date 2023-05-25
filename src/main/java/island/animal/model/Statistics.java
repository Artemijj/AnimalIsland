package island.animal.model;

public class Statistics implements Runnable{
    private Island island;

    public Statistics(Island island) {
        this.island = island;
    }

    @Override
    public void run() {
        island.printArray();
    }
}
