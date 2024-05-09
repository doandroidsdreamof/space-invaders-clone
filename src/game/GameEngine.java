package game;

import java.awt.Graphics;

import constants.Constants;
import inputs.Controller;
import utils.CollisionDetector;

import java.util.List;
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
    private static final int ENEMY_SPACING = 50;
    private static final int ENEMY_X_OFFSET = 150;
    private static final int ENEMY_Y_OFFSET = 50;


    public GameEngine() {
        this.player = EntityFactory.setEntity(0, 0, Constants.EntityType.SPACESHIP);
        this.enemyList = new ArrayList<>();
        this.initEnemies();
        this.collisionDetector = new CollisionDetector(enemyList, (Player) player);
        this.setBackground(Color.BLACK);
        addKeyListener(new Controller(player));
        this.timer = new Timer(Constants.DELAY, this);
        this.timer.start();
    }

    public void initEnemies() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 10; col++) {
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
        renderer.renderPlayerBullets(g, player, player.getBullets());
        for (Entity enemy : enemyList) {
            if (enemy != null) {
                renderer.draw(g, enemy);
                // renderer.renderHitBox(g, enemy);
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

    // TODO abstract here => to player class
    private void updateGame() {
        for (Entity enemy : enemyList) {
            if (enemy != null) {
                enemy.update(); // ! update before drawing

            }
        }
        enemyList.removeIf(enemy -> enemy instanceof Enemy && !((Enemy) enemy).getAliveState()
                && !((Enemy) enemy).getAliveState()); // remove dead enemy objects
        this.player.update();
        this.collisionDetector.checkCollision(this.player.getBullets());

    }

}
