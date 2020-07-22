package teamg.spring.boot.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn (name = "user_id", nullable = false)
    User user;

    @Column
    @NotNull
    @Size(min = 1, max = 20)
    String title;

    @Column
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate startDate;

    @Column
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;

    @Column
    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    LocalTime startTime;

    @Column
    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    LocalTime endTime;

    @Column
    String notes;

    public Appointment() {
    }

    public Appointment(Long id, User user, String title, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, String notes) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.notes = notes;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public LocalDate getStartDate() { return startDate; }

    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }

    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public LocalTime getStartTime() { return startTime; }

    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalTime getEndTime() { return endTime; }

    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }

    public String getNotes() { return notes; }

    public void setNotes(String notes) { this.notes = notes; }

    @Override
    public String toString() {
        return "Appointment{" +
                "id= " + id +
                ", user= " + user +
                ", title= " + title +
                ", startDate= " + startDate +
                ", endDate= " + endDate +
                ", startTime= " + startTime +
                ", endTime= " + endTime +
                ", notes= '" + notes + '\'' +
                '}';
    }
}
