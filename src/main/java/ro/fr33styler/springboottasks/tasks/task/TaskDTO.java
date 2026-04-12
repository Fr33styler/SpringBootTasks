package ro.fr33styler.springboottasks.tasks.task;

import java.time.LocalDateTime;

public class TaskDTO {

    private final long id;
    private final String username;
    private final String task;
    private final TaskPriority priority;
    private final TaskStatus status;
    private final float progress;
    private final LocalDateTime dueDate;
    private final String note;

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.username = task.getUsername();
        this.task = task.getTask();
        this.priority = TaskPriority.getByName(task.getPriority());
        this.status = TaskStatus.getByName(task.getStatus());
        this.progress = task.getProgress();
        this.dueDate = task.getDueDate();
        this.note = task.getNote();
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getTask() {
        return task;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public TaskStatus getStatus() {
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
