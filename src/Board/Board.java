package Board;

import Board.Mark.Mark;
import Board.Mark.None.None;

public class Board {
    Mark[][] boardState;
    int[] moveHistory;
    int movesMade;

    public Board(){
        // Initialize the board state
        boardState = new Mark[3][3];
        resetBoard();

        // Initialize board history
        moveHistory = new int[9];
        resetMoveHistory();

        movesMade = 0;
    }

    public Board(Board otherBoard){
        boardState = new Mark[3][3];
        // Copy each of the marks of other boardState to this one
        for(int i = 0; i < 3; ++i){
            for(int j = 0; j < 3; ++j){
                boardState[i][j] = otherBoard.boardState[i][j].getCopy();
            }
        }

        moveHistory = new int[9];
        // copy the move history over
        for(int i = 0; i < 9; ++i){
            moveHistory[i] = otherBoard.moveHistory[i];
        }

        movesMade = 0;
    }

    public void printState(){
        for(int i = 0; i < 3; ++i){
            System.out.println("--------------");
            System.out.print("|");
            for(int j = 0; j < 3; ++j){
                System.out.print(' ' + boardState[i][j].getType() + ' ');
                System.out.print('|');
            }
            System.out.println("");
        }
        System.out.println("--------------");
    }

    public void showNumOption(){
        for(int i = 0; i < 3; ++i){
            System.out.println("--------------");
            System.out.print("|");
            for(int j = 0; j < 3; ++j){
                System.out.print(' ');
                System.out.print(i*3+j);
                System.out.print(' ');
                System.out.print('|');
            }
            System.out.println("");
        }
        System.out.println("--------------");
    }

    public boolean isDone(){
        // Check if rows have 3 in a row
        if(rowHaveThreeInRow()){
            return true;

        // Check if columns have 3 in a row
        }else if(colHaveThreeInRow()){
            return true;

        // Check if diagonals have 3 in row
        }else if(diaHaveThreeInRow()){
            return true;

        // Check if board in full
        }else if(isBoardFull()){
            return true;
        
        // Then return false
        }else{
            return false;
        }
    }

    public boolean isSpotEmpty(int spot){
        // If it is out of bounds
        if(spot < 0 || spot > 8){
            return false;
        }

        int i = spot / 3;
        int j = spot % 3;
        return !boardState[i][j].isNotNone();
    }

    public void makeMove(Mark mark, int spot) throws Exception{
        // If it is out of bounds
        if(spot < 0 || spot > 8){
            return;
        }

        int i = spot / 3;
        int j = spot % 3;
        if(!boardState[i][j].isNotNone()){
            // Mark it on the board
            boardState[i][j] = mark.getCopy();

            // Mark it on history
            moveHistory[movesMade] = spot;
            ++movesMade;
        }else{
            throw new Exception("Move was made on an occupied space");
        }
    }

    public void undoMove(){
        // Decriment moves made
        --movesMade;

        // Remove the move from the board
        int i = moveHistory[movesMade] / 3;
        int j = moveHistory[movesMade] % 3;
        boardState[i][j] = new None();
    }

    // Mark of None if it is a tie
    public Mark getWinningMark(){
        Mark winner;

        // Check the rows
        winner = getRowWinner();
        if(winner.isNotNone()){
            return winner;
        }

        // Check columns
        winner = getColWinner();
        if(winner.isNotNone()){
            return winner;
        }

        // Check diagonals
        winner = getDiagWinner();
        if(winner.isNotNone()){
            return winner;
        }

        // No winner
        return new None();
    }

    public int getNumAvailableMoves(){
        // int avilMoves = 0;
        // for(int i = 0; i < 3; ++i){
        //     for(int j = 0; j < 3; ++j){
        //         if(!boardState[i][j].isNotNone()){
        //             ++avilMoves;
        //         }
        //     }
        // }
        // return avilMoves;

        return 9 - movesMade;
    }

    public void reset(){
        resetBoard();
        resetMoveHistory();
        movesMade = 0;
    }

    // Private methods

    private void resetBoard(){
        for(int i = 0; i < 3; ++i){
            for(int j = 0; j < 3; ++j){
                boardState[i][j] = new None();
            }
        }
    }

    private void resetMoveHistory(){
        for(int i = 0; i < 9; ++i){
            moveHistory[i] = 0;
        }
    }

    private boolean rowHaveThreeInRow(){
        // For each row it checks if it is not none and they are all the same
        for(int i = 0; i < 3; ++i){
            if(
                boardState[i][0].isNotNone() &&
                boardState[i][0].isSame(boardState[i][1]) && 
                boardState[i][1].isSame(boardState[i][2])
            ){
                return true;
            }
        }

        return false;
    }

    private Mark getRowWinner(){
        // For each row it checks if it is not none and they are all the same
        for(int i = 0; i < 3; ++i){
            if(
                boardState[i][0].isNotNone() &&
                boardState[i][0].isSame(boardState[i][1]) && 
                boardState[i][1].isSame(boardState[i][2])
            ){
                return boardState[i][0].getCopy();
            }
        }

        return new None();
    }

    private boolean colHaveThreeInRow(){
        // For each column it checks if it is not none and they are all the same
        for(int j = 0; j < 3; ++j){
            if(
                boardState[0][j].isNotNone() &&
                boardState[0][j].isSame(boardState[1][j]) &&
                boardState[1][j].isSame(boardState[2][j])
            ){
                return true;
            }
        }

        return false;
    }

    private Mark getColWinner(){
        // For each column it checks if it is not none and they are all the same
        for(int j = 0; j < 3; ++j){
            if(
                boardState[0][j].isNotNone() &&
                boardState[0][j].isSame(boardState[1][j]) &&
                boardState[1][j].isSame(boardState[2][j])
            ){
                return boardState[0][j].getCopy();
            }
        }

        return new None();
    }

    private boolean diaHaveThreeInRow(){
        // if center is None, its false
        if(!boardState[1][1].isNotNone()){
            return false;
        }

        // Check the top left to bottom right diagonal
        if(
            boardState[0][0].isSame(boardState[1][1]) &&
            boardState[2][2].isSame(boardState[1][1])
        ){
            return true;
        }

        // Check the top right to bottom left diagonal
        if(
            boardState[0][2].isSame(boardState[1][1]) &&
            boardState[2][0].isSame(boardState[1][1])
        ){
            return true;
        }

        return false;
    }

    private Mark getDiagWinner(){
        // if center is None, its false
        if(!boardState[1][1].isNotNone()){
            return new None();
        }

        // Check the top left to bottom right diagonal
        if(
            boardState[0][0].isSame(boardState[1][1]) &&
            boardState[2][2].isSame(boardState[1][1])
        ){
            return boardState[1][1].getCopy();
        }

        // Check the top right to bottom left diagonal
        if(
            boardState[0][2].isSame(boardState[1][1]) &&
            boardState[2][0].isSame(boardState[1][1])
        ){
            return boardState[1][1].getCopy();
        }

        return new None();
    }

    private boolean isBoardFull(){
        // // Check if all the spaces are not None
        // for(int i = 0; i < 3; ++i){
        //     for(int j = 0; j < 3; ++j){
        //         if(!boardState[i][j].isNotNone()){
        //             return false;
        //         }
        //     }
        // }

        // return true;

        return movesMade >= 9;
    }
}
