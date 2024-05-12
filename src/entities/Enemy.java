package entities;

import constants.Constants;
import state.StateMachine;

public class Enemy extends Entity {
    private StateMachine stateMachine;
    // TODO => this should be increased based on difficulty
    private static final int ENEMY_SPEED = 3;
    protected static boolean isStop = false;
    protected static boolean isPlayer = false;

    public Enemy(int x, int y) {
        super(x, y, Constants.ENEMY_ANIMATION_FRAMES, Constants.ENEMY_EXPLOSION, Enemy.isPlayer);
        this.stateMachine = new StateMachine(this);

    }

    public void movement() {
        if (Enemy.isStop) {
            this.moveRight();
        } else {
            this.moveLeft();
        }

    }

    public boolean getIsStop() {
        return Enemy.isStop;
    }

    public void setIsStop(Boolean stop) {
        Enemy.isStop = stop;
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
