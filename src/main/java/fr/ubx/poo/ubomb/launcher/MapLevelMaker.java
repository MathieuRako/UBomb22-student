package fr.ubx.poo.ubomb.launcher;

public class MapLevelMaker implements MapRepo{
    public MapLevel load(String string, boolean compression){
        if(compression){
            string = unCompress(string);
        }
        System.out.println(string);
        int width = string.indexOf('x');
        if(width == -1) throw new MapException("No x in the level");
        int height = string.length() / (width + 1);
        MapLevel ml = new MapLevel(width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ml.set(i, j, Entity.fromCode(string.charAt(j * width + i + j)));
            }
        }
        return ml;
    }

    private String unCompress(String string){
        StringBuilder unCompressed = new StringBuilder();
        for(int i = 0; i < string.length(); i++){
            char c = string.charAt(i);
            if(c >= 48 && c <= 57){
                if(i == 0) throw new MapException("One of the levels is invalid (start by a number)");
                int nb = Integer.parseInt(c + "");
                for(int j = 0; j < nb-1; j++) unCompressed.append(string.charAt(i-1));
            }
            else{
                unCompressed.append(c);
            }
        }
        return unCompressed.toString();
    }

    public String export(MapLevel mapLevel){
        return "TODO";
    }
}
