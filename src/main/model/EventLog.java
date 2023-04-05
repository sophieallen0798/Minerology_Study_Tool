package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// Code adapted from https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git

// EventLog tracking events occuring while application runs - created using singleton design pattern
public class EventLog implements Iterable<model.Event> {
    private static EventLog theLog;
    private Collection<model.Event> events;

    // EFFECTS: Constructs EventLog with list of events - Prevent external construction.
    private EventLog() {
        events = new ArrayList<model.Event>();
    }

    // EFFECTS: Gets instance of EventLog
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }
        return theLog;
    }

    // EFFECTS: Adds event e to the event log
    public void logEvent(model.Event e) {
        events.add(e);
    }

    // EFFECTS: Clears the event log and events
    public void clear() {
        events.clear();
        logEvent(new model.Event("Event log cleared."));
    }

    // EFFECTS: Iterates over EventLog
    @Override
    public Iterator<model.Event> iterator() {
        return events.iterator();
    }
}
