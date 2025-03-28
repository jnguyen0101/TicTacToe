
public class Player {
    
    private char symbol;
    private boolean isHuman;
    
    /**
     * Creates a new game player
     * @param symbol The symbol associated with this player
     */
    public Player(char symbol, boolean isHuman) {
        this.symbol = symbol;
        this.isHuman = isHuman;
    }
    
    /**
     * Gets the symbol of this player
     * @return The char representing the symbol of this player
     */
    public char getSymbol() {
        return symbol;
    }
    
    /**
     * Gets whether or not this player is human
     * @return true if the player is human and false otherwise
     */
    public boolean isHuman() {
        return isHuman;
    }
    
    /**
     * Gets the coordinates given the spot number
     * @param spot The spot where the player wishes to move
     * @return the coordinates of the spot
     */
    public int[] makeMove(int spot) {
        int row = (spot - 1) / 3;
        int column = (spot - 1) % 3;
        int[] coords = {row, column};
        
        return coords;
    }
    
    /**
     * Check the board for the best move for the CPU
     * @param board The game board to check
     * @param symbol The symbol representing the CPU
     * @return the spot number
     */
    public static int comMedium(Board gameBoard, char symbol) {
        int[] coords = checkTwo(gameBoard, symbol);
        if (coords[0] != -1) return coordsToSpot(coords);
        
        coords = checkOne(gameBoard, symbol);
        if (coords[0] != -1) return coordsToSpot(coords);
        
        int num = (int) (Math.random() * 9 + 1);
        while(!gameBoard.validateMove(num)) {
            num = (int) (Math.random() * 9 + 1);
        }
        
        return num;
    }
    
    /**
     * Check the board to block the player from winning
     * @param board The game board to check
     * @param symbol The symbol representing the CPU
     * @return the spot number
     */
    public static int comHard(Board gameBoard, char symbol) {
        // find a win
        char player = symbol == 'X' ? 'O' : 'X';
        int[] coords = checkTwo(gameBoard, symbol);
        if (coords[0] != -1) {
            return coordsToSpot(coords);
        }
        
        // find a block
        coords = checkTwo(gameBoard, player);
        if (coords[0] != -1) {
            return coordsToSpot(coords);
        }

        return comMedium(gameBoard, symbol);
    }
    
    /**
     * Helper method to check for two in a row
     * @param gameBoard The game board to check
     * @param symbol The symbol representing either the player or the CPU
     * @return the coordinates of the spot
     */
    private static int[] checkTwo(Board gameBoard, char symbol) {
        char[][] board = gameBoard.getBoard();
        int[] coords = {-1, -1};
        
        // check horizontal
        for (int i = 0; i < board[0].length; i++ ) {
            if (board[i][0] == symbol && board[i][1] == symbol) {
                coords[0] = i;
                coords[1] = 2;
                if (gameBoard.validateMove(coordsToSpot(coords))) {
                    return coords;
                }
            } else if (board[i][1] == symbol && board[i][2] == symbol) {
                coords[0] = i;
                coords[1] = 0;
                if (gameBoard.validateMove(coordsToSpot(coords))) {
                    return coords;
                }
            }
        }
        
        // check vertical
        for (int j = 0; j < board.length; j++) {
            if (board[0][j] == symbol && board[1][j] == symbol) {
                coords[0] = 2;
                coords[1] = j;
                if (gameBoard.validateMove(coordsToSpot(coords))) {
                    return coords;
                }
            } else if(board[1][j] == symbol && board[2][j] == symbol) {
                coords[0] = 0;
                coords[1] = j;
                if (gameBoard.validateMove(coordsToSpot(coords))) {
                    return coords;
                }
            }
        }
        
        // check diagonal1
        if (board[0][0] == symbol && board[1][1] == symbol) {
            coords[0] = 2;
            coords[1] = 2;
            if (gameBoard.validateMove(coordsToSpot(coords))) {
                return coords;
            }
        } else if (board[1][1] == symbol && board[2][2] == symbol) {
            coords[0] = 0;
            coords[1] = 0;
            if (gameBoard.validateMove(coordsToSpot(coords))) {
                return coords;
            }
        }
        
        // check diagonal2
        if (board[0][2] == symbol && board[1][1] == symbol) {
            coords[0] = 2;
            coords[1] = 0;
            if (gameBoard.validateMove(coordsToSpot(coords))) {
                return coords;
            }
        } else if (board[1][1] == symbol && board[2][0] == symbol) {
            coords[0] = 0;
            coords[1] = 2;
            if (gameBoard.validateMove(coordsToSpot(coords))) {
                return coords;
            }
        }
        coords[0] = -1;
        coords[1] = -1;
        
        return coords;
    }
    
    /**
     * Helper method to check for one in a row
     * @param gameBoard The game board to check
     * @param symbol The symbol representing the CPU
     * @return the coordinates of the spot
     */
    private static int[] checkOne(Board gameBoard, char symbol) {
        char[][] board = gameBoard.getBoard();
        int[] coords = {-1, -1};
        
        // check horizontal
        for (int i = 0; i < board.length; i++ ) {
            if (board[i][0] == symbol && board[i][2] == ' ') {
                coords[0] = i;
                coords[1] = 1;
                if (gameBoard.validateMove(coordsToSpot(coords))) {
                    return coords;
                }
            } else if (board[i][1] == symbol) {
                coords[0] = i;
                coords[1] = 0;
                if (gameBoard.validateMove(coordsToSpot(coords))) {
                    return coords;
                }
                coords[1] = 2;
                if (gameBoard.validateMove(coordsToSpot(coords))) {
                    return coords;
                }
            } else if (board[i][2] == symbol && board[i][0] == ' ') {
                coords[0] = i;
                coords[1] = 1;
                if (gameBoard.validateMove(coordsToSpot(coords))) {
                    return coords;
                }
            }
        }
        
        // check vertical
        for (int j = 0; j < board[0].length; j++) {
            if (board[0][j] == symbol && board[2][j] == ' ') {
                coords[0] = 1;
                coords[1] = j;
                if (gameBoard.validateMove(coordsToSpot(coords))) {
                    return coords;
                }
            } else if (board[1][j] == symbol) {
                coords[0] = 0;
                coords[1] = j;
                if (gameBoard.validateMove(coordsToSpot(coords))) {
                    return coords;
                }
                coords[0] = 2;
                if (gameBoard.validateMove(coordsToSpot(coords))) {
                    return coords;
                }
            } else if (board[2][j] == symbol && board[2][j] == ' ') {
                coords[0] = 1;
                coords[1] = j;
                if (gameBoard.validateMove(coordsToSpot(coords))) {
                    return coords;
                }
            }
        }
        
        // check diagonal1
        if (board[0][0] == symbol && board[2][2] == ' ') {
            coords[0] = 1;
            coords[1] = 1;
            if (gameBoard.validateMove(coordsToSpot(coords))) {
                return coords;
            }
        } else if (board[1][1] == symbol) {
            coords[0] = 0;
            coords[1] = 0;
            if (gameBoard.validateMove(coordsToSpot(coords))) {
                return coords;
            }
            coords[0] = 2;
            coords[1] = 2;
            if (gameBoard.validateMove(coordsToSpot(coords))) {
                return coords;
            } else if (board[2][2] == symbol && board[0][0] == ' ') {
                coords[0] = 1;
                coords[1] = 1;
                if (gameBoard.validateMove(coordsToSpot(coords))) {
                    return coords;
                }
            }
        }
        
        // check diagonal2
        if (board[0][2] == symbol && board[2][0] == ' ') {
            coords[0] = 1;
            coords[1] = 1;
            if (gameBoard.validateMove(coordsToSpot(coords))) {
                return coords;
            }
        } else if (board[1][1] == symbol) {
            coords[0] = 0;
            coords[1] = 2;
            if (gameBoard.validateMove(coordsToSpot(coords))) {
                return coords;
            }
            coords[0] = 2;
            coords[1] = 0;
            if (gameBoard.validateMove(coordsToSpot(coords))) {
                return coords;
            } else if (board[2][0] == symbol && board[0][2] == ' ') {
                coords[0] = 1;
                coords[1] = 1;
                if (gameBoard.validateMove(coordsToSpot(coords))) {
                    return coords;
                }
            }
        }
        coords[0] = -1;
        coords[1] = -1;
        
        return coords;
    }
    
    /**
     * Converts board coordinates to spot number
     * @param coords The coordinates to convert
     * @return the spot number on the board
     */
    private static int coordsToSpot(int[] coords) {
        int row = coords[0] + 1;
        int column = coords[1] + 1;
        
        int spot = row * 3 - (3 - column);
        return spot;
    }
}
