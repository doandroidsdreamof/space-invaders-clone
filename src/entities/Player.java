package entities;

import constants.Constants;

public class Player extends AbstractEntity {
    private static final int BULLET_X_OFFSET = 5;
    private static final int PLAYER_SPEED = 8;
    private static final int X_POS = Constants.WIDTH / 2, Y_POS = Constants.HEIGHT - 60;
    private static boolean isPlayer = true;
    private int tempPosition;

    public Player() {
        super(X_POS, Y_POS, Constants.PLAYER_ANIMATION_FRAMES, isPlayer);
        this.tempPosition = this.getX();

    }

    public void update() {
        this.updateBullets();
        this.updateAnimation();
    }

    @Override
    public void moveLeft() {
        System.out.println(this.getX());

        this.tempPosition -= PLAYER_SPEED;
        this.setX(tempPosition);

    }

    @Override
    public void moveRight() {
        System.out.println(this.getX());

        this.tempPosition += PLAYER_SPEED;
        this.setX(tempPosition);

    }

    public void shoot() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - this.getLastShotTime() > Constants.SHOT_RATE_LIMITER_PLAYER) {
            int bulletStartY = this.getY();
            int bulletStartX = (this.getX() - BULLET_X_OFFSET) + this.getWidth() / 2;
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
