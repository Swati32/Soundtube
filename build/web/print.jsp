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
String email=request.getParameter("email"); 
String password=request.getParameter("password"); 
Class.forName("oracle.jdbc.driver.OracleDriver"); 
java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl","anjali","250792lt26i"); 
Statement st= con.createStatement(); 

String sql ="select c1+c2+c3+c4+c5+c6+c7+c8+c9+a1+a2+a3+a4+a5+a6+a7+a8+a9+b1+b2 as TOTAL from (select count(*) as c1 from album),"

           +"(select count(*) as c2 from albumartistlist),"
           + "(select count(*) as c3 from albumsonglist),(select count(*) as c4 from artist),(select count(*) as c5 from artistlist),(select count(*) as c6 from comments),"
           +"(select count(*) as c7 from favorites),(select count(*) as c8 from genres),(select count(*) as c9 from playlist_info),(select count(*) as a1 from playlists),"
           +"(select count(*) as a2 from rating),(select count(*) as a3 from requesttable),(select count(*) as a4 from reviews),(select count(*) as a5 from songartistlist),"
           +"(select count(*) as a6 from songnamealbummap),(select count(*) as a7 from songs),(select count(*) as a8 from subscription),(select count(*) as a9 from user_rating),"
           +"(select count(*) as b1 from user_song_history),(select count(*) as b2 from users)";
ResultSet rs=st.executeQuery(sql); 
if(rs.next()) 
{ 
    out.println(rs.getString(1));
} 
%>

</body>
</html>