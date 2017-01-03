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
  //String email="swati.sisodia61@gmail.com";
  //String songid = "1";  
String email=request.getParameter("email").toString(); 
String songid=request.getParameter("id").toString(); 
Class.forName("oracle.jdbc.driver.OracleDriver"); 
java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl","anjali","250792lt26i"); 
Statement st= con.createStatement(); 
int rs=st.executeUpdate("insert into subscription values('"+email+"','"+songid+"')"); 
con.commit();
con.close();
response.sendRedirect("SongProfile.jsp?id="+songid);
%>

</body>
</html>