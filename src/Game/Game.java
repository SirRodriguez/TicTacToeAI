package Game;

import java.util.Scanner;

import Board.Board;
import Board.Mark.Mark;
import Board.Mark.None.None;
import Board.Mark.O.O;
import Board.Mark.X.X;
import Game.Player.Player;
import Game.Player.Human.Human;
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

    public void runGame(){
        // loop while game is not done yet
        while(!board.isDone()){
            makeTurn();
        }

        board.printState();

        // Find which player won
        Mark winnerMark = board.getWinningMark();

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

    // Private methods

    private void makeTurn(){
        // player 1
        if(turn){
            System.out.println("Player 1 turn");
            player1.makeTurn(board);
        // Player 2
        }else{
            System.out.println("Player 2 turn");
            player2.makeTurn(board);
        }

        // Change the turn
        turn = !turn;
    }

    private void initializePlayerArray(){
        playerArray = new Player[2];

        playerArray[0] = new Human(new None());
        playerArray[1] = new RandomMove(new None());
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
