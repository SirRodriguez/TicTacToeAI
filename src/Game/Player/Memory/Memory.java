package Game.Player.Memory;

import java.util.ArrayList;

import Board.Board;
import Board.Mark.Mark;
import Game.Player.Player;

public class Memory implements Player{
    Mark type;

    // Memory of each board state
    // An array of board states
    // Oarganized as a list of lists that are indexed by number of moves
    // So: boardStateMemories.get(0) means all board states with no moves
    ArrayList<ArrayList<Mark[][]>> boardStateMemories;

    public Memory(Mark mark){
        type = mark;

        // Load memory
        
    }

    // There will be a code for the spots in the board
    // And their likley hood of winning, losing, etc.
    // Winning: 0
    // Losing:  1
    // Tie:     2
    // Unknown: 3
    // X:       4
    // O:       5
    public void makeTurn(Board board) throws Exception{
        // Copy Board History
        // copyBoardHistory(board);

        // Get board state and check if it is in memory
        
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
