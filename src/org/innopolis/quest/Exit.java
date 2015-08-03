package org.innopolis.quest;

import java.io.Serializable;

/**
 * Created by krikun on 8/2/2015.
 */
public class Exit implements Serializable {
    private int location;
    private int required;
    private String description;

    public Exit(int location, String description) {
        this.location = location;
        this.required = -1;
        this.description = description;
    }

    public Exit(int location, int required, String description) {
        this.location = location;
        this.required = required;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getRequired() {
        return required;
    }

    @Override
    public String toString() {
        return description;
    }
}
