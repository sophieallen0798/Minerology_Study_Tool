package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReviewFolder implements Folder {
    protected List<Mineral> mineralList;
    protected String name;
    private JsonWriter jsonWriterRev;
    private JsonReader jsonReaderRev;
    private JsonWriter jsonWriterLearn;
    private JsonReader jsonReaderLearn;
    private static final String JSON_FOLDERS_R = "./data/review.json";
    private static final String JSON_FOLDERS_L = "./data/learned.json";

    // EFFECTS: Constructs a folder with a name and list of minerals
    public ReviewFolder(String name) {
        this.name = name;
        this.mineralList = new ArrayList<>();
        jsonReaderRev = new JsonReader(JSON_FOLDERS_R);
        jsonWriterRev = new JsonWriter(JSON_FOLDERS_R);
        jsonReaderLearn = new JsonReader(JSON_FOLDERS_L);
        jsonWriterLearn = new JsonWriter(JSON_FOLDERS_L);
    }

    public String getName() {
        return name;
    }

    public List<Mineral> getMineralList() {
        return mineralList;
    }

    // MODIFIES: this
    // EFFECTS: Add mineral to list
    public void addToMineralList(Mineral mineral) {
        mineralList.add(mineral);
    }

    // MODIFIES: this
    // EFFECTS: If list is not empty, remove specified mineral
    //          If list is empty, throw empty list exception
    public void removeFromMineralList(Mineral min) {
        if (!mineralList.isEmpty()) {
            mineralList.remove(min);
        }
    }

    // EFFECTS: Check if mineral is in list
    public boolean checkInMineralList(String minName) {
        boolean value = false;
        for (int i = 0; i < mineralList.size(); i++) {
            Mineral inMineral = mineralList.get(i);
            if (minName.equals(inMineral.getName())) {
                value = true;
            }
        }
        return value;
    }

    // EFFECTS: Return true if list is not empty
    public boolean mineralListNotEmpty() {
        return !mineralList.isEmpty();
    }

    // EFFECTS: Randomly select next mineral to study
    public Mineral nextStudyMineral() {
        Random rand = new Random();
        int randIndex = rand.nextInt(mineralList.size());
        Mineral selectedMineral = mineralList.get(randIndex);
        return selectedMineral;
    }

    // EFFECTS: Returns this as a jason object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("minerals", mineralsToJson());
        return json;
    }

    // EFFECTS: Returns minerals in this folder as a JSON array
    public JSONArray mineralsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Mineral m : mineralList) {
            jsonArray.put(m.toJson());
        }
        return jsonArray;
    }

    // SAVE and LOAD methods:
    // SOURCE: Code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    // EFFECTS: Saves the review and learned folders to file
    public void saveFolders() {
        try {
            jsonWriterRev.open();
            jsonWriterRev.write(this);
            jsonWriterRev.close();

            System.out.println("Saved review list to " + JSON_FOLDERS_R);
//
//            jsonWriterLearn.open();
//            jsonWriterLearn.write(learned);
//            jsonWriterLearn.close();
//
//            System.out.println("Saved learned list to " + JSON_FOLDERS_L);

        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_FOLDERS_R);
        }
    }


}
