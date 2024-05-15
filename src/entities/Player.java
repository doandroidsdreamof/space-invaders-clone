package entities;

import constants.Constants;

public class Player extends Entity {
    private static final int BULLET_X_OFFSET = 5;
    private static final int PLAYER_SPEED = 8;
    private static final int X_POS = Constants.WIDTH / 2, Y_POS = Constants.HEIGHT - 60;
    protected static boolean isPlayer = true;

    public Player() {
        super(X_POS, Y_POS, Constants.PLAYER_ANIMATION_FRAMES, Constants.PLAYER_EXPLOSION, isPlayer);

    }

    public void update() {
        this.updateBullets();
        this.updateAnimation();
    }

    @Override
    public void moveLeft() {
        this.x -= PLAYER_SPEED;

    }

    @Override
    public void moveRight() {
        this.x +=  PLAYER_SPEED;

    }

    public void shoot() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - this.getLastShotTime() > Constants.SHOT_RATE_LIMITER_PLAYER) {
            int bulletStartY = this.y;
            int bulletStartX = (this.x - BULLET_X_OFFSET) + this.width / 2;
            this.getBullets().add(new Bullet(bulletStartX, bulletStartY, Constants.BULLET_SPEED, true));
            this.setLastShotTime(currentTime);
        }

    }

    // TODO ConcurrentModificationException => use iterator
    public void updateBullets() {
        for (int i = 0; i < getBullets().size(); i++) {
            Bullet currentBullet = getBullets().get(i);
            currentBullet.update();
            if (currentBullet.getY() < 0) {
                getBullets().remove(i);
                i--;
            }

        }
    }

}
