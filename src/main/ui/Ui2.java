package ui;

import model.LearnedFolder;
import model.ReviewFolder;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import static ui.AddMineralGUI.label;


// Application to study and keep a list of minerals
public class Ui2 extends JFrame {

    private Table table;
    private AddMineralGUI addMinGUI;

    static Color green1;
    static Color green2;
    static Color red1;
    static Color purple1;
    static Color blueish;

    private static final int NUM_PROPERTIES = 12;  // number of fields in mineral class
    static ReviewFolder toReview;
    static LearnedFolder learned;
    static JsonWriter jsonWriterRev;
    static JsonReader jsonReaderRev;
    static JsonWriter jsonWriterLearn;
    private final JsonReader jsonReaderLearn;
    private static final String JSON_FOLDERS_R = "./data/review.json";
    private static final String JSON_FOLDERS_L = "./data/learned.json";

    private JLabel pic;
    protected JButton button;
    static JLabel menuLabel;

    // EFFECTS: Initialize Folders and json writers and readers, goes to open menu
    public Ui2() throws FileNotFoundException {
        learned = new LearnedFolder();
        toReview = new ReviewFolder();
        jsonReaderRev = new JsonReader(JSON_FOLDERS_R);
        jsonWriterRev = new JsonWriter(JSON_FOLDERS_R);
        jsonReaderLearn = new JsonReader(JSON_FOLDERS_L);
        jsonWriterLearn = new JsonWriter(JSON_FOLDERS_L);
        //openMenu();
        guiMenu();
    }

    // Photo source: https://fairdinkumseeds.com/products-page/ethnobotanical-or-medicinal-plants/smiley-rock-massive-
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
        setButtons(addMinBtn, studyBtn, viewRev, viewLer, loadBtn, saveBtn, viewCsImageBtn);
        setButtonActions(addMinBtn, studyBtn, viewRev, viewLer, loadBtn, viewCsImageBtn);
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
        blueish = new Color(200, 200, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 500));
        setLayout(new GridLayout(14, 2, 2, 2));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        menuLabel = new JLabel("");
        ImageIcon photo = new ImageIcon("data/rock.jpg");
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
        return e -> {
            if (e.getActionCommand().equals("addMin")) {
                try {
                    new AddMineralGUI();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getActionCommand().equals("save")) {
                saveFolders();
                label.setForeground(green1);
            }
        };
    }

    // EFFECTS: Creates action listener for other menu buttons
    public ActionListener actionHappening() {
        return e -> {
            if (e.getActionCommand().equals("load")) {
                loadFolders();
            } else if (e.getActionCommand().equals("save") | e.getActionCommand().equals("save")) {
                saveFolders();
            } else if (e.getActionCommand().equals("viewRev")) {
                table = new Table(toReview);
                table.fun();
            } else if (e.getActionCommand().equals("viewLer")) {
                table = new Table(learned);
                table.fun();
            } else if (e.getActionCommand().equals("study")) {
                tryStudy();
            } else if (e.getActionCommand().equals("viewCS")) {
                csImage();
            }
        };
    }

    private void tryStudy() {
        try {
            studyGUI();
        } catch (IllegalArgumentException k) {
            System.out.println("hi");
            emptyWarning();
        }
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
