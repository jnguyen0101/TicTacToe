
public class Board {
    
    private static char[][] board;
    
    /**
     * Creates a new game board
     */
    public Board() {
        // make the board and fill it with spaces
        board = new char[3][3];
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = ' ';
            }
        }
    }
    
    /**
     * Gets the current board
     * @return a 2d char array representing the board
     */
    public char[][] getBoard() {
        return board;
    }
    
    /**
     * Updates the game board
     * @param coords The coordinates to place the symbol [r][c]
     * @param symbol The symbol, 'X' or 'O', to be put in the spot
     * @return true if placement was successful and false otherwise
     */
    public boolean updateBoard(int[] coords, char symbol) {
        int row = coords[0];
        int col = coords[1];
        
        if (board[row][col] == ' ') {
            board[row][col] = symbol;
        }
        return false;
    }
    
    /**
     * Validates the move according to the current state of the board
     * @param spot The spot in which a player wishes to move
     * @return true if the spot is valid and false otherwise
     */
    public boolean validateMove(int spot) {
        int row = (spot - 1) / 3;
        int column = (spot - 1) % 3;
        
        if (board[row][column] != ' ') {
            return false;
        }
        
        return true;
    }
    
    /**
     * Checks the board to see if there has been a winner
     * @return true if someone has won and false otherwise
     */
    public boolean checkForWinner() {
        // check horizontal
        for (int i = 0; i < board[0].length; i++ ) {
            char hor = board[i][0];
            if (hor == ' ') break;
            
            for (int j = 1; j < board.length; j++) {
                if (board[i][j] != hor) break;
                if (j == board.length - 1) return true;
            }
        }
        
        // check vertical
        for (int j = 0; j < board.length; j++) {
            char ver = board[0][j];
            if (ver == ' ') break;
            
            for (int i = 1; i < board[0].length; i++) {
                if (board[i][j] != ver) break;
                if (i == board.length - 1) return true;
            }
        }
        
        // check diagonal1
        char diag1 = board[0][0];
        if (diag1 != ' ') {
            for (int i = 1; i < board[0].length; i++) {
                if (board[i][i] != diag1) break;
                if (i == board.length - 1) return true;
            }
        }
        
        // check diagonal2
        char diag2 = board[0][board.length - 1];
            if (diag2 != ' ') {
            for (int i = 1; i < board[0].length; i++) {
                if (board[i][board.length - 1 - i] != diag2) break;
                if (i == board.length - 1) return true;
            }
        }
            
        return false;
    }
    
    /**
     * Prints the current status of the game board
     */
    public void printBoard() {
        final int LEN = 7;
        StringBuilder sb = new StringBuilder();
        int r = 0;
        for (int i = 0; i < LEN; i++) {
            if (i % 2 == 0) {
                sb.append("-------\n");
            } else {
                for (int j = 0; j < board.length; j++) {
                    sb.append("|");
                    sb.append(board[r][j]);
                }
                sb.append("|\n");
                r++;
            }
        }
        
        String result = sb.toString();
        System.out.println("Game board:");
        System.out.println(result + "\n");
    }
    
    /**
     * Prints a reference of the game board labeled with spot numbers 1-9
     */
    public static void printReferenceBoard() {
        final int LEN = 7;
        StringBuilder sb = new StringBuilder();
        int num = 1;
        for (int i = 0; i < LEN; i++) {
            if (i % 2 == 0) {
                sb.append("-------\n");
            } else {
                for (int j = 0; j < 3; j++) {
                    sb.append("|");
                    sb.append(num);
                    num++;
                }
                sb.append("|\n");
            }
        }
        
        String result = sb.toString();
        System.out.println("Reference board:");
        System.out.println(result + "\n");
    }
}
