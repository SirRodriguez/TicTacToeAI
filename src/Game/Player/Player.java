package Game.Player;

import Board.Board;
import Board.Mark.Mark;

public interface Player {
    public void makeTurn(Board board) throws Exception;
    public void giveWin();
    public void giveWinQuiet();
    public void giveLose();
    public void giveLoseQuiet();
    public void giveTie();
    public void giveTieQuiet();
    public String getPlayerType();
    public Player getCopy(Mark mark);
}
