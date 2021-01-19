package Game.Player.Memory;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

import Board.Board;
import Board.Mark.Mark;
import Board.Mark.O.O;
import Board.Mark.X.X;
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
    Mark[][] boardState;
    int[] moveHistory;
    int movesMade;

    Board copyBoard;

    public Memory(Mark mark){
        type = mark;

        // Initialize boardState
        boardState = new Mark[3][3];

        // Initialize Move history
        moveHistory = new int[9];

        // Initialize copy board
        copyBoard = new Board();

        // Load memory
        loadMemory();
    }

    // There will be a code for the spots in the board
    // And their likley hood of winning, losing, etc.
    // Winning: 0
    // Losing:  1
    // Tie:     2
    // Unknown: 3
    // Self mark:4
    // Opposing mark:5
    // Whether the player is X or O, the reference will be changed to the point of view as X
    public void makeTurn(Board board) throws Exception{
        // Copy Board History
        // copyBoardHistory(board);
        copyBoard.copyFrom(board);

        // Get board state and check if it is in memory
        Integer[][] boardStateMoves = getBoardStateFromMem(board);

        // --- Analyze and make a move

        // Create the arrays for holding moves spots
        ArrayList<Integer> winning = new ArrayList();
        ArrayList<Integer> losing = new ArrayList();
        ArrayList<Integer> tie = new ArrayList();
        ArrayList<Integer> unknown = new ArrayList();
        ArrayList<Integer> x = new ArrayList();
        ArrayList<Integer> o = new ArrayList();

        // Add the spots to the corresponding lists
        for(int i = 0; i < 3; ++i){
            for(int j = 0; j < 3; ++j){
                int spot = i*3+j;
                switch (boardStateMoves[i][j]) {
                    case 0:
                        winning.add(spot);
                        break;
                    
                    case 1:
                        losing.add(spot);
                        break;

                    case 2:
                        tie.add(spot);
                        break;

                    case 3:
                        unknown.add(spot);
                        break;

                    case 4:
                        x.add(spot);
                        break;

                    case 5:
                        o.add(spot);
                        break;
                    default:
                        break;
                }
            }
        }

        Random randGen = new Random();

        // Unknown spots will be picked first in order to learn new plays
        if(!unknown.isEmpty()){
            // Pick a random spot on the board
            int randMove = randGen.nextInt(unknown.size());
            board.makeMove(type, unknown.get(randMove));
            copyBoard.makeMove(type, unknown.get(randMove));

        // Then pick winning moves
        }else if(!winning.isEmpty()){
            int randMove = randGen.nextInt(winning.size());
            board.makeMove(type, winning.get(randMove));
            copyBoard.makeMove(type, winning.get(randMove));

        // Then pick tie moves
        }else if(!tie.isEmpty()){
            int randMove = randGen.nextInt(tie.size());
            board.makeMove(type, tie.get(randMove));
            copyBoard.makeMove(type, tie.get(randMove));

        // Then pick remaining losing moves
        }else if(!losing.isEmpty()){
            int randMove = randGen.nextInt(losing.size());
            board.makeMove(type, losing.get(randMove));
            copyBoard.makeMove(type, losing.get(randMove));

        }else{
            throw new Exception("No available moves exist for Memory player");
        }
        
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
        copyBoardState(board);
    }

    private void copyBoardState(Board board){
        for(int i = 0; i < 3; ++i){
            for(int j = 0; j < 3; ++j){
                boardState[i][j] = board.getMarkOnSpot(i, j).getCopy();
            }
        }
    }

    // ArrayList<Integer[][]>[] boardStateMemories;
    // returns board state from memory and creates a new one if none exists
    private Integer[][] getBoardStateFromMem(Board board){
        // Look through all the board states with the same moves
        // as board to find the one with the state
        int numMoves = 9 - board.getNumAvailableMoves();
        for(int i = 0; i < boardStateMemories[numMoves].size(); ++i){
            if(hasSameBoardState(board, boardStateMemories[numMoves].get(i))){
                return boardStateMemories[numMoves].get(i);
            }
        }

        // If not in memory
        // create a new one and add it to memory
        return createNewBoardMem(board);
    }

    private boolean hasSameBoardState(Board board, Integer[][] boardStateMem){
        // Look through all spots and check if they are the same
        for(int i = 0; i < 3; ++i){
            for(int j = 0; j < 3; ++j){
                if(!sameMarkWithMem(board.getMarkOnSpot(i,j), boardStateMem[i][j])){
                    return false;
                }
            }
        }

        return true;
    }

    // There will be a code for the spots in the board
    // And their likley hood of winning, losing, etc.
    // Winning: 0
    // Losing:  1
    // Tie:     2
    // Unknown: 3
    // Self mark:4
    // Opposing mark:5
    // Whether the player is X or O, the reference will be changed to the point of view as X
    private boolean sameMarkWithMem(Mark mark, int memCode){
        if(mark.isSame(type) && memCode == 4){
            return true;
        }else if(mark.isOpposing(type) && memCode == 5){
            return true;
        }else{
            return false;
        }
    }

    // ArrayList<Integer[][]>[] boardStateMemories;
    private Integer[][] createNewBoardMem(Board board){
        Integer[][] boardMem = new Integer[3][3];

        for(int i = 0; i < 3; ++i){
            for(int j = 0; j < 3; ++j){
                // If the spot is same
                if(board.getMarkOnSpot(i, j).isSame(type)){
                    boardMem[i][j] = 4;
                // If spot is opposing
                }else if(board.getMarkOnSpot(i, j).isOpposing(type)){
                    boardMem[i][j] = 5;
                // If it is None
                }else{
                    boardMem[i][j] = 3;
                }
            }
        }

        // Add it to memory
        int numMoves = 9 - board.getNumAvailableMoves();
        boardStateMemories[numMoves].add(boardMem);

        return boardMem;
    }
}
