/**
 * This enumerated type represents the difficulty levels of the CPU
 * for Tic Tac Toe.
 * EASY causes the CPU to make a random move.
 * MEDIUM causes the CPU to make the best move given the state of
 * the board.
 * HARD causes the CPU to prioritize blocking the player from
 * winning, and if no move is available, it makes the best move
 * given the state of the board.
 *
 */
public enum TicTacToeDifficulty {
    EASY, MEDIUM, HARD;
}
