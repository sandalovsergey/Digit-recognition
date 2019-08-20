package id.sandalov.neural.network;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public abstract class Sample implements Iterable<Double> {
    protected int gridH;
    protected int gridW;
    protected double[][] grid;

    public void setGridH(int gridH) {
        this.gridH = gridH;
    }

    public int getGridH() {
        return gridH;
    }

    public void setGridW(int gridW) {
        this.gridW = gridW;
    }

    public int getGridW() {
        return gridW;
    }

    public double[][] getGrid() {
        return grid;
    }

    public int size() {
        return gridH * gridW;
    }

    @Override
    public Iterator<Double> iterator() {
        return new Iterator<Double>() {
            private int i = 0;
            private int j = 0;

            @Override
            public boolean hasNext() {
                return i < gridH && j < gridW;
            }

            @Override
            public Double next() {
                double c = grid[i][j++];
                if (j == gridW) {
                    ++i;
                    j = 0;
                }
                return c;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}

class OldSample extends Sample {
    public OldSample(String filename) {
        gridH = 5;
        gridW = 3;
        grid = new double[gridH][gridW];
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.out.println("Cant read sample");
            System.exit(-3);
        }

        int i = 0;
        while (scanner.hasNext()) {
            if (i == gridH) {
                throw new IllegalArgumentException("Too much in sample");
            }

            String line = scanner.nextLine();
            if (line.length() != gridW) {
                throw new IllegalArgumentException("Wrong params in sample");
            }
            int j = 0;
            for (char c : line.toCharArray()) {
                grid[i][j++] = c == 'X' ? 1.0 : 0.0;
            }
            ++i;
        }
    }
}

class OldSampleMarked extends OldSample {
    private final int mark;

    public OldSampleMarked(String filename) {
        super(filename);
        mark = Integer.parseInt(filename.split("_")[0]);
        if (mark < 0 || mark > 9) {
            throw new IllegalArgumentException("Check filename");
        }
    }
}
