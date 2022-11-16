package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.CharacterMovable;

public class Princess extends Decor{

    public Princess(Position position) {
        super(position);
    }

    @Override
    public boolean walkableBy(CharacterMovable character){
        return true;
    }
}
