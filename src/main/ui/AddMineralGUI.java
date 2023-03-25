package ui;

import model.Folder;
import model.Mineral;
import model.ReviewFolder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import static ui.Ui2.actions;
import static ui.Ui2.toReview;


public class AddMineralGUI extends JFrame {

    private Mineral min;
    private JTextField field;
    private Color green1;

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
    private JLabel label;

    private JFrame jframe;
    private JPanel jpanel;

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

    private ReviewFolder revFolder;
    private Ui2 ui2;

    public AddMineralGUI(ReviewFolder folder) throws FileNotFoundException {
        super();
        jframe = new JFrame();
        addMineralMenu();
    }

    public void addMineralMenu() {
        //super("The title");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //add(controlPanel);
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new GridLayout(14,2));
        JButton addBtn = new JButton("Add");
        JButton saveBtn = new JButton("Save");
        addBtn.setActionCommand("add");
        //addBtn.addActionListener(actions());
        addBtn.addActionListener(myActions());
        saveBtn.setActionCommand("save");
        saveBtn.addActionListener(actions());

        label = new JLabel("");
        green1 = new Color(90, 180,  90);

        makeMineralLabels();
        //mineral = new Mineral();
        makeMineralBoxes();
        resetBoxesEmpty();
        //field = new JTextField(5);
        labelsBoxes();
        add(saveBtn);
        add(addBtn);
        add(label);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    public ActionListener myActions() {
        ActionListener myListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mineral min = new Mineral();
                min = getMineral();
                toReview.addToMineralList(min);
                label.setForeground(green1);
                System.out.println(toReview);
                System.out.println(min.getName());
                label.setText("   " + min.getName() + " added to review list.");
                resetBoxesEmpty();
            }
        };
        return myListener;
    }



    //This is the method that is called when the JButton btn is clicked
//    public void actionPerformed(ActionEvent e) {
//        if (e.getActionCommand().equals("add")) {
//            Mineral min = getMineral();
//            addMineralToFolder(min);
//            label.setForeground(green1);
//            label.setText("   " + min.getName() + " added to review list.");
//            resetBoxesEmpty();
//        }
//        if (e.getActionCommand().equals("save")) {
//            revFolder.saveFolders();
//            label.setText("Minerals saved");
//        }
//    }

    private void addMineralToFolder(Mineral min) {
        toReview.addToMineralList(min);
    }

    public ReviewFolder getFolder() {
        return toReview;
    }

    private void makeMineralLabels() {
        labLabel = new JLabel("Lab Number:");
        nameLabel = new JLabel("Name:");
        lustreLabel = new JLabel("Lustre:");
        colorLabel = new JLabel("Color:");
        streakLabel = new JLabel("Streak:");
        hardnessLabel = new JLabel("Hardness:");
        specificGravityLabel = new JLabel("Specific Gravity:");
        cleavageLabel = new JLabel("Cleavage:");
        fractureLabel = new JLabel("Fracture:");
        habitLabel = new JLabel("Habit:");
        crystalSystemLabel = new JLabel("Crystal System:");
        otherLabel = new JLabel("Other:");
    }

    private void makeMineralBoxes() {
        labBox = new JTextField(5);
        nameBox = new JTextField(5);
        colorBox = new JTextField(5);
        lustreBox = new JTextField(5);
        streakBox = new JTextField(5);
        hardnessBox = new JTextField(5);
        specificGravityBox = new JTextField(5);
        cleavageBox = new JTextField(5);
        fractureBox = new JTextField(5);
        habitBox = new JTextField(5);
        crystalSystemBox = new JTextField(5);
        otherBox = new JTextField(5);
    }

    @SuppressWarnings("methodlength")
    private void labelsBoxes() {
        add(labLabel);
        add(labBox);
        add(nameLabel);
        add(nameBox);
        add(lustreLabel);
        add(lustreBox);
        add(colorLabel);
        add(colorBox);
        add(streakLabel);
        add(streakBox);
        add(hardnessLabel);
        add(hardnessBox);
        add(specificGravityLabel);
        add(specificGravityBox);
        add(cleavageLabel);
        add(cleavageBox);
        add(fractureLabel);
        add(fractureBox);
        add(habitLabel);
        add(habitBox);
        add(crystalSystemLabel);
        add(crystalSystemBox);
        add(otherLabel);
        add(otherBox);
    }

    private void resetBoxesEmpty() {
        labBox.setText("");
        nameBox.setText("");
        lustreBox.setText("");
        colorBox.setText("");
        streakBox.setText("");
        hardnessBox.setText("");
        specificGravityBox.setText("");
        cleavageBox.setText("");
        fractureBox.setText("");
        habitBox.setText("");
        crystalSystemBox.setText("");
        otherBox.setText("");
    }

    private Mineral getMineral() {
        this.min = new Mineral();
        min.setLab(Integer.parseInt(labBox.getText()));
        min.setName(nameBox.getText());
        min.setLustre(lustreBox.getText());
        min.setColor(colorBox.getText());
        min.setStreak(streakBox.getText());
        min.setHardness(hardnessBox.getText());
        min.setSpecificGravity(specificGravityBox.getText());
        min.setCleavage(cleavageBox.getText());
        min.setFracture(fractureBox.getText());
        min.setHabit(habitBox.getText());
        min.setCrystalSystem(crystalSystemBox.getText());
        min.setOther(otherBox.getText());
        return min;
    }

//    static Mineral getMin() {
//        return min;
//    }

}
