package Game.Player;

import Board.Board;
import Board.Mark.Mark;

public interface Player {
    public void makeTurn(Board board);
    public void giveWin();
    public void giveLose();
    public void giveTie();
    public String getPlayerType();
    public Player getCopy(Mark mark);
}
