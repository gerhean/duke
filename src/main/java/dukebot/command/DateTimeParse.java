package dukebot.command;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class DateTimeParse {
    // TODO: Think of a good way to use custom defined localDateTimeProvider for testing purpose
    // private static LocalDateTime localDateTimeProvider = LocalDateTime.now();

    private static final DateTimeFormatter[] formats = {
            new DateTimeFormatterBuilder()
                    .appendPattern("yyyy/M/d")
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .toFormatter(),
            new DateTimeFormatterBuilder()
                    .appendPattern("d/M/yyyy")
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .toFormatter(),
            new DateTimeFormatterBuilder()
                    .appendPattern("d/M")
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.YEAR, ZonedDateTime.now().getYear())
                    // Bad, only generates time at code start time,
                    // need to edit while making sure it continues to run efficiently.
                    .toFormatter(),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            new DateTimeFormatterBuilder()
                    .appendPattern("d/M HHmm")
                    .parseDefaulting(ChronoField.YEAR, ZonedDateTime.now().getYear())
                    .toFormatter(),
            new DateTimeFormatterBuilder()
                    .appendPattern("HHmm")
                    .parseDefaulting(ChronoField.DAY_OF_MONTH, ZonedDateTime.now().getDayOfMonth())
                    .parseDefaulting(ChronoField.MONTH_OF_YEAR, ZonedDateTime.now().getMonthValue())
                    .parseDefaulting(ChronoField.YEAR, ZonedDateTime.now().getYear())
                    .toFormatter(),
            DateTimeFormatter.ofPattern("yyyy/M/d HHmm")
    };

    /**
     * Parse the input string as a LocalDateTime if possible.
     *
     * @param dateTime String to be parsed.
     * @return The parsed string as a LocalDateTime.
     * @throws DateTimeParseException if string unable to be parsed.
     */
    public static LocalDateTime parseDate(String dateTime) throws DateTimeParseException {
        dateTime = dateTime.replaceAll("[\\\\/\\-]+", "/");
        for (DateTimeFormatter dateFormat: formats) {
            try {
                return LocalDateTime.parse(dateTime, dateFormat);
            } catch (DateTimeParseException e) {
                // System.out.println(e);
            }
        }
        // This throws a DateTimeParseException
        return LocalDateTime.parse(dateTime, formats[3]);
    }

}