package lastpencil;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        GameState gameState = new GameState();

        GamePencils gamePencils;
        gamePencils = new GamePencils();

        gamePencils.setPencils(gameState.pencilAmount(scanner));

        PlayerNames playerNames;
        playerNames = new PlayerNames();

        String john = playerNames.getJohn();
        String jack = playerNames.getJack();




        String firstPlayer = "";
        String secondPlayer = "";
        int firstPlayerTurn = 0;
        int secondPlayerTurn = 0;

        gameState.gameLoop(scanner, john, jack, gamePencils, firstPlayer, secondPlayer,
                firstPlayerTurn, secondPlayerTurn);

        scanner.close();
    }
}

class GameState {

    public int pencilAmount(Scanner scanner) {

        System.out.println("How many pencils would you like to use:");
        return scanner.nextInt();
    }

    public void gameLoop(Scanner scanner, String john, String jack, GamePencils gamePencils,
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

            if (gamePencils.getPencils() != 0) {
                gamePencils.printPencils(gamePencils.getPencils());

                System.out.println(firstPlayer + "'s turn:");
                firstPlayerTurn = scanner.nextInt();
                gamePencils.setPencils(gamePencils.getPencils() - firstPlayerTurn);
            } else {
                break;
            }

            if (gamePencils.getPencils() != 0) {
                gamePencils.printPencils(gamePencils.getPencils());
                System.out.println(secondPlayer + "'s turn:");
                secondPlayerTurn = scanner.nextInt();
                gamePencils.setPencils(gamePencils.getPencils() - firstPlayerTurn);
            } else {
                break;
            }
        }
    }
}

class GamePencils {
    private static int pencils = 0;

    public int getPencils() {
        return pencils;
    }

    public void setPencils(int pencils) {
        GamePencils.pencils = pencils;
    }

    public void printPencils(int pencils) {
        for (int i = 1; i <= pencils; i++) {
            System.out.print("|");
        }
        System.out.println();
    }
}

class PlayerNames {
    private static final String JOHN = "John";
    private static final String JACK = "Jack";

    public String getJohn() {
        return JOHN;
    }

    public String getJack() {
        return JACK;
    }
}