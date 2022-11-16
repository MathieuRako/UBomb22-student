package fr.ubx.poo.ubomb.view;

import fr.ubx.poo.ubomb.go.character.Monster;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class SpriteMonster extends SpriteCharacter{
    public SpriteMonster(Pane layer, Monster monster) {
        super(layer, monster);
    }

    @Override
    public void updateImage() {
        Monster monster = (Monster) getGameObject();
        Image image = ImageResourceFactory.getMonster(monster.getDirection()).getImage();
        setImage(image);
    }
}
