package pojo;

import java.time.*;

public class Meeting {

    private Long id;

    private Employee createdBy;

    private ZonedDateTime startsAt;

    private Duration duration;

    public Meeting(Long id, Employee createdBy, ZonedDateTime startsAt, Duration duration) {
        this.id = id;
        this.createdBy = createdBy;
        this.startsAt = startsAt;
        this.duration = duration;
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

    
}
