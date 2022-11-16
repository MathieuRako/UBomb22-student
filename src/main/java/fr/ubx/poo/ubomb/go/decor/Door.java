package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.game.Position;

public class Door extends Decor{

    int level;
    private final boolean oppened;

    private boolean up;
    private Position positionUp;


    public Door(Position position, boolean oppened, boolean up) {
        super(position);
        this.oppened = oppened;
        this.up = up;
    }

    public void setPositionUp(Position pos){
        positionUp = pos;
    }
    public boolean isOpen(){
        return oppened;
    }

    public boolean isUp(){
        return up;
    }
}
