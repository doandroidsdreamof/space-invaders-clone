package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import constants.Constants;
import entities.Player;
import interfaces.Entity;

public class Controller implements KeyListener {
    private Entity player;

    public Controller(Entity player) {
        this.player = player; // aggregation
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // System.out.println(e.getKeyCode());

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && player.getX() + 60 < Constants.WIDTH) {
            // System.out.println(player.getX());
            player.moveRight();

        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT && player.getX() > 5) {
            player.moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            // System.out.println("fire");
            player.shoot();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // System.out.println(e.getKeyCode());
    }

}
