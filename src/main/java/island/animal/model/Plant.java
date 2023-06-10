package island.animal.model;

public class Plant implements Runnable{
    private Island island;

    public Plant(Island island) {
        this.island = island;
    }

    @Override
    public void run() {
        island.arrayCells[RandomValue.getIntRandom(island.arrayCells.length)].addPlant(1);
        Logger.printLog("The plant is planted");
    }
}
