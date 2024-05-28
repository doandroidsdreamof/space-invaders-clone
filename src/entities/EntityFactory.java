package entities;

import constants.Constants;

public class EntityFactory {

  public static AbstractEntity createEntity(int x, int y, Constants.EntityType type) {
    switch (type) {
    case SPACESHIP:
      return new Player();
    case ENEMY:
      return new Enemy(x, y);
    default:
      throw new IllegalArgumentException("Unsupported entity type: " + type);
    }
  }
}
