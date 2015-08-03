package org.innopolis.quest;

import java.io.*;

/**
 * class for store game environment, stage
 * Created by krikun on 8/2/2015.
 */
public class Game implements Serializable {
    private Location[] locations;
    private Object[] objects;
    private Character[] characters;

    private String name;
    private Player player;

    private boolean play;

    public Game(boolean play, String name) {
        this.play = play;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location[] getLocations() {
        return locations;
    }

    public void setLocations(Location[] locations) {
        this.locations = locations;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Object[] getObjects() {
        return objects;
    }

    public void setObjects(Object[] objects) {
        this.objects = objects;
    }

    public Character[] getCharacters() {
        return characters;
    }

    public void setCharacters(Character[] characters) {
        this.characters = characters;
    }

    public boolean getPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    public void save(String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.close();
        fos.close();
    }

    public Game load(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Game game = (Game) ois.readObject();
        ois.close();
        fis.close();
        return game;
    }

    public Object getObjectById(int id) {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i].getId() == id) return objects[i];
        }
        return null;
    }

}
