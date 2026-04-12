package ro.fr33styler.springboottasks.tasks;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fr33styler.springboottasks.tasks.task.TaskDTO;
import ro.fr33styler.springboottasks.tasks.task.TaskStatus;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/{username}")
    public ResponseEntity<String> addTask(@PathVariable String username, @RequestBody TaskRequest request) {
        try {
            taskService.addTask(username, request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Task added successfully!");
        } catch (NullPointerException | IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PatchMapping("/{username}/{id}/status/{status}")
    public ResponseEntity<String> updateStatus(@PathVariable String username, @PathVariable long id, @PathVariable String status) {
        TaskStatus taskStatus = TaskStatus.getByName(status);
        if (taskStatus == null) return ResponseEntity.badRequest().body("Invalid task status!");

        try {
            taskService.updateTaskStatus(username, id, taskStatus);
            return ResponseEntity.ok().body("Task updated successfully!");
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid id!");
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PatchMapping("/{username}/{id}/progress/{progress}")
    public ResponseEntity<String> updateProgress(@PathVariable String username, @PathVariable long id, @PathVariable float progress) {
        try {
            taskService.updateTaskProgress(username, id, progress);
            return ResponseEntity.ok().body("Task updated successfully!");
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid id!");
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping("/{username}/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable String username, @PathVariable long id) {
        try {
            taskService.deleteTask(username, id);
            return ResponseEntity.ok().body("Task deleted successfully!");
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid id!");
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("/{username}/filtered-tasks")
    public List<TaskDTO> filterByUsernameAndParams(@PathVariable String username,
                                          @RequestParam(defaultValue = "") String priority,
                                          @RequestParam(defaultValue = "") String status) {
        return taskService.filterTasksByUsernamePriorityAndStatus(username, priority, status);
    }

    @GetMapping("/{username}/sorted-tasks")
    public List<TaskDTO> sortByUsername(@PathVariable String username,
                                        @RequestParam String sortBy,
                                        @RequestParam(defaultValue = "asc") String direction) {
        Sort.Direction sortDirection = direction.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        return taskService.sortByUsername(username, sortBy, sortDirection);
    }

}