package island.animal.model;

public abstract class Animal implements IAnimal{
    private int typeId;
    private double weight;
    private double normalWeight;
    private boolean sex;
    private boolean fullAnimal;
    private double maxSpeed;
    private double maxFoodWeight;

    public Animal(int typeId) {
        this.typeId = typeId;
        double[] parameters = MainData.getAnimalParameters(typeId);
        normalWeight = weight = parameters[0];
        maxSpeed = parameters[2];
        maxFoodWeight = parameters[3];
        sex = RandomValue.getBoolRandom();
        fullAnimal = false;
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
        if (weight <= normalWeight * 0.4) {
//     Животное умерло
        }
    }
}
