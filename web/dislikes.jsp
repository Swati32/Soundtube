<%@page import="java.util.*" 
        import="java.sql.*" 
        import="java.text.*" 
        import="java.lang.*" 
        %>

<%
    String s=null;
   if(session.getAttribute("id")!=null)
  s=session.getAttribute("id").toString();
  
    
    
    // Fetching the SONGID
    String songid=null;
    if(request.getParameter("id")!=null)
    songid=request.getParameter("id").toString();
  
  
 

Class.forName("oracle.jdbc.driver.OracleDriver");         
Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl","anjali","250792lt26i");
String vis="select dislikes from songs where sid='"+songid+"'";
ResultSet rsv= connection.prepareStatement(vis).executeQuery();
int vi=0;
while(rsv.next())
{
    vi=rsv.getInt("dislikes");
    
}

vi=vi+1;
String up="update songs set dislikes='"+vi+"'where sid='"+songid+"'";
int x=connection.prepareStatement(up).executeUpdate();

response.sendRedirect("SongProfile.jsp?id="+songid);
%>




