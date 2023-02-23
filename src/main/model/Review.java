package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Review {
    private List<Mineral> reviewList;

    public Review() {
        reviewList = new ArrayList<>();
    }

    public List getReviewList() {
        return reviewList;
    }

    // EFFECTS: If review list is not empty, remove specified mineral
    public void removeFromReview(Mineral min) {
        if (reviewList.size() > 0) {
            reviewList.remove(reviewList.indexOf(min));
        }
    }

    // EFFECTS: Add mineral to review list
    public void addToReview(Mineral min) {
        reviewList.add(min);
    }

    // EFFECTS: Print all minerals in review list
    public void printReviewList() {
        for (Mineral mineral : reviewList) {
            mineral.printMineral();
        }
    }

    // EFFECTS: Check if the review list contains a specified mineral
    public boolean checkInReview(String minName) {
        boolean value = false;
        for (int i = 0; i < reviewList.size(); i++) {
            Mineral inMineral = reviewList.get(i);
            if (minName.equals(inMineral.getName())) {
                value = true;
            }
        }
        return value;
    }

    // EFFECTS: Return true if review list is not empty
    public boolean reviewNotEmpty() {
        return !reviewList.isEmpty();
    }

    // EFFECTS: Randomly select next mineral to study
    public Mineral nextStudyMineral() {
        Random rand = new Random();
        int randIndex = rand.nextInt(reviewList.size());
        Mineral selectedMineral = reviewList.get(randIndex);
        return selectedMineral;
    }
}