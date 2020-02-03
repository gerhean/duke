package dukebot.tasklist;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Abstract class for tasks to inherit from.
 */
public abstract class Task implements Serializable {
    private static final DateTimeFormatter DEFAULT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
    // private LocalDateTime localDateTimeProvider = LocalDateTime.now();
    protected final String description;

    private boolean isDone;
    protected final TaskType taskType;
    protected final LocalDateTime dateTime;

    protected Task(String description, TaskType taskType, LocalDateTime dateTime) {
        this.taskType = taskType;
        this.dateTime = dateTime;
        this.description = description;
        this.isDone = false;
    }

    // /**
    //  * For testing purposes.
    //  */
    // public void setLocalDateTimeProvider(LocalDateTime localDateTimeProvider) {
    //     this.localDateTimeProvider = localDateTimeProvider;
    // }

    /**
     * Sets task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Get done status of task.
     *
     * @return  Done status of task
     */
    public boolean getDone() {
        return this.isDone;
    }

    /**
     * Gets name of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get type of task.
     *
     * @return  String of taskType
     */
    public String getType() {
        return this.taskType.toString();
    }

    /**
     * Formats the internal datetime to display in list.
     *
     * @return DateTime string to be displayed.
     */
    public String dateTimeToString() {
        // Think of a way to use custom datetime.
        if (dateTime == null) {
            return "";
        } else if (dateTime.toLocalDate().equals(LocalDateTime.now().toLocalDate())) {
            return "Today " + dateTime.toLocalTime().toString();
        } else if (dateTime.compareTo(LocalDateTime.now()) < 0) {
            return this.dateTime.format(DEFAULT_FORMAT) + " [Over]";
        } else {
            return this.dateTime.format(DEFAULT_FORMAT);
        }

    }

    @Override
    public String toString() {
        return (description);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task task = (Task) obj;
            return taskType.equals(task.taskType)
                    && description.equals(task.description)
                    && Objects.equals(dateTime, task.dateTime);
        }
        return false;
    }

    //...
}