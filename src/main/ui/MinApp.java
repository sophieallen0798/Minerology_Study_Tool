package ui;

import model.Learned;
import model.Mineral;
import model.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Mineral studying application
public class MinApp {
    protected Mineral mineral;
    private int mineralsStudied;
    private static final int NUM_PROPERTIES = 5;
    private Scanner input;
    private final Review toReview;
    private final Learned learned;


    // EFFECTS: Runs the mineral app
    public MinApp() {
        learned = new Learned();
        toReview = new Review();
        runApp("a");
    }

    //EFFECTS: Display start menu, get user input and sends to next activity, quit if user input == "q"
    private void runApp(String str) {
        if (!str.equals("q")) {
            while (!str.equals("q")) {
                startMenu();
                input = new Scanner(System.in);
                String choose = input.next();
                choose = choose.toLowerCase();
                if (choose.equals("q")) {
                    break;
                } else {
                    nextActivity(choose);
                }
            }
        }
        System.out.println("Quit from run app");
        learned.printLearnedList();
        toReview.printReviewList();
        System.exit(0);
    }

    // EFFECTS: Quit app when prompted by user
    public void quitMenu() {
        System.out.println("Quit?  q to quit, any other key to return to main menu");
        String choice = input.next().toLowerCase();
        runApp(choice);
    }

    // EFFECTS: Print start menu options
    public void startMenu() {
        System.out.println("What would you like to do?");
        System.out.println("s to study");
        System.out.println("m to enter minerals");
        System.out.println("o to organize folders");
        System.out.println("q to quit anytime");
    }

    // EFFECTS: Takes user input from runApp() and determines next activity
    public void nextActivity(String chosen) {
        if (chosen.equals("s")) {
            mineralsStudied = 1;
            startGame();
        } else if (chosen.equals("m")) {
            while (!chosen.equals("q")) {
                toReview.addToReview(addMineralPrompts());
                System.out.println("Enter c to continue or q to return to main menu.");
                chosen = input.next();
                quit(chosen);
            }
        } else if (chosen.equals("o")) {
            while (!chosen.equals("q")) {
                organizeFolders();
                System.out.println("Keep organizing? c to continue, q to return to main menu.");
                chosen = input.next();
                quit(chosen);
            }
        } else if (chosen.equals("q")) {
            quitMenu();
        } else {
            System.out.println("Invalid Input");
            runApp("a");
        }
    }

    // EFFECTS: Print learned folder and review folder, prompt user for name of mineral to move
    public void organizeFolders() {
        learned.printLearnedList();
        toReview.printReviewList();
        System.out.println("Which mineral would you like to move?");
        String selection = input.next();
        if (!selection.equals("q")) {
            folderOptionsMenu(selection);
        } else {
            quitMenu();
        }
    }

    // EFFECTS: move minerals chosen by user to other folder
    public void folderOptionsMenu(String selection) {
        if (checkInLearned(selection)) {
            organizeFolders();
        } else if (checkInReview(selection)) {
            organizeFolders();
        }
        System.out.println("Selected mineral is not in either folder.");
        organizeFolders();
    }

    // EFFECTS: Check if mineral is in learned list, if true, remove from learn and put in review
    // else return false
    public boolean checkInLearned(String inName) {
        boolean val = false;
        List<Mineral> learnedList = learned.getLearnedList();
        for (int i = 0; i < learnedList.size(); i++) {
            Mineral inMineral = learnedList.get(i);
            if (inName.equals(inMineral.getName())) {
                learned.removeFromLearned(inMineral);
                toReview.addToReview(inMineral);
                System.out.println(inMineral.getName() + " moved to review list.");
                val = true;
            }
        }
        return val;
    }

    // EFFECTS: check if mineral is in review list, if true, remove from review and put in learn
    // else return false
    public boolean checkInReview(String inName) {
        boolean val = false;
        List<Mineral> reviewList = toReview.getReviewList();
        for (int i = 0; i < toReview.getReviewList().size(); i++) {
            Mineral inMineral = reviewList.get(i);
            if (inName.equals(inMineral.getName())) {
                toReview.removeFromReview(inMineral);
                learned.addToLearned(inMineral);
                System.out.println(inMineral.getName() + " moved to learned list.");
                val = true;
            }
        }
        return val;
    }


    //STUDY METHODS:

    // EFFECTS: Print study options for user
    private void studyMenu() {
        System.out.println("Select a property to view.");
        System.out.println("c for color, " + "h for hardness, "
                + "cs for crystal system, " + "or g to guess mineral name");
    }

    // EFFECTS: Get new random mineral, start study game, continue game while user does not input "q"
    public void startGame() {
        String selection = "";
        if (toReview.reviewNotEmpty()) {
            while (!selection.equals("q")) {
                Mineral currentMin = toReview.nextStudyMineral();
                System.out.println("Mineral Number " + mineralsStudied + ":");
                mineralsStudied += 1;
                studyMenu();
                selection = input.next().toLowerCase();
                if (selection.equals("q")) {
                    quitMenu();
                    break;
                }
                nextProperty(selection, currentMin);
            }
        } else {
            System.out.println("Review list is empty. Please add minerals.");
        }
    }

    // EFFECTS: Reveal properties requested by user
    public void nextProperty(String selection, Mineral currentMin) {
        while (!selection.equals("q")) {
            if (propertyValid(selection)) {
                continueGame(selection, currentMin);
                studyMenu();
                selection = input.next().toLowerCase();
                quit(selection);
//            } else if (selection.equals("q")) {
//                quitMenu();
//                break;
            } else if (!propertyValid(selection)) {
                System.out.println("Please enter a valid letter.");
                selection = input.next().toLowerCase();
                quit(selection);
            }
        }
    }

    // EFFECTS: Check if letter entered by user is valid
    public boolean propertyValid(String str) {
        return str.equals("g") | str.equals("c") | str.equals("h") | str.equals("cs");
    }

    // EFFECTS: Determine if user guessed mineral name correctly, give option to continue playing
    public void guessMineral(Mineral currentMin) {
        System.out.println("Enter guess:");
        String selection = input.next().toLowerCase();
        if (selection.equals(currentMin.getName())) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect, the mineral was " + currentMin.getName());
        }
        System.out.println("Enter c to continue studying or q to return to main menu.");
        selection = input.next();
        quit(selection);
        startGame();
    }

    // EFFECTS: Display properties specified by user input letter
    public void continueGame(String selection, Mineral currentMin) {
        if (selection.equals("q")) {
            quitMenu();
        } else if (selection.equals("h")) {
            System.out.println("Hardness: " + currentMin.getHardness());
        } else if (selection.equals("cs")) {
            System.out.println("Crystal System: " + currentMin.getCrystalSystem());
        } else if (selection.equals("c")) {
            System.out.println("Color: " + currentMin.getColor());
        } else if (selection.equals("g")) {
            guessMineral(currentMin);
        }
    }

    // EFFECTS: If selection is q, go to quit menu, if not, do nothing
    public void quit(String selection) {
        if (selection.equals("q")) {
            quitMenu();
        }
    }


    // ADD MINERAL METHODS:

    // EFFECTS: Take mineral properties as user inputs and create mineral, add created mineral to the Review list
    public Mineral addMineralPrompts() {
        Mineral nextMin = new Mineral();
        int i = 0;
        while (i < NUM_PROPERTIES) {
            System.out.println(messagesList(i));
            input = new Scanner(System.in);
            if (input.next().equalsIgnoreCase("q")) {
                return (nextMin);
            } else {
                try {
                    setProperties(nextMin, input, i);
                    i += 1;
                } catch (Exception e) {
                    System.out.println("Please enter a number");
                }
            }
        }
        return (nextMin);
    }


    // EFFECTS: Return next prompt message for mineral input by index int
    public String messagesList(int i) {
        List<String> messages = new ArrayList<>();
        messages.add("\nLab Number:");
        messages.add("\nMineral Name:");
        messages.add("\nColor:");
        messages.add("\nHardness:");
        messages.add("\nCrystal System: "
                + "\ni for isometric, "
                + "tetra for tetragonal, "
                + "tri for triclinic, "
                + "h for hexagonal, "
                + "m for monoclinic,  "
                + "o for orthorhombic");
        return messages.get(i);
    }


    // MODIFIES: Mineral
    // EFFECTS: Sets specified property of the given mineral
    public void setProperties(Mineral m, Scanner inpu, int i) {
        if (i == 0) {
            m.setLab(inpu.nextInt());
        } else if (i == 1) {
            m.setName(inpu.next());
        } else if (i == 2) {
            m.setColor(inpu.next());
        } else if (i == 3) {
            m.setHardness(inpu.nextInt());
        } else if (i == 4) {
            m.setCrystalSystem(inpu.next().toLowerCase());
        }
    }

}
