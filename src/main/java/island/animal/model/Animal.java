package island.animal.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public abstract class Animal implements IAnimal{
    private int typeId;
    private double weight;
    private double normalWeight;
    private double maxAnimalWeight;
    private boolean sex;
    private boolean fullAnimal;
    private int maxSpeed;
    private double maxFoodWeight;
    Animals animals;
    Island island;
    private int position;
    private long uuid;

    public Animal(Animals animals, Island island) {
        this.animals = animals;
        this.island = island;
//        double[] parameters = MainData.getAnimalParameters(typeId);
        normalWeight = weight = animals.weight;
        maxSpeed = animals.speed;
        maxFoodWeight = animals.feed;
        maxAnimalWeight = normalWeight + maxFoodWeight;
        sex = RandomValue.getBoolRandom();
        fullAnimal = false;
        position = -1;
        uuid = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }

//    ScheduledExecutorService ses = new ScheduledThreadPoolExecutor(3);


    public Animals getAnimals() {
        return animals;
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

    public double getMaxFoodWeight() {
        return maxFoodWeight;
    }

    public double getMaxAnimalWeight() {
        return maxAnimalWeight;
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
        System.out.println("Step = " + step); //!!!!!!!!!!!!!!
        if (step != 0) {
            int y = 0; //!!!!!!!!!!!!!!
            for (int j = 0; j < step; j++) {
                int nextPosition = -1;
                System.out.println("for = " + j); //!!!!!!!!!!!!!!
                while (nextPosition == -1) {
                    System.out.println("while = " + y); //!!!!!!!!!!!!!!
                    int randomDirection = RandomValue.getIntRandom(Cell.Direction.values().length);
                    Cell.Direction dir = Cell.Direction.values()[randomDirection];
                    System.out.println("Dir = " + dir); //!!!!!!!!!!!!!!
                    nextPosition = island.arrayCells[position].nextCell(dir);
                    System.out.println("W nextPosition = " + nextPosition); //!!!!!!!!!!!!!!
                    if (nextPosition == position) {
                    nextPosition = -1;
                    }
                    y++; //!!!!!!!!!!!!!!
                }
//            System.out.println("Dir = " + dir);
                System.out.println("F nextPosition = " + nextPosition); //!!!!!!!!!!!!!!
                island.arrayCells[position].removeFromCellAnimalList(this);
                island.arrayCells[nextPosition].addToCellAnimalList(this);
                position = nextPosition;
//            ses.scheduleWithFixedDelay(this.eat(), 1, 5, TimeUnit.SECONDS);
            }
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
//        eat();
    }

    @Override
    public void reproduction() {
        String type = this.getClass().getSimpleName();
        List<Animal> list =  new ArrayList<>(island.arrayCells[getPosition()].getAnimals());
        for (Animal animal : list) {
            if (type == animal.getClass().getSimpleName() && this.isSex() != animal.isSex()) {
                new DefineAnimals(island).createAnimal(type.toLowerCase(), getPosition());
                Logger.printLog("Animal " + this.getClass().getSimpleName() + " was born.");
            }
        }
    }

    @Override
    public void die() {
//        if (weight <= normalWeight * 0.4) {
            island.arrayCells[position].removeFromCellAnimalList(this);
            Logger.printLog("Animal " + this.getClass().getSimpleName() + " (" + this.uuid + ")" + " is dead...");
//        }
    }
}
