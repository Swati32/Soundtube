<%@page import="oracle.jdbc.OraclePreparedStatement"%>
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
  
    
    
    // Fetching the SONGID
    String songid=null;
    if(request.getParameter("id")!=null)
    songid=request.getParameter("id").toString();
  
 
String comment=request.getParameter("content"); 
String comment1= "Oooooo\n"

+"When she was just a girl\n"
+"She expected the world\n"
+"But it flew away from her reach\n"
+"So she ran away in her sleep\n"

+"And dreamed of para-para-paradise\n"
+"Para-para-paradise\n"
+"Para-para-paradise\n"
+"Every time she closed her eyes\n"

+"Oooooo\n"
+"Oooooo\n"

+"When she was just a girl\n"
+"She expected the world\n"
+"But it flew away from her reach\n"
+"And the bullets catch in her teeth\n";

 Class.forName("oracle.jdbc.driver.OracleDriver"); 
java.sql.Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl","anjali","250792lt26i"); 

    String sql = "update songs set lyrics=? where sid='"+songid+"'";
    OraclePreparedStatement st = (OraclePreparedStatement) connection.prepareStatement(sql);
    st.setStringForClob(1, comment);
out.println(st.execute());


response.sendRedirect("SongProfile.jsp?id="+songid);

        %>

</body>
</html>