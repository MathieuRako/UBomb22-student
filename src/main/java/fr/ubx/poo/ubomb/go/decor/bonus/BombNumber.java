package fr.ubx.poo.ubomb.go.decor.bonus;

import fr.ubx.poo.ubomb.game.Position;

public class BombNumber extends Bonus{
    private final boolean increase;
    public BombNumber(Position position, boolean inc){

        super(position);
        increase = inc;
    }

    public boolean isIncrease(){
        return increase;
    }
}
