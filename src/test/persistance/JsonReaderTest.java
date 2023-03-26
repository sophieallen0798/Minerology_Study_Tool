package persistance;

import model.Folder;
import model.LearnedFolder;
import model.Mineral;
import model.ReviewFolder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// SOURCE: Code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Test class for json reader methods
public class JsonReaderTest {

    Folder rev = new ReviewFolder();
    Folder ler = new LearnedFolder();

    // Test attempt to read a file that doesn't exist, IOException expected, fail if not caught
    @Test
    void testReadFileDNE() {
        JsonReader reader = new JsonReader("./data/fileDNE.json");
        try {
            Folder rev = reader.revRead();
            fail("IO exception not caught");
        } catch (IOException e) {
            // test passes
        }
    }

    // Test reading a file with an empty folder, IOException not expected, fail if thrown
    @Test
    void testEmptyFolder() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyFolder.json");
        try {
            Folder ler = reader.lerRead();
            assertEquals("Learned Folder", ler.getName());
        } catch (IOException e) {
            fail("Couldn't read empty folder from file");
        }
    }

    // Test reading a general folder from a file - fail if exception is thrown
    @Test
    void testReaderNormalFolder() {
        JsonReader reader = new JsonReader("./data/testReaderNormalFolder.json");
        try {
            Folder rev = reader.revRead();
            assertEquals("Review Folder", rev.getName());
            List<Mineral> mineralList = rev.getMineralList();
            assertEquals(2, mineralList.size());
            Mineral min1 = mineralList.get(0);
            Mineral min2 = mineralList.get(1);
            assertEquals("mica", min1.getName());
            assertEquals("quartz", min2.getName());

        } catch (IOException e) {
            fail("Couldn't read non-empty folder from file");
        }
    }


}
