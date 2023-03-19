package ui;

import model.Folder;
import model.Mineral;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RealFns extends JFrame implements ActionListener {
    private JLabel label;
    private JTextField field;
    private Folder folder;
    private Mineral mineral;

    public RealFns() {
        super("The title");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 90));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(new FlowLayout());
        JButton btn = new JButton("Change");
        btn.setActionCommand("myButton");
        btn.addActionListener(this); // Sets "this" object as an action listener for btn
        // so that when the btn is clicked,
        // this.actionPerformed(ActionEvent e) will be called.
        // You could also set a different object, if you wanted
        // a different object to respond to the button click
        label = new JLabel("flag");
        field = new JTextField(5);
        add(field);
        add(btn);
        add(label);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        mineral = new Mineral();
        mineral.setName("Name");
        //folder.addToMineralList(mineral);
    }

    //This is the method that is called when the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myButton")) {
            folder.addToMineralList(mineral);
            System.out.println(folder.getMineralList().get(1).getName());
            label.setText(field.getText());
        }
    }

    public static void main(String[] args) {
        new RealFns();
    }
}