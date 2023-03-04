package persistance;

import model.Folder;
import model.Mineral;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Class and methods adapted from:
// SOURCE: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Test class for jason writer
public class JsonWriterTest {
    private Folder folder;
    private Mineral testMin1;
    private Mineral testMin2;

    // Create new folder and 2 minerals, specify mineral names
    @BeforeEach
    void runBefore() {
        folder = new Folder("review");
        testMin1 = new Mineral();
        testMin2 = new Mineral();
        testMin1.setName("mica");
        testMin2.setName("quartz");

    }

    // Test write to an invalid file, expect IOException, if not caught, fail
    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/folder\0illegal:fileName.json");
            writer.open();
            fail("IOException expected but not found");
        } catch (IOException e) {
            // test passed, exception caught
        }
    }

    // Test write to empty folder, expect that exception is not thrown, fail if thrown
    @Test
    void testWriterEmptyFolder() {
        try {
            JsonWriter writer = new JsonWriter("./data/testReaderEmptyFolder.json");
            writer.open();
            writer.write(folder);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderEmptyFolder.json");
            folder = reader.read();
            assertEquals("review", folder.getName());
            assertEquals(0, folder.getMineralList().size());
        } catch (IOException e) {
            fail("Unexpected exception thrown on writing empty folder");
        }
    }

    // Test write to general folder, folder has 2 minerals, exception not expected, fail if thrown
    @Test
    void testWriterNormalFolder() {
        try {
            folder.addToMineralList(testMin1);
            folder.addToMineralList(testMin2);
            JsonWriter writer = new JsonWriter("./data/testReaderNormalFolder.json");
            writer.open();
            writer.write(folder);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderNormalFolder.json");
            folder = reader.read();
            List<Mineral> mineralList = folder.getMineralList();
            assertEquals("review", folder.getName());
            assertEquals("mica", mineralList.get(0).getName());
            assertEquals("quartz", mineralList.get(1).getName());

        } catch (IOException e) {
            fail("Unexpected exception thrown writing normal folder");
        }
    }

}
