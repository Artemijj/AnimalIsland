package island.animal.model;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private int plantCount;
    private List<Animal> animals = new ArrayList<>();
    private int i;
    private int n;
    private int m;

    public Cell(int i, int n, int m) {
        this.i = i;
        this.n = n;
        this.m = m;
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
