package game;

import constants.Constants;
import entities.*;
import inputs.Controller;
import managers.EnemyManager;
import ui.Button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import java.awt.*;

public class GameEngine extends JPanel implements ActionListener {
  private AbstractEntity player;
  private CollisionDetector collisionDetector;
  private Timer timer;
  private List<AbstractEntity> enemyList;
  private Renderer renderer;
  private EnemyManager enemyManager;
  private Button playAgainButton;

  // TODO refactoring package imports

  public GameEngine() {
    setLayout(new BorderLayout());
    this.initGame();
    this.setupUI();

  }

  public void initGame() {
    this.player = EntityFactory.createEntity(0, 0, Constants.EntityType.SPACESHIP);
    this.enemyList = new ArrayList<>();
    this.enemyManager = new EnemyManager(enemyList);
    this.renderer = new Renderer();
    this.enemyManager.initEnemies();
    this.collisionDetector = new CollisionDetector((Player) player);
    this.setBackground(Color.BLACK);
    addKeyListener(new Controller(player));
    this.timer = new Timer(Constants.Game.DELAY, this);
    this.timer.start();

  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (!this.player.getAliveState()) {
      g.setColor(Color.BLACK);
    } else {
      renderer.renderEntitiesWithBullets(g, player);
      for (AbstractEntity enemy : enemyList) {
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
    for (AbstractEntity enemy : enemyList) {
      if (enemy != null) {
        // ! update before drawing
        enemy.update();
        this.collisionDetector.checkPlayerCollision(enemy.getBullets(), this.player);
      }
    }
    enemyManager.enemyMovement();
    // TODO decoupling => EnemyManager
    // * remove dead enemy object
    // ! bug => bullets removed when enemy destroyed it must be independent from
    // ! enemy instance
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
    requestFocusInWindow();
  }

  private void setupUI() {
    setBackground(Color.BLACK);
    addKeyListener(new Controller(player));
    setFocusable(true);
    requestFocusInWindow();

    playAgainButton = new Button(e -> resetGame());
    playAgainButton.centerButton(getWidth(), getHeight());
    add(playAgainButton);
  }

  private void initializeGameState() {
    this.initGame();
  }

}
