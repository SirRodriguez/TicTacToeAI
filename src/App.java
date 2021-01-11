import Board.Mark.O.O;
import Board.Mark.X.X;
import Game.Game;
import Game.Player.RandomMove.RandomMove;

public class App {
    public static void main(String[] args) throws Exception {
        // Default game
        // Game game = new Game();

        // Run random AI's against each other
        Game game = new Game(new RandomMove(new X()), new RandomMove(new O()));

        // Default Single run
        game.runGame();

        // Run a number of times
        // game.runGame(100000000);
    }
}
