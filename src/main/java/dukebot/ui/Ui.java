package dukebot.ui;

import dukebot.gui.DukeExpression;
import dukebot.tasklist.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String LOGO = "*******   **     ** **   ** ********\n"
            + "/**////** /**    /**/**  ** /**/////\n"
            + "/**    /**/**    /**/** **  /**\n"
            + "/**    /**/**    /**/****   /*******\n"
            + "/**    /**/**    /**/**/**  /**////\n"
            + "/**    ** /**    /**/**//** /**\n"
            + "/*******  //******* /** //**/********\n"
            + "///////    ///////  //   // //////// \n";
    private boolean sayFirst = true;
    private final Scanner sc;
    private final boolean withGui;
    private StringBuilder guiOutput = new StringBuilder();
    private DukeExpression dukeExpression = DukeExpression.HAPPY;

    /**
     * Generates the Ui with GUI.
     */
    public Ui(boolean withGui) {
        this.withGui = withGui;
        sc  = new Scanner(System.in);
    }

    /**
     * Generates the Ui.
     */
    public Ui() {
        withGui = false;
        sc  = new Scanner(System.in);
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        System.out.println("\nYahallo! Duke's name is \n" + LOGO);
        dukeSays("Master! Duke's so glad Master used Duke!");
        dukeSays("What will Master do to Duke today?");
    }

    /**
     * Prints welcome message for GUI.
     */
    public void showWelcomeGui() {
        dukeSays("\nYahallo! Duke's name is \n" + LOGO);
        dukeSays("Master! Duke's so glad Master used Duke!");
        dukeSays("What will Master do to Duke today?");
    }

    /**
     * Prompts and reads the input given.
     *
     * @return The read input.
     */
    public String readCommand() {
        sayFirst = true;
        System.out.println("\nMaster: ");
        String inp = sc.nextLine();
        System.out.println();
        return inp;
    }

    /**
     * Prints lines based on lineName.
     *
     * @param lineName Line to say.
     */
    public void sayLine(LineName lineName) {
        dukeExpression = DukeExpression.HAPPY;
        switch (lineName) {
        case HELP:
            dukeExpression = DukeExpression.BLUSH;
            dukeSays("Master wants to know more about Duke?");
            dukeSays("Exposing myself to master... Duke's so embarrassed...");
            dukeSays("But if it's for Master... Duke'll do it!");
            dukeSays("Duke's precious secrets:");
            dukeSays("todo <name> -- Adds a todo to your task list.");
            dukeSays("deadline <name> /by <time> -- Adds a dead line to your task list.");
            dukeSays("event <name> /at <time> -- Adds a event to your task list.");
            dukeSays("list -- Displays your task list.");
            dukeSays("find <query> -- Finds a task which has <query> as a substring of the task's name.");
            dukeSays("done <task index> -- Marks the task as done.");
            dukeSays("delete <task index> -- Deletes the task.");
            dukeSays("bye -- Exits this application");
            break;
        case NO_INPUT:
            dukeExpression = DukeExpression.SAD;
            dukeSays("Duke can't hear anything... Is Master all right?");
            break;
        case SAY_DUKE:
            dukeSays("Master!");
            break;
        case LIST:
            dukeExpression = DukeExpression.SURPRISED;
            dukeSays("Master already forgotten what Master wanted to do?!");
            dukeSays("Duke has no choice but to remind Master then!");
            dukeSays("These are the tasks which Master forgot:");
            break;
        case LIST_EMPTY:
            dukeSays("Huh there are no tasks! Master is so forgetful...");
            break;
        case LIST_EXISTS:
            dukeSays("These are the tasks which Master forgot:");
            break;
        case DONE_EMPTY:
            dukeExpression = DukeExpression.SAD;
            dukeSays("Duke doesn't think Master has done anything yet...");
            break;
        case DONE_OUT_OF_INDEX:
            dukeExpression = DukeExpression.SAD;
            dukeSays("Duke can't seem to recall that item...");
            break;
        case DONE_ALREADY:
            dukeExpression = DukeExpression.SURPRISED;
            dukeSays("Didn't Master already do that?");
            break;
        case DONE_SUCCESS:
            // use another function;
            break;
        case NOT_A_NUMBER:
            dukeExpression = DukeExpression.BLUSH;
            dukeSays("Stop teasing Duke... Even Duke knows that isn't a number...");
            break;
        case DELETE_EMPTY:
            dukeExpression = DukeExpression.SAD;
            dukeSays("Master, please don't delete Duke...");
            break;
        case DELETE_OUT_OF_INDEX:
            dukeExpression = DukeExpression.SAD;
            dukeSays("That item already doesn't exist in Duke's memory...");
            break;
        case TODO_EMPTY:
            dukeExpression = DukeExpression.SAD;
            dukeSays("Duke doesn't see any description of the todo...");
            break;
        case DEADLINE_EMPTY:
            dukeExpression = DukeExpression.SAD;
            dukeSays("Duke doesn't see any deadline...");
            break;
        case DATE_TIME_PARSE_FAIL:
            dukeExpression = DukeExpression.SAD;
            dukeSays("Master gave a date that Duke cannot read...");
            break;
        case DEADLINE_BY_MISSING:
            dukeExpression = DukeExpression.SAD;
            dukeSays("Master, use '/by' to indicate deadline, Duke wouldn't know otherwise...");
            break;
        case EVENT_EMPTY:
            dukeExpression = DukeExpression.SAD;
            dukeSays("Duke doesn't see any start time...");
            break;
        case EVENT_AT_MISSING:
            dukeExpression = DukeExpression.SAD;
            dukeSays("Master, use '/at' to indicate starting time, Duke wouldn't know otherwise...");
            break;
        case FIND_EMPTY:
            dukeExpression = DukeExpression.SAD;
            dukeSays("There's nothing for Duke to find...");
            break;
        case FIND_FAIL:
            dukeExpression = DukeExpression.SAD;
            dukeSays("There isn't any tasks which matches Master's queries.");
            break;
        case FIND_SUCCESS:
            dukeSays("Master! Duke found all these tasks!");
            break;
        case INVALID_COMMAND:
            dukeExpression = DukeExpression.SAD;
            dukeSays("Duke doesn't understand Master...");
            break;
        case EXIT:
            dukeExpression = DukeExpression.SAD;
            dukeSays("Is Master leaving already?");
            dukeSays("Please come back and play with Duke soon...");
            break;
        case SAVE_FAIL:
            dukeExpression = DukeExpression.SAD;
            dukeSays("Duke feels dizzy...");
            dukeSays("It seems that Duke will forget everything when Master leaves...");
            break;
        case LOAD_FAIL:
            dukeExpression = DukeExpression.BLUSH;
            dukeSays("There does not seem to be any existing save file.");
            dukeSays("Is this the first time Master has ever used Duke?");
            dukeSays("Type 'help' to see the list of commands.");
            dukeSays("Thanks for using Duke. Duke is really happy.");
            break;
        case ERROR_PLACEHOLDER:
            // Purely for testing, should never be called in deployment
            // Fallthrough
        default:
            // Purely for testing, should never be called in deployment
            dukeSays("There is an unexpected error :(");
            break;
        }
    }

    /**
     * Prints array of tasks.
     *
     * @param tasks  The array of tasks to print.
     */
    public void sayTasks(ArrayList<Task> tasks) {
        int i = 1;
        for (Task task : tasks) {
            dukeSays(+ i + ". "
                    + "[" + task.getType() + "] "
                    + task
                    + (task.getDone() ? " [Done!]" : "")
            );
            i += 1;
        }
    }

    /**
     * Prints done success message.
     *
     * @param task  Task to use.
     */
    public void doneSuccess(Task task) {
        dukeSays("So Master finally completed " + task + "?");
        dukeSays("Duke's really proud of Master!");
    }

    /**
     * Prints new task successfully made message.
     *
     * @param task  Task to use.
     */
    public void newTask(Task task) {
        dukeSays("So Master has " + task.getType() + ": " + task + "...");
    }

    /**
     * Prints task successfully deleted message.
     *
     * @param task  Task to use.
     */
    public void deleteSuccess(Task task) {
        dukeExpression = DukeExpression.BLUSH;
        dukeSays("For Master, Duke can forget anything, even the:");
        dukeSays("[" + task.getType() + "] " + task + (task.getDone() ? " [Done!]" : ""));
    }

    /**
     * Formats and prints the string input.
     *
     * @param line  Line to print.
     */
    private void dukeSays(String line) {
        if (withGui) {
            guiOutput.append(line);
            guiOutput.append("\n");
        } else {
            if (this.sayFirst) {
                System.out.print("Duke: ");
                this.sayFirst = false;
            } else {
                System.out.print("      ");
            }
            System.out.println(line);
        }
    }

    public void resetGuiOutput() {
        if (withGui) {
            guiOutput = new StringBuilder();
        }
    }

    public String getGuiOutput() {
        if (withGui) {
            return guiOutput.toString();
        } else {
            return "";
        }
    }

    public DukeExpression getDukeExpression() {
        return dukeExpression;
    }
}