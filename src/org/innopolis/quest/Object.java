package org.innopolis.quest;

import java.io.Serializable;

/**
 * Created by krikun on 8/2/2015.
 */
public class Object implements Serializable {
    private int id;
    private String description;
    private boolean picked;

    public Object(int id, String description) {
        this.id = id;
        this.description = description;
        this.picked = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPicked() {
        return picked;
    }

    public void pickUp() {
        picked = true;
    }

    @Override
    public String toString() {
        if (picked) return "";
        return description;
    }
}
