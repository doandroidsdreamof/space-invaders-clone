package entities;

import java.awt.image.BufferedImage;
import constants.Constants;

public class EntityFactory {

    public static Entity setEntity(int x, int y, Constants.EntityType type) {
        if (type == type.SPACESHIP) {
            return new <Entity>Player();
        }
        if (type == type.ENEMY) {
            return new <Entity>Enemy(x, y);
        }
        return null;

    }

}
