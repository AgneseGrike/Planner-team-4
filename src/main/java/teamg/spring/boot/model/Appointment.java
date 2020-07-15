package teamg.spring.boot.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column
    long userID;

    @Column
    LocalDateTime startDateTime;

    @Column
    LocalDateTime endDateTime;

    @Column
    String notes;

    public Appointment() {
    }

    public Appointment(long id, long userID, LocalDateTime startDateTime, LocalDateTime endDateTime, String notes) {
        this.id = id;
        this.userID = userID;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
