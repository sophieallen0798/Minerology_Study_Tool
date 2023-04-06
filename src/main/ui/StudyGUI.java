package ui;

import model.Mineral;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;

import static ui.MineralAppGUI.*;

// Class Declaration: JFrame for studying minerals

public class StudyGUI extends JFrame {

    private JButton lustre;
    private JButton color;
    private JButton streak;
    private JButton hardness;
    private JButton sg;
    private JButton cleavage;
    private JButton fracture;
    private JButton habit;
    private JButton cs;
    private JButton other;
    private JButton guessSubmit;

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
    private JLabel message;
    private JLabel currentMineral;

    private Mineral mineral;
    private int mineralsStudied;
    private static int MINERAL_PROBABILITY;

    private JTextField guessBox;
    private JLabel guessLabel;
    private final JFrame jframe;

    public StudyGUI() {
        super();
        MINERAL_PROBABILITY = 5; // change of selecting mineral from learned folder (ex 5 = 1 in 5 chance)
        jframe = new JFrame();
        mineral = randomFolder();
        mineralsStudied = 1;
        studyButtons();
    }

    // EFFECTS: creates display with buttons and labels
    public void studyButtons() {
        JPanel panel = new JPanel(new GridLayout(14, 2, 8, 8));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        if (!toReview.mineralListNotEmpty()) {
            message.setText("Review list is empty. Please add at least one mineral.");
        }
        makeMineralLabels();
        guessDisplay();
        message = new JLabel();
        JLabel titleLabel = new JLabel("Study Minerals");
        panel.setSize(500, 500);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
        makeButtons();
        setButtonCommands();
        setActionListeners();
        panel.add(titleLabel);
        addPropertyLabels(panel);
        addGuesses(panel);
        add(panel, BorderLayout.CENTER);
    }

    // EFFECTS: Add guess boxes and labels to panel
    private void addGuesses(JPanel panel) {
        panel.add(guessLabel);
        panel.add(guessBox);
        panel.add(guessSubmit);
        panel.add(message);
    }

    // EFFECTS: Make labels used to display mineral property requested by user
    private void makeMineralLabels() {
        lustreLabel = new JLabel("");
        colorLabel = new JLabel("");
        streakLabel = new JLabel("");
        hardnessLabel = new JLabel("");
        specificGravityLabel = new JLabel("");
        cleavageLabel = new JLabel("");
        fractureLabel = new JLabel("");
        habitLabel = new JLabel("");
        crystalSystemLabel = new JLabel("");
        otherLabel = new JLabel("");
    }

    // EFFECTS: Makes guess display and sets actions
    private void guessDisplay() {
        guessBox = new JTextField(5);
        guessLabel = new JLabel("Guess Mineral Name:");
        guessSubmit = new JButton("Submit Guess");
        guessSubmit.setActionCommand("guessBtn");
        guessSubmit.addActionListener(guessButton());
        currentMineral = new JLabel("Mineral Number " + mineralsStudied);
    }

    // EFFECTS: Sets action listener for buttons
    private void setActionListeners() {
        lustre.addActionListener(propertyButton());
        color.addActionListener(propertyButton());
        streak.addActionListener(propertyButton());
        hardness.addActionListener(propertyButton());
        sg.addActionListener(propertyButton());
        cleavage.addActionListener(propertyButton());
        fracture.addActionListener(propertyButton());
        habit.addActionListener(propertyButton());
        cs.addActionListener(propertyButton());
        other.addActionListener(propertyButton());
    }

    // EFFECTS: Sets commands for buttons
    private void setButtonCommands() {
        lustre.setActionCommand("lustre");
        color.setActionCommand("color");
        streak.setActionCommand("streak");
        hardness.setActionCommand("hardness");
        sg.setActionCommand("sg");
        cleavage.setActionCommand("cleavage");
        fracture.setActionCommand("fracture");
        habit.setActionCommand("habit");
        cs.setActionCommand("cs");
        other.setActionCommand("other");
    }

    // EFFECTS: Make new buttons for panel
    private void makeButtons() {
        lustre = new JButton("Lustre");
        color = new JButton("Color");
        streak = new JButton("Streak");
        hardness = new JButton("Hardness");
        sg = new JButton("Specific Gravity");
        cleavage = new JButton("Cleavage");
        fracture = new JButton("Fracture");
        habit = new JButton("Habit");
        cs = new JButton("Crystal System");
        other = new JButton("Other Properties");
    }

    // EFFECTS: Add property labels to panel
    private void addPropertyLabels(JPanel panel) {
        panel.add(currentMineral);
        panel.add(lustre);
        panel.add(lustreLabel);
        panel.add(color);
        panel.add(colorLabel);
        panel.add(streak);
        panel.add(streakLabel);
        panel.add(hardness);
        panel.add(hardnessLabel);
        panel.add(sg);
        panel.add(specificGravityLabel);
        panel.add(cleavage);
        panel.add(cleavageLabel);
        panel.add(fracture);
        panel.add(fractureLabel);
        panel.add(habit);
        panel.add(habitLabel);
        panel.add(cs);
        panel.add(crystalSystemLabel);
        panel.add(other);
        panel.add(otherLabel);
    }

    // EFFECTS: Determine if user guessed mineral name correctly, move to next mineral
    private ActionListener guessButton() {
        ActionListener guessAction = e -> {
            if (guessBox.getText().equals(mineral.getName())) {
                message.setForeground(green1);
                message.setText("Correct!");
            } else {
                message.setForeground(red1);
                message.setText("Incorrect, the mineral was " + mineral.getName());
            }
            resetLabels();
            mineral = randomFolder();
            mineralsStudied += 1;
            currentMineral.setText("Mineral Number " + mineralsStudied);
        };
        return guessAction;
    }

    // EFFECTS: Action Listener for buttons to reveal properties - sets corresponding label to mineral property
    private ActionListener propertyButton() {
        return e -> {
            if (e.getActionCommand().equals("lustre")) {
                lustreLabel.setText(mineral.getLustre());
            } else if (e.getActionCommand().equals("color")) {
                colorLabel.setText(mineral.getColor());
            } else if (e.getActionCommand().equals("streak")) {
                streakLabel.setText(mineral.getStreak());
            } else if (e.getActionCommand().equals("hardness")) {
                hardnessLabel.setText(mineral.getHardness());
            } else if (e.getActionCommand().equals("sg")) {
                specificGravityLabel.setText(mineral.getSpecificGravity());
            } else if (e.getActionCommand().equals("cleavage")) {
                cleavageLabel.setText(mineral.getCleavage());
            } else if (e.getActionCommand().equals("fracture")) {
                fractureLabel.setText(mineral.getFracture());
            } else if (e.getActionCommand().equals("habit")) {
                habitLabel.setText(mineral.getHabit());
            } else if (e.getActionCommand().equals("cs")) {
                crystalSystemLabel.setText(mineral.getCrystalSystem());
            } else if (e.getActionCommand().equals("other")) {
                otherLabel.setText(mineral.getOther());
            }
        };
    }

    // EFFECTS: Reset labels to empty string
    private void resetLabels() {
        lustreLabel.setText("");
        colorLabel.setText("");
        streakLabel.setText("");
        hardnessLabel.setText("");
        specificGravityLabel.setText("");
        cleavageLabel.setText("");
        fractureLabel.setText("");
        habitLabel.setText("");
        crystalSystemLabel.setText("");
        otherLabel.setText("");
        guessBox.setText("");
    }

    // EFFECTS: Randomly (with weighted probability) selects folder and returns next random mineral
    public Mineral randomFolder() {
        Random rand = new Random();
        int randomFolder = rand.nextInt(MINERAL_PROBABILITY);
        if (learned.mineralListNotEmpty()) {
            if (randomFolder == 1) {
                return learned.nextStudyMineral();
            } else {
                return toReview.nextStudyMineral();
            }
        } else {
            return toReview.nextStudyMineral();
        }
    }

}