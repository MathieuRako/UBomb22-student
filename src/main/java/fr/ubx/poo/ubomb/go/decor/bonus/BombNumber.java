package fr.ubx.poo.ubomb.go.decor.bonus;

import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Player;

public class BombNumber extends BombBonus{

    public BombNumber(Position position, boolean inc){
        super(position, inc);
    }

    @Override
    public void takenBy(Player player) {
            player.take(this);
    }


}
