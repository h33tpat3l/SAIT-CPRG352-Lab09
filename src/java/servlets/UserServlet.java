/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.*;
import services.*;

/**
 *
 * @author heetk
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserService userService = new UserService();
        
        String action = request.getParameter("action");
        String email = request.getParameter("email");

        try {
            switch (action) {
                case "edit":
                    request.setAttribute("editUser", userService.get(email));
                    break;
                case "delete":
                    userService.delete(email);
                    break;
            }
        } catch (Exception e) {
            if (action != null) {
                request.setAttribute("message", "Exception while performing edit or delete operation");
            }
        }
        try {
            request.setAttribute("users", userService.getAll());
        } catch(Exception e)  {
            request.setAttribute("message", "No users found");  
        }
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String action = request.getParameter("action");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        int roleID = Integer.parseInt(request.getParameter("role"));
        Boolean active = false;
        Role role;
        switch (roleID) {
            case 1:
                role = new Role(1, "System Admin");
                break;
            case 2:
                role = new Role(2, "Regular User");
                break;
            case 3:
                role = new Role(3, "Company Admin");
                break;
            default:
                role = new Role(2, "Regular User");
        }
        
        UserService userService = new UserService();

        // Methods based on the action
        try {
            switch (action) {
                case "add":
                    userService.insert(email, active, firstname, lastname, password);
                    break;
                case "update":
                    userService.update(email, active, firstname, lastname, password, role);
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            request.setAttribute("message", "Exception while performing action");
        }
        
         try {
            request.setAttribute("users", userService.getAll());
        } catch(Exception e)  {
            request.setAttribute("message", "No users found");  
        }
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }
}