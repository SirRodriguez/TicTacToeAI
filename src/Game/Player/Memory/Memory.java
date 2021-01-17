package Game.Player.Memory;

import Board.Board;
import Board.Mark.Mark;
import Game.Player.Player;

public class Memory implements Player{
    Mark type;

    public Memory(Mark mark){
        type = mark;
    }

    public void makeTurn(Board board) throws Exception{
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
        return "Memory";
    }

    public Player getCopy(Mark mark){
        return new Memory(mark);
    }
}
