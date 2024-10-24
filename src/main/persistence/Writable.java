package persistence;

import org.json.JSONObject;

/*
 * This interface is implemented by PeriodEntry & PeriodLog classes.
 * Referenced from JSONSerializationDemo
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */
public interface Writable {

    /*
     * EFFECTS: to return this as JSON object
     */
    JSONObject toJson();
}
