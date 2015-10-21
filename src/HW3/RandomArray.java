package HW3;

import java.util.Random;

public class RandomArray {
    private Random rand;
    private int[] array;

    public RandomArray(int length) {
        array = new int[length];
        rand = new Random();
        newRandomArray();
    }

    public RandomArray() {
        array = new int[1000];
        rand = new Random();
        newRandomArray();
    }

    private void newRandomArray() {
        for(int i=0; i < array.length; i++) {
            array[i] = rand.nextInt();
        }
    }

    public int[] getArray() {
        return array;
    }

    public int[] getNewArray() {
        newRandomArray();
        return array;
    }

    public int[] getNewArray(int newLength) {
        array = new int[newLength];
        newRandomArray();
        return array;
    }
}
