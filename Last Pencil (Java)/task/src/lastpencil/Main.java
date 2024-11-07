package lastpencil;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pencils = 0;
        String john = "John";
        String jack = "Jack";
        String firstPlayer = "";
        String secondPlayer = "";


        int gamePencils = pencilAmount(scanner, pencils);


    }

    private static int pencilAmount(Scanner scanner, int pencils) {
        System.out.println("How many pencils would you like to use:");
        pencils = scanner.nextInt();
        return pencils;
    }

    private static void gameLoop(Scanner scanner, String john, String jack, int gamePencils,
                                 String firstPlayer, String secondPlayer) {
        System.out.println("Who will be the first (John, Jack):");
        String input = scanner.next();

        if (input.equals(john)) {
            firstPlayer = "John";
            secondPlayer = "Jack";
        } else {
            firstPlayer = "Jack";
            secondPlayer = "John";
        }

        while (gamePencils > 0) {
            for (int i = 1; i <= pencils; i++) {
                System.out.print("|");
            }
            System.out.println();
            System.out.println(firstName + " is going first!");
        }
    }

}
