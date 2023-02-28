package model;

import model.exceptions.EmptyListExcepetion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Folder {
    protected List<Mineral> mineralList;

    public Folder() {
        this.mineralList = new ArrayList<>();
    }

    public List getMineralList() {
        return mineralList;
    }

    // MODIFIES: this
    // EFFECTS: Add mineral list
    public void addToMineralList(Mineral mineral) {
        mineralList.add(mineral);
    }

    // MODIFIES: this
    // EFFECTS: If list is not empty, remove specified mineral
    //          If list is empty, throw empty list exception
    public void removeFromMineralList(Mineral min) {
        if (!mineralList.isEmpty()) {
            mineralList.remove(mineralList.indexOf(min));
        }
    }

    // EFFECTS: Print all minerals in list
    public void printMineralList() {
        for (Mineral mineral : mineralList) {
            mineral.printMineral();
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

}
