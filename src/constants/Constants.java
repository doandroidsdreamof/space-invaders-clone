package constants;

import java.awt.image.BufferedImage;
import utils.ImageWrapper;

public class Constants {
    public static final BufferedImage[] ENEMY_ANIMATION_FRAMES = new BufferedImage[3];
    public static final BufferedImage[] PLAYER_ANIMATION_FRAMES = new BufferedImage[2];

    public static class Game {
        public static final String TITLE = "space-invaders";
        public static final String PLAY_AGAIN_TEXT = "play again";
        public static final int WIDTH = 800;
        public static final int HEIGHT = 600;
        public static final int DELAY = 60;
    }

    public static class Player {
        public static final int SHOT_RATE_LIMITER = 800;
        public static final int RIGHT_BOUNDARY_OFFSET = 60;
        public static final int LEFT_BOUNDARY = 5;
    }

    public static class Enemy {
        public static final int SHOT_RATE_LIMITER = 1000;
        public static final int ANIMATION_DELAY = 200;
        public static final int X_OFFSET = 150;
        public static final int Y_OFFSET = 50;
        public static final int SPACING = 50;
        public static final int ROW = 4;
        public static final int COL = 12;
    }

    public static class Bullet {
        public static final int SIZE = 10;
        public static final int SPEED = 20; // ! Will change based on difficulty levels
    }

    public static class ERROR_MESSAGES {
        public static final String IMAGE_LOADING = "Image is not loaded";

    }

    public enum EntityType {
        SPACESHIP, ENEMY
    }

    static {
        PLAYER_ANIMATION_FRAMES[0] = ImageWrapper.loadImage("src/images/player.png");
        PLAYER_ANIMATION_FRAMES[1] = ImageWrapper.loadImage("src/images/space__0009_EnemyExplosion.png");

        ENEMY_ANIMATION_FRAMES[0] = ImageWrapper.loadImage("src/images/InvaderA1.png");
        ENEMY_ANIMATION_FRAMES[1] = ImageWrapper.loadImage("src/images/InvaderA2.png");
        ENEMY_ANIMATION_FRAMES[2] = ImageWrapper.loadImage("src/images/space__0009_EnemyExplosion.png");
    }
}
