package managers;

import java.awt.image.BufferedImage;
import constants.Constants;

public class AnimationManager {
    private BufferedImage[] frames;
    private int currentFrame;
    private boolean isPlayer;
    @SuppressWarnings("unused")
    private boolean isExploded;
    long startTime;

    public AnimationManager(BufferedImage[] frames, Boolean isPlayer) {
        this.frames = frames;
        this.isPlayer = isPlayer;
        this.isExploded = false;
        this.currentFrame = 0;
        this.startTime = System.currentTimeMillis();
    }

    public void updateFrame() {
        if (this.isPlayer) {
            return;
        }
        long timeElapsed = System.currentTimeMillis() - startTime;
        if (timeElapsed > Constants.Enemy.ANIMATION_DELAY) {
            this.changeImage();
            this.startTime = System.currentTimeMillis();

        }

    }

    public void changeImage() {
        this.currentFrame++;
        if (this.currentFrame >= this.frames.length - 1) {
            this.currentFrame = 0;
        }
    }

    public BufferedImage getCurrentFrame(Boolean isAlive) {
        if (isAlive) {
            return frames[currentFrame];
        } else {
            // * change explosion image */
            this.isExploded = true;
            return frames[frames.length - 1];
        }

    }

    public int getWidth() {
        return frames[currentFrame].getWidth();
    }

    public int getHeight() {
        return frames[currentFrame].getHeight();
    }

}
