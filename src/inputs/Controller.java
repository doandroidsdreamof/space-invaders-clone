package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import constants.Constants;
import entities.Entity;

public class Controller implements KeyListener {
    private Entity player;
    private static final int PLAYER_RIGHT_BOUNDARY_OFFSET = 60; // Depends on player sprite width
    private static final int PLAYER_LEFT_BOUNDARY = 5;

    public Controller(Entity player) {
        this.player = player; // aggregation
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && player.getX() + PLAYER_RIGHT_BOUNDARY_OFFSET < Constants.WIDTH) {
            player.moveRight();

        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT && player.getX() > PLAYER_LEFT_BOUNDARY) {
            player.moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.shoot();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
