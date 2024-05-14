package constants;
import java.awt.image.BufferedImage;
import utils.ImageWrapper;
public class Constants {
    public static final BufferedImage[] ENEMY_ANIMATION_FRAMES = new BufferedImage[3];
    public static final BufferedImage[] ENEMY_EXPLOSION = new BufferedImage[1];
    public static final BufferedImage[] PLAYER_IMAGE = new BufferedImage[1];
    public static final BufferedImage[] PLAYER_EXPLOSION = new BufferedImage[1];
    public static final String GAME_TITLE = "space-invaders";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int DELAY = 60;
    public static final int BULLET_SIZE = 10;
    public static final String SPACESHIP = null;
    public static final long SHOT_RATE_LIMITER_PLAYER = 800;
    public static final long SHOT_RATE_LIMITER_ENEMY = 1000;
    public static final int ENEMY_ANIMATION_DELAY = 200;

    public enum EntityType {
        SPACESHIP, ENEMY
    }

    // TODO loop and naming convention
    static {
        PLAYER_IMAGE[0] = ImageWrapper.loadImage("src/images/player.png");
        PLAYER_EXPLOSION[0] = ImageWrapper.loadImage("src/images/space__0010_PlayerExplosion.png");
        ENEMY_EXPLOSION[0] = ImageWrapper.loadImage("src/images/space__0009_EnemyExplosion.png");
        ENEMY_ANIMATION_FRAMES[0] = ImageWrapper.loadImage("src/images/InvaderA1.png");
        ENEMY_ANIMATION_FRAMES[1] = ImageWrapper.loadImage("src/images/InvaderA2.png");
        ENEMY_ANIMATION_FRAMES[2] = ImageWrapper.loadImage("src/images/space__0009_EnemyExplosion.png");

    }
}
