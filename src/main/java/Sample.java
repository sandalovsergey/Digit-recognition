import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sample {
    public static final int gridH = 5;
    public static final int gridW = 3;
    private int[][] grid = new int[gridH][gridW];

    public Sample(String filename) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < gridH; ++i) {
            String temp = scanner.nextLine();
            for (int j = 0; j < gridW; ++j) {
                grid[i][j] = temp.charAt(j) == 'X' ? 1 : 0;
            }
        }
    }
}
