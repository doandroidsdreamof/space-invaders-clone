package utils;

import java.util.List;

import entities.Bullet;
import entities.Entity;
import entities.Player;

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
        List<Bullet> removedBullets = new ArrayList<>();

        for (Bullet bullet : bullets) {
            for (Entity enemy : enemiesList) {
                if (enemy.getBounds().intersects(bullet.getBounds())) {
                    enemy.setAliveState(false);
                    removedBullets.add(bullet);
                    break;

                }
            }
        }
        player.getBullets().removeAll(removedBullets);

    }

}
