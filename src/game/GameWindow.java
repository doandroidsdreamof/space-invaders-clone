package game;

import javax.swing.JFrame;
import constants.Constants;

public class GameWindow {
    private JFrame jframe;

    public GameWindow(GameEngine gamePanel) {
        jframe = new JFrame();
        jframe.setSize(Constants.WIDTH, Constants.HEIGHT);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setTitle("space-invaders");
        jframe.setResizable(false);
        jframe.setVisible(true);

    }

}
