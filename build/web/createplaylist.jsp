<%@page import="java.util.*" import="java.sql.*" import="java.text.*" import="java.lang.*"%>
<%   
   String newname= null;
   if(request.getParameter("newname")!=null) 
   newname=request.getParameter("newname").toString();
   System.out.println(newname);
   String user=null;
   if(session.getAttribute("uid")!=null)
   user=session.getAttribute("uid").toString();
   System.out.println(user);
   String redirectto =null;
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        if(newname != null){
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl", "anjali", "250792lt26i");

    String SQL = "insert into playlists (userid,playlistid,name,timestamp) values ("+user+",(select max(playlistid) from playlists)+1,'"+newname+"',current_timestamp)";
    System.out.println(SQL);
        Statement stmt = connection.createStatement();
        
        stmt.executeUpdate(SQL);
        connection.commit();

        connection.close() ;
        response.sendRedirect("playlist.jsp");
        } 
            
        redirectto = "createplaylist.jsp";

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
New playlist name :<input type="text" name="newname" />
<input type="submit" />


</form>
</body>
</html>
