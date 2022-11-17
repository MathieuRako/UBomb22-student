package fr.ubx.poo.ubomb.game;

import fr.ubx.poo.ubomb.go.Bomb;
import fr.ubx.poo.ubomb.go.decor.Box;
import fr.ubx.poo.ubomb.go.GameObject;
import fr.ubx.poo.ubomb.go.character.Monster;
import fr.ubx.poo.ubomb.go.character.Player;
import fr.ubx.poo.ubomb.go.decor.Door;
import fr.ubx.poo.ubomb.launcher.MapException;


import java.util.LinkedList;
import java.util.List;

public class Game {

    private final Configuration configuration;

    private final Player player;
    private final Grid[] grids;

    private int level;
    private final List<Monster>[] monsters;

    private List<Bomb> bombs= new LinkedList<Bomb>();

    public Game(Configuration configuration, Grid[] grids) {
        this.configuration = configuration;
        monsters = new LinkedList[grids.length];
        this.grids = grids;
        this.level = 1;

        for(int i = 0; i < grids.length; i++){
            {
                monsters[i] = new LinkedList<>();
                for (var position : grids[i].getMonsterPositions()){
                    monsters[i].add(new Monster(this, position, 1));}

            }
        }

        player = new Player(this, configuration.playerPosition());
    }

    public Position changeLevel(Door door){
        Boolean goUp = door.goUp();
        try {
            if (goUp){
                level ++;
                return grids[level].getDoorToDown().getPosition();
            }
            level --;
            return grids[level].getDoorToUp().getPosition();
        }
        catch(ArrayIndexOutOfBoundsException e){
            throw new MapException("Door out of levels");
        }
    }

    public GameObject getNextGo(Position position, Direction direction){
        Position nextPos = direction.nextPosition(position);
        return this.grid().get(nextPos);
    }
    public int getLevel(){
        return level;
    }

    public void updateLevel(boolean up){
        System.out.println(level);
        System.out.println(grids.length);
        System.out.println(up);
        if(up && level + 1 <= grids.length) level++;
        else if(!up && level - 1 >= 1) level--;
        else throw new RuntimeException("Level update impossible : level out of bounds");
    }

    public Configuration configuration() {
        return configuration;
    }

    // Returns the player, monsters and bomb at a given position
    public List<GameObject> getGameObjects(Position position) {
        List<GameObject> gos = new LinkedList<>();
        if (player().getPosition().equals(position))
            gos.add(player);
        for(var monster : monsters[level-1]) {
            if (monster.getPosition().equals(position)) {
                gos.add(monster);
            }
        }
        for(var bomb : bombs){
            if (bomb.getPosition().equals(position)){
                gos.add(bomb);
            }
        }
        return gos;
    }


    public Grid grid() {
        return grids[level-1];
    }

    public Player player() {
        return this.player;
    }


    public List<Monster> getMonster() {
        return monsters[level-1];
    }
}
