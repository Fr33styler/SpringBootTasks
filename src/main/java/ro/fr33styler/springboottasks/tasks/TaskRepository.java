package ro.fr33styler.springboottasks.tasks;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fr33styler.springboottasks.tasks.task.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUsername(String username);

    List<Task> findByUsername(String username, Sort sort);

    List<Task> findByUsernameAndStatus(String username, String status);

    List<Task> findByUsernameAndPriority(String username, String priority);

    List<Task> findByUsernameAndPriorityAndStatus(String username, String priority, String status);

}
