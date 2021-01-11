package Game.Player;

import Board.Board;

public interface Player {
    public void makeTurn(Board board);
    public void giveWin();
    public void giveLose();
    public void giveTie();
}
