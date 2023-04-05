package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event e;
    private Date d;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e = new Event("Mineral added.");   // (1)
        d = Calendar.getInstance().getTime();   // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Mineral added.", e.getDescription());
        assertEquals(Calendar.getInstance().getTime(), new Event("Mineral removed.").getDate());
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "Mineral added.", e.toString());
    }

    @Test
    public void hashCodeTest() {
        assertEquals(13 * Calendar.getInstance().getTime().hashCode() + "Mineral added.".hashCode(),
                e.hashCode());

    }

}
