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
        int firstPlayerTurn = 0;
        int secondPlayerTurn = 0;


        int gamePencils = pencilAmount(scanner, pencils);
        gameLoop(scanner, john, jack, gamePencils, firstPlayer, secondPlayer, firstPlayerTurn, secondPlayerTurn);
        scanner.close();
    }

    private static int pencilAmount(Scanner scanner, int pencils) {
        System.out.println("How many pencils would you like to use:");
        pencils = scanner.nextInt();
        return pencils;
    }

    private static void gameLoop(Scanner scanner, String john, String jack, int gamePencils,
                                 String firstPlayer, String secondPlayer, int firstPlayerTurn, int secondPlayerTurn) {
        System.out.println("Who will be the first (John, Jack):");
        String input = scanner.next();

        if (input.equals(john)) {
            firstPlayer = "John";
            secondPlayer = "Jack";
        } else {
            firstPlayer = "Jack";
            secondPlayer = "John";
        }

        while (true) {

                if (gamePencils != 0) {
                    printPencils(gamePencils);

                    System.out.println(firstPlayer + "'s turn:");
                    firstPlayerTurn = scanner.nextInt();
                    gamePencils = gamePencils - firstPlayerTurn;
                } else {
                    break;
                }

                if (gamePencils != 0) {
                    printPencils(gamePencils);
                    System.out.println(secondPlayer + "'s turn:");
                    secondPlayerTurn = scanner.nextInt();
                    gamePencils = gamePencils - secondPlayerTurn;
                } else {
                    break;
                }
        }
    }

    private static void printPencils(int pencils) {
        for (int i = 1; i <= pencils; i++) {
            System.out.print("|");
        }
        System.out.println();
    }
}

class GameState {
    // TODO: Code goes here.
}

class GamePencils {
    // TODO: Code goes here.
}

class Players {
    // TODO: Code goes here.
}