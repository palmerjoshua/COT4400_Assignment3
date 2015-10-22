package HW3;


import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Main {


    private static BigDecimal newBigDecimal(long i) {
        return new BigDecimal(i).setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    private static BigDecimal getTimeForInsertionSort(int[] array) {
        BigDecimal start, end;
        start = new BigDecimal(System.currentTimeMillis());
        InsertionSort.sort(array);
        end = new BigDecimal(System.currentTimeMillis());
        return end.subtract(start).setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    private static BigDecimal getTimeForMergeSort(int[] array) {
        BigDecimal start, end;
        start = new BigDecimal(System.currentTimeMillis());
        MergeSort.sort(array);
        end = new BigDecimal(System.currentTimeMillis());
        return end.subtract(start);
    }

    private static BigDecimal getTimeForHeapSort(int[] array) {
        BigDecimal start, end;
        start = new BigDecimal(System.currentTimeMillis());
        HeapSort.sort(array);
        end = new BigDecimal(System.currentTimeMillis());
        return end.subtract(start).setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    private static BigDecimal getAverageHeapSortTime(int[] array) {
        BigDecimal heapSum = new BigDecimal(0);
        for (int i=0; i<10; i++) {
            heapSum = heapSum.add(getTimeForHeapSort(array));
        }
        return heapSum.divide(new BigDecimal(10), BigDecimal.ROUND_HALF_UP);
    }

    private static BigDecimal getAverageMergeSortTime(int[] array) {
        BigDecimal mergeSum = new BigDecimal(0);
        for (int i=0; i<10; i++) {
            mergeSum = mergeSum.add(getTimeForMergeSort(array));
        }
        return mergeSum.divide(new BigDecimal(10), BigDecimal.ROUND_HALF_UP).setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    private static BigDecimal getAverageInsertionSortTime(int[] array) {
        BigDecimal insertionSum = new BigDecimal(0);
        for (int i=0; i<10; i++) {
            insertionSum = insertionSum.add(getTimeForInsertionSort(array));
        }
        return insertionSum.divide(new BigDecimal(10), BigDecimal.ROUND_HALF_UP).setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    public static void main(String[] args) {
        RandomArray randomArray = new RandomArray();
        SorterTableModel model = new SorterTableModel();
        DecimalFormat formatter = new DecimalFormat("0.000E00");
        int[] currentArray;
        Object[][] data = new Object[20][4];
        int j;
        BigDecimal simulated, theoretical, hiddenConstant;
        System.out.println("Calculating...");
        double[] constants = new double[20];
        for (int i=1000; i <= 20000; i += 1000) {
            j = (i / 1000) - 1;
            currentArray = randomArray.getNewArray(i);
            simulated = getAverageInsertionSortTime(currentArray).setScale(3, BigDecimal.ROUND_HALF_UP);
            theoretical = (new BigDecimal(i).pow(2)).setScale(3, BigDecimal.ROUND_HALF_UP);
            hiddenConstant = simulated.divide(theoretical, BigDecimal.ROUND_HALF_UP).setScale(3, BigDecimal.ROUND_HALF_UP);
            // constants[j] = hiddenConstant;
            data[j][0] = i;
            data[j][1] = formatter.format(theoretical.doubleValue());
            data[j][2] = formatter.format(simulated.doubleValue());
            data[j][3] = formatter.format(hiddenConstant.doubleValue());
        }
        model.addData(data);
        JTable table = new JTable(model);

        JScrollPane pane = new JScrollPane(table);
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.add(pane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
