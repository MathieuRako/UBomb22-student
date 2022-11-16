package fr.ubx.poo.ubomb.launcher;

import fr.ubx.poo.ubomb.game.*;
import fr.ubx.poo.ubomb.go.decor.Door;

import java.text.ParseException;
import java.util.Properties;

public class GameLauncher {

    private static final int bombBagCapacityDefault = 3;
    private static final int levelsDefault = 1;
    private static final boolean compressionDefault = false;
    private static final int playerLivesDefault = 5;
    private static final int playerInvisibilityTimeDefault = 4000;
    private static final int monsterVelocityDefault = 5;
    private static final int monsterInvisibilityTimeDefault = 1000;

    public static Game loadDefault() {
        Configuration configuration = new Configuration(new Position(0, 0), 3, 5, 4000, 5, 1000);
        return new Game(configuration, new Level[]{new Level(new MapLevelDefault())});
    }

    public static Game load(Properties config){
        boolean compression = Boolean.parseBoolean(config.getProperty( "compression")) || compressionDefault;

        String stringPlayerPosition = config.getProperty("player");
        if (stringPlayerPosition == null) throw new MapException("No player position");
        String[] xy = stringPlayerPosition.split("x");
        int x,y;
        try{
            x = Integer.parseInt(xy[0]);
            y = Integer.parseInt(xy[1]);
        }
        catch(Exception e){
            throw new MapException("Player position invalid");
        }
        Position playerPosition = new Position(x,y);

        int bombBagCapacity = integerProperty(config, "bombBagCapacity", bombBagCapacityDefault);
        int playerLives = integerProperty(config, "playerLives", playerLivesDefault);
        int playerInvisibilityTime = integerProperty(config, "playerInvisibilityTimes", playerInvisibilityTimeDefault);
        int monsterVelocity = integerProperty(config,"monsterVelocity", monsterVelocityDefault);
        int monsterInvisibilityTime = integerProperty(config,"monsterInvisibilityTime", monsterInvisibilityTimeDefault);
        Configuration configuration = new Configuration(playerPosition,bombBagCapacity,playerLives,playerInvisibilityTime,monsterVelocity,monsterInvisibilityTime);
        int nbLevels = integerProperty(config, "levels", levelsDefault);
        Grid[] grids = new Grid[nbLevels];

        MapLevelMaker mlm = new MapLevelMaker();
        Boolean isPrincess = false;
        Door lastDoor = null;
        for(int i = 1; i <= nbLevels; i++){
            MapLevel ml = mlm.load(config.getProperty("level" + i), compression);
            Level level = new Level(ml);
            if(level.isPrincess()){
                if (isPrincess) throw new MapException("Only one princess per game");
                else isPrincess = true;
            }
            if(level.isDoorNext() && i == nbLevels) throw new MapException("No up door at the last floor");
            else if(!level.isDoorNext() && i != nbLevels) throw new MapException("An up door is needed at every floor that isn't last");
            else if(level.isDoorNext()) lastDoor = level.getDoorNext();
            if(level.isDoorPrevious() && i == 1) throw new MapException("No down door at first floor");
            else if(!level.isDoorPrevious() && i != 1) throw new MapException("A down door is needed at every floor that isn't first");
            else if(level.isDoorPrevious()) level.set(level.getDoorPrevious(),lastDoor);




            grids[i-1] = level;

        }
        if(!isPrincess) throw new MapException("A princess is needed");
        return new Game(configuration, grids);
    }


    private static int integerProperty(Properties config, String key, int defaultValue){
        int value;
        try{
            value = Integer.parseInt(config.getProperty(key, defaultValue + ""));
        }
        catch(NumberFormatException e){
            throw new ConfigException(key);
        }
        return value;
    }


}
