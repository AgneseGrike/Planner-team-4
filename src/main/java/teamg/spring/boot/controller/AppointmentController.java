package teamg.spring.boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import teamg.spring.boot.model.Appointment;
import teamg.spring.boot.service.AppointmentService;

@RestController
public class AppointmentController {

    private static Logger LOG
            = LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    private AppointmentService appointmentService;

    //TEST - display a list of all appointments for all users
    @GetMapping("/appointments")
    public String viewHomePage(Model model){
        model.addAttribute("listAppointments", appointmentService.getAllAppointments());
        return "appointments";
    }

    @GetMapping("appointment/{id}")
    public Appointment getAppointmentById(@PathVariable Long id){
        LOG.info("getUserById: " + id);
        return appointmentService.getAppointmentById(id);
    }

}
