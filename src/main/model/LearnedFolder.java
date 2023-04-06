package model;

import persistance.JsonReader;
import persistance.JsonWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LearnedFolder extends Folder {
    protected List<Mineral> mineralList;
    protected String name;
    private JsonWriter jsonWriterRev;
    private JsonReader jsonReaderRev;
    private final JsonWriter jsonWriterLearn;
    private final JsonReader jsonReaderLearn;
    private static final String JSON_FOLDERS_L = "./data/learned.json";

    // EFFECTS: Constructs a folder with a name and list of minerals
    public LearnedFolder() {
        this.name = "Learned Folder";
        this.mineralList = new ArrayList<>();
        jsonReaderLearn = new JsonReader(JSON_FOLDERS_L);
        jsonWriterLearn = new JsonWriter(JSON_FOLDERS_L);
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

    // EFFECTS: Return column names for table and adds to event log
    @Override
    public String[] getColNames() {
        String[] columnNames = new String[]{"Name", "Lab", "Lustre", "Color", "Streak", "Hardness", "Specific Gravity",
                "Cleavage", "Fracture", "Habit", "Crystal System", "Other"};
        EventLog.getInstance().logEvent(new Event(this.name + " Displayed."));
        return columnNames;
    }

}
