package entities;

import constants.Constants;
import state.StateMachine;

public class Enemy extends AbstractEntity {
  private StateMachine stateMachine;
  // TODO => this should be increased based on difficulty
  private static final int ENEMY_SPEED = 3;
  private static boolean isStop = false;
  private static boolean isPlayer = false;
  @SuppressWarnings("unused")
  private int bulletStartX, bulletStartY;

  public Enemy(int x, int y) {
    super(x, y, Constants.ENEMY_ANIMATION_FRAMES, isPlayer);
    this.stateMachine = new StateMachine(this);
  }

  public void movement() {
    if (Enemy.isStop) {
      this.moveRight();
    } else {
      this.moveLeft();
    }
  }

  public void setBullets(int x, int y) {
    this.bulletStartX = x;
    this.bulletStartY = y;
  }

  public boolean getMovementStopped() {
    return isStop;
  }

  public void setMovementStopped(Boolean stop) {
    isStop = stop;
  }

  public void update() {
    this.updateAnimation();
    this.stateMachine.update();
    this.movement();
    this.updateBullets();
  }

  @Override
  public void moveLeft() {
    this.setX(this.getX() - Enemy.ENEMY_SPEED);
  }

  @Override
  public void moveRight() {
    this.setX(this.getX() + Enemy.ENEMY_SPEED);
  }

  // TODO code duplication
  @Override
  public void shoot() {
    long currentTime = System.currentTimeMillis();
    if (currentTime - this.getLastShotTime() > Constants.SHOT_RATE_LIMITER_ENEMY) {
      int bulletStartY = this.getY();
      int bulletStartX = (this.getX() - 5) + this.getWidth() / 2;
      this.getBullets().add(new Bullet(bulletStartX, bulletStartY, Constants.BULLET_SPEED, false));
      this.setLastShotTime(currentTime);
    }
  }

  // TODO code duplication
  // TODO read abaout Concurrent Modification && Iterator
  public void updateBullets() {
    for (int i = 0; i < getBullets().size(); i++) {
      Bullet currentBullet = getBullets().get(i);
      currentBullet.update();
      if (currentBullet.getY() < 0) {
        getBullets().remove(i);
        i--;
      }
    }
  }
}
