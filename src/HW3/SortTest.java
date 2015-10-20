package HW3;

import junit.framework.Assert;
import junit.framework.TestCase;


public class SortTest extends TestCase {
    private final int[] ORIGINAL = {2, 5, 3, 7, 5, 9, 6, 7, 3, 5, 8, 1, 8};
    private final int[] EXPECTED = {1, 2, 3, 3, 5, 5, 5, 6, 7, 7, 8, 8, 9};


    private void assertArrayEquals(int[] expected, int[] result) throws Exception {
        boolean equals = true;
        for(int i=0; i<result.length; i++) {
            if (result[i] != expected[i]) {
                equals = false;
                break;
            }
        }
        assertTrue(equals);
    }


    public void testInsertionSort() throws Exception {
        int[] result = ORIGINAL.clone();
        InsertionSort.insertionSort(result);
        assertArrayEquals(EXPECTED, result);
    }

    public void testHeapSort() throws Exception {
        int[] result = ORIGINAL.clone();
        HeapSort.heapSort(result);
        assertArrayEquals(EXPECTED, result);
    }


    public void testMergeSort() throws Exception {
        int[] result = ORIGINAL.clone();
        MergeSort.mergeSort(result);
        assertArrayEquals(EXPECTED, result);
    }

}