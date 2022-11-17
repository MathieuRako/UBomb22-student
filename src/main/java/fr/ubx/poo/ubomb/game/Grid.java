package fr.ubx.poo.ubomb.game;


import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.decor.Decor;
import fr.ubx.poo.ubomb.go.decor.Door;

import java.util.*;

public interface Grid {
    int width();
    int height();

    Decor get(Position position);

    void remove(Position position);

    Door getDoorToUp();

    Door getDoorToDown();
    Collection<Decor> values();

    boolean inside(Position nextPos);

    void set(Position position, Decor decor);


    List<Position> getMonsterPositions();
    List<Position> getBoxesPositions();
}
