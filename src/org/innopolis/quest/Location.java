package org.innopolis.quest;

import java.io.Serializable;

/**
 * Created by krikun on 8/2/2015.
 */
public class Location implements Serializable {
    private int id;
    private int required;
    private String description;
    private Exit[] exits;
    private Character[] characters;
    private Object[] objects;

    public Location(int id, int required, String description, Exit[] exits) {
        this.id = id;
        this.required = required;
        this.description = description;
        this.exits = exits;
    }

    public Location(int id, int required, String description, Exit[] exits, Character[] characters) {
        this(id, required, description, exits);
        this.characters = characters;
    }

    public Location(int id, int required, String description, Exit[] exits, Character[] characters, Object[] objects) {
        this(id, required, description, exits, characters);
        this.objects = objects;
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

    public int getRequired() {
        return required;
    }

    public void setRequired(int required) {
        this.required = required;
    }

    public Character getCharacterByName(String name) {
        for (Character character : characters) {
            if (name.equals(character.toString())) {
                return character;
            }
        }
        return null;
    }

    public Object getObjectByName(String name) {
        for (Object object : objects) {
            if (name.equals(object.toString())) {
                return object;
            }
        }
        return null;
    }

    public void pickUpObject(Object object) {
        for (Object o: objects) {
            if (o.equals(object)) {
                o.pickUp();
            }
        }
    }

    @Override
    public String toString() {
        String locationString = "";
        locationString += description;
        if (characters != null) {
            locationString += "\nCharacters in location:";
            for (Character character : characters) {
                locationString += "\n" + character;
            }
        }
        if (objects != null) {
            locationString += "\nObjects in location:";
            for (Object object : objects) {
                if (object.toString() != "") locationString += "\n" + object;
            }
        }
        locationString += "\nExits in this location:";
        for (int i = 0; i < exits.length; i++) {
            locationString += "\n" + i + ") " + exits[i];
        }
        return locationString;
    }
}
