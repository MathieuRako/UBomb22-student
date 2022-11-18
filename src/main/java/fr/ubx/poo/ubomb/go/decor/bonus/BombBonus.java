package fr.ubx.poo.ubomb.go.decor.bonus;

import fr.ubx.poo.ubomb.game.Position;

public abstract class BombBonus extends Bonus{
    private final boolean increase;

    public BombBonus(Position position, boolean inc){
        super(position);
        increase = inc;
    }

    public boolean isIncrease() {
        return increase;
    }

    public int getDelta(){
        return increase ? 1 : -1;
    }
}
