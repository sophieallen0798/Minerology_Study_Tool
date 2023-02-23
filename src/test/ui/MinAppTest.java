package ui;

import model.Learned;
import model.Mineral;
import model.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.MinApp;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class MinAppTest {
    private Mineral testMineralA;
    private Mineral testMineralB;
    private Review testReview;
    private Learned testLearned;
    private MinApp testMinApp;

    @BeforeEach
    void runBefore() {
        testMineralA = new Mineral();
        testMineralA.setLab(1);
        testMineralA.setName("A");
        testMineralA.setColor("green");
        testMineralA.setHardness(4);
        testMineralA.setCrystalSystem("isometric");

        testMineralB = new Mineral();
        testMineralB.setLab(2);
        testMineralB.setName("B");
        testMineralB.setColor("blue");
        testMineralB.setHardness(7);
        testMineralB.setCrystalSystem("hexagonal");

        testLearned = new Learned();
        testReview = new Review();

        //testMinApp = new MinApp();
    }

    // add mineral to learned list, check if mineral is in list, check mineral
    // that hasn't been added isn't in list, add second mineral, check both
    // minerals are in list
//    @Test
//    void testCheckInLearned() {
//        List<Mineral> learnedList = testLearned.getLearnedList();
//        learnedList.add(testMineralA);
//        assertTrue(testMinApp.checkInLearned(testMineralA.getName()));
//        assertFalse(testMinApp.checkInLearned(testMineralB.getName()));
//        learnedList.add(testMineralB);
//        assertTrue(testMinApp.checkInLearned(testMineralA.getName()));
//        assertTrue(testMinApp.checkInLearned(testMineralB.getName()));
//        assertEquals(2, learnedList.size());
//    }

    // same as above but for review list
//    @Test
//    void testCheckInReview() {
//        List<Mineral> reviewList = testReview.getReviewList();
//        reviewList.add(testMineralA);
//        assertTrue(testMinApp.checkInLearned(testMineralA.getName()));
//        assertFalse(testMinApp.checkInLearned(testMineralB.getName()));
//        reviewList.add(testMineralB);
//        assertTrue(testMinApp.checkInLearned(testMineralA.getName()));
//        assertTrue(testMinApp.checkInLearned(testMineralB.getName()));
//        assertEquals(2, reviewList.size());
//    }

    // Test case when review list has one element, check case when has 2 elements
    @Test
    void testReviewNotEmpty() {
        testReview.addToReview(testMineralA);
        assertEquals(1, testReview.getReviewList().size());
        assertTrue(testReview.reviewNotEmpty());
        testReview.addToReview(testMineralB);
        assertEquals(2, testReview.getReviewList().size());
        assertTrue(testReview.reviewNotEmpty());
    }

    // Test case when review list is empty
    @Test
    void testReviewEmpty() {
        assertFalse(testReview.reviewNotEmpty());
    }

    // Test that the randomly selected mineral is a member of the review list when list has 1 element
    @Test
    void testNextStudyMineralOne() {
        testReview.addToReview(testMineralA);
        assertTrue(testReview.checkInReview(testReview.nextStudyMineral().getName()));
    }

    // Test that the randomly selected mineral is a member of the review list when list has 2 elements
    @Test
    void testNextStudyMineralTwo() {
        testReview.addToReview(testMineralA);
        testReview.addToReview(testMineralB);
        assertTrue(testReview.checkInReview(testReview.nextStudyMineral().getName()));
        assertTrue(testReview.checkInReview(testReview.nextStudyMineral().getName()));
    }

    // Test that random selection doesn't remove mineral from review - will keep selecting if there is only one mineral
    @Test
    void testNextStudyMineralContinue() {
        testReview.addToReview(testMineralA);
        assertTrue(testReview.checkInReview(testReview.nextStudyMineral().getName()));
        assertTrue(testReview.checkInReview(testReview.nextStudyMineral().getName()));
        assertTrue(testReview.checkInReview(testReview.nextStudyMineral().getName()));
    }

    // Test if remove from review will throw error if list is empty
    @Test
    void testRemoveFromReviewEmpty() {
        assertEquals(0, testReview.getReviewList().size());
        testReview.removeFromReview(testMineralA);
        assertEquals(0, testReview.getReviewList().size());
    }

    // Test if element is removed from list with 1 element
    @Test
    void testRemoveFromReviewOneElement() {
        testReview.addToReview(testMineralA);
        assertTrue(testReview.checkInReview(testMineralA.getName()));
        testReview.removeFromReview(testMineralA);
        assertFalse(testReview.checkInReview(testMineralA.getName()));
    }

    // Test that correct element is removed in list with more than one element
    @Test
    void testRemoveFromReviewTwoElements() {
        testReview.addToReview(testMineralA);
        testReview.addToReview(testMineralB);
        assertTrue(testReview.checkInReview(testMineralA.getName()));
        assertTrue(testReview.checkInReview(testMineralB.getName()));
        testReview.removeFromReview(testMineralB);
        assertTrue(testReview.checkInReview(testMineralA.getName()));
        assertFalse(testReview.checkInReview(testMineralB.getName()));
    }

}
