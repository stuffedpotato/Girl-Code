package ui;

import java.time.LocalDate;
import java.util.Scanner;

import model.PeriodEntry;
import model.PeriodLog;

public class PeriodTracker {
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
    public PeriodTracker() {
        myLog = new PeriodLog();
        input = new Scanner(System.in);
        run();
    }

    /*
     * EFFECTS: runs the application.
     */
    private void run() {
        boolean run = true;

        System.out.println(
                "\nWelcome to your period tracking application! Please begin by entering your preferred name: ");
        name = parseName(input.next());

        while (run) {
            choice = menu();

            if (choice.equals("quit")) {
                run = false;
            } else {
                processOption(choice);
            }
        }

        System.out.println("\nThank you for using this application, goodbye!");
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
                + "\nQuit: Close the application");

        choice = input.next();
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
        } else {
            System.out.println("\nInvalid input, please try again.");
        }
    }

    /*
     * EFFECTS: creates a new period entry object by asking user for the date they
     * are entering information for.
     */
    private void logPeriod() {
        System.out.println("\nWhich day would you like to track for?"
                + "\n(Please enter a date in this format: yyyy-m-d):");

        date = parseDate(input.next());
        input.nextLine();
        entry = new PeriodEntry(date);
        myLog.addEntry(entry);
        logParameters(entry);
    }

    /*
     * EFFECTS: user can modify an entry by entering a date for the entry and then
     * logging new information.
     */
    @SuppressWarnings("methodlength")
    private void modifyEntry() {
        System.out.println("\nWhich day would you like to modify the entry for?"
                + "\n(Please enter a date in this format: yyyy-m-d):");

        date = parseDate(input.next());

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
        // stub
    }

    /*
     * EFFECTS: asks user if they want to view a particular entry or view all the
     * entries and displays accordingly.
     */
    private void viewLog() {
        System.out.println("\nIn viewLog...");
        // System.out.println(entry);
        System.out.println(myLog.getLog());
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

    private void modify(PeriodEntry result, String choice) {

        if (choice.equals("heaviness")) {
            logHeaviness(result);
        } else if (choice.equals("pain areas")) {
            result.resetPain();
            logHeaviness(result);
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

    }

    /*
     * REQUIRES: entry must not be null.
     * MODIFIES: this
     * EFFECTS: logs the breast health for the entry chosen by the user.
     */
    private void logBreastHealth(PeriodEntry entry) {

    }

    /*
     * REQUIRES: entry must not be null.
     * MODIFIES: this
     * EFFECTS: logs feelings for the entry chosen by the user.
     */
    private void logFeelings(PeriodEntry entry) {

    }

    /*
     * REQUIRES: name must not be null.
     * MODIFIES: this
     * EFFECTS: formats name to Abcd and returns the formatted name.
     */
    private String parseName(String name) {
        name = name.toLowerCase();
        String firstLetter = name.toUpperCase().substring(0, 1);
        name = firstLetter + name.substring(1);
        return name;
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
