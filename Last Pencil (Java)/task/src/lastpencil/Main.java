package lastpencil;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        GameState gameState = new GameState();

        GamePencils gamePencils = new GamePencils();

        gamePencils.setPencils(gameState.pencilAmount(scanner));

        gameState.gameLoop(scanner, gamePencils);

        scanner.close();
    }
}

class GameState {

    public int pencilAmount(Scanner scanner) {

        System.out.println("How many pencils would you like to use:");
        return scanner.nextInt();
    }

    public void gameLoop(Scanner scanner, GamePencils gamePencils) {
        PlayerSelection playerSelection = new PlayerSelection();
        String firstPlayer = playerSelection.getFirstPlayer(scanner);
        String secondPlayer = determineSecondPlayer(firstPlayer);

        while (true) {

            if (gamePencils.getPencils() > 0) {
                gamePencils.printPencils(gamePencils.getPencils());

                System.out.println(firstPlayer + "'s turn!");
                firstPlayerTurn = scanner.nextInt();
                gamePencils.setPencils(gamePencils.getPencils() - firstPlayerTurn);
                System.out.println(gamePencils.getPencils());
            } else {
                break;
            }

            if (gamePencils.getPencils() > 0) {
                gamePencils.printPencils(gamePencils.getPencils());
                System.out.println(secondPlayer + "'s turn:");
                secondPlayerTurn = scanner.nextInt();
                gamePencils.setPencils(gamePencils.getPencils() - secondPlayerTurn);
                System.out.println(gamePencils.getPencils());
            } else {
                break;
            }
        }
    }

    public String determineSecondPlayer(String firstPlayer) {
        String secondPlayer;

        if (firstPlayer.equals("John")) {
            secondPlayer = "Jack";
        } else {
            secondPlayer = "John";
        }
        return secondPlayer;
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

class PlayerSelection {
    String firstPlayer;

    PlayerNames playerNames = new PlayerNames();

    public String getFirstPlayer(Scanner scanner) {
        while (true) {
            System.out.print("Who will be the first (John, Jack): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase(playerNames.getJohn())) {
                firstPlayer = playerNames.getJohn();
                break;
            } else if (input.equalsIgnoreCase(playerNames.getJack())) {
                firstPlayer = playerNames.getJack();
                break;
            } else {
                System.out.println("Invalid input. Please choose between "
                        + playerNames.getJohn() + " and " + playerNames.getJack() + ".");
            }
        }
        return firstPlayer;
    }
}