package entities;

import java.awt.image.BufferedImage;
import constants.Constants;
import interfaces.Entity;

public class EntityFactory {

    public static Entity setEntity(int xPos, int yPos, Constants.EntityType type, BufferedImage[] image,
            BufferedImage[] explosionImage) {
        if (type == type.SPACESHIP) {
            return new Player(image,explosionImage);
        }
        if (type == type.ENEMY) {
            return new Enemy(xPos, yPos, image, explosionImage);
        }
        return null;

    }

}
