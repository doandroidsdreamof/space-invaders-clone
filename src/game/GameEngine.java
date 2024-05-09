package game;

import java.awt.Graphics;

import constants.Constants;
import inputs.Controller;
import utils.CollisionDetector;

import java.util.List;
import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Rectangle;
import java.awt.Color;

import javax.swing.*;
import entities.*;

public class GameEngine extends JPanel implements ActionListener {
    private Entity player;
    private CollisionDetector collisionDetector;
    private Timer timer;
    private List<Entity> enemiesList;
    private int alienSpacing = 40;


    public GameEngine() {
        this.player = EntityFactory.setEntity(0, 0, Constants.EntityType.SPACESHIP);
        this.enemiesList = new ArrayList<>();
        this.initEnemies();
        this.collisionDetector = new CollisionDetector(enemiesList, (Player) player);
        this.setBackground(Color.BLACK);
        addKeyListener(new Controller(player));
        this.timer = new Timer(Constants.DELAY, this);
        this.timer.start();
    }

    public void initEnemies() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 10; col++) {
                int x = 50 + col * (alienSpacing + 10);
                int y = 50 + row * (alienSpacing + 10);
                enemiesList.add(EntityFactory.setEntity(x, y, Constants.EntityType.ENEMY));
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.player.draw(g);
        this.drawHitbox(g, player);
        for (Entity enemy : enemiesList) {
            if (enemy != null) {
                enemy.draw(g);
                this.drawHitbox(g, enemy);
            }
        }
    }

    // TODO Deterministic game loop with deltaTime
    @Override
    public void actionPerformed(ActionEvent e) {
        updateGame();
        repaint();
    }

    // TODO abstract here => to player class
    private void updateGame() {
        for (Entity enemy : enemiesList) {
            if (enemy != null) {
                enemy.update(); // ! update before drawing
            }
        }
        enemiesList.removeIf(enemy -> enemy instanceof Enemy && !((Enemy) enemy).getAliveState() && !((Enemy) enemy).getAliveState());  // remove dead enemy objects
        this.player.update();
        this.collisionDetector.checkCollision(this.player.getBullets());

    }

    private void drawHitbox(Graphics g, Entity entity) {
        Rectangle box = entity.getBounds();
        g.drawRect(entity.getX(), entity.getY(), (int) box.getWidth(), (int) box.getHeight());
        g.setColor(Color.RED);

    }

}
