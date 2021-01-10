package Board;

import Mark.Mark;
import Mark.None.None;

public class Board {
    Mark[][] boardState;

    public Board(){
        // Initialize the board state
        boardState = new Mark[3][3];
        for(int i = 0; i < 3; ++i){
            for(int j = 0; j < 3; ++j){
                boardState[i][j] = new None();
            }
        }
    }

    public void printState(){
        for(int i = 0; i < 3; ++i){
            System.out.println("-------");
            System.out.print("|");
            for(int j = 0; j < 3; ++j){
                System.out.print(boardState[i][j].getType());
                System.out.print('|');
            }
            System.out.println("");
        }
        System.out.println("-------");
    }
}
