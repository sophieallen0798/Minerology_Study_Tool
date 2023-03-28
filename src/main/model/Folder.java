package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

// Folder objects consists of a name and a list of minerals
public abstract class Folder {
    protected String name;
    protected List<Mineral> mineralList;

    public abstract String getName();

    public abstract List<Mineral> getMineralList();

    // MODIFIES: this
    // EFFECTS: Add mineral to list
    public void addToMineralList(Mineral mineral) {
        this.getMineralList().add(mineral);
    }

    // MODIFIES: this
    // EFFECTS: If list is not empty, remove specified mineral
    public void removeFromMineralList(Mineral min) {
        if (!this.getMineralList().isEmpty()) {
            this.getMineralList().remove(min);
        }
    }

    // EFFECTS: Check if mineral is in list
    public boolean checkInMineralList(String minName) {
        boolean value = false;
        for (int i = 0; i < this.getMineralList().size(); i++) {
            Mineral inMineral = this.getMineralList().get(i);
            if (minName.equals(inMineral.getName())) {
                value = true;
            }
        }
        return value;
    }

    // EFFECTS: Return true if list is not empty
    public boolean mineralListNotEmpty() {
        return !getMineralList().isEmpty();
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
        for (Mineral m : this.getMineralList()) {
            jsonArray.put(m.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: Select next mineral for study activity
    public abstract Mineral nextStudyMineral();

    // EFFECTS: Check if given mineral is in given folder, return false if mineral not in folder
    public boolean mineralInFolder(String inName) {
        boolean val = false;
        for (Mineral m : this.getMineralList()) {
            if (inName.equals(m.getName())) {
                val = true;
                break;
            }
        }
        return val;
    }

}

