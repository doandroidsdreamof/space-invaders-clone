package entities;

import constants.Constants;

public class Player extends AbstractEntity {
    private static final int PLAYER_SPEED = 8;
    private int tempPosition;

    public Player() {
        super(Constants.Game.WIDTH / 2, Constants.Game.HEIGHT - 60, Constants.PLAYER_ANIMATION_FRAMES, true);
        this.tempPosition = this.getX();
    }

    @Override
    public void update() {
        this.updateBullets();
        this.updateAnimation();
    }

    @Override
    public void moveLeft() {
        this.tempPosition -= PLAYER_SPEED;
        this.setX(tempPosition);
    }

    @Override
    public void moveRight() {
        this.tempPosition += PLAYER_SPEED;
        this.setX(tempPosition);
    }

    @Override
    public void shoot() {
        super.shoot(Constants.Bullet.SPEED, Constants.Player.SHOT_RATE_LIMITER, true);
    }
}
