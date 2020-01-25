
package server;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import pojo.Employee;
import pojo.Meeting;

public class MockServerProxy implements IServerProxy {

    List employees;
    List meetings;

    @Override
    public List getEmployees() {
        employees = new ArrayList<Employee>();
        employees.add(new Employee((long)1, "Franz", LocalDate.of(2000, Month.MARCH, 12)));
        employees.add(new Employee((long)2, "Hans", LocalDate.of(2001, Month.MAY, 20)));
        employees.add(new Employee((long)3, "Ludwig", LocalDate.of(1999, Month.AUGUST, 9)));
        employees.add(new Employee((long)4, "Heinrich", LocalDate.of(1973, Month.JANUARY, 28)));
        employees.add(new Employee((long)5, "Doris", LocalDate.of(1980, Month.SEPTEMBER, 2)));
        return employees;
    }

    @Override
    public List getMeetings() {
        meetings = new ArrayList<Meeting>();

        int x = 0;
        
        for (Employee employee : (List<Employee>) getEmployees()) {
            meetings.add(new Meeting((long) x,
                    employee,
                    ZonedDateTime.of(2017, 6, 25, 11, 30, 0, 0, ZoneId.systemDefault()),
                    Duration.of(45, ChronoUnit.MINUTES)));
            x++;
        }
        
        for (Employee employee : (List<Employee>) getEmployees()) {
            meetings.add(new Meeting((long) x,
                    employee,
                    ZonedDateTime.of(2018, 6, 25, 11, 30, 0, 0, ZoneId.systemDefault()),
                    Duration.of(60, ChronoUnit.MINUTES)));
            x++;
        }

        return meetings;
    }
    
    @Override
    public List getMeetingsForEmployee (long id) {
        Stream<Meeting> mstream = getMeetings().stream();
        List meetings = mstream
                .filter(m -> m.getCreatedBy().getId().equals(id))
                .collect(Collectors.toList());
        
        return meetings;
    }

    @Override
    public List getParticipants(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addMeeting() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addMeeting(long employee_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getEmployee(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
