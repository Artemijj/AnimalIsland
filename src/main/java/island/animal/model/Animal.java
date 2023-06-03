package island.animal.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Animal implements IAnimal{
//    private String type;
    private double weight;
    private int quantity;
    private double normalWeight;
    private double maxAnimalWeight;
    private boolean sex;
    private boolean fullAnimal;
    private int maxSpeed;
    private double maxFoodWeight;
    private String icon;
    Species species;

    private int position;
    private long uuid;

    public Animal(Species species) {
        this.species = species;
//        double[] parameters = MainData.getAnimalParameters(typeId);
//        type = "";
        normalWeight = weight = species.weight;
        maxSpeed = species.speed;
        quantity = species.quantity;
        maxFoodWeight = species.feed;
        icon = species.icon;
        maxAnimalWeight = normalWeight + maxFoodWeight;
        sex = RandomValue.getBoolRandom();
        fullAnimal = false;
        position = -1;
        uuid = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }

//    ScheduledExecutorService ses = new ScheduledThreadPoolExecutor(3);


//    public String getType() {
//        return type;
//    }

    public Species getAnimals() {
        return species;
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

    public int getQuantity() {
        return quantity;
    }

    public String getIcon() {
        return icon;
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
    public void move(Island island) {
        int step = RandomValue.getIntRandom(maxSpeed + 1);
//        System.out.println("Step = " + step); //!!!!!!!!!!!!!!
        if (step != 0) {
//            int y = 0; //!!!!!!!!!!!!!!
            int tempPosition = position;
            for (int j = 0; j < step; j++) {
                int nextPosition = -1;
//                System.out.println("for = " + j); //!!!!!!!!!!!!!!
                while (nextPosition == -1) {
//                    System.out.println("while = " + y); //!!!!!!!!!!!!!!
                    int randomDirection = RandomValue.getIntRandom(Cell.Direction.values().length);
                    Cell.Direction dir = Cell.Direction.values()[randomDirection];
//                    System.out.println("Dir = " + dir); //!!!!!!!!!!!!!!
                    nextPosition = island.arrayCells[tempPosition].nextCell(dir);
//                    System.out.println("W nextPosition = " + nextPosition); //!!!!!!!!!!!!!!
                    if (nextPosition == tempPosition) {
                    nextPosition = -1;
                    }
//                    y++; //!!!!!!!!!!!!!!
                }
//            System.out.println("Dir = " + dir);
//                System.out.println("F nextPosition = " + nextPosition); //!!!!!!!!!!!!!!
//                island.arrayCells[position].removeFromCellAnimalList(this);
//                island.arrayCells[nextPosition].addToCellAnimalList(this);
                tempPosition = nextPosition;
//                Logger.printLog(getClass().getSimpleName() + " (" + getUuid() + ")" + " weight " + getWeight() + " move to field " + getPosition());
            }
            Integer counter = (int)island.arrayCells[tempPosition].getAnimals().stream().filter(getClass()::equals).count();
            if (counter < quantity) {
                island.arrayCells[position].removeFromCellAnimalList(this);
                island.arrayCells[tempPosition].addToCellAnimalList(this);
                position = tempPosition;
                Logger.printLog(getClass().getSimpleName() + " " + icon + " (" + getUuid() + ")" + " weight " + getWeight() + " move to field " + getPosition());
//                System.out.println("Type - " + this.getType());
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
    public void reproduction(Island island) {
        String type = getClass().getSimpleName();
        List<Animal> list =  new ArrayList<>(island.arrayCells[getPosition()].getAnimals());
        for (Animal animal : list) {
            if (type == animal.getClass().getSimpleName() && isSex() != animal.isSex()) {
                Animal newAnimal = DefineAnimals.createAnimal(island, type.toLowerCase(), getPosition());
                Logger.printLog(newAnimal.getClass().getSimpleName() + " " + newAnimal.icon + " (" + newAnimal.getUuid() + ") was born.");
            }
        }
    }

    @Override
    public void die(Island island) {
//        if (weight <= normalWeight * 0.4) {
            island.arrayCells[position].removeFromCellAnimalList(this);
///            Logger.printLog("Animal " + this.getClass().getSimpleName() + " (" + this.uuid + ")" + " is dead...");
//        }
    }
}
