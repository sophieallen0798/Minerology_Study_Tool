package model;

// Class Declaration: Mineral with lab number lab, name, lustre, color, streak, hardness, specific gravity, cleavage,
// fracture, habit, crystal system, other (for additional notes)

import org.json.JSONObject;

public class Mineral {
    private String lab;
    private String name;
    private String lustre;
    private String color;
    private String streak;
    private String hardness;
    private String specificGravity;
    private String cleavage;
    private String fracture;
    private String habit;
    private String crystalSystem;
    private String other;

    // EFFECTS: Constructs minerals with the properties listed above
    public Mineral() {
        lab = "";
        name = "";
        lustre = "";
        color = "";
        streak = "";
        hardness = "";
        specificGravity = "";
        cleavage = "";
        fracture = "";
        habit = "";
        crystalSystem = "";
        other = "";
    }

    //SET methods:

    //MODIFIES: this
    public void setLab(String lab) {
        this.lab = lab;
    }

    //MODIFIES: this
    public void setName(String name) {
        this.name = name;
    }

    //MODIFIES: this
    public void setLustre(String lustre) {
        this.lustre = lustre;
    }

    //MODIFIES: this
    public void setColor(String color) {
        this.color = color;
    }

    //MODIFIES: this
    public void setStreak(String streak) {
        this.streak = streak;
    }

    //MODIFIES: this
    public void setHardness(String hardness) {
        this.hardness = hardness;
    }

    //MODIFIES: this
    public void setSpecificGravity(String specificGravity) {
        this.specificGravity = specificGravity;
    }

    //MODIFIES: this
    public void setCleavage(String cleavage) {
        this.cleavage = cleavage;
    }

    //MODIFIES: this
    public void setFracture(String fracture) {
        this.fracture = fracture;
    }

    //MODIFIES: this
    public void setHabit(String habit) {
        this.habit = habit;
    }

    // MODIFIES: this
    // EFFECTS: sets crystal system as full name from input abbreviation
    public void setCrystalSystem(String crystalSystem) {
        this.crystalSystem = crystalSystemName(crystalSystem);
    }

    //MODIFIES: this
    public void setOther(String other) {
        this.other = other;
    }

    // GET methods:

    public String getLab() {
        return lab;
    }

    public String getName() {
        return name;
    }

    public String getLustre() {
        return lustre;
    }

    public String getColor() {
        return color;
    }

    public String getStreak() {
        return streak;
    }

    public String getHardness() {
        return hardness;
    }

    public String getSpecificGravity() {
        return specificGravity;
    }

    public String getCleavage() {
        return cleavage;
    }

    public String getFracture() {
        return fracture;
    }

    public String getHabit() {
        return habit;
    }

    public String getCrystalSystem() {
        return crystalSystem;
    }

    public String getOther() {
        return other;
    }


    // EFFECTS: Returns full name of the crystal system from input abbreviation
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

    // SOURCE: Code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    // EFFECTS: Returns this as a jason object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("lab", lab);
        json.put("name", name);
        json.put("lustre", lustre);
        json.put("color", color);
        json.put("streak", streak);
        json.put("hardness", hardness);
        json.put("specificGravity", specificGravity);
        json.put("cleavage", cleavage);
        json.put("fracture", fracture);
        json.put("habit", habit);
        json.put("crystalSystem", crystalSystem);
        json.put("other", other);
        return json;
    }

}
