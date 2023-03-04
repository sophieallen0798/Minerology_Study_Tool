package model;

// Class Declaration: Mineral with lab number, name, color, hardness, and crystal system

import org.json.JSONObject;

public class Mineral {
    private int lab;
    private String name;
    private String color;
    private int hardness;
    private String crystalSystem;
    public static final String PURPLE = "\u001B[35m";
    public static final String RESET = "\u001B[0m";

    public Mineral() {
        lab = 0;
        name = "";
        color = "";
        hardness = 0;
        crystalSystem = "";
    }

    //SET methods:

    //MODIFIES: this
    public void setLab(int inputLab) {
        lab = inputLab;
    }

    //MODIFIES: this
    public void setName(String inputName) {
        name = inputName;
    }

    //MODIFIES: this
    public void setColor(String inputColor) {
        color = inputColor;
    }

    //MODIFIES: this
    public void setHardness(int inputHardness) {
        hardness = inputHardness;
    }

    // MODIFIES: this
    // EFFECTS: sets crystal system as full name from input abbreviation
    public void setCrystalSystem(String inputCrystalSystem) {
        crystalSystem = crystalSystemName(inputCrystalSystem);
    }


    // GET methods:

    public int getLab() {
        return lab;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getHardness() {
        return hardness;
    }

    public String getCrystalSystem() {
        return crystalSystem;
    }

    // EFFECTS: returns full name of the crystal system from input abbreviation
    public String crystalSystemName(String letter) {
        String str = letter;
        switch (letter) {
            case "i":
                str = "isometric";
                break;
            case "tetra":
                str = "tetragonal";
                break;
            case "tri":
                str = "triclinic";
                break;
            case "h":
                str = "hexagonal";
                break;
            case "m":
                str = "monoclinic";
                break;
            case "o":
                str = "orthorhombic";
                break;
        }
        return str;
    }

    // EFFECTS: returns this as a jason object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("lab", lab);
        json.put("name", name);
        json.put("color", color);
        json.put("hardness", hardness);
        json.put("crystalSystem", crystalSystem);
        return json;
    }

}
