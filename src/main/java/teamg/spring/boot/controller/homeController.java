package teamg.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import teamg.spring.boot.model.Appointment;
import teamg.spring.boot.service.AppointmentService;
import teamg.spring.boot.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class homeController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(Model model) {
        List<Integer> dateList = new ArrayList<>();
        for(int i=1;i<=31;i++){
            dateList.add(i);
        }
        model.addAttribute("listDates",dateList);
        return "home";
        // will show user events
    }

    @GetMapping("/createEvent")
    public String createEvent(Appointment appointment) {
        return "createEvent";
    }

    @PostMapping("/addEvent")
    public RedirectView addEvent(@ModelAttribute Appointment appointment) {
        appointment.setUser(userService.getUserById((long) 9));
        appointmentService.saveAppointment(appointment);
        Long id = appointment.getId();
        return new RedirectView("appointment/"+id);
    }

    @GetMapping("appointment/{id}")
    public String getAppointmentById(@PathVariable Long id, Model model) {
        model.addAttribute("appointment", appointmentService.getAppointmentById(id));
        return "appointment";
    }

    @GetMapping("/delete/{id}")
    public String deleteApp(@PathVariable("id") Long id, Model model) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        appointmentService.deleteAppointment(appointment);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editAppointment(@PathVariable("id") Long id, Model model) {
        model.addAttribute("appointment", appointmentService.getAppointmentById(id));
        return "editAppointment";
    }

    @PostMapping("/update/{id}")
    public RedirectView updateUser(@PathVariable("id") long id, @ModelAttribute Appointment appointment) {
        appointment.setUser(userService.getUserById((long) 9));
        appointmentService.saveAppointment(appointment);
        return new RedirectView("/appointment/"+id);
    }

}
