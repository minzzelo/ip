package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDateTime date;

    public Deadline(String description, String date) {
        super(description);
        this.date = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    public Deadline(String description, String date, boolean done) {
        super(description, done);
        this.date = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a")) + ")";
    }
}
