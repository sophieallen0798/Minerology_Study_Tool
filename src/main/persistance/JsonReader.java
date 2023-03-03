package persistance;

import model.Folder;
import model.Mineral;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// SOURCE: code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Reader that reads folders from JSON data stored in learned and review files
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads folder from file, returns folder
    // throws IOException if an error occurs reading data from file
    public Folder read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFolder(jsonObject);
    }

    // EFFECTS: reads source file as string, returns the string
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses folder from JSON object and returns the folder
    private Folder parseFolder(JSONObject jsonObject) {
        String folderName = jsonObject.getString("name");
        Folder folder = new Folder(folderName);
        addFolders(folder, jsonObject);
        return folder;
    }

    // MODIFIES: folder
    // EFFECTS: parses minerals from JSON object and adds the minerals to the folder
    private void addFolders(Folder f, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("minerals");

        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addMineral(f, nextThingy);
        }
    }

    // MODIFIES: folder
    // EFFECTS: parses mineral from JSON object and adds it to the folder
    private void addMineral(Folder folder, JSONObject jsonObject) {
        Mineral mineral = new Mineral();
        Integer lab = jsonObject.getInt("lab");
        String name = jsonObject.getString("name");
        String color = jsonObject.getString("color");
        Integer hardness = jsonObject.getInt("hardness");
        String crystalSystem = jsonObject.getString("crystalSystem");

        mineral.setLab(lab);
        mineral.setName(name);
        mineral.setColor(color);
        mineral.setHardness(hardness);
        mineral.setCrystalSystem(crystalSystem);

        folder.addToMineralList(mineral);
    }

}
