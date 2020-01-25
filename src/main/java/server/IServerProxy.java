
package server;

import java.util.List;

public interface IServerProxy {
    
    public Object getEmployee(long id);
    public List getEmployees();
    public List getMeetings();
    public List getMeetingsForEmployee(long id);
    public List getParticipants(long id);
    public void addMeeting();
    public void addMeeting(long employee_id);
    
}
