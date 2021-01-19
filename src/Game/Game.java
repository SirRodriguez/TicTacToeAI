package Game;

import java.util.Scanner;

import Board.Board;
import Board.Mark.Mark;
import Board.Mark.None.None;
import Board.Mark.O.O;
import Board.Mark.X.X;
import Game.Player.Player;
import Game.Player.Human.Human;
import Game.Player.Memory.Memory;
import Game.Player.MinMax.MinMax;
import Game.Player.RandomMove.RandomMove;

public class Game {
    Board board;
    Player player1;
    Player player2;

    private boolean turn; // True for player one turn, False for player two turn
    private Player[] playerArray;

    public Game(){
        board = new Board();
        turn = true;
        initializePlayerArray();

        playerSelection();

    }

    public Game(Player p1, Player p2){
        board = new Board();
        turn = true;

        player1 = p1;
        player2 = p2;
    }

    public void runGame() throws Exception{
        // loop while game is not done yet
        while(!board.isDone()){
            makeTurn();
        }

        board.printState();

        // Find which player won
        Mark winnerMark = board.getWinningMark();

        // Give the players actions for winning or losing
        if(winnerMark.isSame( new X() )){
            player1.giveWin();
            player2.giveLose();
        }else if(winnerMark.isSame( new O())){
            player2.giveWin();
            player1.giveLose();
        }else{
            player1.giveTie();
            player2.giveTie();
        }
    }

    // This is to run the game a number of times
    public void runGame(int iterations) throws Exception{
        // Initialize counts
        int p1WInCount = 0;
        int p1LoseCount = 0;
        int p2WinCount = 0;
        int p2LoseCount = 0;
        int tieCount = 0;

        // Run iterations
        for(int i = 0; i < iterations; ++i){
            // Run the game loop
            while(!board.isDone()){
                makeTurn();
            }

            // Find which player won
            Mark winnerMark = board.getWinningMark();

            // Give the players actions for winning or losing
            if(winnerMark.isSame( new X() )){
                player1.giveWinQuiet();
                player2.giveLoseQuiet();

                // record the win or lose
                ++p1WInCount;
                ++p2LoseCount;
            }else if(winnerMark.isSame( new O())){
                player2.giveWinQuiet();
                player1.giveLoseQuiet();

                // record the win or lose
                ++p2WinCount;
                ++p1LoseCount;
            }else{
                player1.giveTieQuiet();
                player2.giveTieQuiet();

                // record the tie
                ++tieCount;
            }

            // Restart the game
            board.reset();
        }

        // Print out counts
        System.out.printf("Player 1/X Win Count: %d%n", p1WInCount);
        // System.out.printf("Player 1/X Lose Count: %d%n", p1LoseCount);
        System.out.printf("Player 2/O Win Count: %d%n", p2WinCount);
        // System.out.printf("Player 2/O Lose Count: %d%n", p2LoseCount);
        System.out.printf("Tie Count: %d%n", tieCount);
    }

    // Private methods

    private void makeTurn() throws Exception{
        // player 1
        if(turn){
            // System.out.println("Player 1 turn");
            player1.makeTurn(board);
        // Player 2
        }else{
            // System.out.println("Player 2 turn");
            player2.makeTurn(board);
        }

        // Change the turn
        turn = !turn;
    }

    private void initializePlayerArray(){
        playerArray = new Player[4];

        playerArray[0] = new Human(new None());
        playerArray[1] = new RandomMove(new None());
        playerArray[2] = new MinMax(new None());
        playerArray[3] = new Memory(new None());
    }

    private void showPlayerOptions(){
        System.out.println("---Options---");
        for(int i = 0; i < playerArray.length; ++i){
            System.out.print("Option ");
            System.out.print(i);
            System.out.print(") ");

            System.out.println(playerArray[i].getPlayerType());
        }
    }

    private void playerSelection(){
        Scanner input = new Scanner(System.in);

        // Pick option for player 1
        showPlayerOptions();
        int choice = -1;
        do {
            try {
                System.out.print("Choose player type for player 1/X: ");
                choice = input.nextInt();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (choice < 0 || choice >= playerArray.length);
        player1 = playerArray[choice].getCopy( new X() );

        // Pick option for player 2
        showPlayerOptions();
        choice = -1;
        do {
            try {
                System.out.print("Choose player type for player 2/O: ");
                choice = input.nextInt();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (choice < 0 || choice >= playerArray.length);
        player2 = playerArray[choice].getCopy( new O() );
    }
}
