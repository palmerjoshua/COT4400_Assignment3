package HW3;

public class MergeSort {

    public static void sort(int[] arr) {
        mergeSort(arr, 0, arr.length-1);
    }

    private static void mergeSort(int[] arr, int first, int last) {
        if (first < last) {
            int middle = (int)Math.floor((first+last) / 2);
            mergeSort(arr, first, middle);
            mergeSort(arr, middle+1, last);
            merge(arr, first, middle, last);
        }
    }

    private static void merge(int[] arr, int first, int middle, int last) {
        int[] copy = arr.clone();
        int i = first, j = middle+1, k = first;
        while (i <= middle && j <= last) {
            if (copy[i] <= copy[j]) {
                arr[k] = copy[i];
                i++;
            } else {
                arr[k] = copy[j];
                j++;
            }
            k++;
        }
        while ( i <= middle) {
            arr[k] = copy[i];
            k++;
            i++;
        }
    }

    public static void main(String[] args) {
        int[] arr = {3,2,7,4,8,5,1};
        HeapSort.printArray(arr);
        sort(arr);
        HeapSort.printArray(arr);
    }
}
