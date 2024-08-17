import java.io.Console;

/**
 * A class used to check, determine and declare a tie or the winner of the game.
 * 
 * This class is run after every move, so the tie or the winner is declared at the right moment and no unnecessary moves are played.
 * 
 * @author Makinen Joni
 */
public class Winner {
    static Console input = System.console();
    static int gameAreaSize;
    static int inARowFTW;
    static boolean playerWins;
    static boolean computerWins;
    static boolean itIsATie;
    static int playerXPos;
    static int playerYPos;
    static int[] playerPosition;
    static int computerXPos;
    static int computerYPos;
    static int[] computerPosition;
    static String[][] gameBoard;
    static String winner = "";

    /**
     * A method used to check for a tie and for a winner.
     * 
     * This method is run after every move. Three to six in a row needed for the win.
     * 
     * @param gameAreaSize Is used in for-loops checking for a tie and a winner.
     * @param gameBoard Is used in for-loops checking for a tie and a winner.
     * @param inARowFTW This is determined by the user in class ProjectWork. It has the information on how many X's or O's in a row are needed for a win.
     * @return Returns the string winner, which contains information about a tie or a winner or information that tells to continue the game.
     */
    public static String checkWinner(int gameAreaSize, String[][] gameBoard, int inARowFTW) {
        // The string winner is always defined as T in the beginning of the method.
        winner = "T";
        // Checks for a tie. The string winner is emptied if there is empty positions on the game board.
        for (int i = 0; i < gameAreaSize; i++) {
            for (int j = 0; j < gameAreaSize; j++) {
                if (gameBoard[i][j].equals("[ ]")) {
                    winner = "";
                }
            }
        }
        if (inARowFTW == 3) {
            // Three X's or O's in a row for the win, horizontal check.
            for (int i = 0; i < gameAreaSize; i++) {
                for (int j = 0; j < gameAreaSize - 2; j++) {
                    if (gameBoard[i][j].equals("[X]") && gameBoard[i][j + 1].equals("[X]") && gameBoard[i][j + 2].equals("[X]")) {
                        winner = "X";
                    }
                    if (gameBoard[i][j].equals("[O]") && gameBoard[i][j + 1].equals("[O]") && gameBoard[i][j + 2].equals("[O]")) {
                        winner = "O";
                    }
                }
            }
            // Three X's or O's in a row for the win, vertical check.
            for (int i = 0; i < gameAreaSize - 2; i++) {
                for (int j = 0; j < gameAreaSize; j++) {
                    if (gameBoard[i][j].equals("[X]") && gameBoard[i + 1][j].equals("[X]") && gameBoard[i + 2][j].equals("[X]")) {
                        winner = "X";
                    }
                    if (gameBoard[i][j].equals("[O]") && gameBoard[i + 1][j].equals("[O]") && gameBoard[i + 2][j].equals("[O]")) {
                        winner = "O";
                    }
                }
            }
            // Three X's or O's in a row for the win, diagonal check.
            for (int i = 0; i < gameAreaSize - 2; i++) {
                for (int j = 0; j < gameAreaSize - 2; j++) {
                    if (gameBoard[i][j].equals("[X]") && gameBoard[i + 1][j + 1].equals("[X]") && gameBoard[i + 2][j + 2].equals("[X]")) {
                        winner = "X";
                    }
                    if (gameBoard[i][j].equals("[O]") && gameBoard[i + 1][j + 1].equals("[O]") && gameBoard[i + 2][j + 2].equals("[O]")) {
                        winner = "O";
                    }
                }
            }
            // Three X's or O's in a row for the win, anti-diagonal check.
            for (int i = gameAreaSize - 3; i >= 0; i--) {
                for (int j = gameAreaSize - 3; j >= 0; j--) {
                    if (gameBoard[i + 2][j].equals("[X]") && gameBoard[i + 1][j + 1].equals("[X]") && gameBoard[i][j + 2].equals("[X]")) {
                        winner = "X";
                    }
                    if (gameBoard[i + 2][j].equals("[O]") && gameBoard[i + 1][j + 1].equals("[O]") && gameBoard[i][j + 2].equals("[O]")) {
                        winner = "O";
                    }
                }
            }
        }
        if (inARowFTW == 4) {
            // Four X's or O's in a row for the win, horizontal check.
            for (int i = 0; i < gameAreaSize; i++) {
                for (int j = 0; j < gameAreaSize - 3; j++) {
                    if (gameBoard[i][j].equals("[X]") && gameBoard[i][j + 1].equals("[X]") && gameBoard[i][j + 2].equals("[X]") && gameBoard[i][j + 3].equals("[X]")) {
                        winner = "X";
                    }
                    if (gameBoard[i][j].equals("[O]") && gameBoard[i][j + 1].equals("[O]") && gameBoard[i][j + 2].equals("[O]") && gameBoard[i][j + 3].equals("[O]")) {
                        winner = "O";
                    }
                }
            }
            // Four X's or O's in a row for the win, vertical check.
            for (int i = 0; i < gameAreaSize - 3; i++) {
                for (int j = 0; j < gameAreaSize; j++) {
                    if (gameBoard[i][j].equals("[X]") && gameBoard[i + 1][j].equals("[X]") && gameBoard[i + 2][j].equals("[X]") && gameBoard[i + 3][j].equals("[X]")) {
                        winner = "X";
                    }
                    if (gameBoard[i][j].equals("[O]") && gameBoard[i + 1][j].equals("[O]") && gameBoard[i + 2][j].equals("[O]") && gameBoard[i + 3][j].equals("[O]")) {
                        winner = "O";
                    }
                }
            }
            // Four X's or O's in a row for the win, diagonal check.
            for (int i = 0; i < gameAreaSize - 3; i++) {
                for (int j = 0; j < gameAreaSize - 3; j++) {
                    if (gameBoard[i][j].equals("[X]") && gameBoard[i + 1][j + 1].equals("[X]") && gameBoard[i + 2][j + 2].equals("[X]") && gameBoard[i + 3][j + 3].equals("[X]")) {
                        winner = "X";
                    }
                    if (gameBoard[i][j].equals("[O]") && gameBoard[i + 1][j + 1].equals("[O]") && gameBoard[i + 2][j + 2].equals("[O]") && gameBoard[i + 3][j + 3].equals("[O]")) {
                        winner = "O";
                    }
                }
            }
            // Four X's or O's in a row for the win, anti-diagonal check.
            for (int i = gameAreaSize - 4; i >= 0; i--) {
                for (int j = gameAreaSize - 4; j >= 0; j--) {
                    if (gameBoard[i + 3][j].equals("[X]") && gameBoard[i + 2][j + 1].equals("[X]") && gameBoard[i + 1][j + 2].equals("[X]") && gameBoard[i][j + 3].equals("[X]")) {
                        winner = "X";
                    }
                    if (gameBoard[i + 3][j].equals("[O]") && gameBoard[i + 2][j + 1].equals("[O]") && gameBoard[i + 1][j + 2].equals("[O]") && gameBoard[i][j + 3].equals("[O]")) {
                        winner = "O";
                    }
                }
            }
        }
        if (inARowFTW == 5) {
            // Five X's or O's in a row for the win, horizontal check.
            for (int i = 0; i < gameAreaSize; i++) {
                for (int j = 0; j < gameAreaSize - 4; j++) {
                    if (gameBoard[i][j].equals("[X]") && gameBoard[i][j + 1].equals("[X]") && gameBoard[i][j + 2].equals("[X]") && gameBoard[i][j + 3].equals("[X]") && gameBoard[i][j + 4].equals("[X]")) {
                        winner = "X";
                    }
                    if (gameBoard[i][j].equals("[O]") && gameBoard[i][j + 1].equals("[O]") && gameBoard[i][j + 2].equals("[O]") && gameBoard[i][j + 3].equals("[O]") && gameBoard[i][j + 4].equals("[O]")) {
                        winner = "O";
                    }
                }
            }
            // Five X's or O's in a row for the win, vertical check.
            for (int i = 0; i < gameAreaSize - 4; i++) {
                for (int j = 0; j < gameAreaSize; j++) {
                    if (gameBoard[i][j].equals("[X]") && gameBoard[i + 1][j].equals("[X]") && gameBoard[i + 2][j].equals("[X]") && gameBoard[i + 3][j].equals("[X]") && gameBoard[i + 4][j].equals("[X]")) {
                        winner = "X";
                    }
                    if (gameBoard[i][j].equals("[O]") && gameBoard[i + 1][j].equals("[O]") && gameBoard[i + 2][j].equals("[O]") && gameBoard[i + 3][j].equals("[O]") && gameBoard[i + 4][j].equals("[O]")) {
                        winner = "O";
                    }
                }
            }
            // Five X's or O's in a row for the win, diagonal check.
            for (int i = 0; i < gameAreaSize - 4; i++) {
                for (int j = 0; j < gameAreaSize - 4; j++) {
                    if (gameBoard[i][j].equals("[X]") && gameBoard[i + 1][j + 1].equals("[X]") && gameBoard[i + 2][j + 2].equals("[X]") && gameBoard[i + 3][j + 3].equals("[X]") && gameBoard[i + 4][j + 4].equals("[X]")) {
                        winner = "X";
                    }
                    if (gameBoard[i][j].equals("[O]") && gameBoard[i + 1][j + 1].equals("[O]") && gameBoard[i + 2][j + 2].equals("[O]") && gameBoard[i + 3][j + 3].equals("[O]") && gameBoard[i + 4][j + 4].equals("[O]")) {
                        winner = "O";
                    }
                }
            }
            // Five X's or O's in a row for the win, anti-diagonal check.
            for (int i = gameAreaSize - 5; i >= 0; i--) {
                for (int j = gameAreaSize - 5; j >= 0; j--) {
                    if (gameBoard[i + 4][j].equals("[X]") && gameBoard[i + 3][j + 1].equals("[X]") && gameBoard[i + 2][j + 2].equals("[X]") && gameBoard[i + 1][j + 3].equals("[X]") && gameBoard[i][j + 4].equals("[X]")) {
                        winner = "X";
                    }
                    if (gameBoard[i + 4][j].equals("[O]") && gameBoard[i + 3][j + 1].equals("[O]") && gameBoard[i + 2][j + 2].equals("[O]") && gameBoard[i + 1][j + 3].equals("[O]") && gameBoard[i][j + 4].equals("[O]")) {
                        winner = "O";
                    }
                }
            }
        }
        if (inARowFTW == 6) {
            // Six X's or O's in a row for the win, horizontal check.
            for (int i = 0; i < gameAreaSize; i++) {
                for (int j = 0; j < gameAreaSize - 5; j++) {
                    if (gameBoard[i][j].equals("[X]") && gameBoard[i][j + 1].equals("[X]") && gameBoard[i][j + 2].equals("[X]") && gameBoard[i][j + 3].equals("[X]") && gameBoard[i][j + 4].equals("[X]") && gameBoard[i][j + 5].equals("[X]")) {
                        winner = "X";
                    }
                    if (gameBoard[i][j].equals("[O]") && gameBoard[i][j + 1].equals("[O]") && gameBoard[i][j + 2].equals("[O]") && gameBoard[i][j + 3].equals("[O]") && gameBoard[i][j + 4].equals("[O]") && gameBoard[i][j + 5].equals("[O]")) {
                        winner = "O";
                    }
                }
            }
            // Six X's or O's in a row for the win, vertical check.
            for (int i = 0; i < gameAreaSize - 5; i++) {
                for (int j = 0; j < gameAreaSize; j++) {
                    if (gameBoard[i][j].equals("[X]") && gameBoard[i + 1][j].equals("[X]") && gameBoard[i + 2][j].equals("[X]") && gameBoard[i + 3][j].equals("[X]") && gameBoard[i + 4][j].equals("[X]") && gameBoard[i + 5][j].equals("[X]")) {
                        winner = "X";
                    }
                    if (gameBoard[i][j].equals("[O]") && gameBoard[i + 1][j].equals("[O]") && gameBoard[i + 2][j].equals("[O]") && gameBoard[i + 3][j].equals("[O]") && gameBoard[i + 4][j].equals("[O]") && gameBoard[i + 5][j].equals("[O]")) {
                        winner = "O";
                    }
                }
            }
            // Six X's or O's in a row for the win, diagonal check.
            for (int i = 0; i < gameAreaSize - 5; i++) {
                for (int j = 0; j < gameAreaSize - 5; j++) {
                    if (gameBoard[i][j].equals("[X]") && gameBoard[i + 1][j + 1].equals("[X]") && gameBoard[i + 2][j + 2].equals("[X]") && gameBoard[i + 3][j + 3].equals("[X]") && gameBoard[i + 4][j + 4].equals("[X]") && gameBoard[i + 5][j + 5].equals("[X]")) {
                        winner = "X";
                    }
                    if (gameBoard[i][j].equals("[O]") && gameBoard[i + 1][j + 1].equals("[O]") && gameBoard[i + 2][j + 2].equals("[O]") && gameBoard[i + 3][j + 3].equals("[O]") && gameBoard[i + 4][j + 4].equals("[O]") && gameBoard[i + 5][j + 5].equals("[O]")) {
                        winner = "O";
                    }
                }
            }
            // Six X's or O's in a row for the win, anti-diagonal check.
            for (int i = gameAreaSize - 6; i >= 0; i--) {
                for (int j = gameAreaSize - 6; j >= 0; j--) {
                    if (gameBoard[i + 5][j].equals("[X]") && gameBoard[i + 4][j + 1].equals("[X]") && gameBoard[i + 3][j + 2].equals("[X]") && gameBoard[i + 2][j + 3].equals("[X]") && gameBoard[i + 1][j + 4].equals("[X]") && gameBoard[i][j + 5].equals("[X]")) {
                        winner = "X";
                    }
                    if (gameBoard[i + 5][j].equals("[O]") && gameBoard[i + 4][j + 1].equals("[O]") && gameBoard[i + 3][j + 2].equals("[O]") && gameBoard[i + 2][j + 3].equals("[O]") && gameBoard[i + 1][j + 4].equals("[O]") && gameBoard[i][j + 5].equals("[O]")) {
                        winner = "O";
                    }
                }
            }
        }
        return winner;
    }

    /**
     * A method used to declare a tie or the winner.
     * 
     * @param gameAreaSize Is used in printing the game board at the end of the game.
     * @param gameBoard Is used in printing the game board at the end of the game.
     */
    public static void declareWinner(int gameAreaSize, String[][] gameBoard) {
        if (winner == "X") {
            // Prints the game board when the player is declared the winner.
            GameBoard.printGameBoard(gameAreaSize, gameBoard);
            System.out.println("\n" + "Congratulations! You won! Thank you for the game.");
        }
        if (winner == "O") {
            // Prints the game board when the computer is declared the winner.
            GameBoard.printGameBoard(gameAreaSize, gameBoard);
            System.out.println("\n" + "I won! You played well. Thank you for the game.");
        }
        if (winner == "T") {
            // Prints the game board when a tie is declared.
            GameBoard.printGameBoard(gameAreaSize, gameBoard);
            System.out.println("\n" + "It is a tie... You played well. Thank you for the game.");
        }
    }
}