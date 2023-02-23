package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test Folder Class
public class FolderTest {
    private Mineral testMineralA;
    private Mineral testMineralB;
    private Folder testFolder;

    @BeforeEach
    void runBefore() {
        testMineralA = new Mineral();
        testMineralA.setLab(1);
        testMineralA.setName("A");
        testMineralA.setColor("green");
        testMineralA.setHardness(4);
        testMineralA.setCrystalSystem("isometric");

        testMineralB = new Mineral();
        testMineralB.setLab(2);
        testMineralB.setName("B");
        testMineralB.setColor("blue");
        testMineralB.setHardness(7);
        testMineralB.setCrystalSystem("hexagonal");

        testFolder = new Folder();
    }

    // Test add mineral to list
    @Test
    void testAddMineralToMineralList() {
        testFolder.addToMineralList(testMineralA);
        assertTrue(testFolder.checkInMineralList(testMineralA.getName()));
        testFolder.addToMineralList(testMineralB);
        assertTrue(testFolder.checkInMineralList(testMineralB.getName()));
        assertEquals(2, testFolder.getMineralList().size());
    }

    // Test that the randomly selected mineral is a member of the list when list has 1 element
    @Test
    void testNextStudyMineralOne() {
        testFolder.addToMineralList(testMineralA);
        assertTrue(testFolder.checkInMineralList(testFolder.nextStudyMineral().getName()));
    }

    // Test that the randomly selected mineral is a member of the list when list has 2 elements
    @Test
    void testNextStudyMineralTwo() {
        testFolder.addToMineralList(testMineralA);
        testFolder.addToMineralList(testMineralB);
        assertTrue(testFolder.checkInMineralList(testFolder.nextStudyMineral().getName()));
        assertTrue(testFolder.checkInMineralList(testFolder.nextStudyMineral().getName()));
    }

    // Test that random selection doesn't remove mineral from list - will keep selecting if there is only one mineral
    @Test
    void testNextStudyMineralContinue() {
        testFolder.addToMineralList(testMineralA);
        assertTrue(testFolder.checkInMineralList(testFolder.nextStudyMineral().getName()));
        assertTrue(testFolder.checkInMineralList(testFolder.nextStudyMineral().getName()));
        assertTrue(testFolder.checkInMineralList(testFolder.nextStudyMineral().getName()));
    }

    // Test mineral list not empty with one element
    @Test
    void testMineralListNotEmpty1() {
        assertFalse(testFolder.mineralListNotEmpty());
        testFolder.addToMineralList(testMineralA);
        assertTrue(testFolder.mineralListNotEmpty());
    }

    // Test mineral list is not empty with two elements
    @Test
    void testMineralListNotEmpty2() {
        assertFalse(testFolder.mineralListNotEmpty());
        testFolder.addToMineralList(testMineralA);
        testFolder.addToMineralList(testMineralB);
        assertTrue(testFolder.mineralListNotEmpty());
    }

    // Test if remove from list will not do anything if list is empty
    @Test
    void testRemoveFromMineralListEmpty() {
        assertFalse(testFolder.mineralListNotEmpty());
        assertEquals(0, testFolder.getMineralList().size());
        testFolder.removeFromMineralList(testMineralA);
        assertEquals(0, testFolder.getMineralList().size());
    }

    // Test if element is removed from list with 1 element
    @Test
    void testRemoveFromMineralListElement() {
        testFolder.addToMineralList(testMineralA);
        assertTrue(testFolder.checkInMineralList(testMineralA.getName()));
        testFolder.removeFromMineralList(testMineralA);
        assertFalse(testFolder.checkInMineralList(testMineralA.getName()));
    }

    // Test that correct element is removed in list with more than one element
    @Test
    void testRemoveFromMineralListTwoElements() {
        testFolder.addToMineralList(testMineralA);
        testFolder.addToMineralList(testMineralB);
        assertTrue(testFolder.checkInMineralList(testMineralA.getName()));
        assertTrue(testFolder.checkInMineralList(testMineralB.getName()));
        testFolder.removeFromMineralList(testMineralB);
        assertTrue(testFolder.checkInMineralList(testMineralA.getName()));
        assertFalse(testFolder.checkInMineralList(testMineralB.getName()));
    }
}
