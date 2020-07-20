package teamg.spring.boot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import teamg.spring.boot.model.Appointment;
import teamg.spring.boot.model.User;

import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

    @Query(value = "select * from appointment where user_id = ?", nativeQuery = true)
    List<Appointment> findUserAppointments(Long userId);
    @Query(value = "SELECT * FROM appointment where user_id = :userid and end_date = :date", nativeQuery = true)
    List<Appointment> findUserEndDateAppointments(@Param("userid")  Long userId, @Param("date") String dateTime);
}
