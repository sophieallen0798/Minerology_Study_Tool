package ui;


import model.Folder;
import model.Mineral;

import javax.swing.*;
import java.awt.*;
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
        return e -> {
            if (table.getSelectedRowCount() == 0) {
                tableMessage.setForeground(red1);
                tableMessage.setText("Please select a row.");
            } else {
                removeAdd();
            }
        };
    }

    private void removeAdd() {
        int i = table.getSelectedRow();
        String minToMove = String.valueOf(table.getValueAt(i, 0));
        for (Mineral m : folder.getMineralList()) {
            if (m.getName().equals(minToMove)) {
                tableMessage.setForeground(green1);
                if (folder.getName().equals("Review Folder")) {
                    toReview.removeFromMineralList(m);
                    learned.addToMineralList(m);
                    tableMessage.setText(m.getName() + " moved to learned list. Reload table to show "
                            + "changes.");
                    break;
                } else if (folder.getName().equals("Learned Folder")) {
                    learned.removeFromMineralList(m);
                    toReview.addToMineralList(m);
                    tableMessage.setText(m.getName() + " moved to learned list. Reload table to show "
                            + "changes.");
                    break;
                }
            }
        }
    }

    public ActionListener deleteAction() {
        return e -> {
            if (table.getSelectedRowCount() == 0) {
                tableMessage.setForeground(red1);
                tableMessage.setText("Please select a row.");
            } else {
                int i = table.getSelectedRow();
                String minNameToDelete = String.valueOf(table.getValueAt(i, 0));
                for (Mineral m : folder.getMineralList()) {
                    if (m.getName().equals(minNameToDelete)) {
                        folder.removeFromMineralList(m);
                        tableMessage.setForeground(red1);
                        tableMessage.setText(m.getName() + " removed. Reload table to show changes.");
                        break;
                    }
                }
            }
        };
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Table");
        frame.setPreferredSize(new Dimension(700, 400));
        frame.setLayout(new GridLayout(14, 2, 2, 2));

        Table newContentPane = new Table(folder);
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);
    }

    public void fun() {
        javax.swing.SwingUtilities.invokeLater(Table::createAndShowGUI);
    }

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
