package HW3;

public class MergeSort {

    private static void mergeSort(int[] arr, int p, int r) {
        if (p < r) {
            double b = (p+r) / 2;
            int q = (int)Math.floor(b);
            mergeSort(arr, p, q);
            mergeSort(arr, q+1, r);
            merge(arr, p, q, r);
        }
        return;
    }

    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length-1);
    }

    private static void merge(int[] arr, int p, int q, int r) {
        int n1 = (q - p) + 1;
        int n2 = r - q;
        int[] left = new int[n1+1];
        int[] right = new int[n2+1];
        int i, j, b, d;
        for (i = 0; i < n1; i++) {
            b = p + i;
            d = arr[b];
            left[i] = d;
        }
        for (j = 0; j < n2; j++) {
            d = arr[q+j];
            right[j] = d;
        }
        left[n1] = Integer.MAX_VALUE;
        right[n2] = Integer.MAX_VALUE;
        i = j = 0;
        for (int k = p; k <= r; k++) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i += 1;
            } else {
                arr[k] = right[j];
                j += 1;
            }
        }
        return;
    }

    public static void main(String[] args) {
        int[] arr = {1,4,3,6,5,8,7,4,6,8,0,3,6,7,4,8};
        HeapSort.printArray(arr);
        mergeSort(arr);
        HeapSort.printArray(arr);
    }
}
