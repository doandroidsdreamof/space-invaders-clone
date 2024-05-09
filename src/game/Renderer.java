
package game;

import java.awt.Graphics;
import java.util.List;
import entities.Bullet;
import entities.Entity;
import java.awt.Color;
import java.awt.Rectangle;

public class Renderer {

    public void draw(Graphics g, Entity entity) {
        if (entity != null) {
            g.drawImage(entity.getCurrentAnimationFrame(), entity.getX(), entity.getY(), null);
        }

    }

    public void renderPlayerBullets(Graphics g, Entity entity, List<Bullet> bullets) {
        g.drawImage(entity.getCurrentAnimationFrame(), entity.getX(), entity.getY(), null);
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }

    public void renderHitBox(Graphics g, Entity entity) {
        Rectangle box = entity.getBounds();
        g.drawRect(entity.getX(), entity.getY(), (int) box.getWidth(), (int) box.getHeight());
        g.setColor(Color.RED);

    }
}
