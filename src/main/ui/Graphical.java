package ui;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;

public class Graphical extends JFrame {
    private final JButton button = new JButton();

    public Graphical() {
        super();
        this.setTitle("HelloApp");
        this.getContentPane().setLayout(null);
        this.setBounds(100, 100, 180, 140);
        this.add(makeButton());
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private JButton makeButton() {
        button.setText("Click me!");
        button.setBounds(40, 40, 100, 30);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(button, "Hello World!");
            }
        });
        return button;
    }

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        // Swing calls must be run by the event dispatching thread.
        SwingUtilities.invokeAndWait(() -> new Graphical());
    }
}