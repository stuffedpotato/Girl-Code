package persistence;

import org.json.JSONObject;

import model.PeriodLog;

import java.io.*;

/*
 * EFFECTS: writes JSON representation of PeriodLog myLog to given destination.
 * Referenced from JSONSerializationDemo
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    /*
     * EFFECTS: constructs writer to write to destination file.
     */
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    /* 
     * MODIFIES: this
     * EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
     * be opened for writing.
     */
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    /*
     * MODIFIES: this
     * EFFECTS: writes JSON representation of myLog to destination file.
     */
    public void write(PeriodLog myLog) {
        JSONObject json = myLog.toJson();
        saveToFile(json.toString(TAB));
    }

    /*
     * MODIFIES: this
     * EFFECTS: closes writer.
     */
    public void close() {
        writer.close();
    }

    /*
     * MODIFIES: this
     * EFFECTS: writes string to file.
     */
    private void saveToFile(String json) {
        writer.print(json);
    }
}
