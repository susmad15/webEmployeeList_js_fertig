package pojo;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class Employee {
 
    private Long id;

    private String name;
 
    private LocalDate birthday;
 
    private LocalDateTime updatedOn;

    public Employee(Long id, String name, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public String toString() {
        return id +", " +name;
    }
    
    public String toDropdownString() {
        return id +", " +name;
    }
    
    
}