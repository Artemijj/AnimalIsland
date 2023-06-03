package island.animal.model.test;

import island.animal.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MoveTest {

    @Test
    public void test0() {
        Island island = new Island(new ModelParameter());
        Animal sheep = new Sheep(Species.Sheep);
        island.arrayCells[15].addToCellAnimalList(sheep);
        System.out.println("Start position = " + 15);
        int position = sheep.move(island, 15);
        System.out.println("New position = " + position);
        assertNotEquals(15, position);
    }
}
