package ui;

import model.Folder;
import model.Mineral;
import org.json.JSONArray;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
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
    private Folder toReview;
    private Folder learned;
    private JsonWriter jsonWriterRev;
    private JsonReader jsonReaderRev;
    private JsonWriter jsonWriterLearn;
    private JsonReader jsonReaderLearn;
    private JSONArray jsonReview;
    private JSONArray jsonLearned;
    private static final String JSON_FOLDERS_R = "./data/review.json";
    private static final String JSON_FOLDERS_L = "./data/learned.json";

    // EFFECTS: Initialize Folders and json writers and readers, start menu
    public MineralApp() {
        this.learned = new Folder("Learned");
        this.toReview = new Folder("Review");
        jsonReview = new JSONArray(toReview.getMineralList());
        jsonLearned = new JSONArray(learned.getMineralList());
        jsonReaderRev = new JsonReader(JSON_FOLDERS_R);
        jsonWriterRev = new JsonWriter(JSON_FOLDERS_R);
        jsonReaderLearn = new JsonReader(JSON_FOLDERS_L);
        jsonWriterLearn = new JsonWriter(JSON_FOLDERS_L);
        openMenu();
    }

    // EFFECTS: prompt use to load folders, if user input = y, load folders, if n, proceed to main menu, if neither,
    // ask user again
    public void openMenu() {
        System.out.println("load folders? y for yes, n to proceed to main menu");
        input = new Scanner(System.in);
        String pu = input.next();
        if (pu.equals("y")) {
            loadFolders();
            runApp("");
        } else if (pu.equals("n")) {
            runApp("");
        } else {
            System.out.println("Please enter y or n.");
            openMenu();
        }
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
        System.out.println("Save folders? s to save, q to quit without saving");
        if (input.next().equals("s")) {
            saveFolders();
        }
        System.out.println("App Quit!");
        System.exit(0);
    }

    // EFFECTS: Quit app when prompted by user
    public void quitMenu() {
        System.out.println("Quit?  q to quit, m to return main menu");
        String choice = input.next().toLowerCase();
        runApp(choice);
    }

    // EFFECTS: Print start menu options
    public void startMenu() {
        System.out.println("What would you like to do?");
        System.out.println("\tb to begin studying");
        System.out.println("\tm to enter minerals");
        System.out.println("\to to organize folders");
        System.out.println("\tp to view all minerals");
        System.out.println("\tl to load folders");
        System.out.println("\ts to save folders");
        System.out.println("\tq to quit anytime");
    }

    // EFFECTS: Takes user input from runApp() and determines next activity
    public void nextActivity(String chosen) {
        if (chosen.equals("b")) {
            mineralsStudied = 1;
            startGame();
        } else if (chosen.equals("m")) {
            addMineralsActivity();
        } else if (chosen.equals("o")) {
            organizeFolders(learned);
            organizeFolders(toReview);
            organizePrompt();
        } else if (chosen.equals("s")) {
            saveFolders();
        } else if (chosen.equals("l")) {
            loadFolders();
        } else if (chosen.equals("q")) {
            runApp("q");
        } else if (chosen.equals("p")) {
            printFolders();
        } else {
            System.out.println(RED + "Invalid input" + RESET);
            runApp("");
        }
    }

    // EFFECTS: Calls method to print names of minerals in folders or print empty if empty
    public void organizeFolders(Folder folder) {
        if (learned.mineralListNotEmpty() | toReview.mineralListNotEmpty()) {
            System.out.println(BLUE + folder.getName() + RESET);
            if (folder.getMineralList().isEmpty()) {
                System.out.println("(empty)");
            }
            printMineralNames(folder);
        } else {
            System.out.println(RED + "Both lists are empty. Please add at least one mineral.\n" + RESET);
            quit("m");
        }
    }

    // Effects: Print labeled review and learned folders
    public void printFolders() {
        System.out.println("\nReview Folder");
        printFoldersInColumns(toReview);
        System.out.println("\nLearned Folder");
        printFoldersInColumns(learned);
    }

    // EFFECTS: Prints minerals in folder specified by user
    public void printFoldersInColumns(Folder f) {
        System.out.printf("%13s %6s %10s %10s %18s\n", "Name", "Lab", "Color", "Hardness", "Crystal System");
        List<Mineral> minList = f.getMineralList();
        for (Mineral m : minList) {
            String name = m.getName();
            int lab = m.getLab();
            String color = m.getColor();
            int hardness = m.getHardness();
            String cs = m.getCrystalSystem();

            System.out.printf("%13s %6s %9s %10s %18s\n", name, lab, color, hardness, cs);
        }
    }

    // EFFECTS: Print names of minerals in folder
    public void printMineralList(Folder folder) {
        List<Mineral> mineralList = folder.getMineralList();
        for (Mineral mineral : mineralList) {
            printMineral(mineral);
        }
    }

    // EFFECTS: Print names of all minerals in given folder
    public void printMineralNames(Folder folder) {
        List<Mineral> mineralList = folder.getMineralList();
        for (Mineral mineral : mineralList) {
            System.out.println(mineral.getName());
        }
    }

    // EFFECTS: Print mineral and it's properties
    public void printMineral(Mineral m) {
        System.out.println(PURPLE + "Lab: " + RESET + m.getLab());
        System.out.println(PURPLE + "Name: " + RESET + m.getName());
        System.out.println(PURPLE + "Color: " + RESET + m.getColor());
        System.out.println(PURPLE + "Hardness: " + RESET + m.getHardness());
        System.out.println(PURPLE + "Crystal System: " + RESET + m.getCrystalSystem());
    }

    // EFFECTS: prompt user for name of mineral to move
    public void organizePrompt() {
        System.out.println("Which mineral would you like to move? Enter mineral name or m to return to main menu");
        String selection = input.next();
        quit(selection);
        folderOptionsMenu(selection);
    }

    // EFFECTS: If both lists aren't empty, move minerals chosen by user to other folder, otherwise return to main menu
    public void folderOptionsMenu(String selection) {
        if (checkInLearned(selection) | checkInReview(selection)) {
            organizeFolders(learned);
            organizeFolders(toReview);
        }
        System.out.println("Selected mineral is not in either folder.");
        nextActivity("o");
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

    // EFFECTS: check if mineral is in review list, if true, remove from review and put in learn else return false
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

    // EFFECTS: Keep adding minerals until quit
    public void addMineralsActivity() {
        String chosen = "";
        while (!chosen.equals("q")) {
            toReview.addToMineralList(addMineralPrompts());
            System.out.println("Enter c to continue or m to return to main menu.");
            chosen = input.next();
            quit(chosen);
        }
    }

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
            String in = inpu.next();
            if (checkName(in)) {
                m.setName(in);
            } else {
                System.out.println("This mineral already exists in your lists. Please enter another name.");
                setProperties(m, inpu, i);
            }
        } else if (i == 2) {
            m.setColor(inpu.next());
        } else if (i == 3) {
            m.setHardness(inpu.nextInt());
        } else if (i == 4) {
            m.setCrystalSystem(inpu.next().toLowerCase());
        }
    }

    // EFFECTS: check if mineral of input name already exists in either folder
    public boolean checkName(String name) {
        boolean val = true;
        List<Mineral> reviewList = toReview.getMineralList();
        List<Mineral> learnedList = learned.getMineralList();

        for (Mineral min : reviewList) {
            if (name.equals(min.getName())) {
                val = false;
            }
        }
        for (Mineral min : learnedList) {
            if (name.equals(min.getName())) {
                val = false;
            }
        }
        return val;
    }


    // Save and load methods:

    // SOURCE: code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    // EFFECTS: saves the review and learned folders to file
    private void saveFolders() {
        try {
            jsonWriterRev.open();
            jsonWriterRev.write(toReview);
            jsonWriterRev.close();

            System.out.println("Saved review list to " + JSON_FOLDERS_R);

            jsonWriterLearn.open();
            jsonWriterLearn.write(learned);
            jsonWriterLearn.close();

            System.out.println("Saved learned list to " + JSON_FOLDERS_L);

        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_FOLDERS_L);
        }
    }

    // SOURCE: code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // MODIFIES: this
    // EFFECTS: loads review folder and learned folder
    private void loadFolders() {
        try {
            toReview = jsonReaderRev.read();
            System.out.println("Loaded review list from " + JSON_FOLDERS_R);
            learned = jsonReaderLearn.read();
            System.out.println("Loaded learned list from " + JSON_FOLDERS_L);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_FOLDERS_L);
        }
    }

}