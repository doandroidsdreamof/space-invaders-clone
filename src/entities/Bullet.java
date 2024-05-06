package entities;

import java.awt.Graphics;

import constants.Constants;
import java.awt.Rectangle;

import java.awt.Color;

public class Bullet {
    private int x, y;
    private int speed;
    private boolean isPlayerBullet;

    Bullet(int x, int y, int speed, boolean isPlayerBullet) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.isPlayerBullet = isPlayerBullet;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, Constants.BULLET_SIZE, Constants.BULLET_SIZE);
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, Constants.BULLET_SIZE, Constants.BULLET_SIZE);
    }

    public void update() {
        this.y += isPlayerBullet ? -speed : speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
