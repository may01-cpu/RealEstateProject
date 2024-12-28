package utils;
import java.io.IOException;
import java.util.Scanner;

public class ConsoleUtils {

    // Clear the console screen
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error clearing the console.");
        }
    }

    // Print a title with borders
    public static void printTitle(String title) {
        clearConsole();
        String border = "=".repeat(title.length() + 4);
        System.out.println(border);
        System.out.println(" " + title);
        System.out.println(border);
    }

    // Print a custom divider
    public static void printCustomDivider(String symbol, int length) {
        System.out.println(symbol.repeat(length));
    }

    // Show a loading effect
    public static void showLoading(String message, int seconds) {
        System.out.print(message);
        for (int i = 0; i < seconds; i++) {
            try {
                Thread.sleep(500); // Pause for 500ms
                System.out.print(".");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    // Pause for user input
    public static void pause(String message) {
        System.out.print(message);
        new Scanner(System.in).nextLine();
    }

}
