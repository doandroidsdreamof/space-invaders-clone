package interfaces;
import java.awt.Graphics;

import java.util.List; // Import for List

import entities.Bullet;
import java.awt.Rectangle;

//TODO convert this to abstract class
public interface Entity {
    
    void draw(Graphics g);
    void update();

    void moveLeft();

    void moveRight();

    void updateBullets();

    List<Bullet> getBullets();

    void shoot();

    int getX();

    int getY();

    void setAliveState(Boolean state);

    void setX(int xPos);

    void setY(int yPos);
    
    Boolean getAliveState();

    default Bullet getLastBullet(){
        return null;}
    
    Rectangle getBounds(); 
}