package sbuniversity.DAO;


	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;

import sbuniversity.model.Student;

	public class Studentdao {
	    private Connection connection;

	    // Constructor to initialize the connection
	    public Studentdao(Connection connection) {
	        this.connection = connection;
	    }

	    public Studentdao(String jdbcUrl, String username, String password) {
	    	try {
				this.connection=DriverManager.getConnection(jdbcUrl,username,password);
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			// TODO Auto-generated constructor stub
		}

		// Insert a new student into the database
	    public void addStudent(Student student) {
	    	
	        String sql = "INSERT INTO student (username, password, first_name, last_name) VALUES (?, ?, ?, ?)";
	        
	        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	            pstmt.setString(1, student.getUsername());
	            pstmt.setString(2, student.getPassword());
	            pstmt.setString(3, student.getFirstName());
	            pstmt.setString(4, student.getLastName());

	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // Retrieve a student by username and password
	    public Student getStudent(String username, String password) {
	        String sql = "SELECT * FROM student WHERE username = ? AND password = ?";
	        
	        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	            pstmt.setString(1, username);
	            pstmt.setString(2, password);

	            try (ResultSet rs = pstmt.executeQuery()) {
	                if (rs.next()) {
	                    Student student = new Student();
	                    student.setId(rs.getInt("id"));
	                    student.setUsername(rs.getString("username"));
	                    student.setPassword(rs.getString("password"));
	                    student.setFirstName(rs.getString("first_name"));
	                    student.setLastName(rs.getString("last_name"));
	                    return student;
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return null;
	    }

	    // Get a list of all students in the database
	    public List<Student> getAllStudents() {
	        List<Student> students = new ArrayList<>();
	        String sql = "SELECT * FROM student";
	        
	        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	            try (ResultSet rs = pstmt.executeQuery()) {
	                while (rs.next()) {
	                    Student student = new Student();
	                    student.setId(rs.getInt("id"));
	                    student.setUsername(rs.getString("username"));
	                    student.setPassword(rs.getString("password"));
	                    student.setFirstName(rs.getString("first_name"));
	                    student.setLastName(rs.getString("last_name"));
	                    students.add(student);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return students;
	    
}
}