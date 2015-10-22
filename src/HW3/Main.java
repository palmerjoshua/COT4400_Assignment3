package HW3;


import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        DataParser parser = new DataParser();
        Object[][] insertionSortData = parser.generateInsertionSortData(),
                        heapSortData = parser.generateHeapSortData(),
                       mergeSortData = parser.generateMergeSortData();

        SorterTableModel insertionSortModel = new SorterTableModel(),
                              heapSortModel = new SorterTableModel(),
                             mergeSortModel = new SorterTableModel();

        insertionSortModel.addData(insertionSortData);
        heapSortModel.addData(heapSortData);
        mergeSortModel.addData(mergeSortData);

        JTable insertionSortTable = new JTable(insertionSortModel),
               heapSortTable = new JTable(heapSortModel),
               mergeSortTable = new JTable(mergeSortModel);

        JScrollPane insertionSortPane = new JScrollPane(insertionSortTable),
                    heapSortPane = new JScrollPane(heapSortTable),
                    mergeSortPane = new JScrollPane(mergeSortTable);

        JFrame insertionSortFrame = new JFrame(),
               heapSortFrame = new JFrame(),
               mergeSortFrame = new JFrame();

        insertionSortFrame.setLayout(new FlowLayout());
        insertionSortFrame.setTitle("Insertion Sort");
        insertionSortFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        insertionSortFrame.add(insertionSortPane);
        insertionSortFrame.pack();

        heapSortFrame.setLayout(new FlowLayout());
        heapSortFrame.setTitle("Heap Sort");
        heapSortFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        heapSortFrame.add(heapSortPane);
        heapSortFrame.pack();

        mergeSortFrame.setLayout(new FlowLayout());
        mergeSortFrame.setTitle("Merge Sort");
        mergeSortFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mergeSortFrame.add(mergeSortPane);
        mergeSortFrame.pack();

        insertionSortFrame.setVisible(true);
        heapSortFrame.setVisible(true);
        mergeSortFrame.setVisible(true);
    }
}
