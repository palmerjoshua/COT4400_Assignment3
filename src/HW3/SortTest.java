package HW3;

import junit.framework.TestCase;
import org.junit.Assert;

public class SortTest extends TestCase {
    private final int[] ORIGINAL = {2, 5, 3, 7, 5, 9, 6, 7, 3, 5, 8, 1, 8};
    private final int[] EXPECTED = {1, 2, 3, 3, 5, 5, 5, 6, 7, 7, 8, 8, 9};

    public void testInsertionSort() throws Exception {
        int[] result = ORIGINAL.clone();
        InsertionSort.insertionSort(result);
        Assert.assertArrayEquals(EXPECTED, result);
    }

    public void testHeapSort() throws Exception {
        int[] result = ORIGINAL.clone();
        HeapSort.heapSort(result);
        Assert.assertArrayEquals(EXPECTED, result);
    }

    public void testMergeSort() throws Exception {
        int[] result = ORIGINAL.clone();
        MergeSort.mergeSort(result, 0, result.length-1);
        Assert.assertArrayEquals(EXPECTED, result);
    }

}