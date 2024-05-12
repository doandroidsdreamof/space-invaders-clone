package utils;

import java.util.List;

import entities.Bullet;
import entities.Entity;
import entities.Player;

import java.util.ArrayList;

public class CollisionDetector {
    private Player player;

    public CollisionDetector(Player player) {
        this.player = player;

    }

    public void checkCollision(List<Bullet> bullets, List<Entity> entity) {
        List<Bullet> removedBullets = new ArrayList<>();

        for (Bullet bullet : bullets) {
            for (Entity enemy : entity) {
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
