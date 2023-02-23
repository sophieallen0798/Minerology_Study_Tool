package model;

import java.util.ArrayList;
import java.util.List;

public class Learned {
    private List<Mineral> learnedList;

    public Learned() {
        learnedList = new ArrayList<>();
    }

    public List getLearnedList() {
        return learnedList;
    }

    // EFFECTS: Add mineral to learned list
    public void addToLearned(Mineral mineral) {
        learnedList.add(mineral);
    }

    // EFFECTS: If learned list is not empty, remove specified mineral
    public void removeFromLearned(Mineral min) {
        if (learnedList.size() > 0) {
            learnedList.remove(learnedList.indexOf(min));
        }
    }

    // EFFECTS: Print all minerals in learned list
    public void printLearnedList() {
        for (Mineral mineral : learnedList) {
            mineral.printMineral();
        }
    }

    // Check if mineral is in learned list
    public boolean checkInLearned(String minName) {
        boolean value = false;
        for (int i = 0; i < learnedList.size(); i++) {
            Mineral inMineral = learnedList.get(i);
            if (minName.equals(inMineral.getName())) {
                value = true;
            }
        }
        return value;
    }

}
