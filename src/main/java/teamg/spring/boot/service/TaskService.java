package teamg.spring.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import teamg.spring.boot.exception.UserNotFoundException;
import teamg.spring.boot.model.Appointment;
import teamg.spring.boot.model.TaskList;
import teamg.spring.boot.repository.TaskRepository;

import java.util.List;

@Component
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public TaskList getTaskById(Long id) {
        return taskRepository
                .findById(id).orElseThrow(
                        () -> new UserNotFoundException(
                                "Task with ID: " + id + " not found!"));
    }

    public List<TaskList> getTasksByUser(long userId) {
        return taskRepository.findUserTasks(userId);
    }

    public List<TaskList> getDoneTasksByUser(long userId) {
        return taskRepository.findDoneUserTasks(userId);
    }

    public void saveTask(TaskList taskList) {
        taskRepository.save(taskList);
    }

    public void deleteTask(TaskList taskList) {
        taskRepository.delete(taskList);
    }
}
