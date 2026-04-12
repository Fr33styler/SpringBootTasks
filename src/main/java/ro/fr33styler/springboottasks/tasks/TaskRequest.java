package ro.fr33styler.springboottasks.tasks;

import java.time.LocalDateTime;

public class TaskRequest {

    private String task;
    private String priority;
    private String status;
    private float progress;
    private LocalDateTime dueDate;
    private String note;

    public String getTask() {
        return task;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public float getProgress() {
        return progress;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public String getNote() {
        return note;
    }

}
