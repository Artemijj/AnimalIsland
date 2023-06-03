package island.animal.model;

public class DefineAnimals implements Runnable{
    private static Island island;

    public static Animal createAnimal(Island island, String type, int i) {
        switch (type) {
            case "wolf" :
                Animal wolf = new Fox(Species.Fox);
                island.arrayCells[i].addToCellAnimalList(wolf);
                wolf.setPosition(i);
                return wolf;
            case "fox" :
                Animal fox = new Fox(Species.Fox);
                island.arrayCells[i].addToCellAnimalList(fox);
                fox.setPosition(i);
                return fox;
            case "sheep" :
                Animal sheep = new Sheep(Species.Sheep);
                island.arrayCells[i].addToCellAnimalList(sheep);
                sheep.setPosition(i);
                return sheep;
        }
        return null;
    }


    @Override
    public void run() {
        Animal fox = new Fox(Species.Fox);
        island.arrayCells[RandomValue.getIntRandom(island.getN() * island.getM())].addToCellAnimalList(fox);
        Animal sheep = new Sheep(Species.Sheep);
        island.arrayCells[RandomValue.getIntRandom(island.getN() * island.getM())].addToCellAnimalList(sheep);
        Logger.printLog("The animal is created");
    }
}
