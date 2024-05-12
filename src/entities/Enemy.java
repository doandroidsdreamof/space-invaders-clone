package entities;

import constants.Constants;
import state.StateMachine;

public class Enemy extends Entity {
    private StateMachine stateMachine;
    //TODO => this should be increased based on difficulty
    private static final int ENEMY_SPEED = 3;

    public Enemy(int x, int y) {
        Boolean isPlayer = false;
        super(x, y, Constants.ENEMY_ANIMATION_FRAMES, Constants.ENEMY_EXPLOSION, isPlayer);
        this.stateMachine = new StateMachine(this);

    }

    public void movement() {
        if (Entity.isStop) {
            this.moveRight();
        } else {
            this.moveLeft();
        }

    }

    public void update() {
        this.updateAnimation();
        this.stateMachine.update();
        this.movement();

    }

    @Override
    public void moveLeft() {
        this.x = this.x - Enemy.ENEMY_SPEED;

    }

    @Override
    public void moveRight() {
        this.x = this.x + Enemy.ENEMY_SPEED;

    }

    @Override
    public void shoot() {
        // TODO Auto-generated method stub
    }

}
