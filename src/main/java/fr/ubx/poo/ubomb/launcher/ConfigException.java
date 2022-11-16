package fr.ubx.poo.ubomb.launcher;

public class ConfigException extends RuntimeException{
    public ConfigException(String key) {
        super("Key" + key + "unvalid");
    }
}
