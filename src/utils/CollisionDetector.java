package utils;

import java.util.List;

import entities.Bullet;
import entities.AbstractEntity;
import entities.Player;

import java.util.ArrayList;

public class CollisionDetector {
    private Player player;

    public CollisionDetector(Player player) {
        this.player = player;

    }

    // TODO code duplication
    public void checkCollision(List<Bullet> bullets, List<AbstractEntity> entities) {
        List<Bullet> removedBullets = new ArrayList<>();
        for (Bullet bullet : bullets) {
            for (AbstractEntity entity : entities) {
                if (entity.getBounds().intersects(bullet.getBounds())) {
                    entity.setAliveState(false);
                    removedBullets.add(bullet);
                    break;

                }
            }
        }
        player.getBullets().removeAll(removedBullets);

    }

    public void checkPlayerCollision(List<Bullet> bullets, AbstractEntity entity) {
        List<Bullet> removedBullets = new ArrayList<>();
        for (Bullet bullet : bullets) {
            if (entity.getBounds().intersects(bullet.getBounds())) {
                System.out.println("fire in the hole");
                this.player.setAliveState(false);
                removedBullets.add(bullet);

            }
        }

        entity.getBullets().removeAll(removedBullets);

    }

}
