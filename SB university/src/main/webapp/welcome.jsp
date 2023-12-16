<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Welcome Page</title>
</head>
<body>
    <h2>Welcome</h2>
    
    <%-- Retrieve user information from the session --%>
    <% 
        Object studentObj = session.getAttribute("student");
        if (studentObj != null) {
            // User is in session
            sbuniversity.model.Student student = (sbuniversity.model.Student) studentObj;
    %>
            <p>Welcome, <%= student.getFirstName() %> <%= student.getLastName() %>!</p>
            <p><a href="logout">logout</a></p>
    <%
        } else {
            // No user in session, redirect to login page
    %>
            <p>Please <a href="login.jsp">login</a>.</p>
    <%
        }
    %>

</body>
</html>
