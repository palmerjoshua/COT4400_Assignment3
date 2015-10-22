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

    private static int heapSize;
}
