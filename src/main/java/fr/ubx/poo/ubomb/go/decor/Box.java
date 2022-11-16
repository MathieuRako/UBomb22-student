package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.Walkable;
import fr.ubx.poo.ubomb.go.character.CharacterMovable;
import fr.ubx.poo.ubomb.go.character.Player;

public class Box extends Decor implements Walkable {

    private Direction nextBoxDirection;
    public Box(Position position){
        super(position);
        nextBoxDirection = null;
    }

    public void setNextBoxDirection(Direction direction){
        nextBoxDirection = direction;
    }

    public boolean isNextBox(){
        return nextBoxDirection != null;
    }
    public Box getNextBox(){
        Position boxPosition = nextBoxDirection.nextPosition(this.getPosition());
        return new Box(boxPosition);
    }
    @Override
    public boolean walkableBy(CharacterMovable character) {
       return character instanceof Player;
    }


}
