package entities;

import constants.Constants;
import state.StateMachine;

public class Enemy extends Entity {
    private StateMachine stateMachine;
    int xPos;
    boolean direction = false;

    public Enemy(int x, int y) {
        Boolean isPlayer = false;
        super(x, y, Constants.ENEMY_ANIMATION_FRAMES, Constants.ENEMY_EXPLOSION, isPlayer);
        this.stateMachine = new StateMachine(this);

    }

    // TODO overlapping but
    public void movement() {
        xPos = direction ? this.x + 10 : this.x - 10;
        if (xPos < 0) {
            xPos = 0;
        } else if (xPos > Constants.WIDTH - 60) {
            xPos = Constants.WIDTH - 60;
        }
        this.x = xPos;
    }

    public void update() {
        this.updateAnimation();
        this.stateMachine.update();
        //this.movement();

    }

    @Override
    public void moveLeft() {
        this.x -= 10;

    }

    @Override
    public void moveRight() {
        this.x += 10;

    }

    @Override
    public void shoot() {
        // TODO Auto-generated method stub
    }

}
