package fr.ubx.poo.ubomb.view;

import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.decor.Door;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import static fr.ubx.poo.ubomb.view.ImageResource.DOOR_CLOSED;
import static fr.ubx.poo.ubomb.view.ImageResource.DOOR_OPENED;

public class SpriteDoor extends Sprite{
    public SpriteDoor(Pane layer, Door door){
        super(layer, null, door);
        this.updateImage();
    }

    @Override
    public void updateImage() {
        if(((Door)getGameObject()).isOpen()) this.setImage(DOOR_OPENED.getImage());
        else this.setImage(DOOR_CLOSED.getImage());
    }

}
