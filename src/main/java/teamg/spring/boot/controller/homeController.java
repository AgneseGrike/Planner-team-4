package teamg.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import teamg.spring.boot.model.Appointment;
import teamg.spring.boot.service.AppointmentService;

@Controller
public class homeController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/")
    public String home() {
        return "home";
        // will show user events
    }

    @GetMapping("/createEvent")
    public String createEvent(Appointment appointment) {
        return "createEvent";
    }

    @PostMapping("/addEvent")
    public String addEvent(@ModelAttribute Appointment appointment) {

        return "home";
    }

    @GetMapping("appointment/{id}")
    public String getAppointmentById(@PathVariable Long id, Model model) {
        model.addAttribute("appointment", appointmentService.getAppointmentById(id));
        return "appointment";
    }

}
