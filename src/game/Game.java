package game;

public class Game {
    private GameEngine gameEngine;
    private GameWindow gameWindow;

    public Game() {
        gameEngine = new GameEngine();
        gameWindow = new GameWindow(gameEngine);
        // When a component has focus, it can receive keyboard input.
        gameEngine.requestFocus();
    }
}
