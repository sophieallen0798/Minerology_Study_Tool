package model;

// Class Declaration: Mineral with lab number, name, color, hardness, and crystal system

public class Mineral {
    private int lab;
    private String name;
    private String color;
    private int hardness;
    private String crystalSystem;

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

    // EFFECTS: print mineral and it's properties
    public void printMineral() {
        // stub
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
}
