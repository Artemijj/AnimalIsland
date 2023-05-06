package island.animal.model;

import java.sql.Array;

public class Island {
    private int n;
    private int m;
    private Cell[] arrayCells;
    private int animalCount;
    public Island(int n, int m) {
        this.n = n;
        this.m = m;
        arrayCells = new Cell[n * m];
        for (int i = 0; i < n * m; i++) {
            arrayCells[i] = new Cell(i, n, m);
        }
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public Cell[] getArrayCells() {
        return arrayCells;
    }

    public int getAnimalCount() {
        for (int i = 0; i < n * m; i++) {
            animalCount += arrayCells[i].getAnimals().size();
        }
        return animalCount;
    }
}
