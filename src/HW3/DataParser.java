package HW3;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.function.Function;

public class DataParser {

    private Object[][] data;
    private RandomArray randomArray;

    public DataParser() {
        data = new Object[21][4];
        randomArray = new RandomArray();
    }

    public Object[][] generateInsertionSortData() {
        Function<int[], BigDecimal> func = new Function<int[], BigDecimal>() {
            @Override
            public BigDecimal apply(int[] ints) {
                return getAverageInsertionSortTime(ints);
            }
        };

        Function<Integer, BigDecimal> bigO = new Function<Integer, BigDecimal>() {
            @Override
            public BigDecimal apply(Integer integer) {
                return newBigDecimal(integer).pow(2);
            }
        };
        return generateData(bigO, func);
    }

    public Object[][] generateMergeSortData() {
        Function<int[], BigDecimal> func = new Function<int[], BigDecimal>() {
            @Override
            public BigDecimal apply(int[] ints) {
                return getAverageMergeSortTime(ints);
            }
        };
        Function<Integer, BigDecimal> bigO = new Function<Integer, BigDecimal>() {
            @Override
            public BigDecimal apply(Integer integer) {
                BigDecimal n = newBigDecimal(integer);
                BigDecimal logn = newBigDecimal(Math.log(integer) / Math.log(2));
                return n.multiply(logn);
            }
        };
        return generateData(bigO, func);
    }

    public Object[][] generateHeapSortData() {
        Function<int[], BigDecimal> func = new Function<int[], BigDecimal>() {
            @Override
            public BigDecimal apply(int[] ints) {
                return getAverageHeapSortTime(ints);
            }
        };
        Function<Integer, BigDecimal> bigO = new Function<Integer, BigDecimal>() {
            @Override
            public BigDecimal apply(Integer integer) {
                BigDecimal n = newBigDecimal(integer);
                BigDecimal logn = newBigDecimal(Math.log(integer) / Math.log(2));
                return n.multiply(logn);
            }
        };
        return generateData(bigO, func);
    }

    private Object[][] generateData(Function<Integer, BigDecimal> bigO, Function<int[], BigDecimal> averageFunction) {
        data = new Object[21][4];
        int j;
        BigDecimal simulated, theoretical, hiddenConstant;
        System.out.println("Calculating...");
        double[] constants = new double[20];
        DecimalFormat formatter = new DecimalFormat("0.0#####E00");
        int[] currentArray;
        for (int i=1000; i <= 20000; i += 1000) {
            j = (i / 1000) - 1;
            currentArray = randomArray.getNewArray(i);
            simulated = averageFunction.apply(currentArray);
            theoretical = (bigO.apply(i)).setScale(10, BigDecimal.ROUND_HALF_UP);
            hiddenConstant = simulated.divide(theoretical, BigDecimal.ROUND_HALF_UP).setScale(10, BigDecimal.ROUND_HALF_UP);
            // constants[j] = hiddenConstant;
            data[j][0] = i;
            data[j][1] = formatter.format(theoretical.doubleValue());
            data[j][2] = formatter.format(simulated.doubleValue());
            data[j][3] = formatter.format(hiddenConstant.doubleValue());
            constants[j] = hiddenConstant.doubleValue();
        }
        data[20][0] = data[20][1] = "";
        data[20][2] = "Max Constant:";
        data[20][3] = formatter.format(getMaxConstant(constants));
        return data;
    }

    private BigDecimal newBigDecimal(long i) {
        return new BigDecimal(i).setScale(10, BigDecimal.ROUND_HALF_UP);
    }
    private BigDecimal newBigDecimal(double i) {return new BigDecimal(i).setScale(10, BigDecimal.ROUND_HALF_UP);}

    private BigDecimal getTimeForSort(Runnable function) {
        BigDecimal start, end;
        start = newBigDecimal(System.currentTimeMillis());
        function.run();
        end = newBigDecimal(System.currentTimeMillis());
        return newBigDecimal(end.subtract(start).doubleValue());
    }

    private BigDecimal getTimeForInsertionSort(int[] array) {
        return getTimeForSort(() -> InsertionSort.sort(array));
    }

    private BigDecimal getTimeForMergeSort(int[] array) {
       return getTimeForSort(()->MergeSort.sort(array));
    }

    private BigDecimal getTimeForHeapSort(int[] array) {
        return getTimeForSort(() -> HeapSort.sort(array));
    }


    private BigDecimal getAverageTime(int[] array, Function<int[], BigDecimal> timeFunction) {
        BigDecimal heapSum = newBigDecimal(0);
        int[] copy = array.clone();
        for (int i=0; i<10; i++) {
            heapSum = heapSum.add(timeFunction.apply(array));
            array = copy.clone();
        }
        return heapSum.divide(newBigDecimal(10), BigDecimal.ROUND_HALF_UP).setScale(10, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal getAverageHeapSortTime(int[] array) {
        Function<int[], BigDecimal> heapTimeFunc = new Function<int[], BigDecimal>() {
            @Override
            public BigDecimal apply(int[] ints) {
                return getTimeForHeapSort(ints);
            }
        };
        return getAverageTime(array, heapTimeFunc);
    }

    private BigDecimal getAverageInsertionSortTime(int[] array) {
        Function<int[], BigDecimal> insertionTimeFunc = new Function<int[], BigDecimal>() {
            @Override
            public BigDecimal apply(int[] ints) {
                return getTimeForInsertionSort(ints);
            }
        };
        return getAverageTime(array, insertionTimeFunc);
    }

    private BigDecimal getAverageMergeSortTime(int[] array) {
        Function<int[], BigDecimal> mergeTimeFunc = new Function<int[], BigDecimal>() {
            @Override
            public BigDecimal apply(int[] ints) {
                return getTimeForMergeSort(ints);
            }
        };
        return getAverageTime(array, mergeTimeFunc);
    }

    private double getMaxConstant(double[] constants) {
        double max = constants[0];
        for(int i=1; i<constants.length; i++) {
            if (constants[i] > max) {
                max = constants[i];
            }
        }
        return max;
    }
}
