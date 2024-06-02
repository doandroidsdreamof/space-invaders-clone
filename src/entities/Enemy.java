package entities;

import constants.Constants;

public class Enemy extends AbstractEntity {
    private static final int ENEMY_SPEED = 3;
    private static boolean isStop = false; // ! it should be the same across all instances for movement logic
    @SuppressWarnings("unused")
    private int bulletStartX, bulletStartY;

    public Enemy(int x, int y) {
        super(x, y, Constants.ENEMY_ANIMATION_FRAMES, false);
    }

    public void setBullets(int x, int y) {
        this.bulletStartX = x;
        this.bulletStartY = y;
    }

    public void movement() {
        if (Enemy.isStop) {
            this.moveRight();
        } else {
            this.moveLeft();
        }
    }

    @Override
    public void update() {
        this.updateAnimation();
        this.movement();
        this.updateBullets();
    }

    @Override
    public void moveLeft() {
        this.setX(this.getX() - ENEMY_SPEED);
    }

    @Override
    public void moveRight() {
        this.setX(this.getX() + ENEMY_SPEED);
    }

    @Override
    public void shoot() {
        super.shoot(Constants.BULLET_SPEED, Constants.SHOT_RATE_LIMITER_ENEMY, false);
    }

    public boolean getMovementStopped() {
        return isStop;
    }

    public void setMovementStopped(Boolean stop) {
        isStop = stop;
    }

}
