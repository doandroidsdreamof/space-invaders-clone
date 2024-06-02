package managers;

import java.util.List;
import java.util.Random;
import constants.Constants;
import entities.Enemy;
import entities.AbstractEntity;
import entities.EntityFactory;

public class EnemyManager {
    private List<AbstractEntity> enemyList;
    long lastShotTime;

    public EnemyManager(List<AbstractEntity> enemyList) {
        this.enemyList = enemyList;
    }

    public void enemyShootController() {
        // TODO generate bullets from closest part of enemies
        long currentTime = System.currentTimeMillis();
        Random random = new Random();
        int randomShipIndex = random.nextInt(enemyList.size());
        if (currentTime - lastShotTime > Constants.Enemy.SHOT_RATE_LIMITER) {
            AbstractEntity shootingShip = enemyList.get(randomShipIndex);
            int bulletStartY = shootingShip.getY();
            int bulletStartX = shootingShip.getX() - 5 + shootingShip.getWidth() / 2;
            ((Enemy) shootingShip).setBullets(bulletStartX, bulletStartY);
            shootingShip.shoot();
            this.lastShotTime = System.currentTimeMillis();

        }

    }

    public void enemyMovement() {
        for (AbstractEntity enemy : enemyList) {
            if (enemy.getX() < 0) {
                ((Enemy) enemy).setMovementStopped(true);
            } else if (enemy.getX() > Constants.Game.WIDTH - 60) {
                ((Enemy) enemy).setMovementStopped(false);
            }
        }
    }

    // TODO Constants.X => search object destructurel java equivalent
    public void initEnemies() {
        for (int row = 0; row < Constants.Enemy.ROW; row++) {
            for (int col = 0; col < Constants.Enemy.COL; col++) {
                int x = Constants.Enemy.X_OFFSET + col * (Constants.Enemy.SPACING);
                int y = Constants.Enemy.Y_OFFSET + row * (Constants.Enemy.SPACING);
                enemyList.add(EntityFactory.createEntity(x, y, Constants.EntityType.ENEMY));
            }
        }
    }

}
