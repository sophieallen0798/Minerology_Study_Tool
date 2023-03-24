package ui;

import model.Folder;
import model.Mineral;
import model.ReviewFolder;

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;

public class EnterMinerals extends JFrame implements ActionListener {
    private Mineral mineral;
    private MineralApp minApp;
    private JButton submitButton;

    private JTextField name;
    private JTextField color;
    private JTextField habit;
    private JTextField fracture;
    private JTextField crystalSystem;
    private JTextField other;
    private Folder folder;
    private JPanel panel1;
    private JLabel printMineral;
    private Table table;

    public EnterMinerals() throws FileNotFoundException {
        super("The title");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setPreferredSize(new Dimension(400, 90));
//        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
//        setLayout(new FlowLayout());
//        JButton btn = new JButton("Change");
//        btn.setActionCommand("myButton");
//        btn.addActionListener(this);
        mineral = new Mineral();
        minApp = new MineralApp();
        folder = new ReviewFolder("R");
        printMineral = new JLabel("flag");
        name = new JTextField(5);
        color = new JTextField(5);
        habit = new JTextField(5);
        fracture = new JTextField(5);
        crystalSystem = new JTextField(5);
        other = new JTextField(5);
        add(printMineral);
        add(name);
        add(color);
        add(habit);
        add(fracture);
        add(crystalSystem);
        add(other);
        pack();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("submitButton")) {
                    mineral.setName(name.getText());
                    mineral.setColor(color.getText());
                    mineral.setHabit(habit.getText());
                    mineral.setFracture(fracture.getText());
                    mineral.setCrystalSystem(crystalSystem.getText());
                    mineral.setOther(crystalSystem.getText());
                    folder.addToMineralList(mineral);
                    table = new Table(folder);
                    System.out.println(folder.getMineralList().get(0).getName());
                }
            }
        });
    }

    //This is the method that is called when the the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myButton")) {
            mineral.setName(name.getText());
            mineral.setColor(color.getText());
            mineral.setHabit(habit.getText());
            mineral.setFracture(fracture.getText());
            mineral.setCrystalSystem(crystalSystem.getText());
            mineral.setOther(crystalSystem.getText());
            folder.addToMineralList(mineral);
            String outPrint = folder.getMineralList().get(1).getName();
            printMineral.setText(outPrint);
            System.out.println(folder.getMineralList().get(1).getName());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new EnterMinerals().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
