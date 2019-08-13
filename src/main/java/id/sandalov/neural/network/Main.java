package id.sandalov.neural.network;

import java.util.Scanner;

public class Main {
    public static void run() {
        //NeuralNetwork network = new NeuralNetwork();


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
                    break;
                case 2:
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
