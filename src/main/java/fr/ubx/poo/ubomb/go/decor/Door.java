package fr.ubx.poo.ubomb.go.decor;

import com.sun.javafx.scene.traversal.TopMostTraversalEngine;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.Walkable;
import fr.ubx.poo.ubomb.go.character.CharacterMovable;

public class Door extends Decor implements Walkable {

    private boolean oppened;

    private boolean goUp;


    public Door(Position position, boolean oppened) {
        super(position);
        this.oppened = oppened;
        this.goUp = true;
    }

    public Door(Position position){
        super(position);
        this.oppened = true;
        this.goUp = false;
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

    public boolean goUp(){
        return goUp;
    }

}
