package server;

import data.Employee;
import data.Meeting;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DBServerProxy implements IServerProxy {

    EntityManagerFactory emf;
    EntityManager em;

    public DBServerProxy() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        em = emf.createEntityManager();
    }

    @Override
    public List getEmployees() {
        Query query = em.createQuery("from Employee");
        return query.getResultList();
    }

    @Override
    public List getMeetings() {
        Query query = em.createQuery("from Meeting");
        return query.getResultList();
    }

    @Override
    public List getMeetingsForEmployee(long id) {
        return (List) getMeetings()
                .stream()
                .filter(m -> ((Meeting) m).getCreatedBy().getId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public List getParticipants(long id) {
        Query query = em.createQuery("from Meeting");
        return ((List<Meeting>) query.getResultList()
                .stream()
                .filter(m -> ((Meeting) m).getId() == id)
                .collect(Collectors.toList()))
                .get(0)
                .getParticipants();
    }

    //Only used for testing
    @Override
    public void addMeeting() {
        List employees = getEmployees();
        int lastIndex = employees.size() - 1;
        Employee createdBy = (Employee) employees.get(lastIndex);
        List<Employee> participants = (List<Employee>) employees.subList(
                lastIndex - 2,
                lastIndex - 1
        );

        Meeting m = new Meeting(
                createdBy,
                participants,
                ZonedDateTime.now(),
                Duration.ofMinutes(60)
        );

        em.getTransaction().begin();
        em.persist(m);
        em.getTransaction().commit();
    }
    
    @Override
    public void addMeeting(long employee_id) {
        
        Employee createdBy = (Employee) getEmployee(employee_id);
        
        List<Employee> employees = getEmployees();
        
        List<Employee> participants = new ArrayList<>();
        Random random = new Random();
        int index1 = -1;
        int index2 = -1;
        while (index1 == index2){
            index1 = random.nextInt(employees.size());
            index2 = random.nextInt(employees.size());
        }
        participants.add(employees.get(index1));
        participants.add(employees.get(index2));

        Meeting m = new Meeting(
                createdBy,
                participants,
                ZonedDateTime.now(),
                Duration.ofMinutes(60)
        );

        em.getTransaction().begin();
        em.persist(m);
        em.getTransaction().commit();
    }

    @Override
    public Object getEmployee(long id) {
        Query query = em.createQuery("from Employee");
        return ((List) query
                .getResultList()
                .stream()
                .filter(e -> ((Employee) e).getId() == id)
                .collect(Collectors.toList()))
                .get(0);
    }

}
