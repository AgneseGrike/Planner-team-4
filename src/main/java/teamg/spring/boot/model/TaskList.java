package teamg.spring.boot.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class TaskList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn (name = "user_id", nullable = false)
    User user;

    @Column
    String title;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate deadline;

    @Column
    @Value("false")
    boolean done;

    public TaskList() {
    }

    public TaskList(Long id, User user, String title, LocalDate deadline, boolean done) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.deadline = deadline;
        this.done = done;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public LocalDate getDeadline() { return deadline; }

    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }

    public boolean isDone() { return done; }

    public void setDone(boolean done) { this.done = done; }

    @Override
    public String toString() {
        return "TaskList{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", deadline=" + deadline +
                ", done=" + done +
                '}';
    }
}
