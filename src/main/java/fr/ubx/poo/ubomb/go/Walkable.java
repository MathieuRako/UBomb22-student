package fr.ubx.poo.ubomb.go;

import fr.ubx.poo.ubomb.go.character.CharacterMovable;

public interface Walkable {
    default boolean walkableBy(CharacterMovable character) { return false; }
}
