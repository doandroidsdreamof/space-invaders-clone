package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.*;
import java.awt.Rectangle;

import interfaces.Entity;
import state.StateMachine;
import utils.AnimationManager;

public class Enemy implements Entity {
    private AnimationManager animation;
    private StateMachine stateMachine;
    private Boolean isAlive;
    private int width, height;
    private List<Bullet> bullet;
    private int xPos, yPos;

    public Enemy(int xPos, int yPos, BufferedImage[] images, BufferedImage[] explosionImage) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.isAlive = true;
        this.bullet = new ArrayList<Bullet>();
        this.animation = new AnimationManager(images, explosionImage, false);
        this.stateMachine = new StateMachine(this);
        this.width = animation.getWidth();
        this.height = animation.getHeight();

    }

    public Rectangle getBounds() {
        return new Rectangle(xPos, yPos, animation.getWidth(), animation.getHeight());
    }

    public void setAliveState(Boolean state) {
        this.isAlive = state;

    }

    public Boolean getAliveState() {
        return this.isAlive;
    }

    public void update() {
        this.animation.updateFrame();
        this.stateMachine.update();
    }

    @Override
    public void moveLeft() {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveRight() {
        // TODO Auto-generated method stub
    }

    @Override
    public void shoot() {
        // TODO Auto-generated method stub
    }

    @Override
    public int getX() {
        // TODO Auto-generated method stub
        return this.xPos;
    }

    @Override
    public int getY() {
        // TODO Auto-generated method stub
        return this.yPos;

    }

    @Override
    public void updateBullets() {
        // TODO Auto-generated method stub
    }

    @Override
    public List<Bullet> getBullets() {
        // TODO Auto-generated method stub
        return bullet;
    }

    @Override
    public void setX(int newPos) {
        this.xPos = newPos;

    }

    @Override
    public void setY(int newPos) {
        this.yPos = newPos;

    }

    @Override
    public void draw(Graphics g) {
        System.out.println("Drawing entity - Alive: " + this.isAlive);
        g.drawImage(animation.getCurrentFrame(this.isAlive), this.xPos, this.yPos, null);

    }
}
