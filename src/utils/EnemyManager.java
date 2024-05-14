package utils;

import java.util.List;
import java.util.Random;
import constants.Constants;
import entities.Enemy;
import entities.Entity;

public class EnemyManager {
    private List<Entity> enemyList;
    long lastShotTime;

    public EnemyManager(List<Entity> enemyList) {
        this.enemyList = enemyList;
    }

    public void enemyShootController() {
        //TODO generate bullets from closest part of enemies
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

}
