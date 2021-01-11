package Game.Player.Human;

import java.util.Scanner;

import Board.Board;
import Board.Mark.Mark;
import Game.Player.Player;

public class Human implements Player{
    Mark type;

    public Human(Mark mark){
        type = mark;
    }

    public void makeTurn(Board board){
        // Show type of the player
        System.out.print("Type: ");
        System.out.println(type.getType());

        // Show number option to player
        System.out.println("---Options: ---");
        board.showNumOption();

        // Show the board to the player
        System.out.println("---Board: ---");
        board.printState();

        // Ask for input
        Scanner scan = new Scanner(System.in);
        int choice = -1;
        do{
            try {
                System.out.print("Choose an empty square: ");
                choice = scan.nextInt();
            } catch (Exception e) {
                System.out.println(e);
            }
        }while(!board.isSpotEmpty(choice));
        // scan.close();

        // Make the coice on the board
        board.changeSpace(type, choice);
    }
}
