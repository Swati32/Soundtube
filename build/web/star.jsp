<%@page import="java.util.*" 
        import="java.sql.*" 
        import="java.text.*" 
        import="java.lang.*" 
        %>

<%

  
   String s=null;
   if(session.getAttribute("uid")!=null)
  s=session.getAttribute("uid").toString();
 
    
    
    // Fetching the SONGID
    String songid=null;
    if(request.getParameter("id")!=null)
    songid=request.getParameter("id").toString();
 // if(songid==null)
   //   response.sendRedirect("Dashboard.jsp");
 //session.setAttribute("sid",songid);
 
 
  
  String rating=null;
   rating=(request.getParameter("UserRating").toString());
 System.out.println("Rating" +rating);
Class.forName("oracle.jdbc.driver.OracleDriver");         
Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl","anjali","250792lt26i");
String que="insert into user_rating(userid, songid, rating) values("+ s +",'"+songid+"',"+rating+")";
connection.commit();
int x=connection.prepareStatement(que).executeUpdate();
response.sendRedirect("SongProfile.jsp?id="+songid);
%>
