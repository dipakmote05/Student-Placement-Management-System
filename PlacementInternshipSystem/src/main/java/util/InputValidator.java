package util;

import java.util.Scanner;

public class InputValidator {

    private static final Scanner scanner = new Scanner(System.in);

    // Read integer
    public static int readInt(String message) {

        while (true) {

            System.out.print(message);

            try {
                return Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // Read double
    public static double readDouble(String message) {

        while (true) {

            System.out.print(message);

            try {
                return Double.parseDouble(scanner.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid decimal number.");
            }
        }
    }

    // Read String
    public static String readString(String message) {

        System.out.print(message);
        return scanner.nextLine();
    }

    // Read non-empty String
    public static String readNonEmptyString(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine();

            if (!input.trim().isEmpty()) {
                return input;
            }

            System.out.println("Input cannot be empty.");
        }
    }
}