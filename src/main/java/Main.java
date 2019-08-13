import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int[][] weights = {
                {-1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1},
                {1, 1, 1, -1, -1, 1, 1, 1, 1, 1, -1, -1, 1, 1, 1},
                {1, 1, 1, -1, -1, 1, 1, 1, 1, -1, -1, 1, 1, 1, 1},
                {1, -1, 1, 1, -1, 1, 1, 1, 1, -1, -1, 1, -1, -1, 1},
                {1, 1, 1, 1, -1, -1, 1, 1, 1, -1, -1, 1, 1, 1, 1},
                {1, 1, 1, 1, -1, -1, 1, 1, 1, 1, -1, 1, 1, 1, 1},
                {1, 1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1, -1, -1, 1},
                {1, 1, 1, 1, -1, 1, 1, 1, 1, 1, -1, 1, 1, 1, 1},
                {1, 1, 1, 1, -1, 1, 1, 1, 1, -1, -1, 1, 1, 1, 1},
                {1, 1, 1, 1, -1, 1, 1, -1, 1, 1, -1, 1, 1, 1, 1}
        };

        int[] bias = {6, 0, 0, 2, 0, -1, 4, -2, -1, -1};

        System.out.println("1. Learn the network");
        System.out.println("2. Guess a number");
        System.out.print("Your choice: ");
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("E:\\JetBrainsAcademy\\" +
                    "Projects\\Digit-recognition\\src\\main\\resources\\in.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int choice = scanner.nextInt();

        int[] signals = new int[15];
        int i = 0;
        for (int j = 0; j < 5; ++j) {
            String line = scanner.nextLine();
            for (int k = 0; k < line.length(); ++k) {
                signals[i++] = line.charAt(k) == 'X' ? 1 : 0;
            }
        }
        int[] outputs = new int[10];
        for (int j = 0; j < outputs.length; ++j) {
            outputs[j] = 0;
            for (int k = 0; k < signals.length; ++k) {
                outputs[j] += weights[j][k] * signals[k];
            }
            outputs[j] += bias[j];
        }

        int max = outputs[0];
        int maxIndex = 0;
        for (int j = 1; j < outputs.length; ++j) {
            if (outputs[j] > max) {
                maxIndex = j;
                max = outputs[j];
            }
        }
        System.out.println("This number is " + (maxIndex == 9 ? 0 : maxIndex + 1));
    }
}
