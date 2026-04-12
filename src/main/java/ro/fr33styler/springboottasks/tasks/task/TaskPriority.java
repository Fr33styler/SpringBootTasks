package ro.fr33styler.springboottasks.tasks.task;

import java.util.HashMap;
import java.util.Map;

public enum TaskPriority {

    HIGH("HIGH"),
    MEDIUM("MEDIUM"),
    LOW("LOW");

    private final String name;

    private static final Map<String, TaskPriority> BY_NAME = new HashMap<>();

    static {
        for (TaskPriority taskPriority : TaskPriority.values()) {
            BY_NAME.put(taskPriority.getName(), taskPriority);
        }
    }

    TaskPriority(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static TaskPriority getByName(String name) {
        return BY_NAME.get(name);
    }

}
