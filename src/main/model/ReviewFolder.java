package model;

import persistance.JsonReader;
import persistance.JsonWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ReviewFolder extends Folder {
    protected List<Mineral> mineralList;
    protected String name;
    private Folder reviewFolder;
    private JsonWriter jsonWriterRev;
    private JsonReader jsonReaderRev;
    private JsonWriter jsonWriterLearn;
    private JsonReader jsonReaderLearn;
    private static final String JSON_FOLDERS_R = "./data/review.json";

    // EFFECTS: Constructs a folder with a name and list of minerals
    public ReviewFolder() {
        this.name = "Review Folder";
        this.mineralList = new ArrayList<>();
        reviewFolder = this;
        jsonReaderRev = new JsonReader(JSON_FOLDERS_R);
        jsonWriterRev = new JsonWriter(JSON_FOLDERS_R);
    }

    public String getName() {
        return name;
    }

    public List<Mineral> getMineralList() {
        return mineralList;
    }

    // EFFECTS: Randomly select next mineral to study
    public Mineral nextStudyMineral() {
        Random rand = new Random();
        int randIndex = rand.nextInt(mineralList.size());
        Mineral selectedMineral = mineralList.get(randIndex);
        return selectedMineral;
    }

    @Override
    public void addToMineralList(Mineral mineral) {
        this.getMineralList().add(mineral);
        EventLog.getInstance().logEvent(new Event("Mineral Added."));
    }

    @Override
    public String[] getColNames() {
        String[] columnNames = new String[]{"Name", "Lab", "Lustre", "Color", "Streak", "Hardness", "Specific Gravity",
                "Cleavage", "Fracture", "Habit", "Crystal System", "Other"};
        EventLog.getInstance().logEvent(new Event(this.name + " Displayed."));
        return columnNames;
    }

    // EFFECTS: Fills table with mineral properties
    public Object[][] fillTable() {
        Object[][] data = new Object[mineralList.size()][12];
        for (int i = 0; i < mineralList.size(); i++) {
            for (int j = 0; j < 12; j++) {
                data[i][j] = createObject(i, j);
            }
        }
        EventLog.getInstance().logEvent(new Event("Displayed."));
        return data;
    }

    // EFFECTS: Gets mineral property for table entry
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

    // EFFECTS: Get table entry
    public String createObject(int i, int j) {
        return getMin(j, mineralList.get(i));
    }

}
