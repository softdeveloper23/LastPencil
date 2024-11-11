package lastpencil;

import java.util.Random;
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
                    System.out.println("The number of pencils should be positive");
                    continue;
                }
                return amount;
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
            }
        }
    }

    public void gameLoop(Scanner scanner, GamePencils gamePencils) {
        PlayerSelection playerSelection = new PlayerSelection();
        String firstPlayer = playerSelection.getFirstPlayer(scanner);
        playGame(scanner, gamePencils, firstPlayer);
    }

    public void playGame(Scanner scanner, GamePencils gamePencils, String firstPlayer) {
        String currentPlayer = firstPlayer;
        String secondPlayer = determineSecondPlayer(firstPlayer);

        while (gamePencils.getPencils() > 0) {
            gamePencils.printPencils(gamePencils.getPencils());
            System.out.println(currentPlayer + "'s turn:");
            int move;

            if (currentPlayer.equals("Jack")) {
                move = getBotMove(gamePencils.getPencils());
                System.out.println(move);
            } else {
                move = getValidPencilCount(scanner, gamePencils, currentPlayer);
            }

            gamePencils.setPencils(gamePencils.getPencils() - move);

            if (gamePencils.getPencils() == 0) {
                String winner = currentPlayer.equals(firstPlayer) ? secondPlayer : firstPlayer;
                System.out.println(winner + " won!");
                break;
            }

            // Switch players
            currentPlayer = currentPlayer.equals(firstPlayer) ? secondPlayer : firstPlayer;
        }
    }

    public String determineSecondPlayer(String firstPlayer) {
        if (firstPlayer.equals("John")) {
            return "Jack";
        } else {
            return "John";
        }
    }

    private int getValidPencilCount(Scanner scanner, GamePencils gamePencils, String playerName) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                int count = Integer.parseInt(input);
                if (count <= 0 || count > 3) {
                    System.out.println("Possible values: '1', '2' or '3'");
                    continue;
                }
                if (count > gamePencils.getPencils()) {
                    System.out.println("Too many pencils were taken");
                    continue;
                }
                return count;
            } catch (NumberFormatException e) {
                System.out.println("Possible values: '1', '2' or '3'");
            }
        }
    }

    private int getBotMove(int pencilsRemaining) {
        int move;
        if (pencilsRemaining % 4 == 0) {
            move = 3;
        } else if (pencilsRemaining % 4 == 3) {
            move = 2;
        } else if (pencilsRemaining % 4 == 2) {
            move = 1;
        } else {
            // Losing position, bot can take any valid move
            // For simplicity, we'll take 1
            move = 1;
        }
        // Ensure the move doesn't exceed the pencils remaining
        if (move > pencilsRemaining) {
            move = pencilsRemaining;
        }
        return move;
    }
}

class GamePencils {
    private int pencils = 0;

    public int getPencils() {
        return pencils;
    }

    public void setPencils(int pencils) {
        this.pencils = pencils;
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
            System.out.println("Who will be the first (John, Jack):");
            String input = scanner.nextLine().trim();

            if (input.equals(playerNames.getJohn())) {
                firstPlayer = playerNames.getJohn();
                break;
            } else if (input.equals(playerNames.getJack())) {
                firstPlayer = playerNames.getJack();
                break;
            } else {
                System.out.println("Choose between 'John' and 'Jack'");
            }
        }
        return firstPlayer;
    }
}
