package entities;

import constants.Constants;

public class Player extends Entity {
    private static final int BULLET_X_OFFSET = 5;

    public Player() {
        Boolean isPlayer = true;
        int xPos = Constants.WIDTH / 2;
        int yPos = Constants.HEIGHT - 60;
        super(xPos, yPos, Constants.PLAYER_IMAGE, Constants.PLAYER_EXPLOSION, isPlayer);

    }

    public void update() {
        this.updateBullets();
        this.updateAnimation();
    }

    @Override
    public void moveLeft() {
        this.x -= this.speed;

    }

    @Override
    public void moveRight() {
        this.x += this.speed;

    }

    public void shoot() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - this.getLastShotTime() > Constants.SHOT_RATE_LIMITER) {
            int bulletStartY = this.y;
            int bulletStartX = (this.x - BULLET_X_OFFSET) + this.width / 2;
            this.getBullets().add(new Bullet(bulletStartX, bulletStartY, speed, true));
            this.setLastShotTime(currentTime);
        }

    }

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
