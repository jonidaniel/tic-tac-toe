import java.io.Console;

/**
 * A class used to create, fill and print the game board.
 * 
 * The game board is created before the game starts, filled with the latest X's and O's during the game
 * and printed after each pair of moves. The game board is printed also when the last to move is the player.
 * 
 * @author Makinen Joni
 */
public class GameBoard {
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
     * A method used to create the game board before the game starts. Also demonstrates the game board to the player.
     * 
     * @param gameAreaSize Is used in for-loops creating and demonstrating the game board.
     * @param gameBoard Is used in for-loops creating and demonstrating the game board.
     * @return Returns the array gameBoard, which contains the newly created game board.
     */
    public static String[][] createGameBoard(int gameAreaSize, String[][] gameBoard) {
        System.out.println("Our game board looks like this:" + "\n");
        // Creates the game board.
        for (int i = 0; i < gameAreaSize; i++) {
            for (int j = 0; j < gameAreaSize; j++) {
                gameBoard[i][j] = "[ ]";
            }
        }
        // Demonstrates the game board.
        for (int i = 0; i < gameAreaSize; i++) {
            for (int j = 0; j < gameAreaSize; j++) {
                System.out.print(gameBoard[i][j]);
            }
            System.out.println();
        }
        input.readLine();
        return gameBoard;
    }

    /**
     * A method used to fill the game board with new X's and O's.
     * 
     * @param gameBoard Is used in filling the game board with new X's and O's.
     * @param playerPosition Contains the latest position for X.
     * @param computerPosition Contains the latest position for O.
     * @return Returns the array gameBoard, which contains the newly updated game board.
     */
    public static String[][] fillGameBoard(String[][] gameBoard, int[] playerPosition, int[] computerPosition) {
        gameBoard[playerPosition[0] - 1][playerPosition[1] - 1] = "[X]";
        // Both player and computer positions are filled in the game board separately.
        // This if-command makes sure that the game board is not filled with the missing computer position after the first move of the player.
        if (computerPosition != null) {
            gameBoard[computerPosition[0]][computerPosition[1]] = "[O]";
        }
        return gameBoard;
    }

    /**
     * A method used to print the current game board.
     * 
     * @param gameAreaSize Is used in printing the game board.
     * @param gameBoard Is used in printing the game board.
     */
    public static void printGameBoard(int gameAreaSize, String[][] gameBoard) {
        System.out.println();
        for (int i = 0; i < gameAreaSize; i++) {
            for (int j = 0; j < gameAreaSize; j++) {
                System.out.print(gameBoard[i][j]);
            }
            System.out.println();
        }
    }
}