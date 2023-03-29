package ui;

import model.LearnedFolder;
import model.ReviewFolder;
import persistance.JsonReader;
import persistance.JsonWriter;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import static ui.AddMineralGUI.label;

// Rock photo source: https://fairdinkumseeds.com/products-page/ethnobotanical-or-medicinal-plants/smiley-rock-massive-
// 175-00-discount/?fbclid=IwAR1XmPqBKLMuIK8tOwgKQQafO2VtkG1bsvwyIeuNHPc-m2CEmev6nEiTmIg

// Application to study and keep a list of minerals
public class MineralApp extends JFrame {

    private Table table;
    private AddMineralGUI addMinGUI;

    static Color green1;
    static Color green2;
    static Color red1;
    static Color purple1;
    static Color blueish;

    static ReviewFolder toReview;
    static LearnedFolder learned;
    static JsonWriter jsonWriterRev;
    static JsonReader jsonReaderRev;
    static JsonWriter jsonWriterLearn;
    static JsonReader jsonReaderLearn;
    private static final String JSON_FOLDERS_R = "./data/review.json";
    private static final String JSON_FOLDERS_L = "./data/learned.json";

    private JLabel pic;
    protected JButton button;
    static JLabel menuLabel;
    private JDialog dialog;

    // EFFECTS: Initialize Folders and json writers and readers, goes to open menu
    public MineralApp() throws FileNotFoundException {
        learned = new LearnedFolder();
        toReview = new ReviewFolder();
        jsonReaderRev = new JsonReader(JSON_FOLDERS_R);
        jsonWriterRev = new JsonWriter(JSON_FOLDERS_R);
        jsonReaderLearn = new JsonReader(JSON_FOLDERS_L);
        jsonWriterLearn = new JsonWriter(JSON_FOLDERS_L);
        ImageIcon photo = new ImageIcon("data/rock.jpg");
        pic = new JLabel(photo);
        JOptionPane optionPane = new JOptionPane(getPanel(),
                JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null,
                new Object[] {}, null);
        dialog = optionPane.createDialog("Start Mineral App");
        dialog.setVisible(true);
        guiMenu();
    }

    // Source: Code adaped from http://www.java2s.com/Tutorials/Java/Swing_How_to/JOptionPane/Create_JOptionPane_
    // from_an_inner_JPanel.htm
    private JPanel getPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Load Folders?");
        JButton loadFolders = new JButton("Yes");
        loadFolders.setActionCommand("load");
        loadFolders.addActionListener(actionHappening());
        JButton noBtn = new JButton("No");
        noBtn.setActionCommand("no");
        noBtn.addActionListener(e -> dialog.dispose());
        panel.setLayout(new FlowLayout());
        panel.setSize(400, 400);
        panel.add(label);
        panel.add(loadFolders);
        panel.add(noBtn);
        panel.add(pic);
        return panel;
    }

    // EFFECTS: Display start menu with button options
    public void guiMenu() {
        jframeDesign();
        JButton addMinBtn = new JButton("Add Minerals");
        JButton studyBtn = new JButton("Study");
        JButton viewRev = new JButton("View Review Folder");
        JButton viewLer = new JButton("View Learned Folder");
        JButton loadBtn = new JButton("Load Folders");
        JButton saveBtn = new JButton("Save Folders");
        setButtons(addMinBtn, studyBtn, viewRev, viewLer, loadBtn, saveBtn);
        setButtonActions(addMinBtn, studyBtn, viewRev, viewLer, loadBtn, saveBtn);
        addButtons(addMinBtn, studyBtn, viewRev, viewLer, loadBtn, saveBtn);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    // EFFECTS: Sets JFrame specifications, creates new label and colors
    private void jframeDesign() {
        green1 = new Color(90, 180, 90);
        green2 = new Color(150, 210, 150);
        red1 = new Color(230, 20, 20);
        purple1 = new Color(170, 20, 100);
        blueish = new Color(200, 200, 250);
        onClose();
        setPreferredSize(new Dimension(400, 500));
        setLayout(new GridLayout(14, 2, 2, 2));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        menuLabel = new JLabel("");
    }

    // MODIFIES: this
    // EFFECTS: Sets action for JFrame close
    private void onClose() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                String[] objButtons = new String[]{"Yes","No"};
                int promptResult = JOptionPane.showOptionDialog(null,
                        "Save folders?", "Save",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                        objButtons, objButtons[1]);
                if (promptResult == 0) {
                    saveFolders();
                }
                System.exit(0);
            }
        });
    }

    // EFFECTS: Add buttons, photo, and label to JFrame
    private void addButtons(JButton addMinBtn, JButton studyBtn, JButton viewRev, JButton viewLer,
                            JButton loadBtn, JButton saveBtn) {
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
                                  JButton loadBtn, JButton saveBtn) {
        addMinBtn.addActionListener(actions());
        studyBtn.addActionListener(actionHappening());
        viewRev.addActionListener(actionHappening());
        viewLer.addActionListener(actionHappening());
        loadBtn.addActionListener(actionHappening());
        saveBtn.addActionListener(actions());
    }

    // EFFECTS: Sets action commands for buttons
    private void setButtons(JButton addMinBtn, JButton studyBtn, JButton viewRev, JButton viewLer, JButton loadBtn,
                            JButton saveBtn) {
        addMinBtn.setActionCommand("addMin");
        studyBtn.setActionCommand("study");
        viewRev.setActionCommand("viewRev");
        viewLer.setActionCommand("viewLer");
        loadBtn.setActionCommand("load");
        saveBtn.setActionCommand("save");
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
                try {
                    label.setForeground(green1);
                    label.setText("Folders Saved");
                } catch (NullPointerException n) {
                    menuLabel.setForeground(green1);
                    menuLabel.setText("Folders Saved");
                }
            }
        };
    }

    // EFFECTS: Creates action listener for other menu buttons
    public ActionListener actionHappening() {
        return e -> {
            if (e.getActionCommand().equals("load")) {
                loadFolders();
                dialog.dispose();
            } else if (e.getActionCommand().equals("save")) {
                saveFolders();
                label.setForeground(green1);
                label.setText("Folders Saved");
            } else if (e.getActionCommand().equals("viewRev")) {
                table = new Table(toReview);
                table.fun();
            } else if (e.getActionCommand().equals("viewLer")) {
                table = new Table(learned);
                table.fun();
            } else if (e.getActionCommand().equals("study")) {
                tryStudy();
            }
        };
    }

    // EFFECTS: Start study if review list not empty, otherwise show warning message
    private void tryStudy() {
        try {
            studyGUI();
        } catch (IllegalArgumentException k) {
            emptyWarning();
        }
    }

    // EFFECTS: Starts studyGUI
    public static void studyGUI() {
        Study frame = new Study();
        frame.setSize(400, 500);
        frame.setVisible(true);
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
            jsonWriterLearn.open();
            jsonWriterLearn.write(learned);
            jsonWriterLearn.close();
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
            learned = jsonReaderLearn.lerRead();
            try {
                menuLabel.setForeground(green1);
                menuLabel.setText("Folders Loaded");
            } catch (NullPointerException e) {
                // Set no labels
            }

        } catch (IOException e) {
            menuLabel.setForeground(red1);
            menuLabel.setText("Unable to read from file: " + JSON_FOLDERS_R + JSON_FOLDERS_L);
        }
    }

}
