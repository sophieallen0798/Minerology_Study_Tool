package persistance;

import model.Folder;
import model.Mineral;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.fail;

// tests adapted from:
// SOURCE: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReaderTest {

    @Test
    void testReadFileDNE() {
        JsonReader reader = new JsonReader("./data/fileDNE.json");
        try {
            Folder folder = reader.read();
            fail("IO exception not caught");
        } catch (IOException e) {
            // test passes
        }
    }

    @Test
    void testEmptyFolder() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyFolder.json");
        try {
            Folder folder = reader.read();
            assertEquals("review", folder.getName());
        } catch (IOException e) {
            fail("Couldn't read empty folder from file");
        }
    }

    @Test
    void testReaderNormalFolder() {
        JsonReader reader = new JsonReader("./data/testReaderNormalFolder.json");
        try {
            Folder folder = reader.read();
            assertEquals("review", folder.getName());
            List<Mineral> mineralList = folder.getMineralList();
            assertEquals(2, mineralList.size());
            Mineral min0 = mineralList.get(0);
            assertEquals("mica", min0.getName());
            assertEquals("mica", min0.getName());

        } catch (IOException e) {
            fail("Couldn't read non-empty folder from file");
        }
    }


}
