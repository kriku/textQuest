package org.innopolis.quest;

import java.io.Serializable;

/**
 * Created by krikun on 8/2/2015.
 */
public class Character implements Serializable {
    private int id;
    private String name;
    private boolean enemy;
    private Object[] objects;

    public Character(int id, String name) {
        this.id = id;
        this.name = name;
        this.enemy = false;
        this.objects = null;
    }

    public Character(int id, String name, boolean enemy) {
        this(id, name);
        this.enemy = enemy;
    }

    public Character(int id, String name, boolean enemy, Object[] objects) {
        this(id, name, enemy);
        this.objects = objects;
    }
}
