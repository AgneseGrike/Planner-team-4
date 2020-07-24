package teamg.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import teamg.spring.boot.DateAppointments;
import teamg.spring.boot.model.Appointment;
import teamg.spring.boot.model.User;
import teamg.spring.boot.service.AppointmentService;
import teamg.spring.boot.service.UserService;

import javax.validation.Valid;
import java.text.DateFormatSymbols;
import java.util.*;

@Controller
public class homeController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/home";
    }
@GetMapping("/logout")
public String logout(){
    SecurityContextHolder.getContext().setAuthentication(null);
    return "redirect:/";
}
    @GetMapping("/home")
    public String home( @RequestParam(value = "month", required = false) String month, Model model) {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Riga"));
        DateAppointments da = new DateAppointments(userService.getByLogin(userName).getId(),cal, appointmentService);
        if (month != null && !month.contentEquals("")) {
            da.setMonth(Integer.parseInt(month) - 1);
        }

        model.addAttribute("username",userName);
        model.addAttribute("month", new DateFormatSymbols().getMonths()[cal.get(Calendar.MONTH)]);

        model.addAttribute("days", da);
        return "home";
        // will show user events
    }

    @GetMapping("/createEvent")
    public String createEvent(Appointment appointment, Model model) {
        model.addAttribute(new Appointment());
        return "createEvent";
    }

    @PostMapping("/addEvent")
    public String addEvent(@ModelAttribute @Valid Appointment appointment, Errors errors, Model model) {
        //Fixed the hardcoded user id -roberts
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        if (errors.hasErrors()) {
            return "createEvent";
        } else {
            appointment.setUser(userService.getByLogin(userName));
            appointmentService.saveAppointment(appointment);
            Long id = appointment.getId();
            return "redirect:/appointment/" + id;
        }
    }

    @GetMapping("appointment/{id}")
    public String getAppointmentById(@PathVariable Long id, Model model) {
        model.addAttribute("appointment", appointmentService.getAppointmentById(id));
        return "appointment";
    }

    @GetMapping("/testQuery")
    public String getAppointmentByUserIdAndDate(Model model) {
        Date dateee = new Date(1595116800000L);
        System.out.println(dateee.toString());
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByLogin(userName);

        model.addAttribute("listAppointments", appointmentService.getAllAppointmentsByUserAndDate(user.getId(), dateee));
        return "appointments";
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
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        appointment.setUser(userService.getByLogin(userName));
        appointmentService.saveAppointment(appointment);
        return new RedirectView("/appointment/" + id);
    }

}
