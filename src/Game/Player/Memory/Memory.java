package Game.Player.Memory;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import Board.Board;
import Board.Mark.Mark;
import Game.Player.Player;

public class Memory implements Player{
    Mark type;

    // Memory of each board state
    // An array of board states
    // Oarganized as an array of lists that are indexed by number of moves
    // So: boardStateMemories[0] means all board states with no moves
    // ArrayList<ArrayList<Integer[][]>> boardStateMemories;
    ArrayList<Integer[][]>[] boardStateMemories;

    // History of the board
    int[] moveHistory;
    int movesMade;

    public Memory(Mark mark){
        type = mark;

        // Initialize Move history
        moveHistory = new int[9];

        // Load memory
        loadMemory();
    }

    // There will be a code for the spots in the board
    // And their likley hood of winning, losing, etc.
    // Winning: 0
    // Losing:  1
    // Tie:     2
    // Unknown: 3
    // X:       4
    // O:       5
    // Whether the player is X or O, the reference will be changed to the point of view as X
    public void makeTurn(Board board) throws Exception{
        // Copy Board History
        copyBoardHistory(board);

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

    public void printMem(){
        for(int m = 0; m < 10; ++m){
            System.out.print('m');
            System.out.println(m);
            if(boardStateMemories[m] != null){
                for(int b = 0; b < boardStateMemories[m].size(); ++b){
                    for(int i = 0; i < 3; ++i){
                        for(int j = 0; j < 3; ++j){
                            System.out.print(boardStateMemories[m].get(b)[i][j]);
                        }
                    }
                    System.out.println();
                }
            }
        }
    }

    // Private

    private void loadMemory(){
        // Initialize the array list to size of 10, 10 possible move positions
        initializeBoardStateMemories();
        // boardStateMemories = new ArrayList[10];

        // Check if the file memory exists
        // If not make one
        String fileMemoryName = "src\\Game\\Player\\Memory\\FileMemory.txt";
        File fileMemory = new File(fileMemoryName);
        try {
            fileMemory.createNewFile();
        } catch (Exception e) {
            System.out.println(e);
        }

        // Iterate through the file and get all the data from it
        // File will be in the form
        // m1
        // b111111111
        // ...
        // Where the line m 1 will stand for the number of moves
        // the board state will have
        // the following lines with be will have the board states leading with b
        FileReader fileMemoryReader = null;
        try {
            fileMemoryReader = new FileReader(fileMemoryName);
            int numMoves = 0;
            int ch;
            while((ch=fileMemoryReader.read()) != -1){
                switch ((char) ch) {
                    // Update number of moves
                    case 'm':
                        if((ch=fileMemoryReader.read()) != -1){
                            numMoves = ch - '0';
                        }
                        break;
                    // Add board states
                    case 'b':
                        // copy the board state from file to the variable
                        Integer[][] boardState = new Integer[3][3];
                        for(int i = 0; i < 3; ++i){
                            for(int j = 0; j < 3; ++j){
                                if((ch=fileMemoryReader.read()) != -1){
                                    boardState[i][j] = ((char) ch) - '0';
                                }
                            }
                        }
                        // Add it to the memories
                        boardStateMemories[numMoves].add(boardState);
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }

    }

    private void initializeBoardStateMemories(){
        boardStateMemories = new ArrayList[10];
        for(int i = 0; i < 10; ++i){
            boardStateMemories[i] = new ArrayList<Integer[][]>();
        }
    }

    private void copyBoardHistory(Board board){
        movesMade = 9 - board.getNumAvailableMoves();
        board.copyMoveHistoryOver(moveHistory);
    }

    
}
