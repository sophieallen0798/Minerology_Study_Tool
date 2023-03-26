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
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        makeMineralLabels();
        labels();
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

//        panel.add(lustre);
//        panel.add(color);
//        panel.add(streak);
//        panel.add(hardness);
//        panel.add(sg);
//        panel.add(cleavage);
//        panel.add(fracture);
//        panel.add(habit);
//        panel.add(cs);
//        panel.add(other);
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

// EFFECTS: Determine if user guessed mineral name correctly, give option to continue playing or return to menu
    private ActionListener guessButton() {
        ActionListener guessAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (guessBox.getText().equals(mineral.getName())) {
                    message.setText("Correct!");
                } else {
                    message.setText("Incorrect, the mineral was " + mineral.getName());
                }
                resetBoxesEmpty();
                mineral = toReview.nextStudyMineral();
                mineralsStudied += 1;
                currentMineral.setText("Mineral Number " + mineralsStudied);
            }
        };
        return guessAction;
    }

    private ActionListener propertyButton() {
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("lustre")) {
                    lustreLabel.setText(mineral.getLustre());
                }
                if (e.getActionCommand().equals("color")) {
                    colorLabel.setText(mineral.getColor());
                }
                if (e.getActionCommand().equals("streak")) {
                    streakLabel.setText(mineral.getStreak());
                }
                if (e.getActionCommand().equals("hardness")) {
                    hardnessLabel.setText(mineral.getHardness());
                }
                if (e.getActionCommand().equals("sg")) {
                    specificGravityLabel.setText(mineral.getSpecificGravity());
                }
                if (e.getActionCommand().equals("cleavage")) {
                    cleavageLabel.setText(mineral.getCleavage());
                }
                if (e.getActionCommand().equals("fracture")) {
                    fractureLabel.setText(mineral.getFracture());
                }
                if (e.getActionCommand().equals("habit")) {
                    habitLabel.setText(mineral.getHabit());
                }
                if (e.getActionCommand().equals("cs")) {
                    crystalSystemLabel.setText(mineral.getCrystalSystem());
                }
                if (e.getActionCommand().equals("other")) {
                    otherLabel.setText(mineral.getOther());
                }
            }
        };
        return action;
    }

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

    private void labels() {
//        add(lustreLabel);
//        add(lustre);
//        add(colorLabel);
//        add(color);
//        add(streakLabel);
//        add(streak);
//        add(hardnessLabel);
//        add(hardness);
//        add(specificGravityLabel);
//        add(sg);
//        add(cleavageLabel);
//        add(cleavage);
//        add(fractureLabel);
//        add(fracture);
//        add(habitLabel);
//        add(habit);
//        add(crystalSystemLabel);
//        add(cs);
//        add(otherLabel);
//        add(other);
    }





    private void resetBoxesEmpty() {
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


    // EFFECTS: Get new random mineral, start study game, continue game while user does not input "q"
//    public void startGame() {
//        String selection;
//        if (toReview.mineralListNotEmpty()) {
//            while (true) {
//                Mineral currentMin = toReview.nextStudyMineral();
//                System.out.println("\nMineral Number " + mineralsStudied + ":");
//                mineralsStudied += 1;
//                studyMenu();
//                selection = input.next().toLowerCase();
//                if (selection.equals("q")) {
//                    quitMenu();
//                    break;
//                }
//                nextProperty(selection, currentMin);
//            }
//        } else {
//            System.out.println(RED + "Review list is empty. Please add at least one mineral.\n" + RESET);
//        }
//    }
//
//    // EFFECTS: Determine if user guessed mineral name correctly, give option to continue playing or return to menu
//    public void guessMineral(Mineral currentMin) {
//        System.out.println("Enter guess:");
//        if (selection.equals(currentMin.getName())) {
//            message.setText("Correct!");
//        } else {
//            message.setText("Incorrect, the mineral was " + currentMin.getName());
//        }
//    }
//
//
//
//    // EFFECTS: Display properties specified by user input
//    @SuppressWarnings("methodlength")
//    public void continueGame(String selection, Mineral currentMin) {
//        if (selection.equals("q") | selection.equals("m")) {
//            quit(selection);
//        } else if (selection.equals("l")) {
//            System.out.println(PURPLE + "\nLustre: " + RESET + currentMin.getLustre());
//        } else if (selection.equals("co")) {
//            System.out.println(PURPLE + "\nColor: " + RESET + currentMin.getColor());
//        } else if (selection.equals("s")) {
//            System.out.println(PURPLE + "\nStreak: " + RESET + currentMin.getStreak());
//        } else if (selection.equals("har")) {
//            System.out.println(PURPLE + "\nHardness: " + RESET + currentMin.getHardness());
//        } else if (selection.equals("sp")) {
//            System.out.println(PURPLE + "\nSpecific Gravity: " + RESET + currentMin.getSpecificGravity());
//        } else if (selection.equals("cl")) {
//            System.out.println(PURPLE + "\nCleavage: " + RESET + currentMin.getCleavage());
//        } else if (selection.equals("f")) {
//            System.out.println(PURPLE + "\nFracture: " + RESET + currentMin.getFracture());
//        } else if (selection.equals("hab")) {
//            System.out.println(PURPLE + "\nHabit: " + RESET + currentMin.getHabit());
//        } else if (selection.equals("cs")) {
//            System.out.println(PURPLE + "\nCrystal System: " + RESET + currentMin.getCrystalSystem());
//        } else if (selection.equals("o")) {
//            System.out.println(PURPLE + "\nOther: " + RESET + currentMin.getOther());
//        } else if (selection.equals("g")) {
//            guessMineral(currentMin);
//        }
//    }
//
//    public void nextProperty(String selection, Mineral currentMin) {
//        while (!selection.equals("q")) {
//            if (propertyValid(selection)) {
//                continueGame(selection, currentMin);
//                studyMenu();
//                selection = input.next().toLowerCase();
//                quit(selection);
//            } else if (!propertyValid(selection)) {
//                System.out.println(RED + "Please enter a valid property code." + RESET);
//                selection = input.next().toLowerCase();
//                quit(selection);
//            }
//        }
//    }

}