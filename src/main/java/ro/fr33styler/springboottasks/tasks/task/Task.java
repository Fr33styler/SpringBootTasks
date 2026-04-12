package ro.fr33styler.springboottasks.tasks.task;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String task;
    private String priority;
    private String status;
    private float progress;
    private LocalDateTime dueDate;
    private String note = "";

    public Task() {}

    public Task(String username, String task, String priority, LocalDateTime dueDate) {
        Objects.requireNonNull(username, "username cannot be null!");
        this.username = username;

        Objects.requireNonNull(task, "task cannot be null!");
        this.task = task;

        setPriority(priority);
        setDueDate(dueDate);
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        Objects.requireNonNull(priority, "priority must not be null!");

        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        Objects.requireNonNull(status, "status must not be null!");

        this.status = status;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        if (progress < 0 || progress > 1) throw new IllegalArgumentException("progress must be between 0 and 1!");

        this.progress = progress;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        Objects.requireNonNull(dueDate, "dueDate must not be null!");
        if (dueDate.isBefore(LocalDateTime.now())) throw new IllegalArgumentException("dueDate must be after current date!");

        this.dueDate = dueDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        Objects.requireNonNull(note, "note must not be null!");

        this.note = note;
    }

}
