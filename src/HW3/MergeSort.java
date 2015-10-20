package HW3;

public class MergeSort {

    public static void mergeSort(int[] arr, int p, int r) {
        if (p < r) {
            int q = (p+r) / 2;
            mergeSort(arr, p, q);
            mergeSort(arr, q+1, r);
            merge(arr, p, q, r);
        }
    }

    private static void merge(int[] arr, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        int[] left = new int[n1+1];
        int[] right = new int[n2+1];
        int i, j;
        for (i = 0; i < n1; i++) {
            left[i] = arr[p + i - 1];
        }
        for (j = 0; j < n2; j++) {
            right[j] = arr[q+j];
        }
        left[n1] = (int)Double.POSITIVE_INFINITY;
        right[n2] = (int)Double.POSITIVE_INFINITY;
        i = j = 0;
        for (int k = p; k < r; k++) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i += 1;
            } else {
                arr[k] = right[j];
                j += 1;
            }
        }
    }

    public static void main(String[] args) {

    }
}
