package teamg.spring.boot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import teamg.spring.boot.model.Appointment;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
}
