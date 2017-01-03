
<%@page import="java.util.*" import="java.sql.*" import="java.text.*" import="java.lang.*"%>

<%   String playlist_name[] = new String[100];
    String thumb[] = new String[100];
    String ss=null;
    if(session.getAttribute("uid")!= null)
        ss=session.getAttribute("uid").toString();
    else
        response.sendRedirect("Dashboard.jsp");
    String playlist_id[] = new String[100];int i = 0;
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl", "anjali", "250792lt26i");
        /*String a=session.getAttribute("UID").toString();  '"+a+"' */
         /*String SQL = "select name,playlistid,thumbnail from (select a.name,a.playlistid,b.thumbnail,a.timestamp from (select distinct name,playlistid,timestamp from playlists) a LEFT OUTER JOIN  (select name,playlistid,thumbnail from(select distinct playlists.name,playlists.playlistid,songs.THUMBNAIL,playlists.timestamp from playlists,songs,playlist_info a where playlists.userid="+s+" and a.playlistid = playlists.playlistid and songs.SID= (select  min(b.songid ) from playlist_info b where a.playlistid=b.playlistid))) b on a.playlistid=b.playlistid) order by timestamp desc";*/
       String SQL = "select name,playlistid,thumbnail from (select a.name,a.playlistid,b.thumbnail,a.timestamp from (select distinct name,playlistid,timestamp from playlists where userid="+ss+") a LEFT OUTER JOIN  (select name,playlistid,thumbnail from(select distinct playlists.name,playlists.playlistid,songs.THUMBNAIL,playlists.timestamp from playlists,songs,playlist_info a where playlists.userid="+ss+" and a.playlistid = playlists.playlistid and songs.SID= (select  min(b.songid ) from playlist_info b where a.playlistid=b.playlistid))) b on a.playlistid=b.playlistid) order by timestamp desc";
        PreparedStatement stmt = connection.prepareStatement(SQL);
      
        ResultSet k = stmt.executeQuery();

        while (k.next()) {
            playlist_name[i] = k.getString("name");
            playlist_id[i] = k.getString("playlistid");
            thumb[i] = k.getString("thumbnail"); 
             if (thumb[i] == null) {
                thumb[i] = "http://partyspace.com/images/blog_entries/no-music.png";
            } else if (thumb[i].length() < 10) {
                thumb[i] = "http://partyspace.com/images/blog_entries/no-music.png";
            }
           i = i + 1;
        }
        
        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
%> 

<!DOCTYPE HTML>
<html>
<head>
<title>Duhoot Bootstarp Website Template | Home :: w3layouts</title>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Custom Theme files -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
 <link href="css/swati.css" rel='stylesheet' type='text/css' />
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
						    	<li class="active"><a href="playlist.jsp">Playlists</a></li>
						    	<li><a href="favorite.jsp">Favorites</a></li>
						    	<li><a href="request.html">Requests</a></li>
						    	<li><a href="setting.html">Settings</a></li>
							</ul>
				    </div>								
	    		    <div class="clearfix"></div>
	    	    </div>
	            <div class="col-sm-4 header_right">
	    		       <%                           String s = null;
                    try {
                        s = session.getAttribute("name").toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (s != null) {
                        out.println("<div id='loginContainer'><a href='#' id='loginButton' style='color:inherit;text-decoration:none;'><img src='images/login.png'><span>" + s + "</span></a>"
                                + "<a href='Setting.html' id='loginButton' style='color:inherit;text-decoration:none;'><img src=''><span>"
                                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                                + "Settings</span></a>"
                                + "<a href='Signout.jsp' id='loginButton' style='color:inherit;text-decoration:none;'><img src=''><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sign Out</span></a>"
                                + "</div>");

                    } else {
                        out.println("<div id='loginContainer'><a href='#' id='loginButton'><img src='images/login.png'><span>Login</span></a>"
                                + "<div id='loginBox'>"
                                + "<form id='loginForm' method='post' action='Login.jsp'>"
                                + "<fieldset id='body'>"
                                + "<fieldset>"
                                + "<label for='email'>Email Address</label>"
                                + "<input type='text' name='email' id='email'>"
                                + "</fieldset>"
                                + "<fieldset>"
                                + "<label for='password'>Password</label>"
                                + "<input type='password' name='password' id='password'>"
                                + "</fieldset>"
                                + "<input type='submit' id='login' value='Sign in'>                                                       "
                                + "<label for='checkbox'><input type='checkbox' id='checkbox'> <i>Remember me</i></label>"
                                + "</fieldset>"
                                + "<span><a href='#'>Forgot your password?</a></span>"
                                + "</form>"
                                + "</div>"
                                + "</div>");
                    }
                %>
		                 <div class="clearfix"></div>
	                 </div>
	                <div class="clearfix"></div>
   </div>
 
                
                
                
                
        <div class="banner1" style = "width:auto; margin-bottom : 0">
           
        </div>
        <div class="content_topss" style = "width:auto; margin-top : 0 ;border:none"  >
            <div class="container" style = "width:auto; margin-top : 0;border:none">
                <div class ="col-md-2 wrapper" style = "width:auto; margin-top : 0 ">
                    <ul style="list-style-type: none">
                        <li> 

                            <form name="Search" action="SearchResult.jsp" method="POST" style="display:inline" >

                               <input type="text"  name = "Keyword" placeholder="What are you looking for ?" class="textox"  >
                                    <input name="submit" type="submit" id="submit" value="Search" class="Button" >
                                    
                                
                                <span class="content_dropdown2">
                                    <select name="SearchBy">
                                        <option selected tabindex="9" style="display:list-item;color:#e94c38;">Title</option>	
                                        <option>Album</option>
                                        <option>Artist </option>
                                    </select>
                                    <select name = "SortBy">
                                        <option selected tabindex="9" style="display:list-item;color:#e94c38;">Rating</option>	
                                        <option>Popularity</option>
                                        <option>Newest</option>
                                    </select>
                                </span>
                                
                                    
                                    
                                

                                                  

                            </form> </li> 
                    </ul>
                </div>
            </div>
        </div>
   <div class="content_top">
   	  <div class="container">
   		<div class="col-md-8 wow fadeInRight playlist-window" data-wow-delay="0.4s" >
                   
                    
          <div class="educate_grid">
               <div class="createplaylist_button"><h3><a href="createplaylist.jsp">create playlist</a></h3></div>                 
                <%for (int j = 0; j < i; j++) { %>    
 <%if(playlist_name[j] !=null){%>
            <div class="col-md-6">
   	    	  <div class="living_box">
			   <a href="subplaylist.jsp?playlistid=<%out.println(playlist_id[j]);%>"><img style="width:367px" src=<%out.println("\"" + thumb[j] + "\"");%>  class="img-responsive" alt=""/></a>
				<div class="living_desc desc1 playlist-function">
      
				<h3 class="playlist-function-song"><a href="subplaylist.jsp?playlistid=<%out.println(playlist_id[j]);%>"><%out.println(playlist_name[j]);%></a></h3>
                                <h6 class="playlist-function-add"><a href="deleteplaylist.jsp?playlistid=<%out.println(playlist_id[j]);%>">delete</a></h3>
                                <h6 class="playlist-function-delete"><a href="renameplaylist.jsp?playlistid=<%out.println(playlist_id[j]);%>">rename</a></h3>
                                
				<div class="clearfix"></div>
				</div>
			  </div>
		    </div>
                                 <%}else{ %>
                      
                    <div style="text-align: center"><img src="images/NoVideosFound.png" width="100" /></div>
                       
                      
                    <%}%>
		<%}%>
                
		    <div class="clearfix"></div>
		   </div>
                
		   <div class="educate_grid1">
           
		    
		    <div class="clearfix"></div>
		   </div>
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