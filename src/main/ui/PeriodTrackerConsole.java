package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import model.PeriodEntry;
import model.PeriodLog;
import persistence.JsonReader;
import persistence.JsonWriter;

/*
 * This is the primary class that runs the period tracker application. 
 * It provides user with multiple options to work with the application 
 * including adding an entry, modifying an entry, viewing their log, 
 * saving and loading log, and analyzing information entered.
 */
public class PeriodTrackerConsole {
    private static final String JSON_DIRECTORY = "./data/mylog.json";
    private JsonWriter writer;
    private JsonReader reader;
    private PeriodLog myLog;
    private PeriodEntry entry;
    private Scanner input;
    private String choice;
    private String name;
    private LocalDate date;

    /*
     * EFFECTS: creates an instance of the PeriodTracker application and runs the
     * application by calling the run method.
     */
    public PeriodTrackerConsole() throws FileNotFoundException {
        myLog = new PeriodLog(LocalDate.now());
        input = new Scanner(System.in);
        writer = new JsonWriter(JSON_DIRECTORY);
        reader = new JsonReader(JSON_DIRECTORY);
        run();
    }

    /*
     * EFFECTS: runs the application.
     */
    private void run() {
        boolean run = true;

        System.out.println(
                "\nWelcome to your period tracking application! Please begin by entering your preferred name: ");
        name = parseString(input.nextLine());

        while (run) {
            choice = menu();

            if (choice.equals("quit")) {
                run = false;
            } else {
                processOption(choice);
            }
        }

        System.out.println("\nThank you for using this application, goodbye!\n");
    }

    /*
     * EFFECTS: displays a menu to the user with possible functionalities and asks
     * user what they would like to do.
     */
    private String menu() {
        System.out.println("\nHi " + name + ", what would you like to do today?"
                + "\nLog: Log a new entry"
                + "\nModify: Modify a previous entry"
                + "\nAnalyze: Analyze your period log"
                + "\nView: View your period log"
                + "\nClear: Clear your period log"
                + "\nSave: Save this period log"
                + "\nLoad: Load previous period log"
                + "\nQuit: Close the application");

        choice = input.nextLine();
        choice = choice.trim().toLowerCase();
        return choice;
    }

    /*
     * REQUIRES: string must not be empty.
     * EFFECTS: processes the chosen option as selected by the user from the menu as
     * long as it's not to quit the application.
     */
    private void processOption(String choice) {
        if (choice.equals("log")) {
            logPeriod();
        } else if (choice.equals("modify")) {
            modifyEntry();
        } else if (choice.equals("analyze")) {
            analyze();
        } else if (choice.equals("view")) {
            viewLog();
        } else if (choice.equals("clear")) {
            clearLog();
        } else if (choice.equals("save")) {
            saveLog();
        } else if (choice.equals("load")) {
            loadLog();
        } else {
            System.out.println("\nInvalid input, please try again.");
        }
    }

    /*
     * EFFECTS: creates a new period entry object by asking user for the date they
     * are entering information for.
     */
    private void logPeriod() {
        boolean flag = true;

        System.out.println("\nWhich day would you like to track for?"
                + "\n(Please enter a date in this format: yyyy-m-d):");

        date = parseDate(input.nextLine());
        entry = new PeriodEntry(date);

        if (myLog.addEntry(entry)) {
            logParameters(entry);
        } else {
            do {
                System.out.println(
                        "\nAn entry already exists for this date. \nTo modify, enter \"Modify\". "
                                + "To return to the main menu, enter \"Return\":");

                choice = input.nextLine().toLowerCase();
                flag = true;
                if (choice.equals("modify")) {
                    modifyEntry();
                } else if (choice.equals("return")) {
                    // do nothing - pass
                } else {
                    System.out.println("Invalid input, please try again.");
                    flag = false;
                }
            } while (!flag);
        }
    }

    /*
     * EFFECTS: user can modify an entry by entering a date for the entry and then
     * logging new information.
     */
    @SuppressWarnings("methodlength")
    private void modifyEntry() {
        System.out.println("\nWhich day would you like to modify the entry for?"
                + "\n(Please enter a date in this format: yyyy-m-d):");

        date = parseDate(input.nextLine());

        PeriodEntry result = myLog.getEntry(date);

        if (result == null) {
            System.out.println("\nNo entry found with this date.");
        } else {
            boolean runModify = true;

            do {
                System.out.println("\nWhat would you like to modify in this entry: " + "\nHeaviness"
                        + "\nPain areas" + "\nCollection method" + "\nFeelings" + "\nBreast health"
                        + "\n(Please choose one at a time. Enter \"Done\" if done.)");

                choice = input.nextLine();
                choice.toLowerCase();

                if (choice.equals("done")) {
                    runModify = false;
                } else if (choice.equals("heaviness") || choice.equals("pain areas")
                        || choice.equals("collection method") || choice.equals("feelings")
                        || choice.equals("breast health")) {
                    modify(result, choice);
                } else {
                    System.out.println("\nInvalid input, please try again.");
                }
            } while (runModify);
        }

    }

    /*
     * EFFECTS: user can analyze their period log by choosing from the menu option
     * provided.
     */
    private void analyze() {
        boolean runAnalyze = true;

        do {
            System.out.println("\nWhat would you like to analyze?: "
                    + "\nAverage cycle length"
                    + "\n(Please choose one at a time. Enter \"Done\" if done.)");

            choice = input.nextLine();
            choice = choice.toLowerCase();

            if (choice.equals("average cycle length")) {
                int averageCycleLength = myLog.getAverageCycleLength();

                if (myLog.getLog().isEmpty()) {
                    System.out.println("\nYour log is empty. Please create some entries for analyzation");
                } else {
                    System.out.println("\nYour average cycle length is " + averageCycleLength + " days.");
                }
            } else if (choice.equals("done")) {
                runAnalyze = false;
            } else {
                System.out.println("\nInvalid input, please try again.");
            }
        } while (runAnalyze);
    }

    /*
     * EFFECTS: prints all the entries in the log or a particular entry per user's request.
     */
    private void viewLog() {
        boolean runViewLog = true;

        do {
            System.out.println("\nWould you like to view entry for a particular day or all entries?: "
                    + "\nParticular day"
                    + "\nAll entries"
                    + "\n(Please choose one at a time. Enter \"Done\" if done.)");

            choice = input.nextLine().toLowerCase();

            if (choice.equals("all entries")) {
                System.out.println("\n" + myLog.getLog());
            } else if (choice.equals("particular day")) {
                System.out.println("\nPlease enter the day you would like to view: ");
                String date = input.nextLine();
                System.out.println("\n" + myLog.getEntry(parseDate(date)));
            } else if (choice.equals("done")) {
                runViewLog = false;
            } else {
                System.out.println("\nInvalid input, please try again.");
            }
        } while (runViewLog);

        
    }

    /*
     * EFFECTS: clears the log of entries.
     */
    private void clearLog() {
        if (myLog.clearLog()) {
            System.out.println("\nAll entries have been removed from the log.");
        } else {
            System.out.println("\nNo entries available to be removed from the log.");
        }

    }

    /*
     * EFFECTS: saves the current log that user is working with. Replaces any
     * previously saved work.
     * Referenced from JSONSerializationDemo
     * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
     */
    private void saveLog() {
        try {
            writer.open();
            writer.write(myLog);
            writer.close();
            System.out.println("Saved " + myLog.getDate() + "'s log to " + JSON_DIRECTORY + ".");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_DIRECTORY + ".");
        }
    }

    /*
     * EFFECTS: loads previously saved period log that user can work with.
     * Referenced from JSONSerializationDemo
     * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
     */
    private void loadLog() {
        try {
            myLog = reader.read();
            System.out.println("Loaded " + myLog.getDate() + "'s log from " + JSON_DIRECTORY + ".");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_DIRECTORY + ".");
        }
    }

    /*
     * REQUIRES: entry must not be null.
     * EFFECTS: asks user if they would like to log particular aspects (heaviness,
     * pain areas, etc.) for entry.
     */
    private void logParameters(PeriodEntry entry) {
        boolean runLogParameters = true;

        while (runLogParameters) {
            System.out.println("\nWhat would you like to track for " + date + "?" + "\nHeaviness"
                    + "\nPain areas" + "\nCollection method" + "\nFeelings" + "\nBreast health"
                    + "\n(Please choose one at a time. Enter \"Done\" if done.)");

            choice = input.nextLine();
            choice = choice.toLowerCase();

            if (choice.equals("heaviness")) {
                logHeaviness(entry);
            } else if (choice.equals("pain areas")) {
                logPainAreas(entry);
            } else if (choice.equals("collection method")) {
                logCollection(entry);
            } else if (choice.equals("feelings")) {
                logFeelings(entry);
            } else if (choice.equals("breast health")) {
                logBreastHealth(entry);
            } else if (choice.equals("done")) {
                runLogParameters = false;
            } else {
                System.out.println("\nInput invalid, please try again.");
            }
        }
    }

    /*
     * REQUIRES: result must not be null, choice must not be null.
     * MODIFIES: result, myLog
     * EFFECTS: modifies the given PeriodEntry result's fields as chosen by the
     * user.
     */
    private void modify(PeriodEntry result, String choice) {
        if (choice.equals("heaviness")) {
            logHeaviness(result);
        } else if (choice.equals("pain areas")) {
            result.resetPain();
            logPainAreas(result);
        } else if (choice.equals("collection method")) {
            result.resetCollectionMethodNumUsed();
            logCollection(result);
        } else if (choice.equals("feelings")) {
            result.resetFeelings();
            logFeelings(result);
        } else if (choice.equals("breast health")) {
            result.resetBreastHealth();
            logBreastHealth(result);
        }
    }

    /*
     * REQUIRES: entry must not be null.
     * MODIFIES: this
     * EFFECTS: logs the heaviness for the entry chosen by the user.
     */
    private void logHeaviness(PeriodEntry entry) {
        boolean flag = true;

        do {
            System.out.println("\nPlease enter level of heaviness where: "
                    + "\n0 - none, 1 - light flow, 2 - medium flow, 3 - heavy flow, 4 - very heavy flow");

            int choice = input.nextInt();
            input.nextLine();

            if (choice < 0 || choice > 4) {
                System.out.println("\nInvalid input, please try again.");
                flag = false;
            } else {
                entry.logHeaviness(choice);
                flag = true;
            }
        } while (!flag);
    }

    /*
     * REQUIRES: entry must not be null.
     * MODIFIES: this
     * EFFECTS: logs the pain areas for the entry chosen by the user.
     */
    private void logPainAreas(PeriodEntry entry) {
        boolean recordMore = true;

        do {
            System.out.println("\nEnter the affected area: "
                    + "\nNone" + "\nBack" + "\nHead" + "\nBreasts" + "\nLegs" + "\nJoints" + "\nVulvar" + "\nOvulation"
                    + "\n(Please choose one at a time. Enter \"Done\" once done.)");

            choice = input.nextLine();
            choice = choice.toLowerCase();

            if (choice.equals("none") || choice.equals("back")
                    || choice.equals("head") || choice.equals("breasts")
                    || choice.equals("legs") || choice.equals("joints")
                    || choice.equals("vulvar") || choice.equals("ovulation")) {
                entry.logPain(choice);
            } else if (choice.equals("done")) {
                recordMore = false;
            } else {
                System.out.println("\nInvalid input, please try again.");
            }
        } while (recordMore);
    }

    /*
     * REQUIRES: entry must not be null.
     * MODIFIES: this
     * EFFECTS: logs the collection method and number of products used for the entry
     * chosen by the user.
     */
    private void logCollection(PeriodEntry entry) {
        int numUsed;

        System.out.println("\nPlease choose the collection method used: "
                + "\nPads" + "\nTampons" + "\nLiner" + "\nPeriod cup" + "\nPeriod underwear"
                + "\n(Please choose only one option.)");

        choice = input.nextLine();
        choice = parseString(choice);

        System.out.println("\nPlease enter how many " + choice + " were used: ");
        numUsed = input.nextInt();
        input.nextLine();

        entry.logCollectionMethod(choice, numUsed);
    }

    /*
     * REQUIRES: entry must not be null.
     * MODIFIES: this
     * EFFECTS: logs the breast health for the entry chosen by the user.
     */
    private void logBreastHealth(PeriodEntry entry) {
        boolean recordMore = true;

        do {
            System.out.println("\nEnter your breast condition: "
                    + "\nHealthy" + "\nLumpy" + "\nSwollen" + "\nTender"
                    + "\n(Please choose one at a time. Enter \"Done\" once done.)");

            choice = input.nextLine();
            choice = choice.toLowerCase();

            if (choice.equals("healthy") || choice.equals("lumpy")
                    || choice.equals("swollen") || choice.equals("tender")) {
                entry.logBreastHealth(choice);
            } else if (choice.equals("done")) {
                recordMore = false;
            } else {
                System.out.println("\nInvalid input, please try again.");
            }
        } while (recordMore);
    }

    /*
     * REQUIRES: entry must not be null.
     * MODIFIES: this
     * EFFECTS: logs feelings for the entry chosen by the user.
     */
    private void logFeelings(PeriodEntry entry) {
        boolean recordMore = true;

        do {
            System.out.println("\nEnter how you are feeling today: "
                    + "\nHappy" + "\nSad" + "\nSensitive" + "\nAnxious" + "\nAngry" + "\nMood swings"
                    + "\n(Please choose one at a time. Enter \"Done\" once done.)");

            choice = input.nextLine();
            choice = choice.toLowerCase();

            if (choice.equals("happy") || choice.equals("sad")
                    || choice.equals("sensitive") || choice.equals("anxious")
                    || choice.equals("angry") || choice.equals("mood swings")) {
                entry.logFeelings(choice);
            } else if (choice.equals("done")) {
                recordMore = false;
            } else {
                System.out.println("\nInvalid input, please try again.");
            }
        } while (recordMore);
    }

    /*
     * REQUIRES: s must not be null.
     * MODIFIES: this
     * EFFECTS: formats s to Abcd and returns the formatted s.
     */
    private String parseString(String s) {
        s = s.toLowerCase();
        String firstLetter = s.toUpperCase().substring(0, 1);
        s = firstLetter + s.substring(1);
        return s;
    }

    /*
     * REQUIRES: date must not be null and must in the format yyyy-m-d.
     * MODIFIES: this
     * EFFECTS: formats string input date to LocalDate and returns the LocalDate
     * object.
     */
    private LocalDate parseDate(String date) {
        return (LocalDate.parse(date));
    }
}
