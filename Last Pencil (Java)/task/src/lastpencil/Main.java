package lastpencil;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pencils = 0;
        String john = "John";
        String jack = "Jack";


        pencilAmount(scanner, pencils);

        System.out.println("Who will be the first (John, Jack):");
        String firstName = scanner.next();
        for (int i = 1; i <= pencils; i++) {
            System.out.print("|");
        }
        System.out.println();
        System.out.println(firstName + " is going first!");

    }

    private static int pencilAmount(Scanner scanner, int pencils) {
        System.out.println("How many pencils would you like to use:");
        pencils = scanner.nextInt();
        return pencils;
    }
}
