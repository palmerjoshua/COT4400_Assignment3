package HW3;


public class Main {

    private static long getTimeForInsertionSort(int[] array) {
        long start, end;
        start = System.nanoTime();
        InsertionSort.sort(array);
        end = System.nanoTime();
        return end - start;
    }

    private static long getTimeForMergeSort(int[] array) {
        long start, end;
        start = System.nanoTime();
        MergeSort.sort(array);
        end = System.nanoTime();
        return end - start;
    }

    private static long getTimeForHeapSort(int[] array) {
        long start, end;
        start = System.nanoTime();
        HeapSort.sort(array);
        end = System.nanoTime();
        return end - start;
    }

    private static long[] getAverageRuntime(int[] array) {
        long[] result = new long[3];
        int i;
        long heapSum = 0, insertionSum = 0, mergeSum = 0;
        for (i=0; i < 10; i++) {
            heapSum += getTimeForHeapSort(array);
            insertionSum += getTimeForInsertionSort(array);
            mergeSum += getTimeForMergeSort(array);
        }
        result[0] = heapSum / 10;
        result[1] = insertionSum / 10;
        result[2] = mergeSum / 10;
        return result;
    }

    public static void main(String[] args) {
        RandomArray randomArray = new RandomArray();
        int[] currentArray;
        long[] runTimes;
        for (int i=1000; i <= 20000; i += 1000) {
            currentArray = randomArray.getNewArray(i);
            runTimes = getAverageRuntime(currentArray);
            System.out.println("n: "+ i);
            System.out.println("Heap Sort: " + runTimes[0] + " microseconds");
            System.out.println("Insertion Sort: " + runTimes[1] + " microseconds");
            System.out.println("Merge Sort:" + runTimes[2] + " microseconds\n");
        }
    }
}
