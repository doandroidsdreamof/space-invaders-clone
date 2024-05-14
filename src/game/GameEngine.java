package game;

import java.awt.Graphics;

import constants.Constants;
import inputs.Controller;
import utils.CollisionDetector;
import utils.EnemyManager;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.*;
import entities.*;

public class GameEngine extends JPanel implements ActionListener {
    private Entity player;
    private CollisionDetector collisionDetector;
    private Timer timer;
    private List<Entity> enemyList;
    private Renderer renderer = new Renderer();
    private EnemyManager enemyManager;
    private static final int ENEMY_X_OFFSET = 150, ENEMY_Y_OFFSET = 50, ENEMY_SPACING = 50;
    private static final int ENEMY_ROW = 4, ENEMY_COL = 12;

    public GameEngine() {
        this.player = EntityFactory.setEntity(0, 0, Constants.EntityType.SPACESHIP);
        this.enemyList = new ArrayList<>();
        this.enemyManager = new EnemyManager(enemyList);
        this.initEnemies();
        this.collisionDetector = new CollisionDetector((Player) player);
        this.setBackground(Color.BLACK);
        addKeyListener(new Controller(player));
        this.timer = new Timer(Constants.DELAY, this);
        this.timer.start();
    }

    public void initEnemies() {
        for (int row = 0; row < GameEngine.ENEMY_ROW; row++) {
            for (int col = 0; col < GameEngine.ENEMY_COL; col++) {
                int x = ENEMY_X_OFFSET + col * (ENEMY_SPACING);
                int y = ENEMY_Y_OFFSET + row * (ENEMY_SPACING);
                enemyList.add(EntityFactory.setEntity(x, y, Constants.EntityType.ENEMY));
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderer.draw(g, player);
        renderer.renderHitBox(g, player);
        renderer.renderBullets(g, player, player.getBullets());
        for (Entity enemy : enemyList) {
            if (enemy != null) {
                renderer.draw(g, enemy);
                renderer.renderHitBox(g, enemy);
                renderer.renderBullets(g, enemy, enemy.getBullets());
            }
        }
    }

    // TODO Deterministic game loop with deltaTime
    @Override
    public void actionPerformed(ActionEvent e) {
        {

            updateGame();
            repaint();
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

    private void updateGame() {
        for (Entity enemy : enemyList) {
            if (enemy != null) {
                // ! update before drawing
                enemy.update();

            }
        }
        this.enemyMovement();
        // remove dead enemy objects
        enemyList.removeIf(enemy -> enemy instanceof Enemy && !((Enemy) enemy).getAliveState()
                && !((Enemy) enemy).getAliveState());
        this.player.update();
        enemyManager.enemyShootController();
        this.collisionDetector.checkCollision(this.player.getBullets(), enemyList);

    }

}
