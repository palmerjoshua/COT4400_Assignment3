package HW3;

public class InsertionSort {

    public static void sort(int[] arr) {
        int key, i;
        for (int j=1; j<arr.length; j++) {
            key = arr[j];
            i = j - 1;
            while (i >= 0 && arr[i] > key) {
                arr[i+1] = arr[i];
                i -= 1;
            }
            arr[i+1] = key;
        }
    }
}
