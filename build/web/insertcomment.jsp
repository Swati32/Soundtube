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
            
            String s=null;
   if(session.getAttribute("id")!=null)
  s=session.getAttribute("id").toString();
  s="1";
    
    
    // Fetching the SONGID
    String songid=null;
    if(request.getParameter("id")!=null)
    songid=request.getParameter("id").toString();
  
  
 //songid="000004WLD";
 
String comment=request.getParameter("content"); 

out.println(comment);
out.println(songid);
Class.forName("oracle.jdbc.driver.OracleDriver"); 
java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl","anjali","250792lt26i"); 
int rs=con.prepareStatement("insert into comments(content, timestamp, userid, songid) values('"+comment+"',sysdate, '"+s+"','"+songid+"')").executeUpdate();
response.sendRedirect("SongProfile.jsp?id="+songid);
%>

</body>
</html>