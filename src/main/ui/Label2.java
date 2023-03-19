package ui;

import model.Folder;
import model.Mineral;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class Label2 extends JFrame implements ActionListener {
    private MineralApp mineralApp;
    private JLabel label;
    private Table table;

    private Mineral mineral;
    private JTextField field;
    private Folder folder;

    private JLabel nameLabel;
    private JLabel colorLabel;
    private JLabel labLabel;

    private JTextField labBox;
    private JTextField nameBox;
    private JTextField colorBox;



    public Label2() throws FileNotFoundException {
        super("The title");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new GridLayout(4,2));
        JButton btn = new JButton("Change");
        btn.setActionCommand("myButton");
        btn.addActionListener(this); // Sets "this" object as an action listener for btn
        // so that when the btn is clicked,
        // this.actionPerformed(ActionEvent e) will be called.
        // You could also set a different object, if you wanted
        // a different object to respond to the button click
        mineralApp = new MineralApp();

        label = new JLabel("flag");

        labLabel = new JLabel("Lab Number:");
        nameLabel = new JLabel("Name:");
        colorLabel = new JLabel("Color:");

        mineral = new Mineral();
        folder = new Folder("folderName");

        labBox = new JTextField(5);
        nameBox = new JTextField(5);
        colorBox = new JTextField(5);
        field = new JTextField(5);

        add(labLabel);
        add(labBox);
        add(nameLabel);
        add(nameBox);
        add(colorLabel);
        add(colorBox);
        add(btn);
        add(label);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    //This is the method that is called when the the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myButton")) {
            mineral.setLab(Integer.parseInt(labBox.getText()));
            mineral.setName(nameBox.getText());
            mineral.setColor(colorBox.getText());
            folder.addToMineralList(mineral);
            mineralApp.printFoldersInColumns(folder);
            label.setText(folder.getMineralList().get(0).getName());
            table = new Table(folder);
            table.fun("a");


//            String text = f1.getText();
//            int xaxis1 = Integer.parseInt(text);

        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Label2();
    }
}
