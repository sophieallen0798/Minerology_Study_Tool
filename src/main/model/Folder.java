package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

// Folder objects consists of a name and a list of minerals
public abstract class Folder {
    protected String name;
    protected List<Mineral> mineralList;

    // EFFECTS: Constructs a folder with a name and list of minerals

    public String getName() {
        return this.name;
    }

    public List<Mineral> getMineralList() {
        return this.mineralList;
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

    public abstract Mineral nextStudyMineral();

    // EFFECTS: Check if given mineral is in given folder, return false if mineral not in folder
    public boolean mineralInFolder(String inName) {
        boolean val = false;
        List<Mineral> mineralList = this.getMineralList();
        for (int i = 0; i < this.getMineralList().size(); i++) {
            Mineral inMineral = mineralList.get(i);
            if (inName.equals(inMineral.getName())) {
                val = true;
                break;
            }
        }
        return val;
    }

}

