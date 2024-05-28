
package game;

import java.awt.Graphics;
import java.util.List;
import entities.Bullet;
import entities.AbstractEntity;
import java.awt.Color;
import java.awt.Rectangle;

public class Renderer {

    public void draw(Graphics g, AbstractEntity entity) {
        if (entity != null) {
            g.drawImage(entity.getCurrentAnimationFrame(), entity.getX(), entity.getY(), null);
        }
    }

    public void renderBullets(Graphics g, AbstractEntity entity, List<Bullet> bullets) {
        g.drawImage(entity.getCurrentAnimationFrame(), entity.getX(), entity.getY(), null);
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }

    public void renderHitBox(Graphics g, AbstractEntity entity) {
        Rectangle box = entity.getBounds();
        g.setColor(Color.RED);
        g.drawRect(entity.getX(), entity.getY(), (int) box.getWidth(), (int) box.getHeight());

    }

    public void renderEntitiesWithBullets(Graphics g, AbstractEntity entity) {
        this.draw(g, entity);
        //this.renderHitBox(g, entity);
        this.renderBullets(g, entity, entity.getBullets());
    }
}
