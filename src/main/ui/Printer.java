package ui;

import model.Event;
import model.EventLog;

/**
 * Represents a screen printer for printing event log to screen.
 */
public class Printer implements LogPrinter {

    public Printer(MineralApp mineralApp) {

    }

    @Override
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }
}
