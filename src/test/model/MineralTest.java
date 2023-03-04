package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Test Mineral class methods
public class MineralTest {
    private Mineral testMineral;


    @BeforeEach
    void runBefore() {
        testMineral = new Mineral();
    }

    // Test set, get lab
    @Test
    void testLab() {
        testMineral.setLab(1);
        assertEquals(1,testMineral.getLab());
    }

    // Test set, get name
    @Test
    void testName() {
        testMineral.setName("Mica");
        assertEquals("Mica",testMineral.getName());
    }

    // Test set, get color
    @Test
    void testColor() {
        testMineral.setColor("Silver");
        assertEquals("Silver",testMineral.getColor());
    }

    // Test set, get hardness
    @Test
    void testHardness() {
        testMineral.setHardness(4);
        assertEquals(4,testMineral.getHardness());
    }

    // Test set, get crystal system
    @Test
    void testCrystalSystem() {
        testMineral.setCrystalSystem("isometric");
        assertEquals("isometric",testMineral.getCrystalSystem());
    }

    // Test get crystal system name
    @Test
    void testCrystalSystemName() {
        assertEquals("isometric", testMineral.crystalSystemName("i"));
        testMineral.setCrystalSystem("i");
        assertEquals("isometric", testMineral.getCrystalSystem());
    }

    // Test crystal system change name
    @Test
    void testCrystalSystemNameChange() {
        assertEquals("tetragonal", testMineral.crystalSystemName("tetra"));
        testMineral.setCrystalSystem("tetra");
        assertEquals("tetragonal", testMineral.getCrystalSystem());

        assertEquals("hexagonal", testMineral.crystalSystemName("h"));
        testMineral.setCrystalSystem("h");
        assertEquals("hexagonal", testMineral.getCrystalSystem());
    }

    // Test remaining crystal systems
    @Test
    void testCrystalSystemNames() {
        assertEquals("triclinic", testMineral.crystalSystemName("tri"));
        testMineral.setCrystalSystem("tri");
        assertEquals("triclinic", testMineral.getCrystalSystem());

        assertEquals("monoclinic", testMineral.crystalSystemName("m"));
        testMineral.setCrystalSystem("m");
        assertEquals("monoclinic", testMineral.getCrystalSystem());

        assertEquals("orthorhombic", testMineral.crystalSystemName("o"));
        testMineral.setCrystalSystem("o");
        assertEquals("orthorhombic", testMineral.getCrystalSystem());
    }

}
