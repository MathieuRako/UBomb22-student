package fr.ubx.poo.ubomb.go;

import fr.ubx.poo.ubomb.engine.Timer;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;

public class Bomb extends GameObject{

    private final static int bombDelay = 4000;
    private Timer timer;

    private boolean changed;
    private boolean started;


    private int state;
    public Bomb(Game game, Position position) {
        super(game, position);
        this.timer = new Timer(bombDelay);
        timer.start();
        started = true;
        changed = false;
        this.state = -1;
    }

    public void setChanged(boolean bool){
        changed = bool;
    }

    public boolean isChanged(){
        return changed;
    }

    public void update(long now){
        timer.update(now);
        if(timer.isRunning() && !changed){
            Long remaining = timer.remaining();
            if (remaining > 3000 && state != 3) {
                state = 3;
                changed = true;
            }
            else if (remaining > 2000 && state != 2){
                state = 2;
                changed = true;
            }
            else if (remaining > 1000 && state != 1){
                state = 1;
                changed = true;
            }
            else if (remaining > 0 && state != 0){
                state = 0;
                changed = true;
            }
        }
        else {
            if(started){
                started = false;
                explode();
            }
        }
    }

    @Override
    public void explode(){

    }

    public int getState(){
        return state;
    }
    public long getRemaining(){
        return timer.remaining();
    }

}
