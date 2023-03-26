package persistance;

import model.Folder;
import model.LearnedFolder;
import model.Mineral;
import model.ReviewFolder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// SOURCE: Code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Reader that reads folders from JSON data stored in learned and review files
public class JsonReader {
    private String source;

    // EFFECTS: Constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: Reads folder from file, returns folder, throws IOException if an error occurs reading data from file
    public ReviewFolder revRead() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRevFolder(jsonObject);
    }

    public LearnedFolder lerRead() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLerFolder(jsonObject);
    }

    // EFFECTS: Reads source file as string, returns the string
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: Parses folder from JSON object and returns the folder
    private ReviewFolder parseRevFolder(JSONObject jsonObject) {
        String folderName = jsonObject.getString("name");
        ReviewFolder reFolder = new ReviewFolder();
        addFolders(reFolder, jsonObject);
        return reFolder;
    }

    private LearnedFolder parseLerFolder(JSONObject jsonObject) {
        String folderName = jsonObject.getString("name");
        LearnedFolder leFolder = new LearnedFolder();
        addFolders(leFolder, jsonObject);
        return leFolder;
    }

    // MODIFIES: folder
    // EFFECTS: Parses minerals from JSON object and adds the minerals to the folder
    private void addFolders(Folder f, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("minerals");

        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addMineral(f, nextThingy);
        }
    }

    // MODIFIES: folder
    // EFFECTS: Parses mineral from JSON object and adds it to the folder
    private void addMineral(Folder folder, JSONObject jsonObject) {
        Mineral m = new Mineral();
        int lab = jsonObject.getInt("lab");
        String name = jsonObject.getString("name");
        String lustre = jsonObject.getString("lustre");
        String color = jsonObject.getString("color");
        String streak = jsonObject.getString("streak");
        String hardness = jsonObject.getString("hardness");
        String sp = jsonObject.getString("specificGravity");
        String cleavage = jsonObject.getString("cleavage");
        String fracture = jsonObject.getString("fracture");
        String habit = jsonObject.getString("habit");
        String cs = jsonObject.getString("crystalSystem");
        String other = jsonObject.getString("other");
        addMineralProperties(folder, m, lab, name, lustre, color, streak, hardness, sp, cleavage, fracture, habit, cs,
                other);
    }

    // EFFECTS: set mineral properties
    private void addMineralProperties(Folder folder, Mineral m, int lab, String name, String lustre, String color,
                                      String streak, String hardness, String sp, String cleavage, String fracture,
                                      String habit, String cs, String other) {
        m.setLab(lab);
        m.setName(name);
        m.setLustre(lustre);
        m.setColor(color);
        m.setStreak(streak);
        m.setHardness(hardness);
        m.setSpecificGravity(sp);
        m.setCleavage(cleavage);
        m.setFracture(fracture);
        m.setHabit(habit);
        m.setCrystalSystem(cs);
        m.setOther(other);
        folder.addToMineralList(m);
    }

}
