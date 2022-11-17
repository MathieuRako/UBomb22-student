package fr.ubx.poo.ubomb.game;

import fr.ubx.poo.ubomb.go.decor.bonus.*;
import fr.ubx.poo.ubomb.go.decor.*;
import fr.ubx.poo.ubomb.launcher.Entity;
import fr.ubx.poo.ubomb.launcher.MapException;
import fr.ubx.poo.ubomb.launcher.MapLevel;

import java.util.*;

public class Level implements Grid {

    private final int width;

    private final int height;

    private final MapLevel entities;



    private boolean isPrincess;

    private Door doorToDown;
    private Door doorToUp;
    private final Map<Position, Decor> elements = new HashMap<>();

    private final List<Position> boxes = new LinkedList<>();
    private final List<Position> monsterPositions = new LinkedList<Position>();
    public Level(MapLevel entities) {
        this.entities = entities;
        this.width = entities.width();
        this.height = entities.height();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Position position = new Position(i, j);
                Entity entity = entities.get(i, j);
                switch (entity) {
                    case Stone:
                        elements.put(position, new Stone(position));
                        break;
                    case Tree:
                        elements.put(position, new Tree(position));
                        break;
                    case Key:
                        elements.put(position, new Key(position));
                        break;
                    case Princess:
                        if(!isPrincess) {
                            isPrincess = true;
                            elements.put(position, new Princess(position));
                        }
                        else{
                            throw new MapException("Only one princess for a level");
                        }
                        break;
                    case Box:
                        elements.put(position, new Box(position));
                        break;
                    case BombRangeDec:
                        elements.put(position, new BombRange(position, false));
                        break;
                    case BombRangeInc:
                        elements.put(position, new BombRange(position, true));
                        break;
                    case Heart:
                        elements.put(position, new Heart(position));
                        break;
                    case BombNumberDec:
                        elements.put(position, new BombNumber(position, false));
                        break;
                    case BombNumberInc:
                        elements.put(position, new BombNumber(position, true));
                        break;
                    case DoorNextClosed:
                        if(!isDoorToUp()) {
                            Door door = new Door(position, false);
                            elements.put(position, door);
                            doorToUp = door;
                        }
                        else throw new MapException("Only one up door for a level");
                        break;
                    case DoorNextOpened:
                        if(!isDoorToUp()) {
                            Door door = new Door(position, true);
                            elements.put(position, door);
                            doorToUp = door;
                        }
                        else throw new MapException("Only one up door for a level");
                        break;
                    case DoorPrevOpened:
                        if(!isDoorToDown()) {
                            Door door = new Door(position);
                            elements.put(position, door);
                            this.doorToDown = door;

                        }
                        else throw new MapException("Only one down door for a level");
                        break;
                    case Monster:
                        monsterPositions.add(position);
                        break;
                    case Empty:
                        break;
                    default:
                        throw new RuntimeException("EntityCode " + entity.name() + " not processed");
                }
            }
        }
    }

    @Override
    public int width() {
        return this.width;
    }

    @Override
    public int height() {
        return this.height;
    }

    @Override
    public Decor get(Position position) {
        return elements.get(position);
    }

    @Override
    public void remove(Position position) {
        elements.remove(position);
    }

    @Override
    public Collection<Decor> values() {
        return elements.values();
    }

    @Override
    public List<Position> getMonsterPositions(){
        return monsterPositions;
    }

    @Override
    public List<Position> getBoxesPositions(){
        return boxes;
    }

    public boolean isPrincess(){
        return isPrincess;
    }

    private boolean isDoorToUp(){
        return doorToUp != null ;
    }

    private boolean isDoorToDown(){
        return doorToDown != null;
    }

    public Door getDoorToUp(){
        return doorToUp;
    }

    public Door getDoorToDown(){
        return doorToDown;
    }
    @Override
    public boolean inside(Position position) {
        return position.x() >= 0 && position.x() < width && position.y() >= 0 && position.y() < height;
    }

    @Override
    public void set(Position position, Decor decor) {
        if (!inside(position))
            throw new IllegalArgumentException("Illegal Position");
        if (decor != null)
            elements.put(position, decor);
    }


}
