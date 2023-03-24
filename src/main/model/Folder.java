package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Folder objects consists of a name and a list of minerals
public interface Folder {

    // EFFECTS: Constructs a folder with a name and list of minerals

    public String getName();

    public List<Mineral> getMineralList();

    // MODIFIES: this
    // EFFECTS: Add mineral to list
    public void addToMineralList(Mineral mineral);

    // MODIFIES: this
    // EFFECTS: If list is not empty, remove specified mineral
    //          If list is empty, throw empty list exception
    public void removeFromMineralList(Mineral min);

    // EFFECTS: Check if mineral is in list
    public boolean checkInMineralList(String minName);

    // EFFECTS: Return true if list is not empty
    public boolean mineralListNotEmpty();

    // EFFECTS: Randomly select next mineral to study
    public Mineral nextStudyMineral();

    // EFFECTS: Returns this as a jason object
    public JSONObject toJson();

    // EFFECTS: Returns minerals in this folder as a JSON array
    public JSONArray mineralsToJson();

}
