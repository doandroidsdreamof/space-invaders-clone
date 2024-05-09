package entities;

import java.awt.Graphics;
import constants.Constants;
import state.StateMachine;


public class Enemy extends Entity {
    private StateMachine stateMachine;

    public Enemy(int x, int y) {
        Boolean isPlayer = false;
        super(x, y, Constants.ENEMY_ANIMATION_FRAMES, Constants.ENEMY_EXPLOSION, isPlayer);
        this.stateMachine = new StateMachine(this);

    }

    public void update() {
        this.updateAnimation();
        this.stateMachine.update();
    }

    @Override
    public void moveLeft() {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveRight() {
        // TODO Auto-generated method stub
    }

    @Override
    public void shoot() {
        // TODO Auto-generated method stub
    }

    public void draw(Graphics g) {
        if (this.getAliveState()) {
            g.drawImage(this.getCurrentAnimationFrame(), this.getX(), this.getY(), null);

        }
        // ! if not alive init explosion animation
        g.drawImage(this.getCurrentAnimationFrame(), this.getX(), this.getY(), null);

    }
}
