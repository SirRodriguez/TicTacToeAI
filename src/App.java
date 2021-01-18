import java.io.File;
import java.io.FileReader;

import Board.Board;
import Board.Mark.None.None;
import Board.Mark.O.O;
import Board.Mark.X.X;
import Game.Game;
import Game.Player.Memory.Memory;
import Game.Player.MinMax.MinMax;
import Game.Player.RandomMove.RandomMove;

public class App {
    public static void main(String[] args) throws Exception {
        // Default game
        // Game game = new Game();

        // Run random AI's against each other
        // Game game = new Game(new RandomMove(new X()), new RandomMove(new O()));

        // Run MinMax against random as first
        // Game game = new Game(new MinMax(new X()), new RandomMove(new O()));

        // Run MinMax against random as Second
        // Game game = new Game(new RandomMove(new X()), new MinMax(new O()));

        // Run MinMax against itself
        // Game game = new Game(new MinMax(new X()), new MinMax(new O()));

        // Default Single run
        // game.runGame();

        // Run a number of times
        // game.runGame(500);


        // Test Board
        // Board board = new Board();

        // board.makeMove(new X(), 0);
        // board.makeMove(new O(), 5);

        // board.printState();

        // board.undoMove();

        // board.makeMove(new O(), 4);

        // board.printState();

        // board.undoMove();

        // board.printState();

        // String fileMemoryName = "src\\Game\\Player\\Memory\\FileMemory.txt";
        // File fileMemory = new File(fileMemoryName);

        // if(fileMemory.createNewFile()){
        //     System.out.println("File created: " + fileMemory.getName());
        // }else{
        //     System.out.println("File already exists.");
        // }

        // // Iterate through the file and get all the data from it
        // FileReader fileMemoryReader = null;
        // try {
        //     fileMemoryReader = new FileReader(fileMemoryName);
        // } catch (Exception e) {
        //     System.out.println(e);
        //     System.exit(1);
        // }

        // int ch;
        // while((ch=fileMemoryReader.read()) != -1){
        //     System.out.print((char) ch);
        // }

        Memory mem = new Memory(new None());
        mem.printMem();

    }
}
