package ui;

import model.Folder;
import model.Mineral;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Application to study and keep a list of minerals
public class MineralApp {
    // sout colors
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String YELLOW = "\u001B[33m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    private int mineralsStudied;
    private static final int NUM_PROPERTIES = 5;  // number of properties in mineral class
    private Scanner input;
    private final Folder toReview;
    private final Folder learned;


    public MineralApp() {
        this.learned = new Folder();
        this.toReview = new Folder();
        runApp("");
    }

    //EFFECTS: Display start menu, get user input and sends to next activity, quit if user input == "q"
    private void runApp(String str) {
        if (!str.equals("q")) {
            while (true) {
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
        System.out.println("App Quit!");
        learned.printMineralList();
        toReview.printMineralList();
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
        System.out.println("\ts to study");
        System.out.println("\tm to enter minerals");
        System.out.println("\to to organize folders");
        System.out.println("\tq to quit anytime");
    }

    // EFFECTS: Takes user input from runApp() and determines next activity
    public void nextActivity(String chosen) {
        if (chosen.equals("s")) {
            mineralsStudied = 1;
            startGame();
        } else if (chosen.equals("m")) {
            while (!chosen.equals("q")) {
                toReview.addToMineralList(addMineralPrompts());
                System.out.println("Enter c to continue or m to return to main menu.");
                chosen = input.next();
                quit(chosen);
            }
        } else if (chosen.equals("o")) {
            while (!chosen.equals("q")) {
                organizeFolders();
                System.out.println("Keep organizing? c to continue, m to return to main menu.");
                chosen = input.next();
                quit(chosen);
            }
        } else if (chosen.equals("q")) {
            quitMenu();
        } else {
            System.out.println(RED + "Invalid input" + RESET);
            runApp("");
        }
    }

    // EFFECTS: Print learned folder and review folder, prompt user for name of mineral to move
    public void organizeFolders() {
        if (learned.mineralListNotEmpty() | toReview.mineralListNotEmpty()) {
            System.out.println(BLUE + "Learned List:" + RESET);
            if (!learned.mineralListNotEmpty()) {
                System.out.println("(empty)");
            }
            learned.printMineralList();
            System.out.println(BLUE + "Review List:" + RESET);
            if (!toReview.mineralListNotEmpty()) {
                System.out.println("(empty)");
            }
            toReview.printMineralList();
            System.out.println("Which mineral would you like to move?");
            String selection = input.next();
            if (!selection.equals("q") && !selection.equals("m")) {
                folderOptionsMenu(selection);
            } else {
                quit(selection);
            }
        } else {
            System.out.println(RED + "Both lists are empty. Please add at least one mineral.\n" + RESET);
            quit("m");
        }
    }

    // EFFECTS: If both lists aren't empty, move minerals chosen by user to other folder, otherwise return to main menu
    public void folderOptionsMenu(String selection) {
        if (checkInLearned(selection)) {
            organizeFolders();
        } else if (checkInReview(selection)) {
            organizeFolders();
        }
        System.out.println("Selected mineral is not in either folder.");
        organizeFolders();
    }

    // EFFECTS: Check if mineral is in learned list, if true, remove from learn and put in review, else return false
    public boolean checkInLearned(String inName) {
        boolean val = false;
        List<Mineral> learnedList = learned.getMineralList();
        for (Mineral inMineral : learnedList) {
            if (inName.equals(inMineral.getName())) {
                learned.removeFromMineralList(inMineral);
                toReview.addToMineralList(inMineral);
                System.out.println(GREEN + "\t" + inMineral.getName() + " moved to review list." + RESET);
                val = true;
            }
        }
        return val;
    }

    // EFFECTS: check if mineral is in review list, if true, remove from review and put in learn
    // else return false
    public boolean checkInReview(String inName) {
        boolean val = false;
        List<Mineral> reviewList = toReview.getMineralList();
        for (int i = 0; i < toReview.getMineralList().size(); i++) {
            Mineral inMineral = reviewList.get(i);
            if (inName.equals(inMineral.getName())) {
                toReview.removeFromMineralList(inMineral);
                learned.addToMineralList(inMineral);
                System.out.println(GREEN + "\t" + inMineral.getName() + " moved to learned list." + RESET);
                val = true;
            }
        }
        return val;
    }


    //STUDY METHODS:

    // EFFECTS: Print study options for user
    private void studyMenu() {
        System.out.println("\nSelect a property to view.");
        System.out.println("\tc for color, " + "h for hardness, "
                + "cs for crystal system, " + "or g to guess mineral name");
    }

    // EFFECTS: Get new random mineral, start study game, continue game while user does not input "q"
    public void startGame() {
        String selection;
        if (toReview.mineralListNotEmpty()) {
            while (true) {
                Mineral currentMin = toReview.nextStudyMineral();
                System.out.println("\nMineral Number " + mineralsStudied + ":");
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
            System.out.println(RED + "Review list is empty. Please add at least one mineral.\n" + RESET);
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
            } else if (!propertyValid(selection)) {
                System.out.println(RED + "Please enter a valid letter." + RESET);
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
        System.out.println(YELLOW + "Enter guess:" + RESET);
        String selection = input.next().toLowerCase();
        if (selection.equals(currentMin.getName())) {
            System.out.println(GREEN + "Correct!" + RESET);
        } else {
            System.out.println(RED + "Incorrect, the mineral was " + RESET + currentMin.getName());
        }
        System.out.println("\nEnter c to continue studying or m to return to main menu.");
        selection = input.next().toLowerCase();
        quit(selection);
        startGame();
    }

    // EFFECTS: Display properties specified by user input letter
    public void continueGame(String selection, Mineral currentMin) {
        if (selection.equals("q") | selection.equals("m")) {
            quit(selection);
        } else if (selection.equals("h")) {
            System.out.println(PURPLE + "\nHardness: " + RESET + currentMin.getHardness());
        } else if (selection.equals("cs")) {
            System.out.println(PURPLE + "\nCrystal System: " + RESET + currentMin.getCrystalSystem());
        } else if (selection.equals("c")) {
            System.out.println(PURPLE + "\nColor: " + RESET + currentMin.getColor());
        } else if (selection.equals("g")) {
            guessMineral(currentMin);
        }
    }

    // EFFECTS: If selection is q, go to quit menu, if not, do nothing
    public void quit(String selection) {
        if (selection.equals("q")) {
            quitMenu();
        } else if (selection.equals("m")) {
            runApp(selection);
        }
    }


    // ADD MINERAL METHODS:

    // EFFECTS: Take mineral properties as user inputs and create mineral, add created mineral to the Review list
    public Mineral addMineralPrompts() {
        Mineral nextMin = new Mineral();
        int i = 0;
        while (i < NUM_PROPERTIES) {
            System.out.println(messagesList(i));
            Scanner input = new Scanner(System.in);
            try {
                setProperties(nextMin, input, i);
                i += 1;
            } catch (Exception e) {
                System.out.println(RED + "Please enter a number." + RESET);
            }
        }
        return (nextMin);
    }

    // EFFECTS: Return next prompt message for mineral input by index int
    public String messagesList(int i) {
        List<String> messages = new ArrayList<>();
        messages.add(PURPLE + "\nLab Number:" + RESET);
        messages.add(PURPLE + "\nMineral Name:" + RESET);
        messages.add(PURPLE + "\nColor:" + RESET);
        messages.add(PURPLE + "\nHardness:" + RESET);
        messages.add(PURPLE + "\nCrystal System: "
                + "\n" + "\ti for isometric, "
                + "tetra for tetragonal, "
                + "tri for triclinic, "
                + "h for hexagonal, "
                + "m for monoclinic,  "
                + "o for orthorhombic" + RESET);
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

    public void checkInt() {
//        String input;
//        int inAsInt = -1;
//        boolean isInt = false;
//
//        while (true) {
//            input = this.input.next();
//            isInt = input.matches("[0-9]+");
//            if (isInt) {
//                inAsInt = Integer.getInteger(input);
//                if (inAsInt > 10 || inAsInt == 0) {
//                    continue;
//                }
//            }
//            break;
//        }
//    }

    }
}

