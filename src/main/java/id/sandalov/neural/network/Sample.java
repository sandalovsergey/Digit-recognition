package id.sandalov.neural.network;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Sample {
    private int gridH;
    private int gridW;
    private double[][] grid;

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

    public void setGrid(double[][] grid) {
        this.grid = grid;
    }

    public double[][] getGrid() {
        return grid;
    }
}

class OldSample extends Sample {
    public OldSample(String filename) {
        setGridH(5);
        setGridW(3);
        setGrid(new double[5][3]);

    }
}
