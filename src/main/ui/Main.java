package ui;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                new MineralAppGUI();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

