<%@page import="java.util.*" import="java.sql.*" import="java.text.*" import="java.lang.*"%>
<%   
    
      String user=null;
   if(session.getAttribute("uid")!=null)
   user=session.getAttribute("uid").toString();
   System.out.println(user);
   String song_id= request.getParameter("songid");
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl", "anjali", "250792lt26i");

        String SQL1 = "delete from favorites where userid="+user+" and songid='"+song_id+"'";
       
        Statement stmt = connection.createStatement();
        
        stmt.executeUpdate(SQL1);
       
        connection.commit();

        connection.close() ;
      response.sendRedirect("favorite.jsp");

    } catch (Exception e) {
        e.printStackTrace();
    }

%> 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action ='deletefavorite.jsp'  method="post">


</form>
        
    </body>
</html>
