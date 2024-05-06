package entities;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Rectangle;

import constants.Constants;
import interfaces.Entity;
import utils.AnimationManager;

public class Player implements Entity {
    private List<Bullet> bullet;
    private AnimationManager animation;
    private Boolean isAlive;
    private int width, height;
    private int speed = 20;
    private int xPos, yPos;
    private long lastShotTime; // to keep track last shot

    // TODO factory design pattern
    public Player(BufferedImage[] images, BufferedImage[] explosionImage) {
        this.xPos = Constants.WIDTH / 2;
        this.yPos = Constants.HEIGHT - 60;
        this.isAlive = true;
        this.animation = new AnimationManager(images, explosionImage, true);
        this.width = animation.getWidth(); // TODO code there is duplication here and enemy class
        this.height = animation.getHeight();
        this.bullet = new ArrayList<Bullet>();
        this.lastShotTime = 0;
    }

    public void setAliveState(Boolean state) {
        this.isAlive = state;
    }

    public Boolean getAliveState() {
        return this.isAlive;
    }

    public void update() {
        this.animation.updateFrame();
    }

    public int getX() {
        return this.xPos;
    }

    public int getY() {
        return this.yPos;
    }

    public void moveLeft() {
        this.xPos -= speed;

    }

    public void moveRight() {
        this.xPos += speed;

    }

    public Bullet getLastBullet() {
        if (bullet.size() > 0) {
            return bullet.getLast();
        }
        return null;
    }

    public void shoot() {
        long currentTime = System.currentTimeMillis();
        // System.out.println(currentTime - lastShotTime);
        if (currentTime - lastShotTime > Constants.SHOT_RATE_LIMITER) {
            int bulletStartY = yPos;
            int bulletStartX = (xPos - 6) + this.width / 2;
            this.bullet.add(new Bullet(bulletStartX, bulletStartY, speed, true));
            lastShotTime = currentTime;
        }

    }

    public List<Bullet> getBullets() {
        return bullet;
    }

    public void updateBullets() {
        for (int i = 0; i < bullet.size(); i++) {
            Bullet currentBullet = bullet.get(i);
            // System.out.println("size=> " + bullet.size());
            // System.out.println("index=> " + i);
            currentBullet.update();
            if (currentBullet.getY() < 0) {
                bullet.remove(i);
                // System.out.println("Player bullet removed");
                // TODO read about this
                i--;
                // System.out.println("corrected index=> " + i);
            }

        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(animation.getCurrentFrame(isAlive), this.getX(), this.getY(), null);
        for (Bullet bullet : this.getBullets()) {
            bullet.draw(g);

        }
    }

    @Override
    public void setX(int newPos) {
        this.xPos = newPos;

    }

    @Override
    public void setY(int newPos) {
        this.yPos = newPos;

    }

    public Rectangle getBounds() {
        return new Rectangle(xPos, yPos, animation.getWidth(), animation.getHeight());
    }

}
