package com.microsoft.example.servlet;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.microsoft.example.*;
import com.microsoft.example.models.*;

public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
        throws IOException, ServletException
    {
        String username = request.getParameter("email");
        String password = request.getParameter("password");
        Employee employee = DataAccess.login(username, password);
        if (employee != null) {
            request.setAttribute("employee", employee);
            
            // Fetch all the fares for that employee while we're here
            List<Fare> fareList = DataAccess.employeeFares(employee);
            request.setAttribute("employeeList", fareList);
            
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        }
        else {
            request.getRequestDispatcher("/loginFailed.jsp").forward(request, response);
        }
    }
}

