package controller;

import com.google.gson.Gson;
import data.Meeting;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import server.IServerProxy;
import server.ServerProxyFactory;

@WebServlet(name = "RetrieveMeetingsForEmployee", urlPatterns = {"/RetrieveMeetingsForEmployee"})
public class RetrieveMeetingsForEmployee extends HttpServlet {

    private String requestType = "UNKNOWN";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("RetrieveMeetingsFromEmployee: RequestType = " + requestType);

        response.setContentType("text/html;charset=UTF-8");
        
        String selectedEmployee = new BufferedReader(new InputStreamReader(request.getInputStream())).readLine();
        System.out.println("RetrieveMeetings.processRequest: " +selectedEmployee);
        
        IServerProxy serverProxy = ServerProxyFactory.getInstance();
        List<Meeting> meetings = serverProxy.getMeetings();
        
        System.out.println("RetrieveMeetings.processRequest: " +selectedEmployee);
        System.out.println("RetrieveMeetings.processRequest, meetings: " +meetings.size());
        List<Meeting> filteredMeetings = meetings
                .stream()
                .filter(m -> m.getCreatedBy().getId().equals(Long.parseLong(selectedEmployee)))
                .collect(Collectors.toList());
        
        Gson gson = new Gson();
        String jsonAnswer = gson.toJson(filteredMeetings);
    
        System.out.println("RetrieveMeetings.processRequest, filteredMeetings.size(): " +filteredMeetings.size());
        System.out.println("RetrieveMeetings.processRequest, jsonAnswer: " +jsonAnswer);
        
        response.setContentType("application/json");
        OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream());
        out.write(jsonAnswer);
        out.flush();
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        requestType = "GET";

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        requestType = "POST";

        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
