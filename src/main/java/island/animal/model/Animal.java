package island.animal.model;

public abstract class Animal implements IAnimal{
    private int typeId;
    private double weight;
//    private int maxWeight;
    private boolean sex;
    private boolean fold;
    private double maxSpeed;
    private double maxFoodWeight;

    public Animal(int typeId) {
        this.typeId = typeId;
        double[] parameters = MainData.getAnimalParameters(typeId);
        weight = parameters[0];
        maxSpeed = parameters[2];
        maxFoodWeight = parameters[3];
        sex = RandomValue.getRandom();
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

    public void setFold(boolean fold) {
        this.fold = fold;
    }

    public boolean isFold() {
        return fold;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }
}
