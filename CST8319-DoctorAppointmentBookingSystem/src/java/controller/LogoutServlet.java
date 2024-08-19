/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
/*
 * Servlet implementation class LogoutServlet
 * This servlet handles user logout by invalidating the current session and
 * redirecting the user to the login page or home page.
 */

package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handles GET requests to process user logout
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the current session, if it exists
        HttpSession session = request.getSession(false); // Get current session, don't create a new one

        if (session != null) {
            // Invalidate the current session, effectively logging out the user
            session.invalidate();
        }

        // Redirect to the login page or home page after logging out
        response.sendRedirect("index.jsp");
    }
}
