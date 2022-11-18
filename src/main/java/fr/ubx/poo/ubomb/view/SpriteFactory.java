/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubomb.view;

import fr.ubx.poo.ubomb.go.Bomb;
import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.decor.bonus.*;
import fr.ubx.poo.ubomb.go.decor.*;
import fr.ubx.poo.ubomb.go.decor.Box;
import javafx.scene.layout.Pane;

import static fr.ubx.poo.ubomb.view.ImageResource.*;


public final class SpriteFactory {

    public static Sprite create(Pane layer, GameObject gameObject) {
        if (gameObject instanceof Stone)
            return new Sprite(layer, STONE.getImage(), gameObject);
        if (gameObject instanceof Tree)
            return new Sprite(layer, TREE.getImage(), gameObject);
        if (gameObject instanceof Princess)
            return new Sprite(layer, PRINCESS.getImage(), gameObject);
        if (gameObject instanceof Door door){
            return new SpriteDoor(layer,door);
        }
        if (gameObject instanceof Box)
            return new Sprite(layer, BOX.getImage(), gameObject);
        if (gameObject instanceof BombNumber){
            BombNumber bn = (BombNumber) gameObject;
            if(!bn.isIncrease()) return new Sprite(layer, BONUS_BOMB_NB_DEC.getImage(),gameObject);
            else return new Sprite(layer, BONUS_BOMB_NB_INC.getImage(), gameObject);
        }
        if (gameObject instanceof BombRange){
            BombRange br = (BombRange) gameObject;
            if(br.isIncrease()) return new Sprite(layer, BONUS_BOMB_RANGE_INC.getImage(), gameObject);
            else return new Sprite(layer, BONUS_BOMB_RANGE_DEC.getImage(), gameObject);
        }
        if (gameObject instanceof Heart){
            return new Sprite(layer, HEART.getImage(), gameObject);
        }
        if (gameObject instanceof Key)
            return new Sprite(layer, KEY.getImage(), gameObject);
        if (gameObject instanceof Bomb bomb)
            return new Sprite(layer, ImageResourceFactory.getBomb(bomb.getState()).getImage(), bomb);
        throw new RuntimeException("Unsupported sprite for decor " + gameObject);
    }
}
