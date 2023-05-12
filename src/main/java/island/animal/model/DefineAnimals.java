package island.animal.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DefineAnimals implements Runnable{
    private static Island island;

    public DefineAnimals(Island island) {
        this.island = island;
    }

    public static void createAnimal(String type, int i) {
        switch (type) {
            case "fox" :
                Animal fox = new Fox(Animals.Fox, island);
                island.arrayCells[i].addToCellAnimalList(fox);
                fox.setPosition(i);
                break;
            case "sheep" :
                Animal sheep = new Sheep(Animals.Sheep, island);
                island.arrayCells[i].addToCellAnimalList(sheep);
                sheep.setPosition(i);
                break;
        }
    }


    @Override
    public void run() {
        Animal fox = new Fox(Animals.Fox, island);
        island.arrayCells[RandomValue.getIntRandom(island.getN() * island.getM())].addToCellAnimalList(fox);
        Animal sheep = new Sheep(Animals.Sheep, island);
        island.arrayCells[RandomValue.getIntRandom(island.getN() * island.getM())].addToCellAnimalList(sheep);
        Logger.printLog("The animal is created");
    }
}
