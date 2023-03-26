package ui;


import model.Folder;
import model.Mineral;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import static ui.Ui2.*;

// Source: modified from https://docs.oracle.com/javase/tutorial/uiswing/components/table.html
public class Table extends JPanel {
    private String[] columnNames;
    private JTable table;
    private Object[][] data;
    private static Folder folder;
    private List<Mineral> mineralList;
    private JLabel tableMessage;

    public Table(Folder folder) {
        super(new GridLayout(0, 1));
        setPreferredSize(new Dimension(700, 400));
        tableMessage = new JLabel("");
        //this.folder = toReview;
        this.folder = folder;
        mineralList = folder.getMineralList();
        fillTable();
        columnNames = new String[]{"Name", "Lab", "Lustre", "Color", "Streak", "Hardness", "Specific Gravity",
                "Cleavage", "Fracture", "Habit", "Crystal System", "Other"};
        table = new JTable(data, columnNames);
        //ImageIcon photo = new ImageIcon("data/learnedHeading.png");
        //JLabel pic = new JLabel(photo);
        JButton deleteBtn = new JButton("Delete Selected");
        JButton moveBtn = new JButton("Move Selected to Other Folder");
        moveBtn.setActionCommand("move");
        moveBtn.addActionListener(moveMineral());
        deleteBtn.setActionCommand("delete");
        deleteBtn.addActionListener(deleteAction());
        table.setPreferredScrollableViewportSize(new Dimension(700, 70));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        add(deleteBtn);
        add(moveBtn);
        add(tableMessage);
    }

    private void fillTable() {
        data = new Object[mineralList.size()][12];
        for (int i = 0; i < mineralList.size(); i++) {
            for (int j = 0; j < 12; j++) {
                data[i][j] = createObject(i, j);
            }
        }
    }

    public ActionListener moveMineral() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = table.getSelectedRow();
                String minToMove = String.valueOf(table.getValueAt(i, 0));
                for (Mineral m : folder.getMineralList()) {
                    if (m.getName().equals(minToMove)) {
                        tableMessage.setForeground(green1);
                        if (folder.getName().equals("Review Folder")) {
                            toReview.removeFromMineralList(m);
                            learned.addToMineralList(m);
                            tableMessage.setText(m.getName() + " moved to learned list. Reload table to show changes.");
                        } else if (folder.getName().equals("Learned Folder")) {
                            toReview.removeFromMineralList(m);
                            learned.addToMineralList(m);
                            tableMessage.setText(m.getName() + " moved to learned list. Reload table to show changes.");
                        }
                    }
                }
            }
        };
    }

    public ActionListener deleteAction() {
        ActionListener deleteAction = e -> {
            int i = table.getSelectedRow();
            String minNameToDelete = String.valueOf(table.getValueAt(i, 0));
            for (Mineral m : toReview.getMineralList()) {
                if (m.getName().equals(minNameToDelete)) {
                    toReview.removeFromMineralList(m);
                    tableMessage.setForeground(red1);
                    tableMessage.setText(m.getName() + " removed. Reload table to show changes.");
                    break;
                }
            }

        };
        return deleteAction;
    }

//    private void printDebugData(JTable table) {
//        int numRows = table.getRowCount();
//        int numCols = table.getColumnCount();
//        javax.swing.table.TableModel model = table.getModel();
//
//        System.out.println("Value of data: ");
//        for (int i = 0; i < numRows; i++) {
//            System.out.print("    row " + i + ":");
//            for (int j = 0; j < numCols; j++) {
//                System.out.print("  " + model.getValueAt(i, j));
//            }
//            System.out.println();
//        }
//        System.out.println("--------------------------");
//    }


    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Table");
        frame.setPreferredSize(new Dimension(700, 400));
        frame.setLayout(new GridLayout(14, 2, 2, 2));

        //Create and set up the content pane.
        Table newContentPane = new Table(folder);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public void fun() {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    //double[][] matrix = new double[20][4];

    // EFFECTS: Get mineral property for table entry
    public String getMin(int i, Mineral m) {
        String lab = String.valueOf(m.getLab());
        String name = m.getName();
        String lustre = m.getLustre();
        String color = m.getColor();
        String s = m.getStreak();
        String hardness = String.valueOf(m.getHardness());
        String sg = String.valueOf(m.getSpecificGravity());
        String cleavage = m.getCleavage();
        String fracture = m.getFracture();
        String habit = m.getHabit();
        String cs = m.getCrystalSystem();
        String o = m.getOther();
        List<String> messages = Arrays.asList(name, lab, lustre, color, s, hardness, sg, cleavage, fracture, habit, cs,
                o);
        return messages.get(i);
    }

    // EFFECTS: get table entry
    public String createObject(int i, int j) {
        return getMin(j, mineralList.get(i));
    }
}
