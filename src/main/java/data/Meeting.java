package data;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity(name = "Meeting")
public class Meeting {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee createdBy;
    
    @ManyToMany
    private List<Employee> participants;

    @Column(name = "starts_at")
    private ZonedDateTime startsAt;

    private Duration duration;
    
    public Meeting() {}
    
    public Meeting(Employee createdBy, List<Employee> participants, ZonedDateTime startsAt, Duration duration) {
        this.createdBy = createdBy;
        this.participants = participants;
        this.startsAt = startsAt;
        this.duration = duration;
    }

    public List<Employee> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Employee> participants) {
        this.participants = participants;
    }  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Employee createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(ZonedDateTime startsAt) {
        this.startsAt = startsAt;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Meeting " +id +", Created By: " +createdBy.getName();
    }

    
    
}
