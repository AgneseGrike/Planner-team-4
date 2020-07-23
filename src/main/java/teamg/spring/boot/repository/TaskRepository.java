package teamg.spring.boot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import teamg.spring.boot.model.TaskList;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<TaskList, Long> {

    @Query(value = "select * from tasks where user_id = ?", nativeQuery = true)
    List<TaskList> findUserTasks(Long userId);

}
