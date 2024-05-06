package game;
import javax.swing.JFrame;
import java.awt.Color;

import constants.Constants;
import utils.Logger;

public class GameWindow {
    private JFrame jframe;
    private Logger logger;

    public GameWindow(GameEngine gamePanel) {
        jframe = new JFrame();
        jframe.setSize(Constants.WIDTH, Constants.HEIGHT);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setResizable(false);
        jframe.setVisible(true);
        logger = new Logger();
        logger.cleanConsole(jframe);

    }

}
