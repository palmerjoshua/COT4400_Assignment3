package HW3;


import com.sun.deploy.util.ArrayUtil;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;

public class Main {


    private static BigDecimal newBigDecimal(long i) {
        return new BigDecimal(i).setScale(6, BigDecimal.ROUND_HALF_UP);
    }
    private static BigDecimal newBigDecimal(double i) {return new BigDecimal(i).setScale(6, BigDecimal.ROUND_HALF_UP);}

    private static BigDecimal getTimeForInsertionSort(int[] array) {
        BigDecimal start, end;
        start = new BigDecimal(System.currentTimeMillis());
        InsertionSort.sort(array);
        end = new BigDecimal(System.currentTimeMillis());
        return end.subtract(start);
    }

    private static BigDecimal getTimeForMergeSort(int[] array) {
        BigDecimal start, end;
        start = new BigDecimal(System.currentTimeMillis());
        MergeSort.sort(array);
        end = new BigDecimal(System.currentTimeMillis());
        return newBigDecimal(end.subtract(start).doubleValue());
    }

    private static BigDecimal getTimeForHeapSort(int[] array) {
        BigDecimal start, end;
        start = newBigDecimal(System.currentTimeMillis());
        HeapSort.sort(array);
        end = newBigDecimal(System.currentTimeMillis());
        return newBigDecimal(end.subtract(start).doubleValue());
    }

    private static BigDecimal getAverageHeapSortTime(int[] array) {
        BigDecimal heapSum = newBigDecimal(0);
        for (int i=0; i<10; i++) {
            heapSum = heapSum.add(getTimeForHeapSort(array));
        }
        return heapSum.divide(newBigDecimal(10), BigDecimal.ROUND_HALF_UP).setScale(10);
    }

    private static BigDecimal getAverageMergeSortTime(int[] array) {
        BigDecimal mergeSum = new BigDecimal(0);
        for (int i=0; i<10; i++) {
            mergeSum = mergeSum.add(getTimeForMergeSort(array));
        }
        return mergeSum.divide(newBigDecimal(10), BigDecimal.ROUND_HALF_UP).setScale(10);
    }

    private static BigDecimal getAverageInsertionSortTime(int[] array) {
        BigDecimal insertionSum = newBigDecimal(0.0);
        for (int i=0; i<10; i++) {
            insertionSum = insertionSum.add(getTimeForInsertionSort(array)).setScale(10);
        }
        return insertionSum.divide(new BigDecimal(10), BigDecimal.ROUND_HALF_UP).setScale(10);
    }

    private static double getMaxConstant(double[] constants) {
        double max = constants[0];
        for(int i=1; i<constants.length; i++) {
            if (constants[i] > max) {
                max = constants[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        RandomArray randomArray = new RandomArray();
        SorterTableModel model = new SorterTableModel();
        DecimalFormat formatter = new DecimalFormat("0.0#####E00");
        int[] currentArray;
        Object[][] data = new Object[21][4];
        int j;
        BigDecimal simulated, theoretical, hiddenConstant;
        System.out.println("Calculating...");
        double[] constants = new double[20];
        for (int i=1000; i <= 20000; i += 1000) {
            j = (i / 1000) - 1;
            currentArray = randomArray.getNewArray(i);
            simulated = getAverageInsertionSortTime(currentArray).setScale(10);
            theoretical = (newBigDecimal(i).pow(2)).setScale(10);
            hiddenConstant = simulated.divide(theoretical, BigDecimal.ROUND_HALF_UP).setScale(10);
            // constants[j] = hiddenConstant;
            data[j][0] = i;
            data[j][1] = formatter.format(theoretical.doubleValue());
            data[j][2] = formatter.format(simulated.doubleValue());
            data[j][3] = formatter.format(hiddenConstant.doubleValue());
            constants[j] = hiddenConstant.doubleValue();
        }
        data[20][0] = data[20][1] = "";
        data[20][2] = "Max:";
        data[20][3] = formatter.format(getMaxConstant(constants));
        model.addData(data);
        JTable table = new JTable(model);

        JScrollPane pane = new JScrollPane(table);
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setTitle("Insertion Sort");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(pane);
        frame.pack();
        frame.setVisible(true);
    }
}
