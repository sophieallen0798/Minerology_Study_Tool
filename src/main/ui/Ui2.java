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
    static Color red1;

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

    private JLabel pic;
    private ImageIcon photo;

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
        green1 = new Color(90, 180, 90);
        red1 = new Color(230, 20, 20);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 400));
        setLayout(new GridLayout(14, 2, 2, 2));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        menuLabel = new JLabel("");
        photo = new ImageIcon("data/rock.jpg");
        pic = new JLabel(photo);
        JButton addMinBtn = new JButton("Add Minerals");
        JButton studyBtn = new JButton("Study");
        JButton viewBtn = new JButton("View Folders");
        JButton organizeBtn = new JButton("Organize Folders");
        JButton loadBtn = new JButton("Load Folders");
        JButton saveBtn = new JButton("Save Folders");
        JButton viewCsImageBtn = new JButton("View Crystal Systems");
        JButton clearButton = new JButton("Clear All Folders");
        clearButton.setForeground(red1);
        addMinBtn.setActionCommand("addMin");
        studyBtn.setActionCommand("study");
        viewBtn.setActionCommand("view");
        organizeBtn.setActionCommand("organize");
        loadBtn.setActionCommand("load");
        saveBtn.setActionCommand("saveButton");
        clearButton.setActionCommand("clear");
        viewCsImageBtn.setActionCommand("viewCS");

        addMinBtn.addActionListener(actions());
        studyBtn.addActionListener(actionHappening());
        viewBtn.addActionListener(actionHappening());
        organizeBtn.addActionListener(actions());
        loadBtn.addActionListener(actionHappening());
        viewCsImageBtn.addActionListener(actionHappening());
        clearButton.addActionListener(actionHappening());
        field = new JTextField(5);
        //add(makeButton());
        add(viewCsImageBtn);
        add(addMinBtn);
        add(studyBtn);
        add(viewBtn);
        add(organizeBtn);
        add(loadBtn);
        add(saveBtn);
        add(clearButton);
        add(menuLabel);
        add(pic);
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
                    try {
                        studyGUI();
                    } catch (IllegalArgumentException k) {
                        System.out.println("hi");
                        emptyWarning();
                    }
                }
                if (e.getActionCommand().equals("viewCS")) {
                    csImage();
                }
                if (e.getActionCommand().equals("clear")) {
                    toReview = new ReviewFolder();
                    learned = new LearnedFolder();
                    label.setForeground(red1);
                    label.setText("Folders cleared");
                }
            }
        };
        return actListen;

    }

    // EFFECTS: Starts studyGUI
    public static void studyGUI() {
        Study frame = new Study();
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    // EFFECTS: opens image of a rock in a new panel
    public void csImage() {
        JPanel pan = new JPanel();
        JFrame frame1 = new JFrame();
        frame1.setVisible(true);
        frame1.add(pan);
        frame1.setSize(500, 500);
        frame1.add(pic);
    }

    // EFFECTS: Produces JOptionPane with review list empty warning
    public void emptyWarning() {
        JLabel warningLabel = new JLabel("Review list empty. Please add minerals to study.");
        warningLabel.setForeground(red1);
        JOptionPane.showMessageDialog(button, warningLabel);
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
        }
    }

    // EFFECTS: prompt user for name of mineral to move
    public void organizePrompt() {
        organizeFolders(learned);
        organizeFolders(toReview);
        System.out.println("Which mineral would you like to move? Enter mineral name or m to return to main menu");
        String selection = input.next();
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

    // EFFECTS: Print names of all minerals in given folder
    public void printMineralNames(Folder folder) {
        List<Mineral> mineralList = folder.getMineralList();
        for (Mineral mineral : mineralList) {
            System.out.println(mineral.getName());
        }
    }

    // EFFECTS: If name is already in the list, prompt user to enter a new name
    public void nameProcess(Mineral m, Scanner inpu, int i) {
        String in = inpu.next();
        if (checkName(in)) {
            m.setName(in);
        } else {
            System.out.println("This mineral already exists in your lists. Please enter another name.");
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

}
