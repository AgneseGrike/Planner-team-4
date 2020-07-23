package teamg.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import teamg.spring.boot.model.TaskList;
import teamg.spring.boot.service.TaskService;
import teamg.spring.boot.service.UserService;

import javax.validation.Valid;
import java.util.Calendar;

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
        model.addAttribute("listTasks", taskService.getAllTasksByUser(userService.getByLogin(userName).getId()));
        return "TaskList";
    }
}
