package utils;

import java.util.List;

import entities.Bullet;
import entities.Player;
import interfaces.Entity;
import java.util.ArrayList;

public class CollisionDetector {
    private List<Entity> enemiesList;
    private Player player;
    private Boolean isCollide;

    public CollisionDetector(List enemiesList, Player player) {
        this.enemiesList = enemiesList;
        this.isCollide = false;
        this.player = player;

    }

    public void checkCollision(List<Bullet> bullets) {
        List<Entity> removedEnemies = new ArrayList<>();
        List<Bullet> removedBullets = new ArrayList<>();

        for (Bullet bullet : bullets) {
            for (Entity enemy : enemiesList) {
                // You can use 'i' here as the index of 'enemy'
                if (enemy.getBounds().intersects(bullet.getBounds())) {
                    enemy.setAliveState(false);
                    removedEnemies.add(enemy);
                    removedBullets.add(bullet);
                    break;

                }
            }
        }
        enemiesList.removeAll(removedEnemies);
        player.getBullets().removeAll(removedBullets);

    }

}
