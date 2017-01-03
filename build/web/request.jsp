<%-- 
    Document   : newjsp
    Created on : Apr 16, 2016, 11:25:32 AM
    Author     : Hans
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>
<%
String remail=request.getParameter("email");
String rtitle=request.getParameter("title");
String rinformation=request.getParameter("information");
Calendar calendar = Calendar.getInstance();
java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl","anjali","250792lt26i");
           
            
            
            String SQL = "INSERT INTO RequestTable VALUES(requestId.nextval,1,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(SQL);
            stmt.setString(1,rtitle);
            stmt.setString(2,rinformation);
            stmt.setTimestamp(3,currentTimestamp);
            stmt.setString(4,remail);
            stmt.executeUpdate();
            connection.commit();
            connection.close();
            
        } catch (Exception e)
        {
          e.printStackTrace();
        }     

%>

<!DOCTYPE HTML>
<html>
<head>
<title>Requests</title>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Custom Theme files -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--webfont-->
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<script src="js/jquery.easydropdown.js"></script>
<!--Animation-->
<script src="js/wow.min.js"></script>
<script>
	new WOW().init();
</script>
</head>
<body>
<div class="header">
		   <div class="col-sm-8 header-left">
					 <div class="logo">
						<a href="index.html"><img src="images/logo.png" style="height:auto; width:auto;" alt="logo"/></a>
					 </div>
					 <div class="menu">
						  <a class="toggleMenu" href="#"><img src="images/nav.png" alt="" /></a>
						    <ul class="nav" id="nav">
						    	
						    	<li><a href="Dashboard.jsp">Dashboard</a></li>

						    	<li><a href="playlist.jsp">Playlists</a></li>
						    	<li><a href="favorite.jsp">Favorites</a></li>
						    	<li><a href="request.html">Requests</a></li>
						    	<li class="active"><a href="setting.html">Settings</a></li>

							</ul>
				    </div>								
	    		    <div class="clearfix"></div>
	    	    </div>
	            <div class="col-sm-4 header_right">
	    		     <div id="loginContainer"><a href="Dashboard.jsp"><img src="images/login.png"><span>Back to Home</span></a>
						  
			             </div>
		                 <div class="clearfix"></div>
	                 </div>
	                <div class="clearfix"></div>
   </div>
   <div class="album_banner">
   	  
   </div>
   <div class="content_top">
   	  <div class="container main">
   		   
	     <div class="request-middle">
	       <p class="request-middle-logo">Request Your Song</p>
	     </div>
		 <div class="request-bottom">
		    <p>Your Request has been submitted!</p>
		 </div>
		 </div>
	  
  </div>
   
   <div class="content_middle">
   	  <div class="container">
   	    <div class="content_middle_box">
		
          
   		   
   		  
   		  </div>
   	  </div>
   </div>

</body>
</html>	