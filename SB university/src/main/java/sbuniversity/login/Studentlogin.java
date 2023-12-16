package sbuniversity.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import sbuniversity.DAO.Studentdao;
import sbuniversity.model.Student;
//... (other imports)

@WebServlet("/login")
public class Studentlogin extends HttpServlet {
 private static final long serialVersionUID = 1L;

 protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
     response.setContentType("text/html");
     PrintWriter out = response.getWriter();

     // Load the JDBC driver
     try {
         Class.forName("com.mysql.cj.jdbc.Driver");
     } catch (ClassNotFoundException e) {
         e.printStackTrace();
         out.println("<p>Internal server error. Please contact the administrator.</p>");
         return;
     }

     // Retrieve user input from the login form
     String username = request.getParameter("username");
     String password = request.getParameter("password");

     try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/university", "root", "test")) {
         // Authenticate user using StudentDAO
         Studentdao studentDAO = new Studentdao(connection);
         Student student = studentDAO.getStudent(username, password);

         // Check if the user is authenticated
         if (student != null) {
             // Create a session and store user information
             HttpSession session = request.getSession();
             session.setAttribute("student", student);

             // Redirect to the welcome page
             response.sendRedirect("welcome.jsp");
         } else {
             // Display an error message on login failure
             out.println("<p>Login failed. Please check your username and password.</p>");
             request.getRequestDispatcher("login.jsp").include(request, response);
         }

         out.close();
     } catch (SQLException e) {
         e.printStackTrace();
     }
 }
}

