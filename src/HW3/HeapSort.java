package HW3;

public class HeapSort {

    public static void sort(int[] arr) {
        buildMaxHeap(arr);
        for (int i = heapSize; i > 0; i--) {
            exchange(arr, 0, i);
            heapSize -= 1;
            maxHeapify(arr, 0);
        }
    }

    private static void exchange(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void maxHeapify(int[] arr, int i) {
        int left = 2 * i,
            right = 2 * i + 1,
            largest = i;

        if (left <= heapSize && arr[left] > arr[i]) {
            largest = left;
        }
        if (right <= heapSize && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            exchange(arr, i, largest);
            maxHeapify(arr, largest);
        }
    }

    private static void buildMaxHeap(int[] arr) {
        heapSize = arr.length-1;
        for (int i = arr.length / 2; i >= 0; i--) {
            maxHeapify(arr, i);
        }
    }

    public static void printArray(int[] array) {
        String[] intStrings = new String[array.length];
        for (int i=0; i<array.length; i++) {
            intStrings[i] = String.format("%d", array[i]);
        }
        String result = String.join(", ", intStrings);
        System.out.println(result);
    }

    public static void main(String[] args) {
        int[] arr = {1,4,3,6,5,8,7,4,6,8,0,3,6,7,4,8};
        printArray(arr);
        sort(arr);
        printArray(arr);
    }

    private static int heapSize;
}
