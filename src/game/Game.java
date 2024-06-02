package game;

import ui.GameWindow;

public class Game {
    private GameEngine gameEngine;
    @SuppressWarnings("unused")
    private GameWindow gameWindow;

    public Game() {
        gameEngine = new GameEngine();
        gameWindow = new GameWindow(gameEngine);
        // When a component has focus, it can receive keyboard input.
        gameEngine.requestFocus();
    }
}
