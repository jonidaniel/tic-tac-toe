import java.io.Console;

/**
 * A class used to determine current X and O placements.
 * 
 * Position for X is prompted from the user and position for the O is generated randomly or chosen by the AI.
 * 
 * @author Makinen Joni
 */
public class Position {
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
     * A method used to ask X placement from the user.
     * 
     * @param gameAreaSize Is used in making sure that the position for X is on the game board.
     * @param gameBoard Is used in making sure that the position for X is not already taken.
     * @return Returns the array playerPosition, which contains current X placement chosen by the user.
     */
    public static int[] getPlayerPosition(int gameAreaSize, String[][] gameBoard) {
        System.out.println("\n" + "Where would you like to place the X?");
        playerXPos = Integer.parseInt(input.readLine());
        // Makes sure that the chosen position for X is on the game board.
        while (playerXPos < 1 || playerXPos > gameAreaSize) {
            System.out.println("\n" + "You must choose a spot on the game board! Try again! (1-" + gameAreaSize + " from left towards right)");
            playerXPos = Integer.parseInt(input.readLine());
        }
        playerYPos = Integer.parseInt(input.readLine());
        // Makes sure that the chosen position for X is on the game board.
        while (playerYPos < 1 || playerYPos > gameAreaSize) {
            System.out.println("\n" + "You must choose a spot on the game board! Try again! (1-" + gameAreaSize + " from up towards down)");
            playerYPos = Integer.parseInt(input.readLine());
        }
        // Makes sure that the chosen position for X is not taken.
        while (!gameBoard[playerYPos - 1][playerXPos - 1].equals("[ ]")) {
            System.out.println("\n" + "That square is taken. Choose another square.");
            playerXPos = Integer.parseInt(input.readLine());
            while (playerXPos < 1 || playerXPos > gameAreaSize) {
                System.out.println("\n" + "You must choose a spot on the game board! Try again! (1-" + gameAreaSize + " from left towards right)");
                playerXPos = Integer.parseInt(input.readLine());
            }
            playerYPos = Integer.parseInt(input.readLine());
            while (playerYPos < 1 || playerYPos > gameAreaSize) {
                System.out.println("\n" + "You must choose a spot on the game board! Try again! (1-" + gameAreaSize + " from up towards down)");
                playerYPos = Integer.parseInt(input.readLine());
            }
        }
        playerPosition = new int[2];
        playerPosition[0] = playerYPos;
        playerPosition[1] = playerXPos;
        return playerPosition;
    }

    /**
     * A method used to generate O placement randomly or by the AI.
     * 
     * @param gameAreaSize Is used in generating random O placement and in O placement by the AI. Makes sure that the position is on the game board.
     * @param gameBoard Is used in random O placement and in O placement by the AI. Makes sure that the position is not already taken.
     * @return Returns the array computerPosition, which contains current O placement generated randomly or by the AI.
     */
    public static int[] getComputerPosition(int gameAreaSize, String[][] gameBoard) {
        computerPosition = new int[2];
        // Generates random O placement, not used because the AI below overwrites this.
        // If AI is turned off random placement will be used.
        computerXPos = (int) (java.lang.Math.random() * 30);
        computerYPos = (int) (java.lang.Math.random() * 30);
        while (computerXPos >= gameAreaSize || computerYPos >= gameAreaSize || !gameBoard[computerYPos][computerXPos].equals("[ ]") || (computerXPos == playerXPos - 1 && computerYPos == playerYPos - 1)) {
            computerXPos = (int) (java.lang.Math.random() * 30);
            computerYPos = (int) (java.lang.Math.random() * 30);
        }
        // AI, chooses placement of current O based on previous O and X placements.
        do {
            // One X or one O, threatening second, horizontal check.
            for (int i = 0; i < gameAreaSize; i++) {
                for (int j = 0; j < gameAreaSize - 1; j++) {
                    if (gameBoard[i][j] == "[X]" && gameBoard[i][j + 1] == "[ ]") {
                        computerYPos = i;
                        computerXPos = j + 1;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i][j + 1] == "[X]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                    if (gameBoard[i][j] == "[O]" && gameBoard[i][j + 1] == "[ ]") {
                        computerYPos = i;
                        computerXPos = j + 1;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i][j + 1] == "[O]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                }
            }
            // One X or one O, threatening second, vertical check.
            for (int i = 0; i < gameAreaSize - 1; i++) {
                for (int j = 0; j < gameAreaSize; j++) {
                    if (gameBoard[i][j] == "[X]" && gameBoard[i + 1][j] == "[ ]") {
                        computerYPos = i + 1;
                        computerXPos = j;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i + 1][j] == "[X]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                    if (gameBoard[i][j] == "[O]" && gameBoard[i + 1][j] == "[ ]") {
                        computerYPos = i + 1;
                        computerXPos = j;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i + 1][j] == "[O]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                }
            }
            // One X or one O, threatening second, diagonal check.
            for (int i = 0; i < gameAreaSize - 1; i++) {
                for (int j = 0; j < gameAreaSize - 1; j++) {
                    if (gameBoard[i][j] == "[X]" && gameBoard[i + 1][j + 1] == "[ ]") {
                        computerYPos = i + 1;
                        computerXPos = j + 1;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i + 1][j + 1] == "[X]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                    if (gameBoard[i][j] == "[O]" && gameBoard[i + 1][j + 1] == "[ ]") {
                        computerYPos = i + 1;
                        computerXPos = j + 1;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i + 1][j + 1] == "[O]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                }
            }
            // One X or one O, threatening second, anti-diagonal check.
            for (int i = gameAreaSize - 3; i >= 0; i--) {
                for (int j = gameAreaSize - 3; j >= 0; j--) {
                    if (gameBoard[i + 1][j] == "[X]" && gameBoard[i][j + 1] == "[ ]") {
                        computerYPos = i;
                        computerXPos = j + 1;
                    }
                    if (gameBoard[i + 1][j] == "[ ]" && gameBoard[i][j + 1] == "[X]") {
                        computerYPos = i + 1;
                        computerXPos = j;
                    }
                    if (gameBoard[i + 1][j] == "[O]" && gameBoard[i][j + 1] == "[ ]") {
                        computerYPos = i;
                        computerXPos = j + 1;
                    }
                    if (gameBoard[i + 1][j] == "[ ]" && gameBoard[i][j + 1] == "[O]") {
                        computerYPos = i + 1;
                        computerXPos = j;
                    }
                }
            }
            // Two X's or O's in a row, threatening third, horizontal check.
            for (int i = 0; i < gameAreaSize; i++) {
                for (int j = 0; j < gameAreaSize - 2; j++) {
                    if (gameBoard[i][j] == "[X]" && gameBoard[i][j + 1] == "[X]" && gameBoard[i][j + 2] == "[ ]") {
                        computerYPos = i;
                        computerXPos = j + 2;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i][j + 1] == "[X]" && gameBoard[i][j + 2] == "[X]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                    if (gameBoard[i][j] == "[O]" && gameBoard[i][j + 1] == "[O]" && gameBoard[i][j + 2] == "[ ]") {
                        computerYPos = i;
                        computerXPos = j + 2;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i][j + 1] == "[O]" && gameBoard[i][j + 2] == "[O]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                }
            }
            // Two X's or O's in a row, threatening third, vertical check.
            for (int i = 0; i < gameAreaSize - 2; i++) {
                for (int j = 0; j < gameAreaSize; j++) {
                    if (gameBoard[i][j] == "[X]" && gameBoard[i + 1][j] == "[X]" && gameBoard[i + 2][j] == "[ ]") {
                        computerYPos = i + 2;
                        computerXPos = j;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i + 1][j] == "[X]" && gameBoard[i + 2][j] == "[X]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                    if (gameBoard[i][j] == "[O]" && gameBoard[i + 1][j] == "[O]" && gameBoard[i + 2][j] == "[ ]") {
                        computerYPos = i + 2;
                        computerXPos = j;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i + 1][j] == "[O]" && gameBoard[i + 2][j] == "[O]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                }
            }
            // Two X's or O's in a row, threatening third, diagonal check.
            for (int i = 0; i < gameAreaSize - 2; i++) {
                for (int j = 0; j < gameAreaSize - 2; j++) {
                    if (gameBoard[i][j] == "[X]" && gameBoard[i + 1][j + 1] == "[X]" && gameBoard[i + 2][j + 2] == "[ ]") {
                        computerYPos = i + 2;
                        computerXPos = j + 2;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i + 1][j + 1] == "[X]" && gameBoard[i + 2][j + 2] == "[X]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                    if (gameBoard[i][j] == "[O]" && gameBoard[i + 1][j + 1] == "[O]" && gameBoard[i + 2][j + 2] == "[ ]") {
                        computerYPos = i + 2;
                        computerXPos = j + 2;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i + 1][j + 1] == "[O]" && gameBoard[i + 2][j + 2] == "[O]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                }
            }
            // Two X's or O's in a row, threatening third, anti-diagonal check.
            for (int i = gameAreaSize - 3; i >= 0; i--) {
                for (int j = gameAreaSize - 3; j >= 0; j--) {
                    if (gameBoard[i + 2][j] == "[X]" && gameBoard[i + 1][j + 1] == "[X]" && gameBoard[i][j + 2] == "[ ]") {
                        computerYPos = i;
                        computerXPos = j + 2;
                    }
                    if (gameBoard[i + 2][j] == "[ ]" && gameBoard[i + 1][j + 1] == "[X]" && gameBoard[i][j + 2] == "[X]") {
                        computerYPos = i + 2;
                        computerXPos = j;
                    }
                    if (gameBoard[i + 2][j] == "[O]" && gameBoard[i + 1][j + 1] == "[O]" && gameBoard[i][j + 2] == "[ ]") {
                        computerYPos = i;
                        computerXPos = j + 2;
                    }
                    if (gameBoard[i + 2][j] == "[ ]" && gameBoard[i + 1][j + 1] == "[O]" && gameBoard[i][j + 2] == "[O]") {
                        computerYPos = i + 2;
                        computerXPos = j;
                    }
                }
            }
            // Three X's or O's in a row, threatening fourth, horizontal check.
            for (int i = 0; i < gameAreaSize; i++) {
                for (int j = 0; j < gameAreaSize - 3; j++) {
                    if (gameBoard[i][j] == "[X]" && gameBoard[i][j + 1] == "[X]" && gameBoard[i][j + 2] == "[X]" && gameBoard[i][j + 3] == "[ ]") {
                        computerYPos = i;
                        computerXPos = j + 3;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i][j + 1] == "[X]" && gameBoard[i][j + 2] == "[X]" && gameBoard[i][j + 3] == "[X]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                    if (gameBoard[i][j] == "[O]" && gameBoard[i][j + 1] == "[O]" && gameBoard[i][j + 2] == "[O]" && gameBoard[i][j + 3] == "[ ]") {
                        computerYPos = i;
                        computerXPos = j + 3;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i][j + 1] == "[O]" && gameBoard[i][j + 2] == "[O]" && gameBoard[i][j + 3] == "[O]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                }
            }
            // Three X's or O's in a row, threatening fourth, vertical check.
            for (int i = 0; i < gameAreaSize - 3; i++) {
                for (int j = 0; j < gameAreaSize; j++) {
                    if (gameBoard[i][j] == "[X]" && gameBoard[i + 1][j] == "[X]" && gameBoard[i + 2][j] == "[X]" && gameBoard[i + 3][j] == "[ ]") {
                        computerYPos = i + 3;
                        computerXPos = j;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i + 1][j] == "[X]" && gameBoard[i + 2][j] == "[X]" && gameBoard[i + 3][j] == "[X]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                    if (gameBoard[i][j] == "[O]" && gameBoard[i + 1][j] == "[O]" && gameBoard[i + 2][j] == "[O]" && gameBoard[i + 3][j] == "[ ]") {
                        computerYPos = i + 3;
                        computerXPos = j;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i + 1][j] == "[O]" && gameBoard[i + 2][j] == "[O]" && gameBoard[i + 3][j] == "[O]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                }
            }
            // Three X's or O's in a row, threatening fourth, diagonal check.
            for (int i = 0; i < gameAreaSize - 3; i++) {
                for (int j = 0; j < gameAreaSize - 3; j++) {
                    if (gameBoard[i][j] == "[X]" && gameBoard[i + 1][j + 1] == "[X]" && gameBoard[i + 2][j + 2] == "[X]" && gameBoard[i + 3][j + 3] == "[ ]") {
                        computerYPos = i + 3;
                        computerXPos = j + 3;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i + 1][j + 1] == "[X]" && gameBoard[i + 2][j + 2] == "[X]" && gameBoard[i + 3][j + 3] == "[X]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                    if (gameBoard[i][j] == "[O]" && gameBoard[i + 1][j + 1] == "[O]" && gameBoard[i + 2][j + 2] == "[O]" && gameBoard[i + 3][j + 3] == "[ ]") {
                        computerYPos = i + 3;
                        computerXPos = j + 3;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i + 1][j + 1] == "[O]" && gameBoard[i + 2][j + 2] == "[O]" && gameBoard[i + 3][j + 3] == "[O]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                }
            }
            // Three X's or O's in a row, threatening fourth, anti-diagonal check.
            for (int i = gameAreaSize - 4; i >= 0; i--) {
                for (int j = gameAreaSize - 4; j >= 0; j--) {
                    if (gameBoard[i + 3][j] == "[X]" && gameBoard[i + 2][j + 1] == "[X]" && gameBoard[i + 1][j + 2] == "[X]" && gameBoard[i][j + 3] == "[ ]") {
                        computerYPos = i;
                        computerXPos = j + 3;
                    }
                    if (gameBoard[i + 3][j] == "[ ]" && gameBoard[i + 2][j + 1] == "[X]" && gameBoard[i + 1][j + 2] == "[X]" && gameBoard[i][j + 3] == "[X]") {
                        computerYPos = i + 3;
                        computerXPos = j;
                    }
                    if (gameBoard[i + 3][j] == "[O]" && gameBoard[i + 2][j + 1] == "[O]" && gameBoard[i + 1][j + 2] == "[O]" && gameBoard[i][j + 3] == "[ ]") {
                        computerYPos = i;
                        computerXPos = j + 3;
                    }
                    if (gameBoard[i + 3][j] == "[ ]" && gameBoard[i + 2][j + 1] == "[O]" && gameBoard[i + 1][j + 2] == "[O]" && gameBoard[i][j + 3] == "[O]") {
                        computerYPos = i + 3;
                        computerXPos = j;
                    }
                }
            }
            // Four X's or O's in a row, threatening fifth, horizontal check.
            for (int i = 0; i < gameAreaSize; i++) {
                for (int j = 0; j < gameAreaSize - 4; j++) {
                    if (gameBoard[i][j] == "[X]" && gameBoard[i][j + 1] == "[X]" && gameBoard[i][j + 2] == "[X]" && gameBoard[i][j + 3] == "[X]" && gameBoard[i][j + 4] == "[ ]") {
                        computerYPos = i;
                        computerXPos = j + 4;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i][j + 1] == "[X]" && gameBoard[i][j + 2] == "[X]" && gameBoard[i][j + 3] == "[X]" && gameBoard[i][j + 4] == "[X]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                    if (gameBoard[i][j] == "[O]" && gameBoard[i][j + 1] == "[O]" && gameBoard[i][j + 2] == "[O]" && gameBoard[i][j + 3] == "[O]" && gameBoard[i][j + 4] == "[ ]") {
                        computerYPos = i;
                        computerXPos = j + 4;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i][j + 1] == "[O]" && gameBoard[i][j + 2] == "[O]" && gameBoard[i][j + 3] == "[O]" && gameBoard[i][j + 4] == "[O]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                }
            }
            // Four X's or O's in a row, threatening fifth, vertical check.
            for (int i = 0; i < gameAreaSize - 4; i++) {
                for (int j = 0; j < gameAreaSize; j++) {
                    if (gameBoard[i][j] == "[X]" && gameBoard[i + 1][j] == "[X]" && gameBoard[i + 2][j] == "[X]" && gameBoard[i + 3][j] == "[X]" && gameBoard[i + 4][j] == "[ ]") {
                        computerYPos = i + 4;
                        computerXPos = j;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i + 1][j] == "[X]" && gameBoard[i + 2][j] == "[X]" && gameBoard[i + 3][j] == "[X]" && gameBoard[i + 4][j] == "[X]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                    if (gameBoard[i][j] == "[O]" && gameBoard[i + 1][j] == "[O]" && gameBoard[i + 2][j] == "[O]" && gameBoard[i + 3][j] == "[O]" && gameBoard[i + 4][j] == "[ ]") {
                        computerYPos = i + 4;
                        computerXPos = j;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i + 1][j] == "[O]" && gameBoard[i + 2][j] == "[O]" && gameBoard[i + 3][j] == "[O]" && gameBoard[i + 4][j] == "[O]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                }
            }
            // Four X's or O's in a row, threatening fifth, diagonal check.
            for (int i = 0; i < gameAreaSize - 4; i++) {
                for (int j = 0; j < gameAreaSize - 4; j++) {
                    if (gameBoard[i][j] == "[X]" && gameBoard[i + 1][j + 1] == "[X]" && gameBoard[i + 2][j + 2] == "[X]" && gameBoard[i + 3][j + 3] == "[X]" && gameBoard[i + 4][j + 4] == "[ ]") {
                        computerYPos = i + 4;
                        computerXPos = j + 4;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i + 1][j + 1] == "[X]" && gameBoard[i + 2][j + 2] == "[X]" && gameBoard[i + 3][j + 3] == "[X]" && gameBoard[i + 4][j + 4] == "[X]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                    if (gameBoard[i][j] == "[O]" && gameBoard[i + 1][j + 1] == "[O]" && gameBoard[i + 2][j + 2] == "[O]" && gameBoard[i + 3][j + 3] == "[O]" && gameBoard[i + 4][j + 4] == "[ ]") {
                        computerYPos = i + 4;
                        computerXPos = j + 4;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i + 1][j + 1] == "[O]" && gameBoard[i + 2][j + 2] == "[O]" && gameBoard[i + 3][j + 3] == "[O]" && gameBoard[i + 4][j + 4] == "[O]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                }
            }
            // Four X's or O's in a row, threatening fifth, anti-diagonal check.
            for (int i = gameAreaSize - 5; i >= 0; i--) {
                for (int j = gameAreaSize - 5; j >= 0; j--) {
                    if (gameBoard[i + 4][j] == "[X]" && gameBoard[i + 3][j + 1] == "[X]" && gameBoard[i + 2][j + 2] == "[X]" && gameBoard[i + 1][j + 3] == "[X]" && gameBoard[i][j + 4] == "[ ]") {
                        computerYPos = i;
                        computerXPos = j + 4;
                    }
                    if (gameBoard[i + 4][j] == "[ ]" && gameBoard[i + 3][j + 1] == "[X]" && gameBoard[i + 2][j + 2] == "[X]" && gameBoard[i + 1][j + 3] == "[X]" && gameBoard[i][j + 4] == "[X]") {
                        computerYPos = i + 4;
                        computerXPos = j;
                    }
                    if (gameBoard[i + 4][j] == "[O]" && gameBoard[i + 3][j + 1] == "[O]" && gameBoard[i + 2][j + 2] == "[O]" && gameBoard[i + 1][j + 3] == "[O]" && gameBoard[i][j + 4] == "[ ]") {
                        computerYPos = i;
                        computerXPos = j + 4;
                    }
                    if (gameBoard[i + 4][j] == "[ ]" && gameBoard[i + 3][j + 1] == "[O]" && gameBoard[i + 2][j + 2] == "[O]" && gameBoard[i + 1][j + 3] == "[O]" && gameBoard[i][j + 4] == "[O]") {
                        computerYPos = i + 4;
                        computerXPos = j;
                    }
                }
            }
            // Five X's or O's in a row, threatening sixth, horizontal check.
            for (int i = 0; i < gameAreaSize; i++) {
                for (int j = 0; j < gameAreaSize - 5; j++) {
                    if (gameBoard[i][j] == "[X]" && gameBoard[i][j + 1] == "[X]" && gameBoard[i][j + 2] == "[X]" && gameBoard[i][j + 3] == "[X]" && gameBoard[i][j + 4] == "[X]" && gameBoard[i][j + 5] == "[ ]") {
                        computerYPos = i;
                        computerXPos = j + 5;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i][j + 1] == "[X]" && gameBoard[i][j + 2] == "[X]" && gameBoard[i][j + 3] == "[X]" && gameBoard[i][j + 4] == "[X]" && gameBoard[i][j + 5] == "[X]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                    if (gameBoard[i][j] == "[O]" && gameBoard[i][j + 1] == "[O]" && gameBoard[i][j + 2] == "[O]" && gameBoard[i][j + 3] == "[O]" && gameBoard[i][j + 4] == "[O]" && gameBoard[i][j + 5] == "[ ]") {
                        computerYPos = i;
                        computerXPos = j + 5;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i][j + 1] == "[O]" && gameBoard[i][j + 2] == "[O]" && gameBoard[i][j + 3] == "[O]" && gameBoard[i][j + 4] == "[O]" && gameBoard[i][j + 5] == "[O]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                }
            }
            // Five X's or O's in a row, threatening sixth, vertical check.
            for (int i = 0; i < gameAreaSize - 5; i++) {
                for (int j = 0; j < gameAreaSize; j++) {
                    if (gameBoard[i][j] == "[X]" && gameBoard[i + 1][j] == "[X]" && gameBoard[i + 2][j] == "[X]" && gameBoard[i + 3][j] == "[X]" && gameBoard[i + 4][j] == "[X]" && gameBoard[i + 5][j] == "[ ]") {
                        computerYPos = i + 5;
                        computerXPos = j;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i + 1][j] == "[X]" && gameBoard[i + 2][j] == "[X]" && gameBoard[i + 3][j] == "[X]" && gameBoard[i + 4][j] == "[X]" && gameBoard[i + 5][j] == "[X]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                    if (gameBoard[i][j] == "[O]" && gameBoard[i + 1][j] == "[O]" && gameBoard[i + 2][j] == "[O]" && gameBoard[i + 3][j] == "[O]" && gameBoard[i + 4][j] == "[O]" && gameBoard[i + 5][j] == "[ ]") {
                        computerYPos = i + 5;
                        computerXPos = j;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i + 1][j] == "[O]" && gameBoard[i + 2][j] == "[O]" && gameBoard[i + 3][j] == "[O]" && gameBoard[i + 4][j] == "[O]" && gameBoard[i + 5][j] == "[O]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                }
            }
            // Five X's or O's in a row, threatening sixth, diagonal check.
            for (int i = 0; i < gameAreaSize - 5; i++) {
                for (int j = 0; j < gameAreaSize - 5; j++) {
                    if (gameBoard[i][j] == "[X]" && gameBoard[i + 1][j + 1] == "[X]" && gameBoard[i + 2][j + 2] == "[X]" && gameBoard[i + 3][j + 3] == "[X]" && gameBoard[i + 4][j + 4] == "[X]" && gameBoard[i + 5][j + 5] == "[ ]") {
                        computerYPos = i + 5;
                        computerXPos = j + 5;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i + 1][j + 1] == "[X]" && gameBoard[i + 2][j + 2] == "[X]" && gameBoard[i + 3][j + 3] == "[X]" && gameBoard[i + 4][j + 4] == "[X]" && gameBoard[i + 5][j + 5] == "[X]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                    if (gameBoard[i][j] == "[O]" && gameBoard[i + 1][j + 1] == "[O]" && gameBoard[i + 2][j + 2] == "[O]" && gameBoard[i + 3][j + 3] == "[O]" && gameBoard[i + 4][j + 4] == "[O]" && gameBoard[i + 5][j + 5] == "[ ]") {
                        computerYPos = i + 5;
                        computerXPos = j + 5;
                    }
                    if (gameBoard[i][j] == "[ ]" && gameBoard[i + 1][j + 1] == "[O]" && gameBoard[i + 2][j + 2] == "[O]" && gameBoard[i + 3][j + 3] == "[O]" && gameBoard[i + 4][j + 4] == "[O]" && gameBoard[i + 5][j + 5] == "[O]") {
                        computerYPos = i;
                        computerXPos = j;
                    }
                }
            }
            // Five X's or O's in a row, threatening sixth, anti-diagonal check.
            for (int i = gameAreaSize - 6; i >= 0; i--) {
                for (int j = gameAreaSize - 6; j >= 0; j--) {
                    if (gameBoard[i + 5][j] == "[X]" && gameBoard[i + 4][j + 1] == "[X]" && gameBoard[i + 3][j + 2] == "[X]" && gameBoard[i + 2][j + 3] == "[X]" && gameBoard[i + 1][j + 4] == "[X]" && gameBoard[i][j + 5] == "[ ]") {
                        computerYPos = i;
                        computerXPos = j + 5;
                    }
                    if (gameBoard[i + 5][j] == "[ ]" && gameBoard[i + 4][j + 1] == "[X]" && gameBoard[i + 3][j + 2] == "[X]" && gameBoard[i + 2][j + 3] == "[X]" && gameBoard[i + 1][j + 4] == "[X]" && gameBoard[i][j + 5] == "[X]") {
                        computerYPos = i + 5;
                        computerXPos = j;
                    }
                    if (gameBoard[i + 5][j] == "[O]" && gameBoard[i + 4][j + 1] == "[O]" && gameBoard[i + 3][j + 2] == "[O]" && gameBoard[i + 2][j + 3] == "[O]" && gameBoard[i + 1][j + 4] == "[O]" && gameBoard[i][j + 5] == "[ ]") {
                        computerYPos = i;
                        computerXPos = j + 5;
                    }
                    if (gameBoard[i + 5][j] == "[ ]" && gameBoard[i + 4][j + 1] == "[O]" && gameBoard[i + 3][j + 2] == "[O]" && gameBoard[i + 2][j + 3] == "[O]" && gameBoard[i + 1][j + 4] == "[O]" && gameBoard[i][j + 5] == "[O]") {
                        computerYPos = i + 5;
                        computerXPos = j;
                    }
                }
            }
        } while (!gameBoard[computerYPos][computerXPos].equals("[ ]") || (computerXPos == playerXPos - 1 && computerYPos == playerYPos - 1));
        computerPosition[0] = computerYPos;
        computerPosition[1] = computerXPos;
        return computerPosition;
    }
}