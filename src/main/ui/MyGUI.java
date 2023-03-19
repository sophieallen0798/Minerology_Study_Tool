package ui;

import model.Folder;
import model.Mineral;
import ui.MineralApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JOptionPane;

class SwingDemo {
    private static MineralApp minApp;

    public static void main(final String args[]) throws FileNotFoundException {
        JButton btn = new JButton("Demo Button");
        minApp = new MineralApp();

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String str = event.getActionCommand();
                minApp.runApp(str);
            }
        };
        btn.setActionCommand("FirstButton");
        btn.addActionListener(actionListener);
        JOptionPane.showMessageDialog(null, btn);
    }
}