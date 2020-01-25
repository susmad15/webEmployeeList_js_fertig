package controller;

import com.google.gson.Gson;
import data.Employee;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import server.IServerProxy;
import server.ServerProxyFactory;

@WebServlet(name = "RetrieveEmployees", urlPatterns = {"/RetrieveEmployees"})
public class RetrieveEmployees extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        IServerProxy serverProxy = ServerProxyFactory.getInstance();
        List<Employee> employees = serverProxy.getEmployees();

        Gson gson = new Gson();
        String jsonAnswer = gson.toJson(employees);

        System.out.println("RetrieveEmployees.processRequest, jsonAnswer: " + jsonAnswer);

        response.setContentType("application/json");
        OutputStreamWriter out = new OutputStreamWriter(response.getOutputStream());

        out.write(jsonAnswer);

        out.flush();
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
