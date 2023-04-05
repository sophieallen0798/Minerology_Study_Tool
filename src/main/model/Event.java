package model;

import java.util.Calendar;
import java.util.Date;


// Code adapted from: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git

// Mineral App event
public class Event {
    private static final int HASH_CONSTANT = 13;
    private Date dateLogged;
    private String description;

    // EFFECTS: Creates an event with current date, time, and description of event
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    // EFFECTS: Gets data and time of the event
    public Date getDate() {
        return dateLogged;
    }

    // EFFECTS: Gets the description of this event.
    public String getDescription() {
        return description;
    }

    // EFFECTS: Returns true if objects are equal and are of type Event
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }

        Event otherEvent = (Event) other;

        return (this.dateLogged.equals(otherEvent.dateLogged)
                &&
                this.description.equals(otherEvent.description));
    }

    // EFFECTS: Creates hash code to print event
    @Override
    public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }

    // EFFECTS: Returns date and description as string
    @Override
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }

}
