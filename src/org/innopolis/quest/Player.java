package org.innopolis.quest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by krikun on 8/2/2015.
 */
public class Player implements Serializable {
    private String name;
    private Set<Integer> inventory = new HashSet<>();
    private int location;

    public Player(String name) {
        this.name = name;
        this.location = 0;
    }

    public String getName() {
        return name;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public void addObject(Object object) {
        inventory.add(object.getId());
    }

    public boolean hasItem(int objectId) {
        return inventory.contains(objectId);
    }
}
