package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class App {
    private JButton studyButton;
    private JPanel panel1;
    private JButton organizeFoldersButton;
    private JButton addMineralsButton;
    private JButton viewFoldersButton;
    private JButton loadFoldersButton;
    private JButton saveFoldersButton;
    private JButton quitButton;
    private MineralApp minApp;
    private EnterMinerals enterMinerals;

    public App() throws FileNotFoundException {
        this.minApp = new MineralApp();

        studyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                minApp.startGame();
            }
        });
        organizeFoldersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                minApp.organizePrompt();
            }
        });
        loadFoldersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                minApp.loadFolders();
            }
        });
        addMineralsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    enterMinerals = new EnterMinerals();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                //minApp.addMineralsActivity();
            }
        });
        viewFoldersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                minApp.printFolders();
            }
        });
        saveFoldersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                minApp.saveFolders();
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                minApp.runApp("q");
            }

        });
    }

    public static void main(String[] args) throws FileNotFoundException {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
