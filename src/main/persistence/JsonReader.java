package persistence;

import model.PeriodLog;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

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
        String date = jsonObject.getString("Date saved");
        PeriodLog myLog = new PeriodLog(name);
        addThingies(wr, jsonObject);
        return wr;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addThingies(WorkRoom wr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("thingies");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addThingy(wr, nextThingy);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addThingy(WorkRoom wr, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Category category = Category.valueOf(jsonObject.getString("category"));
        Thingy thingy = new Thingy(name, category);
        wr.addThingy(thingy);
    }

}
