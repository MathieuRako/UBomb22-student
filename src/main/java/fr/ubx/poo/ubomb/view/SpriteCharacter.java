package fr.ubx.poo.ubomb.view;

import fr.ubx.poo.ubomb.go.character.CharacterMovable;

import javafx.scene.layout.Pane;

public class SpriteCharacter extends Sprite{
    public SpriteCharacter(Pane layer, CharacterMovable character) {
        super(layer, null, character);
        updateImage();
    }
}
