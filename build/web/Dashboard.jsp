<%@page import="java.util.*" 
        import="java.sql.*" 
        import="java.text.*" 
        import="java.lang.*" 
        %>


<%
    
    String c=null;
            c=request.getParameter("id");
   
    
    if(c==null)
        c="Top";
            
Class.forName("oracle.jdbc.driver.OracleDriver");
 Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl","anjali","250792lt26i");
 String name[]=new String[12];
 String thumb[]=new String[12];
 String artist_name[]=new String[12];
 String SID[]=new String[12];
                int i=0;
      PreparedStatement ps2=connection.prepareStatement("select * from "+c+" order by visits desc");
         ResultSet x=ps2.executeQuery();
         
         
         while (x.next())
         {
          thumb[i]=x.getString("thumbnail");   
             String g=thumb[i];
             SID[i]=x.getString("SID");
             //g=g.trim();
             if(g==null)
             thumb[i]="p2.jpg";
             else if(g.length()<10)
             thumb[i]="p2.jpg";
                 
            
             
             
             
             
             
          name[i]=x.getString("songtitle");
           if(name[i].length()>25)
         name[i]=name[i].substring(0,25)+"...";
          String b="select name from artist where artistid IN (select artistid from songartistlist where SID='"+x.getString("SID")+"')";
          PreparedStatement p =connection.prepareStatement(b);
          ResultSet k=p.executeQuery();
       artist_name[i]="Enrique Iglesias";
          while(k.next())
              if(k.getString(1)!="null")
          artist_name[i]=k.getString(1);
         
          
          i=i+1;
         }
          connection.close();
     

%>
<!DOCTYPE HTML>
<html>
<head>
<title>Dashboard</title>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="css/style.css" rel='stylesheet' type='text/css' />
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--webfont-->
<link href='//fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<script src="js/jquery.easydropdown.js"></script>
<!--Animation-->
<script src="js/wow.min.js"></script>
<link href="css/animate.css" rel='stylesheet' type='text/css' />
<script>
	new WOW().init();
</script>
</head>
<body>
<div class="header">
		   <div class="col-sm-8 header-left">
					 <div class="logo">
						<a href="print.jsp"><img src="images/logo.png" style="height:auto; width:auto;"alt=""/></a>
					 </div>
					 <div class="menu">
						  <a class="toggleMenu" href="#"><img src="images/nav.png" alt="" /></a>
						    <ul class="nav" id="nav">
                                                        
						    	<li class="active"><a href="Dashboard.jsp">Dashboard</a></li>
                                                        <% if(session.getAttribute("uid")!=null)
                                                        {%>
						      
						    	<li><a href="playlist.jsp">Playlists</a></li>
						    	<li><a href="favorite.jsp">Favorites</a></li>
						    	<li><a href="request.html">Requests</a></li>
						    	
                                                        <%}
						    	%>
								<div class="clearfix"></div>
							</ul>
							<script type="text/javascript" src="js/responsive-nav.js"></script>
				    </div>	
				     <!-- start search-->
				    
						<!----search-scripts---->
						<script src="js/classie.js"></script>
						<script src="js/uisearch.js"></script>
						<script>
							new UISearch( document.getElementById( 'sb-search' ) );
						</script>
						<!----//search-scripts---->						
	    		    <div class="clearfix"></div>
	    	    </div>
    
	            <div class="col-sm-4 header_right">
       <% 
          String s=null;
          try{
          s=session.getAttribute("name").toString();
          }
          catch(Exception e)
          {
              e.printStackTrace();
          }
          System.out.println("Name" +session.getAttribute("name"));
          System.out.println("Name" +s);
          if(s!=null)
          {
              out.println("<div id='loginContainer'><a href='#' id='loginButton' style='color:inherit;text-decoration:none;'><img src='images/login.png'><span>"+s+"</span></a>"
                      +"<a href='setting.html' id='loginButton' style='color:inherit;text-decoration:none;'><img src=''><span>"
                      + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                      + "Settings</span></a>"
                      +"<a href='Signout.jsp' id='loginButton' style='color:inherit;text-decoration:none;'><img src=''><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sign Out</span></a>"
                               +"</div>"); 
              
          }
          else
          {
                out.println("<div id='loginContainer'><a href='#' id='loginButton'><img src='images/login.png'><span>Login</span></a>"
                                 +"<div id='loginBox'>"
                                 +"<form id='loginForm' method='post' action='Login.jsp'>"
				 +"<fieldset id='body'>"
				 +"<fieldset>"
				 +"<label for='email'>Email Address</label>"
				 +"<input type='text' name='email' id='email'>"
				 +"</fieldset>"
                                 +"<fieldset>"
		                 +"<label for='password'>Password</label>"
                                 +"<input type='password' name='password' id='password'>"
                                 +"</fieldset>"
                                 +"<input type='submit' id='login' value='Sign in'>                                                       "
                                 +"<label for='checkbox'><input type='checkbox' id='checkbox'> <i>Remember me</i></label>"
                                 +"</fieldset>"
                                 + "<span><a href='Reg.html'>Register</a></span>"
                                 + "<span><a href='setting.html'>Forgot your password?</a></span>"
                                 +"</form>"
		                 +"</div>"
		                 +"</div>");
          }
                                  %>
	    		      
		                 <div class="clearfix"></div>
	                 </div>
	                <div class="clearfix"></div>
   </div>
                                  
   <div class="banner">
       <div class="container_wrap" style="margin-top: 1%; margin-left: 25%;">
   		<h1>Looking for Something?</h1>
                `<form style="text-align: center" method="post" action='SearchResult.jsp'>
   	       <div class="dropdown-buttons">   
            		  
				     <div class="dropdown-button">
					   <select>
							<option selected="" tabindex="9" style="display:none;color:#eee;" name="SearchBy">Search By</option>
                                                        <option value="Title">Title</option>
                                                        <option value="Artist">Artist</option>
                                                        <option value="Album">Album</option>
					          </select>
					 </div>
				   </div>  
                
				<input type="text" value="Keyword, name, date, ..." onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Keyword, name, date, ...';}" name='Keyword'>
			    <div class="contact_btn">
	               <label class="btn1 btn-2 btn-2g"><input name="submit" type="submit" id="submit" value="Search"></label>
	            </div>
			</form>        		
   		    <div class="clearfix"></div>
         </div>
   </div>
   <div class="content_top">
   	  <div class="container">
   		<div class="col-md-4 bottom_nav">
   		   <div class="content_menu" style="width:700px; color: red;">
				<ul>
                                    
                                    <%
          System.out.println(c.toLowerCase().trim() +     "color");
                                    if(c.toLowerCase().trim().equals("blue"))
                                       {
                                        out.println("<li><a href='Dashboard.jsp?id=Top'>Top 12 Tracks</a></li>" 
					+"<li class='active' style='color: red;'><a href='Dashboard.jsp?id=Blue'>Blues</a></li> "
					+"<li><a href='Dashboard.jsp?id=Rock'>Rock</a></li> "
					+"<li><a href='Dashboard.jsp?id=Jazz'>Jazz</a></li>"
					+"<li><a href='Dashboard.jsp?id=Country'>Country</a></li>"
					+"<li><a href='Dashboard.jsp?id=Classic'>Classic</a></li>");   
                                       }
                                    else if(c.toLowerCase().trim().equals("rock"))
                                       {
                                        out.println("<li><a href='Dashboard.jsp?id=Top'>Top 12 Tracks</a></li>" 
					+"<li><a href='Dashboard.jsp?id=Blue'>Blues</a></li> "
					+"<li class='active' style='color: red;'><a href='Dashboard.jsp?id=Rock'>Rock</a></li> "
					+"<li><a href='Dashboard.jsp?id=Jazz'>Jazz</a></li>"
					+"<li><a href='Dashboard.jsp?id=Country'>Country</a></li>"
					+"<li><a href='Dashboard.jsp?id=Classic'>Classic</a></li>");   
                                       }
                                    else if(c.toLowerCase().trim().equals("jazz"))
                                       {
                                        out.println("<li><a href='Dashboard.jsp?id=Top'>Top 12 Tracks</a></li>" 
					+"<li><a href='Dashboard.jsp?id=Blue'>Blues</a></li> "
					+"<li><a href='Dashboard.jsp?id=Rock'>Rock</a></li> "
					+"<li class='active' style='color: red;'><a href='Dashboard.jsp?id=Jazz'>Jazz</a></li>"
					+"<li><a href='Dashboard.jsp?id=Country'>Country</a></li>"
					+"<li><a href='Dashboard.jsp?id=Classic'>Classic</a></li>");   
                                       }
                                    else if(c.toLowerCase().trim().equals("country"))
                                       {
                                        out.println("<li><a href='Dashboard.jsp?id=Top'>Top 12 Tracks</a></li>" 
					+"<li><a href='Dashboard.jsp?id=Blue'>Blues</a></li> "
					+"<li><a href='Dashboard.jsp?id=Rock'>Rock</a></li> "
					+"<li><a href='Dashboard.jsp?id=Jazz'>Jazz</a></li>"
					+"<li class='active'style='color: red;'><a href='Dashboard.jsp?id=Country'>Country</a></li>"
					+"<li><a href='Dashboard.jsp?id=Classic'>Classic</a></li>");   
                                       }
                                    else if(c.toLowerCase().trim().equals("classic"))
                                       {
                                        out.println("<li><a href='Dashboard.jsp?id=Top'>Top 12 Tracks</a></li>" 
					+"<li><a href='Dashboard.jsp?id=Blue'>Blues</a></li> "
					+"<li><a href='Dashboard.jsp?id=Rock'>Rock</a></li> "
					+"<li><a href='Dashboard.jsp?id=Jazz'>Jazz</a></li>"
					+"<li><a href='Dashboard.jsp?id=Country'>Country</a></li>"
					+"<li class='active' style='color: red;'><a href='Dashboard.jsp?id=Classic'>Classic</a></li>");   
                                       }
                                    else
                                    {
					out.println("<li class='active' style='color: red;'><a href='Dashboard.jsp?id=Top'>Top 12 Tracks</a></li>" 
					+"<li><a href='Dashboard.jsp?id=Blue'>Blues</a></li> "
					+"<li><a href='Dashboard.jsp?id=Rock'>Rock</a></li> "
					+"<li><a href='Dashboard.jsp?id=Jazz'>Jazz</a></li>"
					+"<li><a href='Dashboard.jsp?id=Country'>Country</a></li>"
					+"<li><a href='Dashboard.jsp?id=Classic'>Classic</a></li>");
                                    }
                                       
                                        %>
                                     
                                        
                                        
				</ul>
			</div>
		</div>
		
		
   	</div>
   </div>
   <div class="content_middle">
   	  <div class="container">
   	    <div class="content_middle_box">
          
              
   			<div class="top_grid wow fadeInRight" data-wow-delay="0.4s">
   			<div class="clearfix">
   			   		   	
   			
   			</div>
   			</div>
   		
                
                
                
                
                
                <div class="top_grid wow fadeInRight" data-wow-delay="0.4s" style="margin-top:50px;">
   			<div class="col-md-3">
   			  <div class="grid1">
   				<div class="view view-first">
                  <div class="index_img"><img src=<%out.println("\""+thumb[0]+"\"");%> class='img-responsive' style="height:200px; width: 260px;" alt=""/></div>

   			          <div class="mask">
                                      <div class="info"><i class="search"> </i><a href='<% out.println("SongProfile.jsp?id="+SID[0]);%>'>Play</a></div>
                        <ul class="mask_img">
                        	<li class="star"><img src="" alt=""/></li>
                        	<li class="set"><img src="" alt=""/></li>
                        	<div class="clearfix"> </div>
                        </ul>
                        </div>
                    </div> 
                    <i class="home1"></i>
   				 <div class="inner_wrap">
   				 	<h3><%out.println(name[0]);%></h3>
   				 	<ul class="star1">
                                            <h4 class='green'><% out.println(artist_name[0]); %></h4>
   				 	  <li><a href="#"> <img src="images/star1.png" alt="">1</a></li>
   				 	</ul>
   				 </div>
   			   </div>
   			</div>
                                          
   			<div class="col-md-3">
   			  <div class="grid1">
   				<div class="view view-first">
                  <div class="index_img1"><img src=<%out.println("\""+thumb[1]+"\"");%> class="img-responsive" style="height:200px; width: 260px;" alt=""/></div>
   				     <div class="mask">
                        <div class="info"><i class="search"> </i><a href='<% out.println("SongProfile.jsp?id="+SID[1]);%>'>Play</a></div>
                        <ul class="mask_img">
                        	<li class="star"><img src="" alt=""/></li>
                        	<li class="set"><img src="" alt=""/></li>
                        	<div class="clearfix"> </div>
                        </ul>
                      </div>
                  </div> 
                 <i class="home1"> </i>
   				 <div class="inner_wrap">
   				 	<h3><%out.println(name[1]);%></h3>
   				 	<ul class="star1">
   				 	  <h4 class="green"><% out.println(artist_name[1]); %></h4>
   				 	  <li><a href="#"> <img src="images/star2.png" alt="">2</a></li>
   				 	</ul>
   				 </div>
   			   </div>
   			</div>
                    
                    
                    
   			<div class="col-md-3">
   			  <div class="grid1">
   				<div class="view view-first">
                  <div class="index_img2"><img src=<%out.println("\""+thumb[2]+"\"");%> class="img-responsive" style="height:200px; width: 260px;" alt=""/></div>
   				     <div class="mask">
                        <div class="info"><i class="search"> </i><a href='<% out.println("SongProfile.jsp?id="+SID[2]);%>'>Play</a></div>
                        <ul class="mask_img">
                        	<li class="star"><img src="" alt=""/></li>
                        	<li class="set"><img src="" alt=""/></li>
                        	<div class="clearfix"> </div>
                        </ul>
                      </div>
                  </div> 
                 <i class="home1"> </i>
   				 <div class="inner_wrap">
   				 	<h3><%out.println(name[2]);%></h3>
   				 	<ul class="star1">
   				 	  <h4 class="blue"><% out.println(artist_name[2]); %></h4>
   				 	  <li><a href="#"> <img src="images/star2.png" alt="">3</a></li>
   				 	</ul>
   				 </div>
   			   </div>
   			</div>
   			<div class="col-md-3">
   			  <div class="grid1">
   				<div class="view view-first">
                  <div class="index_img"><img src=<%out.println("\""+thumb[3]+"\"");%> class="img-responsive" style="height:200px; width: 260px;" alt=""/></div>

   			          <div class="mask">
                      <div class="info"><i class="search"> </i><a href='<% out.println("SongProfile.jsp?id="+SID[3]);%>'>Play</a></div>
                        <ul class="mask_img">
                        	<li class="star"><img src="" alt=""/></li>
                        	<li class="set"><img src="" alt=""/></li>
                        	<div class="clearfix"> </div>
                        </ul>
                      </div>
                  </div> 
                  <i class="home1"></i>
   				  <div class="inner_wrap">
   				 	<h3><%out.println(name[3]);%></h3>
   				 	<ul class="star1">
   				 	  <h4 class="green"><% out.println(artist_name[3]); %></h4>
   				 	  <li><a href="#"> <img src="images/star1.png" alt="">4</a></li>
   				 	</ul>
   				  </div>
   			   </div>
   			</div>
   			<div class="clearfix"> </div>
   		</div>
                
                
                
                
                
                
                
                
                
                
   		<div class="top_grid wow fadeInRight" data-wow-delay="0.4s" style="margin-top:50px;">
   			<div class="col-md-3">
   			  <div class="grid1">
   				<div class="view view-first">
                  <div class="index_img"><img src=<%out.println("\""+thumb[4]+"\"");%> class="img-responsive" style="height:200px; width: 260px;" alt=""/></div>

   			          <div class="mask">
                       <div class="info"><i class="search"> </i><a href='<% out.println("SongProfile.jsp?id="+SID[4]);%>'>Play</a></div>
                        <ul class="mask_img">
                        	<li class="star"><img src="" alt=""/></li>
                        	<li class="set"><img src="" alt=""/></li>
                        	<div class="clearfix"> </div>
                        </ul>
                        </div>
                    </div> 
                    <i class="home1"></i>
   				 <div class="inner_wrap">
   				 	<h3><%out.println(name[4]);%></h3>
   				 	<ul class="star1">
   				 	  <h4 class="green"><% out.println(artist_name[4]); %></h4>
   				 	  <li><a href="#"> <img src="images/star1.png" alt="">5</a></li>
   				 	</ul>
   				 </div>
   			   </div>
   			</div>
   			<div class="col-md-3">
   			  <div class="grid1">
   				<div class="view view-first">
                  <div class="index_img1"><img src=<%out.println("\""+thumb[5]+"\"");%> class="img-responsive" style="height:200px; width: 260px;" alt=""/></div>
   				     <div class="mask">
                        <div class="info"><i class="search"> </i><a href='<% out.println("SongProfile.jsp?id="+SID[5]);%>'>Play</a></div>
                        <ul class="mask_img">
                        	<li class="star"><img src="" alt=""/></li>
                        	<li class="set"><img src="" alt=""/></li>
                        	<div class="clearfix"> </div>
                        </ul>
                      </div>
                  </div> 
                 <i class="home1"> </i>
   				 <div class="inner_wrap">
   				 	<h3><%out.println(name[5]);%></h3>
   				 	<ul class="star1">
   				 	  <h4 class="green"><% out.println(artist_name[5]); %></h4>
   				 	  <li><a href="#"> <img src="images/star2.png" alt="">6</a></li>
   				 	</ul>
   				 </div>
   			   </div>
   			</div>
   			<div class="col-md-3">
   			  <div class="grid1">
   				<div class="view view-first">
                  <div class="index_img2"><img src=<%out.println("\""+thumb[6]+"\"");%> class="img-responsive" style="height:200px; width: 260px;" alt=""/></div>
   				     <div class="mask">
                        <div class="info"><i class="search"> </i><a href='<% out.println("SongProfile.jsp?id="+SID[6]);%>'>Play</a></div>
                        <ul class="mask_img">
                        	<li class="star"><img src="" alt=""/></li>
                        	<li class="set"><img src="" alt=""/></li>
                        	<div class="clearfix"> </div>
                        </ul>
                      </div>
                  </div> 
                 <i class="home1"> </i>
   				 <div class="inner_wrap">
   				 	<h3><%out.println(name[6]);%></h3>
   				 	<ul class="star1">
   				 	  <h4 class="blue"><% out.println(artist_name[6]); %></h4>
   				 	  <li><a href="#"> <img src="images/star2.png" alt="">7</a></li>
   				 	</ul>
   				 </div>
   			   </div>
   			</div>
   			<div class="col-md-3">
   			  <div class="grid1">
   				<div class="view view-first">
                  <div class="index_img"><img src=<%out.println("\""+thumb[7]+"\"");%> class="img-responsive" style="height:200px; width: 260px;" alt=""/></div>

   			          <div class="mask">
                      <div class="info"><i class="search"> </i><a href='<% out.println("SongProfile.jsp?id="+SID[7]);%>'>Play</a></div>
                        <ul class="mask_img">
                        	<li class="star"><img src="" alt=""/></li>
                        	<li class="set"><img src="" alt=""/></li>
                        	<div class="clearfix"> </div>
                        </ul>
                      </div>
                  </div> 
                  <i class="home1"></i>
   				  <div class="inner_wrap">
   				 	<h3><%out.println(name[7]);%></h3>
   				 	<ul class="star1">
   				 	  <h4 class="green"><% out.println(artist_name[7]); %></h4>
   				 	  <li><a href="#"> <img src="images/star1.png" alt="">8</a></li>
   				 	</ul>
   				  </div>
   			   </div>
   			</div>
   			<div class="clearfix"> </div>
   		</div>
   		
   		
   		
   		
   		
   		
   		
   		
   		
   		
   		
   		<div class="top_grid wow fadeInRight" data-wow-delay="0.4s" style="margin-top:50px;">
   			<div class="col-md-3">
   			  <div class="grid1">
   				<div class="view view-first">
                  <div class="index_img"><img src=<%out.println("\""+thumb[8]+"\"");%> class="img-responsive" style="height:200px; width: 260px;" alt=""/></div>

   			          <div class="mask">
                       <div class="info"><i class="search"> </i><a href='<% out.println("SongProfile.jsp?id="+SID[8]);%>'>Play</a></div>
                        <ul class="mask_img">
                        	<li class="star"><img src="" alt=""/></li>
                        	<li class="set"><img src="" alt=""/></li>
                        	<div class="clearfix"> </div>
                        </ul>
                        </div>
                    </div> 
                    <i class="home1"></i>
   				 <div class="inner_wrap">
   				 	<h3><%out.println(name[8]);%></h3>
   				 	<ul class="star1">
   				 	  <h4 class="green"><% out.println(artist_name[8]); %></h4>
   				 	  <li><a href="#"> <img src="images/star1.png" alt="">9</a></li>
   				 	</ul>
   				 </div>
   			   </div>
   			</div>
   			<div class="col-md-3">
   			  <div class="grid1">
   				<div class="view view-first">
                  <div class="index_img1"><img src=<%out.println("\""+thumb[9]+"\"");%> class="img-responsive" style="height:200px; width: 260px;" alt=""/></div>
   				     <div class="mask">
                        <div class="info"><i class="search"> </i><a href='<% out.println("SongProfile.jsp?id="+SID[9]);%>'>Play</a></div>
                        <ul class="mask_img">
                        	<li class="star"><img src="" alt=""/></li>
                        	<li class="set"><img src="" alt=""/></li>
                        	<div class="clearfix"> </div>
                        </ul>
                      </div>
                  </div> 
                 <i class="home1"> </i>
   				 <div class="inner_wrap">
   				 	<h3><%out.println(name[9]);%></h3>
   				 	<ul class="star1">
   				 	  <h4 class="green"><% out.println(artist_name[9]); %></h4>
   				 	  <li><a href="#"> <img src="images/star2.png" alt="">10</a></li>
   				 	</ul>
   				 </div>
   			   </div>
   			</div>
   			<div class="col-md-3">
   			  <div class="grid1">
   				<div class="view view-first">
                                    <div class="index_img2"><img src=<%out.println("\""+thumb[10]+"\"");%> class="img-responsive" style="height:200px; width: 260px;" alt=""/></div>
   				     <div class="mask">
                        <div class="info"><i class="search"> </i><a href='<% out.println("SongProfile.jsp?id="+SID[10]);%>'>Play</a></div>
                        <ul class="mask_img">
                        	<li class="star"><img src="" alt=""/></li>
                        	<li class="set"><img src="" alt=""/></li>
                        	<div class="clearfix"> </div>
                        </ul>
                      </div>
                  </div> 
                 <i class="home1"> </i>
   				 <div class="inner_wrap">
   				 	<h3><%out.println(name[10]);%></h3>
   				 	<ul class="star1">
   				 	  <h4 class="blue"><% out.println(artist_name[10]); %></h4>
   				 	  <li><a href="#"> <img src="images/star2.png" alt="">11</a></li>
   				 	</ul>
   				 </div>
   			   </div>
   			</div>
   			<div class="col-md-3">
   			  <div class="grid1">
   				<div class="view view-first">
                  <div class="index_img"><img src=<%out.println("\""+thumb[11]+"\"");%> class="img-responsive" style="height:200px; width: 260px;" alt=""/></div>

   			          <div class="mask">
                      <div class="info"><i class="search"> </i><a href='<% out.println("SongProfile.jsp?id="+SID[11]);%>'>Play</a></div>
                        <ul class="mask_img">
                        	<li class="star"><img src="" alt=""/></li>
                        	<li class="set"><img src="" alt=""/></li>
                        	<div class="clearfix"> </div>
                        </ul>
                      </div>
                  </div> 
                  <i class="home1"></i>
   				  <div class="inner_wrap">
   				 	<h3><%out.println(name[11]);%></h3>
   				 	<ul class="star1">
   				 	  <h4 class="green"><% out.println(artist_name[10]); %></h4>
   				 	  <li><a href="#"> <img src="images/star1.png" alt="">12</a></li>
   				 	</ul>
   				  </div>
   			   </div>
   			</div>
   			<div class="clearfix"> </div>
   		</div>
   		
   		
   		
   		
   		
   		
   		
   		
   		
   		
   		
   		
   		
   		
   		
   		
   		
   		
   		
   		<%connection.close();%>
   		
   		 <div class="clearfix"> </div>
   		
   		
   		
   		
   		
   		  
   		  </div>
   	  </div>
   </div>

</body>
</html>		