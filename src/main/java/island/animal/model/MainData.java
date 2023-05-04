package island.animal.model;

public class MainData {
    public final static double[][] animalParametersArray = {
                    {50,30,3,8},
                    {15,30,1,3},
                    {8,30,2,2},
                    {500,5,2,80},
                    {6,20,3,1},
                    {400,20,4,60},
                    {300,20,4,50},
                    {2,150,2,0.45D},
                    {0.05D,500,1,0.01D},
                    {60,140,3,10},
                    {70,140,3,10},
                    {400,50,2,50},
                    {700,10,3,100},
                    {1,200,4,0.15D},
                    {0.01D,1000,0,0}};

    public final static int[][] probabilityEatingArray = {
            {0,0,0,0,0,10,15,60,80,60,70,15,10,40,0,0},
            {0,0,15,0,0,0,0,20,40,0,0,0,0,10,0,0},
            {0,0,0,0,0,0,0,70,90,0,0,0,0,60,40,0},
            {0,80,0,0,0,40,80,80,90,70,70,50,20,10,0,0},
            {0,0,10,0,0,0,0,90,90,0,0,0,0,80,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,100},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,100},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,100},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,90,100},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,100},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,100},
            {0,0,0,0,0,0,0,0,50,0,0,0,0,0,90,100},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,100},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,90,100},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,100}};

    public static double[] getAnimalParameters(int typeId) {
        return animalParametersArray[typeId];
    }

    public static int[] getProbabilityEating(int typeId) {
        return probabilityEatingArray[typeId];
    }
}
