package dukebot.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final DateTimeFormatter DEFAULT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Generates a new Deadline.
     *
     * @param description  The name of the Deadline.
     * @param dateTime  Due date of Deadline
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description, TaskType.DEADLINE, dateTime);
    }

    //    public String getTime() {
    //        return (this.time);
    //    }

    @Override
    public String toString() {
        return (this.description + " (by: " + this.dateTimeToString() + ")");
    }
}
