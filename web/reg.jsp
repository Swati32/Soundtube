<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Regjsp</title>
</head>
<body>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%
String user=request.getParameter("userid"); 
session.putValue("userid",user);  
String fname=request.getParameter("first_name");
String mname=request.getParameter("middle_name");
String lname=request.getParameter("last_name"); 
String email=request.getParameter("email"); 
String username=request.getParameter("username");
String pwd=request.getParameter("password");
String phone=request.getParameter("phone");
Class.forName("oracle.jdbc.driver.OracleDriver"); 
java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl","anjali","250792lt26i"); 
Statement st= con.createStatement(); 
ResultSet rs; 
int i=st.executeUpdate("insert into users(userid,first_name,middle_name,last_name,email,username,password,phone,userlevel,flag) values ('"+user+"','"+fname+"','"+mname+"','"+lname+"','"+email+"','"+username+"','"+pwd+"','"+phone+"','1','1')"); 

out.println("Registered"); 


%>
<a href ="Dashboard.jsp">Back to Home</a><br/><br/>
</body>
</html>