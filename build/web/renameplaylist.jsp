<%@page import="java.util.*" import="java.sql.*" import="java.text.*" import="java.lang.*"%>
<%   
   String newname=request.getParameter("newname"); 
   String playlist_id= request.getParameter("playlistid");
   String redirectto =null;
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        if(newname != null){
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl", "anjali", "250792lt26i");

    
        String SQL = "update playlists set name = '"+newname+"' where playlistid="+playlist_id;
        

        Statement stmt = connection.createStatement();
        
        stmt.executeUpdate(SQL);
        connection.commit();

        connection.close() ;
        response.sendRedirect("playlist.jsp");
        } 
            
        redirectto = "renameplaylist.jsp?playlistid="+playlist_id;

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
        <form action ='<%out.println(redirectto);%>'  method="post">
New name :<input type="text" name="newname" />
<input type="submit" />


</form>
</body>
</html>
