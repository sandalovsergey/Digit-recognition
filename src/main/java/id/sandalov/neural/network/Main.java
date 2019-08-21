package id.sandalov.neural.network;

import java.util.Scanner;

public class Main {
    public static void run() {
        NeuralNetwork network;
        int mark;
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
                    network = new NeuralNetwork(15, 10, 0, 0.5, 100000, "src/main/resources");
                    System.out.println("Learning ...");
                    network.learning();
                    System.out.println("Done!");
                    network.save("src/main/trained.network/net.ser");
                    break;
                case 2:
                    network = NeuralNetwork.load("src/main/trained.network/net.ser");
                    System.out.println("Input grid:");
                    mark = network.guessingInput();
                    System.out.println("This number is " + mark);
                    break;

                case 3:
                    network = NeuralNetwork.load("src/main/trained.network/net.ser");
                    mark = network.guessingTestSample("src/main/tests/test.sample");
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
