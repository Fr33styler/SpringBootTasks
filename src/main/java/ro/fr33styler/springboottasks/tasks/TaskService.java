package ro.fr33styler.springboottasks.tasks;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.fr33styler.springboottasks.tasks.task.Task;
import ro.fr33styler.springboottasks.tasks.task.TaskDTO;
import ro.fr33styler.springboottasks.tasks.task.TaskPriority;
import ro.fr33styler.springboottasks.tasks.task.TaskStatus;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    public TaskRepository repository;

    public void addTask(String username, TaskRequest request) {
        if (TaskPriority.getByName(request.getPriority()) == null) throw new IllegalArgumentException("Invalid priority!");
        if (TaskStatus.getByName(request.getStatus()) == null) throw new IllegalArgumentException("Invalid status!");

        Task task = new Task(username, request.getTask(), request.getPriority(), request.getDueDate());

        task.setStatus(request.getStatus());
        task.setProgress(request.getProgress());
        task.setNote(request.getNote());

        repository.save(task);
    }

    @Transactional
    public void updateTaskStatus(String username, long id, TaskStatus status) {
        Task task = repository.getReferenceById(id);
        if (!task.getUsername().equals(username)) throw new IllegalArgumentException("Invalid username!");

        task.setStatus(status.getName());
    }

    @Transactional
    public void deleteTask(String username, long id) {
        Task task = repository.getReferenceById(id);

        if (!task.getUsername().equals(username)) throw new IllegalArgumentException("Invalid username!");

        repository.delete(task);
    }

    private List<TaskDTO> toDTO(List<Task> tasks) {
        List<TaskDTO> taskDTOS = new ArrayList<>(tasks.size());
        for (Task task : tasks) {
            taskDTOS.add(new TaskDTO(task));
        }
        return taskDTOS;
    }

    @Transactional
    public List<TaskDTO> filterTasksByUsernamePriorityAndStatus(String username, String priority, String status) {
        List<Task> filtered;
        if (priority.isEmpty() && status.isEmpty()) {
            filtered = repository.findByUsername(username);
        } else if (!priority.isEmpty() && !status.isEmpty()) {
            filtered = repository.findByUsernameAndPriorityAndStatus(username, priority, status);
        } else if (!priority.isEmpty()) {
            filtered = repository.findByUsernameAndPriority(username, priority);
        } else {
            filtered = repository.findByUsernameAndStatus(username, status);
        }

        return toDTO(filtered);
    }

    @Transactional
    public List<TaskDTO> sortByUsername(String username, String sortBy, Sort.Direction sortDirection) {
        List<Task> sorted = repository.findByUsername(username, Sort.by(sortDirection, sortBy));

        return toDTO(sorted);
    }

}