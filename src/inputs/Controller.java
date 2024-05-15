package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import constants.Constants;
import entities.Entity;

public class Controller implements KeyListener {
    private Entity player;
    private static final int PLAYER_RIGHT_BOUNDARY_OFFSET = 60;
    private static final int PLAYER_LEFT_BOUNDARY = 5;
    // * */ keysPressed => simultaneous key handling */
    private Set<Integer> keysPressed = new HashSet<>();

    public Controller(Entity player) {
        this.player = player; // aggregation
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keysPressed.remove(e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keysPressed.add(e.getKeyCode());
        this.handleMovement();
        this.handleAction();

    }

    // TODO controller simultaneous bug
    public void handleMovement() {
        if (keysPressed.contains(KeyEvent.VK_RIGHT) && player.getX() + PLAYER_RIGHT_BOUNDARY_OFFSET < Constants.WIDTH) {
            player.moveRight();
        }

        if (keysPressed.contains(KeyEvent.VK_LEFT) && player.getX() > PLAYER_LEFT_BOUNDARY) {
            player.moveLeft();
        }
    }

    public void handleAction() {
        if (keysPressed.contains(KeyEvent.VK_SPACE)) {
            player.shoot();

        }
    }

}
