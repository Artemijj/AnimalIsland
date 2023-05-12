package island.animal.model.test;

import island.animal.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MoveTest {

    @Test
    public void test0() {
        Island island = new Island(20, 10);
        Animal sheep = new Sheep(Animals.Sheep, island);
        island.arrayCells[15].addToCellAnimalList(sheep);
        sheep.setPosition(15);
        sheep.move();
        int position = sheep.getPosition();
        System.out.println(position);
        assertNotEquals(15, position);
    }
}
