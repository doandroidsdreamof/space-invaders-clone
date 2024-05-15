package utils;

import java.util.List;
import java.util.Random;
import constants.Constants;
import entities.Enemy;
import entities.Entity;
import entities.EntityFactory;

public class EnemyManager {
    private List<Entity> enemyList;
    long lastShotTime;

    public EnemyManager(List<Entity> enemyList) {
        this.enemyList = enemyList;
    }

    public void enemyShootController() {
        // TODO generate bullets from closest part of enemies
        long currentTime = System.currentTimeMillis();
        Random random = new Random();
        int randomShipIndex = random.nextInt(enemyList.size());
        if (currentTime - lastShotTime > Constants.SHOT_RATE_LIMITER_ENEMY) {
            Entity shootingShip = enemyList.get(randomShipIndex);
            int bulletStartY = shootingShip.getY();
            int bulletStartX = shootingShip.getX() - 5 + shootingShip.getWidth() / 2;
            ((Enemy) shootingShip).setBullets(bulletStartX, bulletStartY);
            shootingShip.shoot();
            this.lastShotTime = System.currentTimeMillis();

        }

    }

    public void enemyMovement() {
        for (Entity enemy : enemyList) {
            if (enemy.getX() < 0) {
                ((Enemy) enemy).setMovementStopped(true);
            } else if (enemy.getX() > Constants.WIDTH - 60) {
                ((Enemy) enemy).setMovementStopped(false);
            }
        }
    }

    // TODO Constants.X => search object destructurel java equivalent
    public void initEnemies() {
        for (int row = 0; row < Constants.ENEMY_ROW; row++) {
            for (int col = 0; col < Constants.ENEMY_COL; col++) {
                int x = Constants.ENEMY_X_OFFSET + col * (Constants.ENEMY_SPACING);
                int y = Constants.ENEMY_Y_OFFSET + row * (Constants.ENEMY_SPACING);
                enemyList.add(EntityFactory.setEntity(x, y, Constants.EntityType.ENEMY));
            }
        }
    }

}
