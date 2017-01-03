<%@page import="java.util.*" import="java.sql.*" import="java.text.*" import="java.lang.*"%>
<%   
   String playlist_id= request.getParameter("playlistid");
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl", "anjali", "250792lt26i");

        String SQL1 = "delete from playlist_info where playlistid="+playlist_id;
        String SQL2 = "delete from playlists where playlistid="+playlist_id;
       
        Statement stmt = connection.createStatement();
        
        stmt.executeUpdate(SQL1);
        stmt.executeUpdate(SQL2);
        connection.commit();

        connection.close() ;
      response.sendRedirect("playlist.jsp");

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
        <form action ='deleteplaylist.jsp'  method="post">


</form>
        
    </body>
</html>
