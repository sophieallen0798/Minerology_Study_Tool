package ui;

import model.Mineral;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.Ui2.toReview;

public class Study extends JFrame {

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

    private JTextField guessBox;
    private JLabel guessLabel;
    private JFrame jframe;

    public Study() {
        super();
        jframe = new JFrame();
        mineral = toReview.nextStudyMineral();
        mineralsStudied = 1;
        studyButtons();
    }

    public void studyButtons() {
        makeMineralLabels();
        message = new JLabel();
        JPanel panel = new JPanel(new GridLayout(14, 2, 8, 8));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        if (!toReview.mineralListNotEmpty()) {
            message.setText("Review list is empty. Please add at least one mineral.");
        }
        JLabel titleLabel = new JLabel("Study Minerals");
        panel.setSize(500, 500);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
        makeButtons();
        setButtonCommands();
        guessSubmit.setActionCommand("guessBtn");
        guessSubmit.addActionListener(guessButton());
        setActionListeners();
        panel.add(titleLabel);
        addPropertyLabels(panel);
        guessDisplay(panel);
        add(panel, BorderLayout.CENTER);
    }

    private void guessDisplay(JPanel panel) {
        guessBox = new JTextField(5);
        guessLabel = new JLabel("Guess Mineral Name:");
        guessSubmit = new JButton("Submit Guess");
        currentMineral = new JLabel("Mineral Number " + mineralsStudied);
        panel.add(guessLabel);
        panel.add(guessBox);
        panel.add(guessSubmit);
        panel.add(message);
    }

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
                message.setText("Correct!");
            } else {
                message.setText("Incorrect, the mineral was " + mineral.getName());
            }
            resetLabels();
            mineral = toReview.nextStudyMineral();
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

    // EFFECTS: make labels used to display mineral property requested by user
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


    // EFFECTS: reset labels to empty string
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
    }

}