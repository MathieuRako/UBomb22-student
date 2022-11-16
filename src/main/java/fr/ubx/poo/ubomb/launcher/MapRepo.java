package fr.ubx.poo.ubomb.launcher;

public interface MapRepo {

    MapLevel load(String string, boolean Compression);

    String export(MapLevel mapLevel);
}
