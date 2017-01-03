<%-- 
    Document   : changePsd
    Created on : Apr 16, 2016, 4:27:36 PM
    Author     : Hans
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>
<%
    String user=null;
   if(session.getAttribute("uid")!=null)
   user=session.getAttribute("uid").toString();
   System.out.println(user);
String error1="Old e-mail doesn't match";
String error=null;
String error2="Email Changes successfully";
String oldemail=request.getParameter("oldemail");
String newemail=request.getParameter("newemail");
String dbemail=null;
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl","anjali","250792lt26i");   
try{          
            String sql = "SELECT email FROM users where userid = "+user;       
            PreparedStatement p =connection.prepareStatement(sql);
            ResultSet rs = p.executeQuery();    
            if(rs.next()){
              dbemail=rs.getString("email");
              
            }
        } catch (Exception e)
        {
          e.printStackTrace();
        };
        
if(!dbemail.equals(oldemail))
 {error = error1;}
else if(error == null){
       String sql2="update users set email=\'"+newemail+"\' where userid= "+user;     
       Statement p1 = connection.createStatement();
       p1.executeUpdate(sql2);
       error=error2;
}

connection.close();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ChangeEmail</title>
    </head>
    <body>
        <h1><%out.println(error);%></h1>
        <p><a href="setting.html" ><% if (error==error2) {out.println("Return");} else {out.println("Retry");};%></a></p>
    </body>
</html>
