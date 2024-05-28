package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import constants.Constants;
import entities.AbstractEntity;

public class Controller implements KeyListener {
    private AbstractEntity player;
    private static final int PLAYER_RIGHT_BOUNDARY_OFFSET = 60;
    private static final int PLAYER_LEFT_BOUNDARY = 5;
    // * */ keysPressed => simultaneous key handling */
    private Set<Integer> keysPressed = new HashSet<>();

    public Controller(AbstractEntity player) {
        this.player = player; // aggregation
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void resetKeyHash(){
        this.keysPressed.clear();
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
        System.out.println(this.keysPressed);
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
