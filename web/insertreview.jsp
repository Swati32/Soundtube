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
 
String title=request.getParameter("title"); 
String comment=request.getParameter("content"); 
int stars=Integer.parseInt(request.getParameter("ReviewRating").toString());
out.println(title);
out.println(comment);
out.println(songid);
Class.forName("oracle.jdbc.driver.OracleDriver"); 
Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl","anjali","250792lt26i"); 
int rs=connect.prepareStatement("insert into reviews(content, timestamp, userid, stars,title, sid) values('"+comment+"',sysdate,"+s+", "+stars+", '"+title+"', '"+songid+"')").executeUpdate();
response.sendRedirect("SongProfile.jsp?id="+songid);
connect.close();
%>

</body>
</html>