package ro.fr33styler.springboottasks.tasks.task;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public enum TaskStatus {

    UNFINISHED("unfinished"),
    IN_PROGRESS("in-progress"),
    FINISHED("finished");

    private static final Map<String, TaskStatus> BY_NAME = new HashMap<>();

    static {
        for (TaskStatus taskStatus : TaskStatus.values()) {
            BY_NAME.put(taskStatus.getName(), taskStatus);
        }
    }

    private final String name;

    TaskStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static TaskStatus getByName(String name) {
        return BY_NAME.get(name.toLowerCase(Locale.ROOT));
    }

}
