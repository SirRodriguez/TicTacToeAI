package Game;

import Board.Board;
import Board.Mark.Mark;
import Board.Mark.O.O;
import Board.Mark.X.X;
import Game.Player.Player;
import Game.Player.Human.Human;

public class Game {
    Board board;
    Player player1;
    Player player2;

    private boolean turn; // True for player one turn, False for player two turn

    public Game(){
        board = new Board();

        // For now, 2 human players
        player1 = new Human(new X());
        player2 = new Human(new O());
        turn = true;
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
}
