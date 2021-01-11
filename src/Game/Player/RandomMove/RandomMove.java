package Game.Player.RandomMove;

import java.util.Random;

import Board.Board;
import Board.Mark.Mark;
import Game.Player.Player;

public class RandomMove implements Player{
    Mark type;

    public RandomMove(Mark mark){
        type = mark;
    }

    public void makeTurn(Board board){
        // get a random move
        // int numRemainingMoves = board.getNumAvailableMoves();
        Random randGen = new Random();
        int randMove; // = randGen.nextInt(9);
        do{
            randMove = randGen.nextInt(9);
        }while(!board.isSpotEmpty(randMove));

        // Make the choice on the board
        board.changeSpace(type, randMove);
    }

    public void giveWin(){
        System.out.print(type.getType());
        System.out.println(" Won!");
    }

    public void giveLose(){
        System.out.print(type.getType());
        System.out.println(" Lost!");
    }

    public void giveTie(){
        System.out.print(type.getType());
        System.out.println(" Tied!");
    }

    public String getPlayerType(){
        return "RandomMove";
    }

    public Player getCopy(Mark mark){
        return new RandomMove(mark);
    }
}
