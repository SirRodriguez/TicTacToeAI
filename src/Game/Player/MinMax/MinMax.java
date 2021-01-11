package Game.Player.MinMax;

import Board.Board;
import Board.Mark.Mark;
import Game.Player.Player;

public class MinMax implements Player{
    Mark type;

    public MinMax(Mark mark){
        type = mark;
    }

    public void makeTurn(Board board){
        // TODO
    }

    public void giveWin(){
        // TODO
    }

    public void giveWinQuiet(){
        // TODO
    }

    public void giveLose(){
        // TODO
    }

    public void giveLoseQuiet(){
        // TODO
    }

    public void giveTie(){
        // TODO
    }

    public void giveTieQuiet(){
        // TODO
    }

    public String getPlayerType(){
        return "MinMax";
    }

    public Player getCopy(Mark mark){
        return new MinMax(mark);
    }
}
