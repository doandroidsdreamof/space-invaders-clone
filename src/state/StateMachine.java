package state;

import entities.Enemy;

public class StateMachine {
    enum State {
        STATE_IDLE, STATE_ATTACK, STATE_LEFT_MOVEMENT, STATE_RIGHT_MOVEMENT
    };

    private Enemy enemy;
    private State state;

    public StateMachine(Enemy enemy) {
        this.enemy = enemy;
        this.state = State.STATE_IDLE;

    }

    public void setState(State state) {
        this.state = state;
    }

    public void update() {
        switch (state) {
        case STATE_IDLE:
            break;
        case STATE_ATTACK:
            break;
        case STATE_LEFT_MOVEMENT:
            break;
        case STATE_RIGHT_MOVEMENT:
            break;
        }
    }

}
