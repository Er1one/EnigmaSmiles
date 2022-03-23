package ru.er1one.enigmasmiles.models;

public class Emoji {

    private final String output;
    private final String permission;

    public Emoji(String output, String permission) {
        this.output = output;
        this.permission = permission;
    }

    public String getOutput() {
        return output;
    }

    public String getPermission() {
        return permission;
    }
}
