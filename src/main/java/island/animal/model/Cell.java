package island.animal.model;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private int plantCount;
    private List<Animal> animals;
    private int i;
    private int n;
    private int m;

    public Cell(int i, int n, int m) {
        this.i = i;
        this.n = n;
        this.m = m;
        animals = new ArrayList<>();
    }

    public void setPlantCount(int plantCount) {
        this.plantCount = plantCount;
    }

    public int getPlantCount() {
        return plantCount;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public enum Direction { UP, DOWN, LEFT, RIGHT }
    public int nextCell(Direction dir) {
        switch (dir) {
            case UP:
                return i - n < 0 ? -1 : i - n;
            case DOWN:
                return i + n >= m * n ? -1 : i + n;
            case LEFT:
                return i % n == 0 ? -1 : i - 1;
            case RIGHT:
                return (i + 1) % n == 0 ? -1 : i + 1;
        }
        return -1;
    }

    public int next() {
        if (i ==0) {
            int[] arr = {i + 1, i + n};
            return arr[RandomValue.getIntRandom(2)];
        } else if (i == n - 1) {
            int[] arr = {i - 1, i + n};
            return arr[RandomValue.getIntRandom(2)];
        } else if (i == n * m - n) {
            int[] arr = {i + 1, i - n};
            return arr[RandomValue.getIntRandom(2)];
        } else if (i == n * m - 1) {
            int[] arr = {i - 1, i - n};
            return arr[RandomValue.getIntRandom(2)];
        } else if (i > 0 && i < n - 1) {
            int[] arr = {i + 1, i - 1, i + n};
            return arr[RandomValue.getIntRandom(3)];
        } else if (i > 0 && i < n * m - n && i % n == 0) {
            int[] arr = {i + 1, i + n, i - n};
            return arr[RandomValue.getIntRandom(3)];
        } else if (i > n * m - n && i < n * m) {
            int[] arr = {i + 1, i - 1, i - n};
            return arr[RandomValue.getIntRandom(3)];
        } else if (i > n - 1 && (i + 1) % n == 0 && i < n * m - 1) {
            int[] arr = {i - 1, i + n, i - n};
            return arr[RandomValue.getIntRandom(3)];
        } else {
            int[] arr = {i + 1, i - 1, i + n, i - n};
            return arr[RandomValue.getIntRandom(4)];
        }
    }
}
