package island.animal.model;

import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public abstract class Animal implements IAnimal{
    private int typeId;
    private double weight;
    private double normalWeight;
    private boolean sex;
    private boolean fullAnimal;
    private int maxSpeed;
    private double maxFoodWeight;
    private Animals animals;
    private Island island;
    private int position;
    private long uuid;

    public Animal(Animals animals, Island island) {
        this.animals = animals;
        this.island = island;
//        double[] parameters = MainData.getAnimalParameters(typeId);
        normalWeight = weight = animals.weight;
        maxSpeed = animals.speed;
        maxFoodWeight = animals.feed;
        sex = RandomValue.getBoolRandom();
        fullAnimal = false;
        position = -1;
        uuid = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getNormalWeight() {
        return normalWeight;
    }

    public boolean isSex() {
        return sex;
    }

    public void setFullAnimal(boolean fullAnimal) {
        this.fullAnimal = fullAnimal;
    }

    public boolean isFullAnimal() {
        return fullAnimal;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public long getUuid() {
        return uuid;
    }

    @Override
    public void move() {
        int step = RandomValue.getIntRandom(maxSpeed + 1);
        for (int j = 0; j <= step; j++) {
            int nextPosition = -1;
            while (nextPosition == -1) {
                int randomDirection = RandomValue.getIntRandom(Cell.Direction.values().length);
                Cell.Direction dir = Cell.Direction.values()[randomDirection];
                nextPosition = island.arrayCells[position].nextCell(dir);
//                if (nextPosition != -1) {
//                    break;
//                }
            }
            island.arrayCells[position].removeFromCellAnimalList(this);
            island.arrayCells[nextPosition].addToCellAnimalList(this);
            position = nextPosition;
        }
//        int step = RandomValue.getIntRandom(maxSpeed + 1);
//        for (int j = 0; j <= step; j++) {
//            if (position != -1) {
//                island.arrayCells[position].removeFromCellAnimalList(this);
//                int nextPosition = Cell.next(position);
//                island.arrayCells[nextPosition].addToCellAnimalList(this);        //?????????????????
//                position = nextPosition;
//            }
//
//        }
////        ScheduledExecutorService ses = new ScheduledThreadPoolExecutor(3);
////        ses.scheduleWithFixedDelay(this);
    }

    @Override
    public void multiply() {

    }

    @Override
    public void die() {
        if (weight <= normalWeight * 0.4) {
            island.arrayCells[position].removeFromCellAnimalList(this);
            Logger.printLog("Animal " + this.getClass().getName() + "(" + this.uuid + ")" + " is dead...");
        }
    }
}
