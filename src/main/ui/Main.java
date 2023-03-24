package ui;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {

        //Schedule a job for the event-dispatching thread:

        //creating and showing this application's GUI.

        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                try {
                    new Ui2();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }

        });
    }
}
//        try {
//            new MineralApp();
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found. Unable to run application");
//        }

