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
        while (true) {
            System.out.println("How many pencils would you like to use:");
            String input = scanner.nextLine().trim();
            try {
                int amount = Integer.parseInt(input);
                if (amount <= 0) {
                    System.out.println("The number of pencils should be positive.");
                    continue;
                }
                return amount;
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric.");
            }
        }
    }

    public void gameLoop(Scanner scanner, GamePencils gamePencils) {
        PlayerSelection playerSelection = new PlayerSelection();
        String firstPlayer = playerSelection.getFirstPlayer(scanner);
        String secondPlayer = determineSecondPlayer(firstPlayer);
        playGame(scanner, gamePencils, firstPlayer, secondPlayer);

    }

    public String determineSecondPlayer(String firstPlayer) {
        if (firstPlayer.equals("John")) {
            return "Jack";
        } else {
            return "John";
        }
    }

    public void playGame(Scanner scanner, GamePencils gamePencils, String firstPlayer, String secondPlayer) {
        while (gamePencils.getPencils() > 0) {
            // First player's turn
            gamePencils.printPencils(gamePencils.getPencils());
            System.out.println(firstPlayer + "'s turn!");
            int firstPlayerTurn = getValidPencilCount(scanner, gamePencils);
            gamePencils.setPencils(gamePencils.getPencils() - firstPlayerTurn);
            if (gamePencils.getPencils() == 0) {
                System.out.println(secondPlayer + " won!");
                break;
            }

            // Second player's turn
            gamePencils.printPencils(gamePencils.getPencils());
            System.out.println(secondPlayer + "'s turn!");
            int secondPlayerTurn = getValidPencilCount(scanner, gamePencils);
            gamePencils.setPencils(gamePencils.getPencils() - secondPlayerTurn);
            if (gamePencils.getPencils() == 0) {
                System.out.println(firstPlayer + " won!");
                break;
            }
        }
    }

    private int getValidPencilCount(Scanner scanner, GamePencils gamePencils, String playerName) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                int count = Integer.parseInt(input);
                if (count <= 0 || count > 3) {
                    System.out.println("Possible values: '1', '2' or '3'.");
                    gamePencils.printPencils(gamePencils.getPencils());
                    System.out.println(playerName + "'s turn!");
                    continue;
                }
                if (count > gamePencils.getPencils()) {
                    System.out.println("Too many pencils were taken.");
                    gamePencils.printPencils(gamePencils.getPencils());
                    System.out.println(playerName + "'s turn!");
                    continue;
                }
                return count;
            } catch (NumberFormatException e) {
                System.out.println("Possible values: '1', '2' or '3'.");
                gamePencils.printPencils(gamePencils.getPencils());
                System.out.println(playerName + "'s turn!");
            }
        }
    }

    private String determineWinner(int count, String firstPlayer, String secondPlayer) {
        if (count % 2 == 0) {
            return firstPlayer;
        } else {
            return secondPlayer;
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

class PlayerSelection {
    String firstPlayer;

    PlayerNames playerNames = new PlayerNames();

    public String getFirstPlayer(Scanner scanner) {
        while (true) {
            System.out.println("Who will be the first (John, Jack): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase(playerNames.getJohn())) {
                firstPlayer = playerNames.getJohn();
                break;
            } else if (input.equalsIgnoreCase(playerNames.getJack())) {
                firstPlayer = playerNames.getJack();
                break;
            } else {
                System.out.println("Please choose between "
                        + playerNames.getJohn() + " and " + playerNames.getJack() + ".");
            }
        }
        return firstPlayer;
    }
}