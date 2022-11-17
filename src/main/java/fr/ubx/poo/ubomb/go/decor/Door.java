package fr.ubx.poo.ubomb.go.decor;

import com.sun.javafx.scene.traversal.TopMostTraversalEngine;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.Walkable;
import fr.ubx.poo.ubomb.go.character.CharacterMovable;

public class Door extends Decor implements Walkable {

    int level;
    private boolean oppened;

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

    public void open(){
        this.oppened = true;
        this.setModified(true);
    }

    @Override
    public boolean walkableBy(CharacterMovable character) {
        return oppened;
    }

    public boolean isUp(){
        return up;
    }

    public void changeUp(){
        up = !up;
    }

    @Override
    public Position getPosition(){
        return up ? super.getPosition() : positionUp;
    }
    public Position getUpPosition() {
        return positionUp;
    }
}
