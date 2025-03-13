import java.util.Scanner;

public class TicTacToeGame {

    private static Board board;
    private static int spotsLeft;
    private static int roundNum;
    private static Player player1;
    private static Player player2;
    private static char currentPlayer;
    private static TicTacToeDifficulty difficulty;
    
    /**
     * Starts a new game of tic tac toe
     */
    private static void setUp() {
        board = new Board();
        roundNum = 1;
        spotsLeft = 9;
        currentPlayer = 'X';
    }
    
    /**
     * Sets the players appropriately based on the player's responses
     */
    private static void setPlayers(Scanner sc) {
        System.out.println("Would you like to be 'X' or 'O'?");
        String playerSymbol = sc.next().toUpperCase();
        while (true) {
            if (!playerSymbol.equals("X") && !playerSymbol.equals("O")) {
                System.out.println("Please enter a valid option: 'X' or 'O'.");
            } else {
                break;
            }
            playerSymbol = sc.next().toUpperCase();
        }
        
        System.out.println("Play against a friend(f) or a computer(c)?");
        String opAnswer = sc.next().toLowerCase();
        while (true) {
            if (!opAnswer.equals("f") && !opAnswer.equals("c")) {
                System.out.println("Please enter a valid option: 'f' or 'c'.");
            } else {
                break;
            }
            opAnswer = sc.next().toLowerCase();
        }
        
        if (playerSymbol.equals("X")) {
            player1 = new Player('X', true);
            player2 = new Player('O', opAnswer.equals("f"));
        } else {
            player1 = new Player('O', true);
            player2 = new Player('X', opAnswer.equals("f"));
        }
        
        if (opAnswer.equals("c")) {
            System.out.println("Please choose the CPU difficulty: easy(e), medium(m), or hard(h)");
            String diff = sc.next().toLowerCase();
            while (true) {
                if (!diff.equals("e") && !diff.equals("m") && !diff.equals("d")) {
                    System.out.println("Please enter a valid option: 'e', 'm', or 'h'.");
                } else {
                    break;
                }
                diff = sc.next().toLowerCase();
            }
            
            int level = TicTacToeDifficulty.EASY.ordinal();
            if (diff.equals("m")) {
                level = TicTacToeDifficulty.MEDIUM.ordinal();;
            } else if (diff.equals("h")) {
                level = TicTacToeDifficulty.HARD.ordinal();;
            }
            difficulty = TicTacToeDifficulty.values()[level];
        }
    }
    
    /**
     * Intended to make CPU responses delayed
     */
    private static void delay(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Board.printReferenceBoard();
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("Enter 's' to start a new game or 'q' to quit.");
            String in = sc.next().toLowerCase();
            while (true) {
                if (!in.equals("s") && !in.equals("q")) {
                    System.out.println("Please enter a valid option: 's' or 'q'.");
                } else {
                    break;
                }
                in = sc.next().toLowerCase();
            }
            if (in.equals("s")) {
                setUp();
                setPlayers(sc);
                
                boolean hasWon = false;
                while (!hasWon) {
                    if (spotsLeft == 0) {
                        System.out.println("\nThere's a tie!");
                        break;
                    }
                    System.out.println("\nRound number " + roundNum + "\n");
                    delay(1000);
                    board.printBoard();
                    delay(1000);
                    if (player1.getSymbol() == currentPlayer) {
                        System.out.println("Player 1, what's your move? Enter a number 1-9.");
                        int num = 0;
                        while (true) {
                            if (sc.hasNextInt()) {
                                num = sc.nextInt();
                                if (!(num >= 1 && num <= 9)) {
                                    System.out.println("Please enter a number 1-9.");
                                } else if (!board.validateMove(num)){
                                    System.out.println("Spot is already occupied! Please enter another number 1-9.");
                                } else {
                                    break;
                                }
                            } else {
                                System.out.println("Please enter a number 1-9.");
                                sc.next();
                            }
                        }
                        delay(1000);
                        int[] playerMove = player1.makeMove(num);
                        board.updateBoard(playerMove, currentPlayer);
                        currentPlayer = player2.getSymbol();
                        spotsLeft--;
                        delay(1000);
                    } else {
                        System.out.println("Player 2, what's your move? Enter a number 1-9.");
                        if (player2.isHuman()) {
                            int num = 0;
                            while (true) {
                                if (sc.hasNextInt()) {
                                    num = sc.nextInt();
                                    if (!(num >= 1 && num <= 9)) {
                                        System.out.println("Please enter a number 1-9.");
                                    } else if (!board.validateMove(num)){
                                        System.out.println("Spot is already occupied! Please enter another number 1-9.");
                                    } else {
                                        break;
                                    }
                                } else {
                                    System.out.println("Please enter a number 1-9.");
                                    sc.next();
                                }
                            }
                            delay(1000);
                            int[] playerMove = player2.makeMove(num);
                            board.updateBoard(playerMove, currentPlayer);
                            currentPlayer = player1.getSymbol();
                            spotsLeft--;
                            delay(1000);
                        } else {
                            delay(2000);
                            System.out.println("CPU: I am thinking...");
                            delay(1000);
                            
                            int ran = (int) (Math.random() * 9 + 1);
                            while(!board.validateMove(ran)) {
                                ran = (int) (Math.random() * 9 + 1);
                            }
                            System.out.println("CPU: Okay! I choose " + ran + ".");
                            delay(1000);
                            int[] playerMove = player2.makeMove(ran);
                            board.updateBoard(playerMove, currentPlayer);
                            currentPlayer = player1.getSymbol();
                            spotsLeft--;
                            delay(1000);
                        }
                    }
                    hasWon = board.checkForWinner();
                    roundNum++;
                }
                String winner = currentPlayer == player2.getSymbol() ? "Player 1" : "Player 2";
                System.out.println("\n" + winner + " has won!\n");
                delay(1000);
                board.printBoard();
                delay(1000);
                System.out.print("Play again? ");
            } else if (in.equals("q")) {
                System.out.println("Goodbye.");
                break;
            }
        }
        sc.close();
    }
}
