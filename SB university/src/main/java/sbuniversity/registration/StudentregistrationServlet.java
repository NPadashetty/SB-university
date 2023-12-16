package sbuniversity.registration;
import java.io.IOException;
import sbuniversity.DAO.Studentdao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sbuniversity.model.Student;

@WebServlet("/registration")
public class StudentregistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve user input from the registration form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        // Create a new student using the input data
        Student newStudent = new Student(username, password, firstName, lastName);

        Studentdao studentDAO = new Studentdao("jdbc:mysql://localhost:3306/university","root","test");
        studentDAO.addStudent(newStudent);

        // Redirect to the login page after successful registration
        response.sendRedirect("login.jsp");
    }
}
