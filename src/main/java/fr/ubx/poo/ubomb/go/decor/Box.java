package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.Walkable;
import fr.ubx.poo.ubomb.go.character.CharacterMovable;
import fr.ubx.poo.ubomb.go.character.Player;

public class Box extends Decor implements Walkable {
    public Box(Position position){
        super(position);
    }

    @Override
    public boolean walkableBy(CharacterMovable character) {
       return character instanceof Player;
    }
}
