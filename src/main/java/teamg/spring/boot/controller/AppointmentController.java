package teamg.spring.boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import teamg.spring.boot.exception.UserNotFoundException;
import teamg.spring.boot.model.Appointment;
import teamg.spring.boot.model.User;
import teamg.spring.boot.service.AppointmentService;
import teamg.spring.boot.service.UserService;

import java.util.Collections;
import java.util.List;

@RestController
public class AppointmentController {

    private static Logger LOG
            = LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private UserService us;

    //TEST - display a list of all appointments for all users
    @GetMapping("/appointments")
    public Model viewHomePage(Model model) {
        model.addAttribute("listAppointments", appointmentService.getAllAppointments());
        return model;
    }

  //  @GetMapping("appointment/{id}")
   // public Appointment getAppointmentById(@PathVariable Long id) {
   //     LOG.info("getUserById: " + id);
   //     return appointmentService.getAppointmentById(id);
   // }

    @GetMapping("appointments/{id}")
    public List<Appointment> getAppointments(@PathVariable Long id) {
        LOG.info("getUserAppointmentsById: " + id);
        User user;
        // below code checks if user exists, if not empty list is returned
        try {
            user = us.getUserById(id);
        } catch (UserNotFoundException e) {
            return Collections.emptyList();
        }
        return appointmentService.getAllAppointmentsByUser(id);
    }

}
