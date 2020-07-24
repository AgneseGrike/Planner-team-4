package teamg.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import teamg.spring.boot.model.Appointment;
import teamg.spring.boot.model.TaskList;
import teamg.spring.boot.service.TaskService;
import teamg.spring.boot.service.UserService;

import java.time.LocalDate;


@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @GetMapping("/addTask")
    public String addTaskForm(TaskList taskList, Model model) {
        model.addAttribute(new TaskList());
        return "addTask";
    }

    @PostMapping("/addTask/save")
    public String addTask(@ModelAttribute TaskList taskList, Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        taskList.setUser(userService.getByLogin(userName));
        taskService.saveTask(taskList);
        return "redirect:/home";
    }

    @GetMapping("/tasks")
    public String viewTasks(Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("listTasks", taskService.getTasksByUser(userService.getByLogin(userName).getId()));
        return "TaskList";
    }

    @GetMapping("/tasks/done")
    public String viewDoneTasks(Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("listTasks", taskService.getDoneTasksByUser(userService.getByLogin(userName).getId()));
        return "DoneTaskList";
    }

    @PostMapping("/save/{id}")
    public String done(@PathVariable("id") long id) {
        TaskList taskList = taskService.getTaskById(id);
        taskList.setDone(true);
        taskService.saveTask(taskList);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id, Model model) {
        TaskList taskList = taskService.getTaskById(id);
        taskService.deleteTask(taskList);
        return "redirect:/tasks";
    }
}
