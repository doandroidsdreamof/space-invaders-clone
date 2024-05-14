package entities;

import utils.AnimationManager;
import java.awt.image.BufferedImage;

import java.awt.Rectangle;
import java.util.*;

public abstract class Entity {
    protected int x, y, width, height;
    protected long lastShotTime;
    //! isStop => it must be a single source of truth for each instance in order to achieve movement.
    protected AnimationManager animation;
    private Boolean isAlive;
    private List<Bullet> bullets;


    public Entity(int x, int y, BufferedImage[] images, BufferedImage[] explosionImages, Boolean isPlayer) {
        this.x = x;
        this.y = y;
        this.lastShotTime = 0;
        this.isAlive = true;
        this.animation = new AnimationManager(images, explosionImages, isPlayer);
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

    public int getWidth(){
        return this.width;
    }

    public int getX() {
        return this.x;

    }

    public int getY() {
        return this.y;

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

}
