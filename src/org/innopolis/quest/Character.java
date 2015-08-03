package org.innopolis.quest;

import java.io.Serializable;

/**
 * Created by krikun on 8/2/2015.
 */
public class Character implements Serializable {
    private int id;
    private String description;
    private boolean enemy;
    private Object[] objects;
    private boolean alive;

    public Character(int id, String description) {
        this.id = id;
        this.description = description;
        this.enemy = false;
        this.objects = null;
        this.alive = true;
    }

    public Character(int id, String description, boolean enemy) {
        this(id, description);
        this.enemy = enemy;
    }

    public Character(int id, String description, boolean enemy, Object[] objects) {
        this(id, description, enemy);
        this.objects = objects;
    }

    public Object[] getObjects() {
        return objects;
    }

    public void killHim() {
        this.alive = false;
    }

    @Override
    public String toString() {
        if (!alive) return "corp of " + description;
        return description;
    }
}
