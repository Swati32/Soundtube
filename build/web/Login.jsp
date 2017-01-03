<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Login - In Process</title>
</head>
<body>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%
String email=request.getParameter("email"); 
String password=request.getParameter("password"); 
Class.forName("oracle.jdbc.driver.OracleDriver"); 
java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl","anjali","250792lt26i"); 
Statement st= con.createStatement(); 

ResultSet rs=st.executeQuery("select * from users where username='"+email+"'"); 

if(rs.next()) 
{ 
if(rs.getString(7).equals(password)) 
{  
    String name=rs.getString("First_Name")+ " " +rs.getString("Last_Name") ;
    HttpSession session1=request.getSession();
                    session1.setAttribute("name",name);
                    session1.setAttribute("email",email);
                    session1.setAttribute("uid",rs.getString("userid"));
                   
                    } 

} 
    response.sendRedirect("Dashboard.jsp");
    con.close();
%>

</body>
</html>