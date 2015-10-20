package HW3;
import junit.framework.TestCase;

import java.util.Arrays;

public class SortTest extends TestCase {

    public void testInsertionSort() throws Exception {
        int[] result = ORIGINAL.clone();
        InsertionSort.sort(result);
        assertArrayEquals(EXPECTED, result);
    }

    public void testHeapSort() throws Exception {
        int[] result = ORIGINAL.clone();
        HeapSort.sort(result);
        assertArrayEquals(EXPECTED, result);
    }

    public void testMergeSort() throws Exception {
        int[] result = ORIGINAL.clone();
        MergeSort.sort(result);
        assertArrayEquals(EXPECTED, result);
    }

    private void assertArrayEquals(int[] expected, int[] result) throws Exception {
        boolean equals = true;
        for(int i=0; i<result.length; i++) {
            if (result[i] != expected[i]) {
                equals = false;
                break;
            }
        }
        String error = "\nExpected: " + Arrays.toString(EXPECTED) + "\n";
        error += "Actual: " + Arrays.toString(result) + "\n";
        assertTrue(error, equals);
    }

    private final int[] ORIGINAL = {2, 5, 3, 7, 5, 9, 6, 7, 3, 5, 8, 1, 8};
    private final int[] EXPECTED = {1, 2, 3, 3, 5, 5, 5, 6, 7, 7, 8, 8, 9};
}