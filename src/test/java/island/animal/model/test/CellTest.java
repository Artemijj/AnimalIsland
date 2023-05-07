package island.animal.model.test;
import island.animal.model.*;
import org.junit.jupiter.api.Test;

import static island.animal.model.Cell.Direction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CellTest {
    @Test
    public void testHelloWorld() {
        Cell cell = new Cell(0, 10, 10);
        assertEquals(-1 , cell.nextCell(UP));
        assertEquals(10 , cell.nextCell(DOWN));
        assertEquals(1 , cell.nextCell(RIGHT));
        assertEquals(-1 , cell.nextCell(LEFT));
    }
}
