package ui;

import model.Mineral;

import javax.swing.*;
import javax.swing.border.Border;
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
        //mineral = new Mineral();
        mineral = toReview.nextStudyMineral();
        mineralsStudied = 1;
        studyButtons();
    }

    public void studyButtons() {
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        makeMineralLabels();
        message = new JLabel();
        JTextField text = new JTextField(20);
        JPanel panel = new JPanel(new GridLayout(14, 2,8,8));
        //JButton button1 = new JButton("OK");
        //JButton button2 = new JButton("Next");
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        if (!toReview.mineralListNotEmpty()) {
            message.setText("Review list is empty. Please add at least one mineral.");
        }
        guessBox = new JTextField(5);
        guessLabel = new JLabel("Guess Mineral Name:");
        guessSubmit = new JButton("Submit Guess");
        currentMineral = new JLabel("Mineral Number " + mineralsStudied);
        JLabel titleLabel = new JLabel("Study Minerals");
        panel.setSize(500, 500);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);

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

        guessSubmit.setActionCommand("guessBtn");
        guessSubmit.addActionListener(guessButton());

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
        panel.add(titleLabel);
        panel.add(currentMineral);
        //panel.add(new JLabel(new ImageIcon("data/tobs.jpg")));

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

        panel.add(guessLabel);
        panel.add(guessBox);
        panel.add(guessSubmit);
        //panel.add(text);
        //panel.add(button1);
        //panel.add(button2);
        panel.add(message);
        add(panel, BorderLayout.CENTER);
//        button1.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent ae) {
//                JOptionPane.showConfirmDialog(null, "Are you Confirm?");
//            }
//        });
//        button2.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent ae) {
//                String value = text.getText();
//                JLabel label1 = new JLabel("Welcome: " + value);
//                JPanel pan = new JPanel();
//                pan.add(label1);
//                JFrame frame1 = new JFrame();
//                frame1.setVisible(true);
//                frame1.add(pan);
//                frame1.setSize(500, 500);
//
//            }
//        });
    }

// EFFECTS: Determine if user guessed mineral name correctly, move to next mineral
    private ActionListener guessButton() {
        ActionListener guessAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (guessBox.getText().equals(mineral.getName())) {
                    message.setText("Correct!");
                } else {
                    message.setText("Incorrect, the mineral was " + mineral.getName());
                }
                resetLabels();
                mineral = toReview.nextStudyMineral();
                mineralsStudied += 1;
                currentMineral.setText("Mineral Number " + mineralsStudied);
            }
        };
        return guessAction;
    }

    // EFFECTS: Action Listener for buttons to reveal properties - sets corresponding label to mineral property
    private ActionListener propertyButton() {
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        };
        return action;
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