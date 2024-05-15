package game;

import constants.Constants;
import entities.*;
import inputs.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import utils.CollisionDetector;
import utils.EnemyManager;
import java.awt.*;

public class GameEngine extends JPanel implements ActionListener {
  private Entity player;
  private CollisionDetector collisionDetector;
  private Timer timer;
  private List<Entity> enemyList;
  private Renderer renderer = new Renderer();
  private EnemyManager enemyManager;
  private JButton playAgainButton;

  public GameEngine() {
    setLayout(new BorderLayout());
    this.initGame();
    this.setupUI();

  }

  public void initGame() {
    this.player = EntityFactory.setEntity(0, 0, Constants.EntityType.SPACESHIP);
    this.enemyList = new ArrayList<>();
    this.enemyManager = new EnemyManager(enemyList);
    this.enemyManager.initEnemies();
    this.collisionDetector = new CollisionDetector((Player) player);
    this.setBackground(Color.BLACK);
    addKeyListener(new Controller(player));
    this.timer = new Timer(Constants.DELAY, this);
    this.timer.start();
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (!this.player.getAliveState()) {
      g.setColor(Color.BLACK);
    } else {
      renderer.renderEntitiesWithBullets(g, player);
      for (Entity enemy : enemyList) {
        if (enemy != null) {
          renderer.renderEntitiesWithBullets(g, enemy);
        }
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

  private void updateGame() {
    for (Entity enemy : enemyList) {
      if (enemy != null) {
        // ! update before drawing
        enemy.update();
        this.collisionDetector.checkPlayerCollision(enemy.getBullets(), this.player);
      }
    }
    enemyManager.enemyMovement();
    // remove dead enemy objects
    enemyList.removeIf(
        enemy -> enemy instanceof Enemy && !((Enemy) enemy).getAliveState() && !((Enemy) enemy).getAliveState());
    this.player.update();
    enemyManager.enemyShootController();
    this.collisionDetector.checkCollision(this.player.getBullets(), enemyList);
    // TODO decoupling && state pattern
    if (!player.getAliveState()) {
      timer.stop();
      setBackground(Color.BLACK);
      playAgainButton.setVisible(true);
    }

  }

  // TODO decoupling
  private void resetGame() {
    initializeGameState();
    playAgainButton.setVisible(false);
    addKeyListener(new Controller(this.player));
    requestFocusInWindow();
    timer.start();
  }

  // TODO decoupling
  private void setupUI() {
    setBackground(Color.BLACK);
    addKeyListener(new Controller(player));
    setFocusable(true);
    requestFocusInWindow();

    playAgainButton = new JButton("play again");
    playAgainButton.addActionListener(e -> resetGame());
    playAgainButton.setOpaque(false);
    playAgainButton.setContentAreaFilled(false);
    playAgainButton.setBorderPainted(false);
    playAgainButton.setForeground(Color.WHITE);
    // TODO magic numbers
    playAgainButton.setBounds(getWidth() / 2 - 50, getHeight() / 2 - 15, 100, 30);
    playAgainButton.setVisible(false);
    add(playAgainButton);
  }

  private void initializeGameState() {
    this.player = EntityFactory.setEntity(0, 0, Constants.EntityType.SPACESHIP);
    this.enemyList.clear();
    this.enemyManager.initEnemies();
    this.collisionDetector = new CollisionDetector((Player) player);
  }
}
