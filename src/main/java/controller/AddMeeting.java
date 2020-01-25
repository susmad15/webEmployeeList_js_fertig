package controller;

import com.google.gson.Gson;
import data.Meeting;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import server.IServerProxy;
import server.ServerProxyFactory;

@WebServlet(name = "AddMeeting", urlPatterns = {"/AddMeeting"})
public class AddMeeting extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String selectedEmployee = new BufferedReader(new InputStreamReader(request.getInputStream())).readLine();

        IServerProxy serverProxy = ServerProxyFactory.getInstance();
        serverProxy.addMeeting(Long.parseLong(selectedEmployee));
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
