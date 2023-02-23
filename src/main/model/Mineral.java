package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class Declaration: Mineral with associated properties
public class Mineral {
    private int lab;
    private String name;
    private String color;
    private int hardness;
    private String crystalSystem;

    // EFFECTS: mineral has given lab number and properties
    public Mineral() { //int lab, String name, String color, int hardness, String crystalStystem) {
        lab = 0;
        name = "";
        color = "";
        hardness = 0;
        crystalSystem = "";
    }

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

    //MODIFIES: this
    public void setCrystalSystem(String inputCrystalSystem) {
        crystalSystem = inputCrystalSystem;
    }

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
        return crystalSystemName(crystalSystem);
        //return crystalSystem;
    }

    // EFFECTS: print mineral and it's properties
    public void printMineral() {
        System.out.println("Lab: " + lab);
        System.out.println("Name: " + name);
        System.out.println("Color: " + color);
        System.out.println("Hardness: " + hardness);
        System.out.println("Crystal System: " + crystalSystem);
        // get it to print full name
        //System.out.println("Crystal System: " + crystalSystemName(mineralToPrint.getCrystalSystem()));
    }



    // EFFECTS: returns full name of the crystal system from input abbreviation
    public String crystalSystemName(String letter) {
        String str = letter;
        if (letter.equals("i")) {
            str = "isometric";
        } else if (letter.equals("tetra")) {
            str = "tetragonal";
        } else if (letter.equals("tri")) {
            str = "triclinic";
        } else if (letter.equals("h")) {
            str = "hexagonal";
        } else if (letter.equals("m")) {
            str = "monoclinic";
        } else if (letter.equals("o")) {
            str = "orthorhombic";
        }
        return str;
    }
}
