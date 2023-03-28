package ui;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                new Ui2();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

