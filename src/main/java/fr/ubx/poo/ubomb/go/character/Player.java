/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.go.character;

import fr.ubx.poo.ubomb.game.Direction;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.TakeVisitor;
import fr.ubx.poo.ubomb.go.decor.Box;
import fr.ubx.poo.ubomb.go.decor.Door;
import fr.ubx.poo.ubomb.go.decor.bonus.*;

public class Player extends CharacterMovable implements TakeVisitor {

    private static final int nbKeysDefault = 0;
    private static final int bombRangeDefault = 1;
    private static final int nbBombsDefault = 1;

    private int nbBombs;

    private int bombRange;

    private int nbKeys;

    public Player(Game game, Position position) {
        super(game, position, game.configuration().playerLives());
        nbKeys = nbKeysDefault;
        bombRange = bombRangeDefault;
        nbBombs = nbBombsDefault;
    }

    public int getNbBombs(){
        return nbBombs;
    }

    public int getBombRange(){
        return bombRange;
    }

    public int getNbKeys(){
        return nbKeys;
    }



    @Override
    public void take(Bonus bonus) {
        if(bonus instanceof Key) {
            nbKeys++;
        }
        else if(bonus instanceof BombNumber bn){
            int nb = nbBombs + bn.getDelta();
            if (nb >= 1 && nb <= game.configuration().bombBagCapacity()) nbBombs = nb;
        }
        else if(bonus instanceof BombRange br){
            if (bombRange + br.getDelta() >= 1) bombRange += br.getDelta();
        }
        else if(bonus instanceof Heart){
            this.increaseLives();
        }
        bonus.remove();
    }

    @Override
    public boolean canMove(Direction direction){
        boolean canMove = super.canMove(direction);
        if(!canMove) return false;
        Position nextPos = direction.nextPosition(getPosition());
        GameObject next = game.getNextGo(this.getPosition(), direction);
        if(next instanceof Box){
            nextPos = direction.nextPosition(nextPos);
            next = game.grid().get(nextPos);
            return canMove && next == null && game.grid().inside(nextPos);
        }
        return true;
    }

    @Override
    public void doMove(Direction direction) {
        // This method is called only if the move is possible, do not check again
        Position nextPos = direction.nextPosition(getPosition());
        GameObject next = game.grid().get(nextPos);
        if (next instanceof Bonus bonus) {
                bonus.takenBy(this);
        }
        if (next instanceof Box box){
            box.setNextBoxDirection(direction);
            box.remove();
        }
        setPosition(nextPos);
    }

    public void tryOpenDoor() {
        GameObject next = game.getNextGo(this.getPosition(), this.getDirection());
        if(next instanceof Door door && !door.isOpen() && nbKeys > 0){
            door.open();
            nbKeys --;
        }
    }
}
