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

import static ui.AddMineralGUI.label;


// Application to study and keep a list of minerals
public class Ui2 extends JFrame {
    // sout colors
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String YELLOW = "\u001B[33m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    //private static JLabel label;
    private Table table;
    private AddMineralGUI addMinGUI;

    private Mineral mineral;
    private JTextField field;
    static Color green1;
    static Color green2;
    static Color red1;
    static Color purple1;
    static Color blueish;

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

    // Photo souce: https://fairdinkumseeds.com/products-page/ethnobotanical-or-medicinal-plants/smiley-rock-massive-
    // 175-00-discount/?fbclid=IwAR1XmPqBKLMuIK8tOwgKQQafO2VtkG1bsvwyIeuNHPc-m2CEmev6nEiTmIg

    // EFFECTS: Display start menu with buttons
    public void guiMenu() {
        jframeDesign();
        JButton addMinBtn = new JButton("Add Minerals");
        JButton studyBtn = new JButton("Study");
        JButton viewRev = new JButton("View Review Folder");
        JButton viewLer = new JButton("View Learned Folder");
        JButton loadBtn = new JButton("Load Folders");
        JButton saveBtn = new JButton("Save Folders");
        JButton viewCsImageBtn = new JButton("Display Photo");
        //addMinBtn.setForeground(purple1);
//        addMinBtn.setBackground(blueish);
//        saveBtn.setBackground(green2);
//        loadBtn.setBackground(green2);
        setButtons(addMinBtn, studyBtn, viewRev, viewLer, loadBtn, saveBtn, viewCsImageBtn);
        setButtonActions(addMinBtn, studyBtn, viewRev, viewLer, loadBtn, viewCsImageBtn);
        field = new JTextField(5);
        //add(makeButton());
        addButtons(addMinBtn, studyBtn, viewRev, viewLer, loadBtn, saveBtn, viewCsImageBtn);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    // EFFECTS: Sets JFrame specifications, creates new label, photo, and colors
    private void jframeDesign() {
        green1 = new Color(90, 180, 90);
        green2 = new Color(150, 210, 150);
        red1 = new Color(230, 20, 20);
        purple1 = new Color(170, 20, 100);
        blueish = new Color(200,200,250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 500));
        setLayout(new GridLayout(14, 2, 2, 2));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        menuLabel = new JLabel("");
        photo = new ImageIcon("data/rock.jpg");
        pic = new JLabel(photo);
    }

    // EFFECTS: Add buttons, photo, and label to JFrame
    private void addButtons(JButton addMinBtn, JButton studyBtn, JButton viewRev, JButton viewLer,
                            JButton loadBtn, JButton saveBtn, JButton viewCsImageBtn) {
        add(viewCsImageBtn);
        add(addMinBtn);
        add(studyBtn);
        add(viewRev);
        add(viewLer);
        add(loadBtn);
        add(saveBtn);
        add(menuLabel);
        //add(pic);
    }

    // EFFECTS: Sets Action Listener for buttons
    private void setButtonActions(JButton addMinBtn, JButton studyBtn, JButton viewRev, JButton viewLer,
                                  JButton loadBtn, JButton viewCsImageBtn) {
        addMinBtn.addActionListener(actions());
        studyBtn.addActionListener(actionHappening());
        viewRev.addActionListener(actionHappening());
        viewLer.addActionListener(actionHappening());
        loadBtn.addActionListener(actionHappening());
        viewCsImageBtn.addActionListener(actionHappening());
    }

    // EFFECTS: Sets action commands for buttons
    private void setButtons(JButton addMinBtn, JButton studyBtn, JButton viewRev, JButton viewLer, JButton loadBtn,
                            JButton saveBtn, JButton viewCsImageBtn) {
        addMinBtn.setActionCommand("addMin");
        studyBtn.setActionCommand("study");
        viewRev.setActionCommand("viewRev");
        viewLer.setActionCommand("viewLer");
        loadBtn.setActionCommand("load");
        saveBtn.setActionCommand("save");
        viewCsImageBtn.setActionCommand("viewCS");
    }

    // EFFECTS: Creates action listener for add and organize buttons, accessed from AddMineralGUI
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
                if (e.getActionCommand().equals("save")) {
                    saveFolders();
                    label.setForeground(green1);
                }
            }
        };
        return actionListener;
    }

    // EFFECTS: Creates action listener for other menu buttons
    public ActionListener actionHappening() {
        ActionListener actListen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("load")) {
                    loadFolders();
                } else if (e.getActionCommand().equals("save") | e.getActionCommand().equals("save")) {
                    //toReview = addMinGUI.getFolder();
                    saveFolders();
                } else if (e.getActionCommand().equals("viewRev")) {
                    table = new Table(toReview);
                    table.fun("a");
                } else if (e.getActionCommand().equals("viewLer")) {
                    table = new Table(learned);
                    table.fun("a");
                } else if (e.getActionCommand().equals("study")) {
                    tryStudy();
                } else if (e.getActionCommand().equals("viewCS")) {
                    csImage();
                }
//                } else if (e.getActionCommand().equals("clear")) {
//                    clearFolders();
//                }
            }
        };
        return actListen;
    }

    private void tryStudy() {
        try {
            studyGUI();
        } catch (IllegalArgumentException k) {
            System.out.println("hi");
            emptyWarning();
        }
    }

    // MODIFIES: Learned folder and Review folder
    // EFFECTS: Reset both learned and review folders
    private void clearFolders() {
        toReview = new ReviewFolder();
        learned = new LearnedFolder();
        menuLabel.setForeground(red1);
        menuLabel.setText("Folders cleared");
    }

    // EFFECTS: Starts studyGUI
    public static void studyGUI() {
        Study frame = new Study();
        frame.setSize(400, 500);
        frame.setVisible(true);
    }

    // EFFECTS: Opens image of a rock in a new frame
    public void csImage() {
        JPanel pan = new JPanel();
        JFrame frame1 = new JFrame();
        frame1.setVisible(true);
        frame1.add(pan);
        frame1.setSize(400, 500);
        frame1.add(pic);
    }

    // EFFECTS: Produces JOptionPane with review list empty warning
    public void emptyWarning() {
        JLabel warningLabel = new JLabel("Review list empty. Please add minerals to study.");
        warningLabel.setForeground(red1);
        JOptionPane.showMessageDialog(button, warningLabel);
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

    // SAVE and LOAD methods:
    // SOURCE: Code adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    // EFFECTS: Saves the review and learned folders to file
    static void saveFolders() {
        try {
            jsonWriterRev.open();
            jsonWriterRev.write(toReview);
            jsonWriterRev.close();
            //System.out.println("Saved review list to " + JSON_FOLDERS_R);
            jsonWriterLearn.open();
            jsonWriterLearn.write(learned);
            jsonWriterLearn.close();
            //System.out.println("Saved learned list to " + JSON_FOLDERS_L);
            menuLabel.setForeground(green1);
            menuLabel.setText("Folders Saved");

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
            //System.out.println("Loaded review list from " + JSON_FOLDERS_R);
            learned = jsonReaderLearn.lerRead();
            //System.out.println("Loaded learned list from " + JSON_FOLDERS_L);
            menuLabel.setForeground(green1);
            menuLabel.setText("Folders Loaded");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_FOLDERS_R);
            menuLabel.setForeground(red1);
            menuLabel.setText("Unable to read from file: " + JSON_FOLDERS_R + JSON_FOLDERS_L);
        }
    }

}
