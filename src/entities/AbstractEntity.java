package entities;

import java.awt.image.BufferedImage;

import java.awt.Rectangle;
import java.util.*;

import constants.Constants;
import managers.AnimationManager;

public abstract class AbstractEntity {
    private int x, y, width, height;
    private long lastShotTime;
    private AnimationManager animation;
    private Boolean isAlive;
    private List<Bullet> bullets;

    public AbstractEntity(int x, int y, BufferedImage[] images, Boolean isPlayer) {
        this.x = x;
        this.y = y;
        this.lastShotTime = 0;
        this.isAlive = true;
        this.animation = new AnimationManager(images, isPlayer);
        this.width = this.animation.getWidth();
        this.height = this.animation.getHeight();
        this.bullets = new ArrayList<>();
    }

    public abstract void update();

    public abstract void moveLeft();

    public abstract void moveRight();

    public abstract void shoot();

    public long getLastShotTime() {
        return this.lastShotTime;

    }

    public void updateAnimation() {
        this.animation.updateFrame();

    }

    public BufferedImage getCurrentAnimationFrame() {
        return this.animation.getCurrentFrame(this.isAlive);

    }

    // TODO state pattern refactoring
    public void setAliveState(Boolean isAlive) {
        this.isAlive = isAlive;

    }

    public Boolean getAliveState() {
        return this.isAlive;
    }

    public void setLastShotTime(long lastTime) {
        this.lastShotTime = lastTime;

    }

    public void setX(int newPosX) {
        this.x = newPosX;

    }

    public void setY(int newPosY) {
        this.y = newPosY;
    }

    public int getX() {
        return this.x;

    }

    public int getY() {
        return this.y;

    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public List<Bullet> getBullets() {
        return this.bullets;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.getX(), this.getY(), animation.getWidth(), animation.getHeight());
    }

    public Bullet getLastBullet() {
        if (bullets.size() > 0) {
            return bullets.getLast();
        }
        return null;
    }

    public void updateBullets() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet currentBullet = bullets.get(i);
            currentBullet.update();
            if (currentBullet.getY() < 0 || currentBullet.getY() > Constants.Game.HEIGHT) {
                bullets.remove(i);
                i--;
            }
        }
    }

    public void shoot(int bulletSpeed, int shotRateLimiter, boolean isPlayerBullet) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShotTime > shotRateLimiter) {
            int bulletStartY = this.y;
            int bulletStartX = (this.x - 5) + this.width / 2;
            bullets.add(new Bullet(bulletStartX, bulletStartY, bulletSpeed, isPlayerBullet));
            lastShotTime = currentTime;
        }
    }

}
