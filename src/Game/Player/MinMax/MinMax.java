package Game.Player.MinMax;

import java.util.ArrayList;
import java.util.Random;

import Board.Board;
import Board.Mark.Mark;
import Game.Player.Player;

public class MinMax implements Player{
    Mark type;
    Mark opponentMark;

    public MinMax(Mark mark){
        type = mark;
        opponentMark = mark.makeOpponentMark();
    }

    public void makeTurn(Board board) throws Exception{
        // Make a copy of the board
        // Board copyBoard = new Board(board);

        int bestMove = findBestMove(board);

        board.makeMove(type, bestMove);

    }

    public void giveWin(){
        System.out.print(type.getType());
        System.out.println(" Won!");
    }

    public void giveWinQuiet(){
        // Pass
    }

    public void giveLose(){
        System.out.print(type.getType());
        System.out.println(" Lost!");
    }

    public void giveLoseQuiet(){
        // Pass
    }

    public void giveTie(){
        System.out.print(type.getType());
        System.out.println(" Tied!");
    }

    public void giveTieQuiet(){
        // Pass
    }

    public String getPlayerType(){
        return "MinMax";
    }

    public Player getCopy(Mark mark){
        return new MinMax(mark);
    }

    // Private

    private int findBestMove(Board board) throws Exception{
        // Make a list of best moves
        // Both list will act like a list pair, one is the spot and the other is the value of the spot
        ArrayList<Integer> bestMoveList = new ArrayList<Integer>(9);
        int bestVal = Integer.MIN_VALUE;

        // Make every possible move and evaluate the board position of that move
        for(int i = 0; i < 3; ++i){
            for(int j = 0; j < 3; ++j){
                int spot = i*3+j;
                // Only if the spot is available to make, make the move
                if(board.isSpotEmpty(spot)){
                    board.makeMove(type, spot);

                    // If board is done, Evaluate the board State
                    int boardVal;
                    if(board.isDone()){
                        boardVal = evaluateState(board);

                    // If not done yet, keep going until done
                    }else{
                        boardVal = getMinVal(board);
                    }

                    // If list is empty, just put it in the list
                    if(bestMoveList.size() == 0){
                        bestMoveList.add(spot);
                        bestVal = boardVal;

                    // If the best value is the same as what is in the list
                    }else if(bestVal == boardVal){
                        bestMoveList.add(spot);

                    // If the value is greater than the best value, 
                    // remove all items from best move list and add it to that list 
                    // and update the best value
                    }else if(bestVal < boardVal){
                        bestMoveList.clear();
                        bestMoveList.add(spot);
                        bestVal = boardVal;
                    }

                    board.undoMove();
                }
            }
        }

        // Randomly pick one from the list of moves and return that spot
        Random randomGen = new Random();
        int randIndex = randomGen.nextInt(bestMoveList.size());
        int bestMove = bestMoveList.get(randIndex);
        return bestMove;
    }

    private int getMaxVal(Board board) throws Exception{
        int maxVal = Integer.MIN_VALUE;
        // for every possible opponent move, evaluate the board for each move
        for(int i = 0; i < 3; ++i){
            for(int j = 0; j < 3; ++j){
                int spot = i*3+j;
                if(board.isSpotEmpty(spot)){
                    board.makeMove(type, spot);

                    // If board is done evaluate the state
                    int boardVal;
                    if(board.isDone()){
                        boardVal = evaluateState(board);

                    // If not done yet, keep going until done
                    }else{
                        boardVal = getMinVal(board);
                    }

                    // If the board value is greater than the max value, replace it
                    if(maxVal < boardVal){
                        maxVal = boardVal;
                    }

                    board.undoMove();
                }
            }
        }

        return maxVal;
    }

    private int getMinVal(Board board) throws Exception{
        int minVal = Integer.MAX_VALUE;

        // for every possible opponent move, evaluate the board for each move
        for(int i = 0; i < 3; ++i){
            for(int j = 0; j < 3; ++j){
                int spot = i*3+j;
                // if spot is available to make, make that move
                if(board.isSpotEmpty(spot)){
                    board.makeMove(opponentMark, spot);

                    // If board is done evaluate the state
                    int boardVal;
                    if(board.isDone()){
                        boardVal = evaluateState(board);

                    // If not done yet, keep going until done
                    }else{
                        boardVal = getMaxVal(board);
                    }

                    // If the board value is less than the min value, replace it
                    if(minVal > boardVal){
                        minVal = boardVal;
                    }

                    board.undoMove();
                }
            }
        }

        return minVal;
    }

    private int evaluateState(Board board){
        // this multiplyer will be -1 if player loses,
        // 0 if tie, and 1 if the player wins
        // And will be multiplied by the amount of moves left plus 1
        int resultMultiplyer;
        Mark winnerMark = board.getWinningMark();
        if(winnerMark.isSame(type)){
            resultMultiplyer = 1;
        }else if(winnerMark.isSame(opponentMark)){
            resultMultiplyer = -1;
        }else{
            resultMultiplyer = 0;
            return 0;
        }

        // Get the remaining moves left and add one to it
        int score = board.getNumAvailableMoves() + 1;

        return resultMultiplyer * score;
    }
}
