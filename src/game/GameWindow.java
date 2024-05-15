package game;

import javax.swing.JFrame;
import constants.Constants;

public class GameWindow {
    private JFrame jframe;

    public GameWindow(GameEngine gameEngine) {
        jframe = new JFrame();
        jframe.setSize(Constants.WIDTH, Constants.HEIGHT);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gameEngine);
        jframe.setTitle(Constants.GAME_TITLE);
        jframe.setResizable(false);
        jframe.setVisible(true);

    }

}
