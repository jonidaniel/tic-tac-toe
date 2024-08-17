import java.io.Console;

/**
 * The main class of this Tic-tac-toe game.
 * 
 * The user plays a game of tic-tac-toe against the computer. The size of the game board and the number of X's or O's needed for the win are adjustable.
 * The computer will try to beat the user using random move generating or with a more sophisticated way to try to determine the best moves.
 * 
 * @author Makinen Joni
 */
public class ProjectWork {
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
     * This is the main method. Calling of other methods is primarily implemented here.
     * 
     * @param args This is the command prompt array.
     */
     public static void main(String[] args) {
        gameAreaSize = getGameAreaSize();
        // The array gameBoard is initialized.
        gameBoard = new String[gameAreaSize][gameAreaSize];
        inARowFTW = getInARowFTW();
        gameBoard = GameBoard.createGameBoard(gameAreaSize, gameBoard);
        examplePlacement();
        // This while-loop is implemented for as long as the string winner is empty.
        while (winner.equals("")) {
            playerPosition = Position.getPlayerPosition(gameAreaSize, gameBoard);
            gameBoard = GameBoard.fillGameBoard(gameBoard, playerPosition, computerPosition);
            winner = Winner.checkWinner(gameAreaSize, gameBoard, inARowFTW);
            // This while-loop will break here if the method checkWinner returns a string value of X or T.
            if (winner == "X" || winner == "T") {
                break;
            }
            computerPosition = Position.getComputerPosition(gameAreaSize, gameBoard);
            gameBoard = GameBoard.fillGameBoard(gameBoard, playerPosition, computerPosition);
            winner = Winner.checkWinner(gameAreaSize, gameBoard, inARowFTW);
            // This while-loop will break here if the method checkWinner returns a string value of O or T.
            if (winner == "O" || winner == "T") {
                break;
            }
            // The game board is printed after each pair of moves.
            GameBoard.printGameBoard(gameAreaSize, gameBoard);
        }
        // As soon as the previous while-loop ends, this method is called and a tie or a winner is declared. Then the game will end.
        Winner.declareWinner(gameAreaSize, gameBoard);
    }

    /**
     * A method used to find out the desired game board size.
     * 
     * @return Returns the integer gameAreaSize, which contains the desired size of the game board.
     */
    public static int getGameAreaSize() {
        System.out.println("Let us play a game of tic-tac-toe." + "\n" + "How large would you like the game area to be? (3-15)");
        gameAreaSize = Integer.parseInt(input.readLine());
        // Makes sure that the user enters an appropriate size for the game board.
        while (gameAreaSize < 3 || gameAreaSize > 15) {
            if (gameAreaSize < 3) {
                System.out.println("\n" + "The game area you gave is too small. Try again. (3-15)");
                gameAreaSize = Integer.parseInt(input.readLine());
            }
            if (gameAreaSize > 15) {
                System.out.println("\n" + "The game area you gave is too large. Try again. (3-15)");
                gameAreaSize = Integer.parseInt(input.readLine());
            }
        }
        return gameAreaSize;
    }

    /**
     * A method used to prompt the user for the number of X's or O's in a row needed for the win.
     * 
     * @return Returns the integer inARowFTW, which contains the number of X's or O's in a row needed for the win.
     */
    public static int getInARowFTW() {
        // The value of inARowFTW is set to 3 in the beginning of the method.
        int inARowFTW = 3;
        System.out.println("\n" + "Okay, so we will make it " + gameAreaSize + " squares times " + gameAreaSize + " squares.");
        if (gameAreaSize == 3) {
            System.out.println("You will need three in a row for the win.");
            input.readLine();
        }
        // The user is prompted for the desired number of X's and O's needed for the win (3-4).
        if (gameAreaSize > 3 && gameAreaSize < 10) {
            System.out.println("Which do you like better, three or four in a row for the win? (3-4)");
            inARowFTW = Integer.parseInt(input.readLine());
            System.out.println();
            // Makes sure that the user enters an appropriate value.
            while (inARowFTW < 3 || inARowFTW > 4) {
                System.out.println("You need to choose either option, three or four squares in a row. (3-4)");
                inARowFTW = Integer.parseInt(input.readLine());
                System.out.println();
            }
        }
        // The user is prompted for the desired number of X's and O's needed for the win (5-6).
        if (gameAreaSize > 9) {
            System.out.println("Which do you like better, five or six in a row for the win? (5-6)");
            inARowFTW = Integer.parseInt(input.readLine());
            System.out.println();
            // Makes sure that the user enters an appropriate value.
            while (inARowFTW < 5 || inARowFTW > 6) {
                System.out.println("You need to choose either option, five or six squares in a row. (5-6)");
                inARowFTW = Integer.parseInt(input.readLine());
                System.out.println();
            }
        }
        return inARowFTW;
    }

    /**
     * A method that teaches the user the rules of the game and how to insert the X in the desired position.
     */
    public static void examplePlacement() {
        System.out.println("You will be the X's so you will act first. I will be the O's.");
        input.readLine();
        System.out.println("Whenever it is your turn, I will ask you where on the board would you like to place the X.");
        input.readLine();
        System.out.println("Then you will give me two whole numbers.");
        input.readLine();
        System.out.println("The first number (1-" + gameAreaSize + ") states the X's place on the board counting from left towards right (horizontal),");
        input.readLine();
        System.out.println("and the second number (also 1-" + gameAreaSize + ") states the X's place on the board counting from up towards down (vertical).");
        input.readLine();
        System.out.println("For example, if you want to place the first X here," + "\n");
        // Starts to print the example placement of X.
        for (int i = 0; i < gameAreaSize; i++) {
            System.out.print("[ ]");
        }
        System.out.println("\n" + "[ ][ ][X]" + "[ ]".repeat(gameAreaSize - 3));
        // Continues to print the example placement of X.
        for (int i = 0; i < gameAreaSize - 2; i++) {
            for (int j = 0; j < gameAreaSize; j++) {
                System.out.print("[ ]");
            }
            System.out.println();
        }
        input.readLine();
        System.out.println("you must give me numbers 3 and 2. Press ENTER after each number." + "\n" + "You can not choose a square that is already taken." + "\n\n" + "Press ENTER if you are ready to play.");
        input.readLine();
        System.out.println("Good luck and have fun!");
    }
}