
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
}
