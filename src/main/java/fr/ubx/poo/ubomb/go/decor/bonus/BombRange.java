package fr.ubx.poo.ubomb.go.decor.bonus;

import fr.ubx.poo.ubomb.game.Position;

public class BombRange extends Bonus{

    private final boolean increase;
    public BombRange(Position position, boolean inc){

        super(position);
        increase = inc;
    }

    public boolean isIncrease(){
        return increase;
    }
}