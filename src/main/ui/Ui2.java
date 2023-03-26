package ui;

import model.Folder;
import model.LearnedFolder;
import model.Mineral;
import model.ReviewFolder;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// Application to study and keep a list of minerals
public class Ui2 extends JFrame {
    // sout colors
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String YELLOW = "\u001B[33m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    private JLabel label;
    private Table table;
    private AddMineralGUI addMinGUI;

    private Mineral mineral;
    private JTextField field;
    static Color green1;

    private JLabel labLabel;
    private JLabel nameLabel;
    private JLabel lustreLabel;
    private JLabel colorLabel;
    private JLabel streakLabel;
    private JLabel hardnessLabel;
    private JLabel specificGravityLabel;
    private JLabel cleavageLabel;
    private JLabel fractureLabel;
    private JLabel habitLabel;
    private JLabel crystalSystemLabel;
    private JLabel otherLabel;

    static JLabel menuLabel;

    private JTextField labBox;
    private JTextField nameBox;
    private JTextField lustreBox;
    private JTextField colorBox;
    private JTextField streakBox;
    private JTextField hardnessBox;
    private JTextField specificGravityBox;
    private JTextField cleavageBox;
    private JTextField fractureBox;
    private JTextField habitBox;
    private JTextField crystalSystemBox;
    private JTextField otherBox;

    private int mineralsStudied;
    private static final int NUM_PROPERTIES = 12;  // number of fields in mineral class
    private Scanner input;
    static ReviewFolder toReview;
    static LearnedFolder learned;
    static JsonWriter jsonWriterRev;
    static JsonReader jsonReaderRev;
    static JsonWriter jsonWriterLearn;
    private final JsonReader jsonReaderLearn;
    private static final String JSON_FOLDERS_R = "./data/review.json";
    private static final String JSON_FOLDERS_L = "./data/learned.json";

    private Table tableR;
    private Table tableL;

    private JInternalFrame controlPanel;

    private JButton studyButton;
    private JPanel panel1;
    private JButton organizeFoldersButton;
    private JButton addMineralsButton;
    private JButton viewFoldersButton;
    private JButton loadFoldersButton;
    private JButton saveFoldersButton;
    private JButton quitButton;

    protected JButton button;

    // EFFECTS: Initialize Folders and json writers and readers, goes to open menu
    public Ui2() throws FileNotFoundException {
        this.learned = new LearnedFolder();
        this.toReview = new ReviewFolder();
        jsonReaderRev = new JsonReader(JSON_FOLDERS_R);
        jsonWriterRev = new JsonWriter(JSON_FOLDERS_R);
        jsonReaderLearn = new JsonReader(JSON_FOLDERS_L);
        jsonWriterLearn = new JsonWriter(JSON_FOLDERS_L);
        //openMenu();
        guiMenu();
    }

    public void guiMenu() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 400));
        setLayout(new GridLayout(14, 2,2,2));
        menuLabel = new JLabel("lab");
        JButton addMinBtn = new JButton("Add Minerals");
        JButton studyBtn = new JButton("Study");
        JButton viewBtn = new JButton("View Folders");
        JButton organizeBtn = new JButton("Organize Folders");
        JButton loadBtn = new JButton("Load Folders");
        JButton saveBtn = new JButton("Save Folders");
        JButton sortButton = new JButton("Sort By Lab");
        JButton clearButton = new JButton("Clear All Folders");
        addMinBtn.setActionCommand("addMin");
        studyBtn.setActionCommand("study");
        viewBtn.setActionCommand("view");
        organizeBtn.setActionCommand("organize");
        loadBtn.setActionCommand("load");
        saveBtn.setActionCommand("saveButton");
        clearButton.setActionCommand("clear");
        sortButton.setActionCommand("sort");

        addMinBtn.addActionListener(actions());
        studyBtn.addActionListener(actionHappening());
        viewBtn.addActionListener(actionHappening());
        organizeBtn.addActionListener(actions());
        loadBtn.addActionListener(actionHappening());
        sortButton.addActionListener(actionHappening());
        clearButton.addActionListener(actionHappening());
        field = new JTextField(5);
        //add(makeButton());
        add(addMinBtn);
        add(studyBtn);
        add(viewBtn);
        add(organizeBtn);
        add(loadBtn);
        add(saveBtn);
        add(sortButton);
        add(clearButton);
        add(menuLabel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    static ActionListener actions() {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //addMinGUI = new AddMineralGUI(toReview);
                if (e.getActionCommand().equals("addMin")) {
                    try {
                        new AddMineralGUI(toReview);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if (e.getActionCommand().equals("save") | e.getActionCommand().equals("saveButton")) {
                    saveFolders();
                    menuLabel.setForeground(green1);
                    menuLabel.setText("Folders Saved");
                }

//                if (e.getActionCommand().equals("add")) {
//                    Mineral min = getMin();
//                    toReview.addToMineralList(min);
////                    label.setForeground(green1);
////                    label.setText("   " + min.getName() + " added to review list.");
//                    //resetBoxesEmpty();
//                }
            }
        };
        return actionListener;
    }

    //This is the method that is called when the JButton btn is clicked
    public ActionListener actionHappening() {
        ActionListener actListen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("load")) {
                    loadFolders();
                    menuLabel.setForeground(green1);
                    menuLabel.setText("Folders Loaded");
                }
//        if (e.getActionCommand().equals("addMin")) {
//            addMinGUI = new AddMineralGUI(toReview);
//        }
                if (e.getActionCommand().equals("save") | e.getActionCommand().equals("saveButton")) {
                    toReview = addMinGUI.getFolder();
                    saveFolders();
                    menuLabel.setForeground(green1);
                    menuLabel.setText("Folders Saved");
                }
                if (e.getActionCommand().equals("view")) {
                    table = new Table(toReview);
                    table.fun("a");
                }
                if (e.getActionCommand().equals("organize")) {
                    organizeFolders(toReview);
                    organizeFolders(learned);
                }
                if (e.getActionCommand().equals("study")) {
                    studyGUI();
                }
                if (e.getActionCommand().equals("sort")) {
                    sortLab();
                }
                if (e.getActionCommand().equals("clear")) {
                    toReview = new ReviewFolder();
                    learned = new LearnedFolder();
                }
            }
        };
        return actListen;

    }

    public static void studyGUI() {
        Study frame = new Study();
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public void sortLab() {

    }

//    public ActionListener globalActions() {
//        ActionListener globalAction = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getActionCommand().equals("save")) {
//                    toReview = addMinGUI.getFolder();
//                    saveFolders();
//                    menuLabel.setForeground(green1);
//                    menuLabel.setText("Folders Saved");
//                }
//            }
//        };
//        return globalAction;
//    }

//    public void actionPerformed(ActionEvent e) {
//        if (e.getActionCommand().equals("myButton")) {
//            Mineral min = getMineral();
//            toReview.addToMineralList(min);
//            printFoldersInColumns(toReview);
//            label.setForeground(green1);
//            label.setText("   " + min.getName() + " added to review list.");
//            resetBoxesEmpty();
//
//        }
//        if (e.getActionCommand().equals("saveButton")) {
//            saveFolders();
//        }
//        if (e.getActionCommand().equals("addMin")) {
//            addMineralMenu();
//        }
//    }

//    private void printTable(ActionEvent e) {
//        if (e.getActionCommand().equals("printTable")) {
//            table = new Table(toReview);
//            table.fun("a");
//        }
//    }


    // EFFECTS: Prompt user to load folders, if user input = y, load folders, if n, proceed to main menu, if neither,
    // ask user again
    public void openMenu() {
        System.out.println("Load folders? y for yes, n to proceed to main menu");
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

    //EFFECTS: Display start menu, get user input and sends to next activity, if user input == "q" prompt to save, quit
    public void runApp(String str) {
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

    // EFFECTS: prompt user for name of mineral to move
    public void organizePrompt() {
        organizeFolders(learned);
        organizeFolders(toReview);
        System.out.println("Which mineral would you like to move? Enter mineral name or m to return to main menu");
        String selection = input.next();
        quit(selection);
        folderOptionsMenu(selection);
    }

    // EFFECTS: If both lists aren't empty, move minerals chosen by user to other folder, otherwise return to main menu
    public void folderOptionsMenu(String selection) {
        if (mineralInFolder(selection, toReview)) {
            checkInReview(selection);
        } else if (mineralInFolder(selection, learned)) {
            checkInLearned(selection);
        } else {
            System.out.println("Selected mineral is not in either folder.");
        }
        organizePrompt();
    }

    // EFFECTS: Remove mineral from learned and put in review
    public void checkInLearned(String inName) {
        List<Mineral> reviewList = learned.getMineralList();
        for (int i = 0; i < learned.getMineralList().size(); i++) {
            Mineral inMineral = reviewList.get(i);
            if (inName.equals(inMineral.getName())) {
                learned.removeFromMineralList(inMineral);
                toReview.addToMineralList(inMineral);
                System.out.println(GREEN + "\t" + inMineral.getName() + " moved to review list." + RESET);
            }
        }
    }

    // EFFECTS: Remove mineral from review and put in learn
    public void checkInReview(String inName) {
        List<Mineral> reviewList = toReview.getMineralList();
        for (int i = 0; i < toReview.getMineralList().size(); i++) {
            Mineral inMineral = reviewList.get(i);
            if (inName.equals(inMineral.getName())) {
                toReview.removeFromMineralList(inMineral);
                learned.addToMineralList(inMineral);
                System.out.println(GREEN + "\t" + inMineral.getName() + " moved to learned list." + RESET);
            }
        }
    }

    // EFFECTS: Check if given mineral is in given folder
    public boolean mineralInFolder(String inName, Folder f) {
        boolean val = false;
        List<Mineral> mineralList = f.getMineralList();
        for (int i = 0; i < f.getMineralList().size(); i++) {
            Mineral inMineral = mineralList.get(i);
            if (inName.equals(inMineral.getName())) {
                val = true;
                break;
            }
        }
        return val;
    }

    // PRINT METHODS:

    // Effects: Print labeled review and learned folders
    public void printFolders() {
        tableL = new Table(learned);
        tableR = new Table(toReview);
        tableL.fun("a");
        tableR.fun("a");
        System.out.println(BLUE + "\nReview Folder" + RESET);
        printFoldersInColumns(toReview);
        System.out.println(BLUE + "\nLearned Folder" + RESET);
        printFoldersInColumns(learned);
    }

    // EFFECTS: Prints minerals in given folder
    public void printFoldersInColumns(Folder f) {
        System.out.printf(PURPLE + "%13s %6s %10s %10s %10s %8s %16s %10s %10s %10s %18s %18s\n", "Name", "Lab",
                "Lustre", "Color", "Streak", "Hardness", "Specific Gravity", "Cleavage", "Fracture", "Habit",
                "Crystal System", "Other" + RESET);
        List<Mineral> minList = f.getMineralList();
        for (Mineral m : minList) {
            String name = m.getName();
            int lab = m.getLab();
            String lustre = m.getLustre();
            String color = m.getColor();
            String s = m.getStreak();
            String hardness = m.getHardness();
            String sg = m.getSpecificGravity();
            String cleavage = m.getCleavage();
            String fracture = m.getFracture();
            String habit = m.getHabit();
            String cs = m.getCrystalSystem();
            String o = m.getOther();

            System.out.printf("%13s %6d %10s %10s %10s %8s %16s %10s %10s %10s %18s %18s\n",
                    name, lab, lustre, color, s, hardness, sg, cleavage, fracture, habit, cs, o);
        }
    }

    // EFFECTS: Print names of all minerals in given folder
    public void printMineralNames(Folder folder) {
        List<Mineral> mineralList = folder.getMineralList();
        for (Mineral mineral : mineralList) {
            System.out.println(mineral.getName());
        }
    }

    //STUDY METHODS:

    // EFFECTS: Print study menu options for user
    private void studyMenu() {
        System.out.println("\nSelect a property to view.");
        System.out.println("l for lustre, " + "co for color, " + "s for streak, " + "har for hardness, "
                + "sp for specific gravity, " + "cl for cleavage, " + " f for fracture, " + "hab for habit, "
                + "cs for crystal system, " + "o for other properties, " + "or g to guess mineral name");
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
                System.out.println(RED + "Please enter a valid property code." + RESET);
                selection = input.next().toLowerCase();
                quit(selection);
            }
        }
    }

    // EFFECTS: Check if letter entered by user is valid, valid inputs are ones corresponding to mineral properties
    public boolean propertyValid(String str) {
        return str.equals("g") | str.equals("l") | str.equals("co") | str.equals("s") | str.equals("har")
                | str.equals("sp") | str.equals("cl") | str.equals("f") | str.equals("hab") | str.equals("cs")
                | str.equals("o");
    }

    // EFFECTS: Determine if user guessed mineral name correctly, give option to continue playing or return to menu
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

    // EFFECTS: Display properties specified by user input
    @SuppressWarnings("methodlength")
    public void continueGame(String selection, Mineral currentMin) {
        if (selection.equals("q") | selection.equals("m")) {
            quit(selection);
        } else if (selection.equals("l")) {
            System.out.println(PURPLE + "\nLustre: " + RESET + currentMin.getLustre());
        } else if (selection.equals("co")) {
            System.out.println(PURPLE + "\nColor: " + RESET + currentMin.getColor());
        } else if (selection.equals("s")) {
            System.out.println(PURPLE + "\nStreak: " + RESET + currentMin.getStreak());
        } else if (selection.equals("har")) {
            System.out.println(PURPLE + "\nHardness: " + RESET + currentMin.getHardness());
        } else if (selection.equals("sp")) {
            System.out.println(PURPLE + "\nSpecific Gravity: " + RESET + currentMin.getSpecificGravity());
        } else if (selection.equals("cl")) {
            System.out.println(PURPLE + "\nCleavage: " + RESET + currentMin.getCleavage());
        } else if (selection.equals("f")) {
            System.out.println(PURPLE + "\nFracture: " + RESET + currentMin.getFracture());
        } else if (selection.equals("hab")) {
            System.out.println(PURPLE + "\nHabit: " + RESET + currentMin.getHabit());
        } else if (selection.equals("cs")) {
            System.out.println(PURPLE + "\nCrystal System: " + RESET + currentMin.getCrystalSystem());
        } else if (selection.equals("o")) {
            System.out.println(PURPLE + "\nOther: " + RESET + currentMin.getOther());
        } else if (selection.equals("g")) {
            guessMineral(currentMin);
        }
    }

    // EFFECTS: If selection is q, go to quit menu, if m, go to main menu, else do nothing
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
        messages.add(PURPLE + "\nLustre:" + RESET);
        messages.add(PURPLE + "\nColor:" + RESET);
        messages.add(PURPLE + "\nStreak:" + RESET);
        messages.add(PURPLE + "\nHardness:" + RESET);
        messages.add(PURPLE + "\nSpecific Gravity:" + RESET);
        messages.add(PURPLE + "\nCleavage:" + RESET);
        messages.add(PURPLE + "\nFracture:" + RESET);
        messages.add(PURPLE + "\nHabit:" + RESET);
        messages.add(PURPLE + "\nCrystal System: "
                + "\n" + "\ti for isometric, "
                + "tetra for tetragonal, "
                + "tri for triclinic, "
                + "h for hexagonal, "
                + "m for monoclinic,  "
                + "o for orthorhombic" + RESET);
        messages.add(PURPLE + "\nOther:" + RESET);
        return messages.get(i);
    }

    // MODIFIES: Mineral
    // EFFECTS: Sets specified property of the given mineral
    @SuppressWarnings("methodlength")
    public void setProperties(Mineral m, Scanner inpu, int i) {
        if (i == 0) {
            m.setLab(inpu.nextInt());
        } else if (i == 1) {
            nameProcess(m, inpu, i);
        } else if (i == 2) {
            m.setLustre(inpu.next());
        } else if (i == 3) {
            m.setColor(inpu.next());
        } else if (i == 4) {
            m.setStreak(inpu.next());
        } else if (i == 5) {
            m.setHardness(inpu.next());
        } else if (i == 6) {
            m.setSpecificGravity(inpu.next());
        } else if (i == 7) {
            m.setCleavage(inpu.next());
        } else if (i == 8) {
            m.setFracture(inpu.next());
        } else if (i == 9) {
            m.setHabit(inpu.next());
        } else if (i == 10) {
            m.setCrystalSystem(inpu.next().toLowerCase());
        } else if (i == 11) {
            m.setOther(inpu.next());
        }
    }

    // EFFECTS: If name is already in the list, prompt user to enter a new name
    public void nameProcess(Mineral m, Scanner inpu, int i) {
        String in = inpu.next();
        if (checkName(in)) {
            m.setName(in);
        } else {
            System.out.println("This mineral already exists in your lists. Please enter another name.");
            setProperties(m, inpu, i);
        }
    }

    // EFFECTS: Check if mineral of input name already exists in either folder, return false if not in either
    public boolean checkName(String name) {
        boolean val = true;
        List<Mineral> reviewList = toReview.getMineralList();
        List<Mineral> learnedList = learned.getMineralList();

        for (Mineral min : reviewList) {
            if (name.equals(min.getName())) {
                val = false;
                break;
            }
        }
        for (Mineral min : learnedList) {
            if (name.equals(min.getName())) {
                val = false;
                break;
            }
        }
        return val;
    }

    // SAVE and LOAD methods:
    // SOURCE: Code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    // EFFECTS: Saves the review and learned folders to file
    static void saveFolders() {
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

    // SOURCE: Code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // MODIFIES: this
    // EFFECTS: loads review folder and learned folder
//    public void loadFolders() {
//        try {
//            toReview = (ReviewFolder) jsonReaderRev.revRead();
//            System.out.println("Loaded review list from " + JSON_FOLDERS_R);
//            learned = jsonReaderLearn.lerRead();
//            System.out.println("Loaded learned list from " + JSON_FOLDERS_L);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_FOLDERS_L);
//        }
//    }

    // SOURCE: Code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // MODIFIES: this
    // EFFECTS: loads review folder and learned folder
    public void loadFolders() {
        try {
            toReview = jsonReaderRev.revRead();
            System.out.println("Loaded review list from " + JSON_FOLDERS_R);
            learned = jsonReaderLearn.lerRead();
            System.out.println("Loaded learned list from " + JSON_FOLDERS_L);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_FOLDERS_R);
        }
    }

    // GUI METHODS:

    // Effects: displays table of mineral class and review class
    public void displayTable() {
        tableR = new Table(toReview);
        tableL = new Table(learned);
    }


}
