package id.sandalov.neural.network;

import java.util.Scanner;

public class Main {
    public static void run() {
        NeuralNetwork network = new NeuralNetwork(15, 10, 0, 0.5, 100000, "/home/user/IdeaProjects/" +
                "Digit-recognition/src/main/resources");


        Scanner scanner = new Scanner(System.in);
        boolean isRepeat = true;

        while (isRepeat) {
            System.out.println("1. Learn a network");
            System.out.println("2. Guess a number");
            System.out.println("0. Exit");

            System.out.println("Your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Learning ...");
                    network.learning();
                    System.out.println("Done!");
                    break;
                case 2:
                    int mark = network.guessingTestSample("/home/user/IdeaProjects/Digit-recognition/src/main/tests/test1.sample");
                    System.out.println("This number is " + mark);
                    break;
                case 0:
                    isRepeat = false;
                    break;
                default:
                    System.out.println("Wrong input!");
            }

            System.out.println("\n\n---------------------------\n\n");
        }
    }

    public static void main(String[] args) {
        Main.run();
    }
}
