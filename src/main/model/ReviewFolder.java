package model;

import persistance.JsonReader;
import persistance.JsonWriter;

import java.util.ArrayList;
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

    // EFFECTS: Return column names for table
    @Override
    public String[] getColNames() {
        String[] columnNames = new String[]{"Name", "Lab", "Lustre", "Color", "Streak", "Hardness", "Specific Gravity",
                "Cleavage", "Fracture", "Habit", "Crystal System", "Other"};
        EventLog.getInstance().logEvent(new Event(this.name + " Displayed."));
        return columnNames;
    }

}
