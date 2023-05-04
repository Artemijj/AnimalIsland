package island.animal.model;

public abstract class Animal implements IAnimal{
    private int typeId;
    private double weight;
//    private int maxWeight;
    private boolean sex;
    private boolean fullAnimal;
    private double maxSpeed;
    private double maxFoodWeight;

    public Animal(int typeId) {
        this.typeId = typeId;
        double[] parameters = MainData.getAnimalParameters(typeId);
        weight = parameters[0];
        maxSpeed = parameters[2];
        maxFoodWeight = parameters[3];
        sex = RandomValue.getRandom();
        fullAnimal = false;
    }

    public double getWeight() {
        return weight;
    }

//    public int getMaxWeight() {
//        return maxWeight;
//    }

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

    @Override
    public void eat() {

    }

    @Override
    public void move() {

    }

    @Override
    public void multiply() {

    }

    @Override
    public void die() {

    }
}
