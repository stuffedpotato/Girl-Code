package persistence;

import model.PeriodEntry;
import model.PeriodLog;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import java.time.LocalDate;

import org.json.JSONArray;
import org.json.JSONObject;

/*
 * This class reads the JSON file when user wants to load their previously saved work.
 * Referenced from JSONSerializationDemo
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */
public class JsonReader {
    private String source;

    /*
     * EFFECTS: constructs a reader object to read from the given source file.
     */
    public JsonReader(String source) {
        this.source = source;
    }

    /*
     * EFFECTS: reads myLog from file and returns it;
     * throws IOException if an error occurs reading data from file.
     */
    public PeriodLog read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePeriodLog(jsonObject);
    }

    /*
     * EFFECTS: reads source file as string and returns it.
     */
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    /*
     * EFFECTS: parses myLog from JSON object and returns it.
     */
    private PeriodLog parsePeriodLog(JSONObject jsonObject) {
        LocalDate dateSaved = (LocalDate) jsonObject.get("Date saved");
        PeriodLog myLog = new PeriodLog(dateSaved);
        addEntries(myLog, jsonObject);
        return myLog;
    }

    /*
     * MODIFIES: PeriodLog myLog
     * EFFECTS: parses PeriodEntry entries from JSON object and adds them to myLog.
     */
    private void addEntries(PeriodLog myLog, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Period entries");
        for (Object json : jsonArray) {
            JSONObject nextEntry = (JSONObject) json;
            addEntry(myLog, nextEntry);
        }
    }

    /*
     * MODIFIES: PeriodLog myLog
     * EFFECTS: parses an entry from JSON object and adds it to myLog.
     */
    private void addEntry(PeriodLog myLog, JSONObject jsonObject) {
        String date = jsonObject.getString("Date");
        PeriodEntry entry = new PeriodEntry(LocalDate.parse(date));

        entry.logHeaviness(jsonObject.getInt("Heaviness"));
        entry.logCollectionMethod(jsonObject.getString("Collection method"),
                jsonObject.getInt("Total number of products used"));

        if (jsonObject.has("Areas of pain")) {
            JSONArray areas = jsonObject.getJSONArray("Areas of pain");

            for (int i = 0; i < areas.length(); i++) {
                entry.logPain(areas.getString(i));
            }
        }

        if (jsonObject.has("Feelings")) {
            JSONArray feelings = jsonObject.getJSONArray("Feelings");

            for (int i = 0; i < feelings.length(); i++) {
                entry.logFeelings(feelings.getString(i));
            }
        }

        if (jsonObject.has("Breast health")) {
            JSONArray breastConditions = jsonObject.getJSONArray("Breast health");

            for (int i = 0; i < breastConditions.length(); i++) {
                entry.logBreastHealth(breastConditions.getString(i));
            }
        }

        myLog.addEntry(entry);
    }
}
