package org.innopolis.quest;

import java.io.Serializable;
import java.lang.Character;

/**
 * Created by krikun on 8/2/2015.
 */
public class Location implements Serializable {
    private int id;
    private String description;
    private Exit[] exits;
    private Character[] characters;
    private Object[] objects;

    public Location(int id, String description, Exit[] exits) {
        this.description = description;
        this.id = id;
        this.exits = exits;
    }

    public Location(int id, String description, Exit[] exits, Character[] characters) {
        this.description = description;
        this.id = id;
        this.exits = exits;
        this.characters = characters;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Exit[] getExits() {
        return exits;
    }

    public void setExits(Exit[] exits) {
        this.exits = exits;
    }

    public Character[] getCharacters() {
        return characters;
    }

    public void setCharacters(Character[] characters) {
        this.characters = characters;
    }

    public Object[] getObjects() {
        return objects;
    }

    public void setObjects(Object[] objects) {
        this.objects = objects;
    }

    @Override
    public String toString() {
        String locationString = "";
        locationString += description;
        for (int i = 0; i < exits.length; i++) {
            locationString += "\n" + i + ") " + exits[i];
        }
        return locationString;
    }
}
