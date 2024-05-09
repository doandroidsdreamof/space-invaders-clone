package entities;
import constants.Constants;
import state.StateMachine;


public class Enemy extends Entity {
    private StateMachine stateMachine;

    public Enemy(int x, int y) {
        Boolean isPlayer = false;
        super(x, y, Constants.ENEMY_ANIMATION_FRAMES, Constants.ENEMY_EXPLOSION, isPlayer);
        this.stateMachine = new StateMachine(this);

    }

    public void update() {
        this.updateAnimation();
        this.stateMachine.update();
    }

    @Override
    public void moveLeft() {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveRight() {
        // TODO Auto-generated method stub
    }

    @Override
    public void shoot() {
        // TODO Auto-generated method stub
    }

}
