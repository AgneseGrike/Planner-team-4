package teamg.spring.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import teamg.spring.boot.exception.UserNotFoundException;
import teamg.spring.boot.model.Appointment;
import teamg.spring.boot.repository.AppointmentRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment getAppointmentById (Long id) {
        return appointmentRepository
                .findById(id).orElseThrow(
                        () -> new UserNotFoundException(
                                "Appointment with ID: " + id + " not found!"));
    }

    public List<Appointment> getAllAppointments() {
        Iterable<Appointment> iterable
                = appointmentRepository.findAll();
        List<Appointment> result
                = new ArrayList<>();
        iterable.forEach(result::add);

        return result;
    }

//  FIX-ME : Do we need another method for appointments?


    public void saveAppointment(Appointment appointment){
        appointmentRepository.save(appointment);
    }

}
