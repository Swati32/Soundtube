<%@page import="jdk.nashorn.internal.ir.Symbol"%>
<%@page import="java.util.*" 
        import="java.sql.*" 
        import="java.text.*" 
        import="java.lang.*" 
        %>

<%
    String t=null;
   if(session.getAttribute("uid")!=null)
  t=session.getAttribute("uid").toString();
   System.out.println(t);
  //s="1";
    
    
    // Fetching the SONGID
    String songid=null;
    if(request.getParameter("id")!=null)
    songid=request.getParameter("id").toString();
 // if(songid==null)
   //   response.sendRedirect("Dashboard.jsp");
 //session.setAttribute("sid",songid);
 
 
//GET THE DATA FROM THE DATABASE

String query="select songs.SID, songtitle, length, genre, visits, dislikes, likes, URL, thumbnail, year, lyrics, artistnames, ids, albumnames, albumIDss from "
+"songs, songartistlist, "
+"(SELECT sid ,wm_concat(name) as artistnames FROM "
        +"(Select distinct(name),s.sid FROM ARTIST a ,songartistlist s where s.artistid = a.artistid and s.SID='"+songid+"') GROUP BY sid), "
+"(SELECT sid ,wm_concat(artis) as ids FROM "
+"(Select distinct(s.artistid) as artis, s.sid FROM ARTIST a ,songartistlist s where s.artistid = a.artistid and s.SID='"+songid+"') GROUP BY sid),"
+"(SELECT songid ,wm_concat(albumname) as albumnames FROM "
+"(Select distinct(albumname),s.songid FROM album a ,albumsonglist s where s.albumid = a.albumid and s.songID='"+songid+"') GROUP BY songid),"
+"(SELECT songid ,wm_concat(artistids) as albumIDss FROM" 
+"(Select distinct(s.albumid) as artistids, s.songid FROM album a ,albumsonglist s where s.albumid = a.albumid and s.songID='"+songid+"') GROUP BY songid)"
+"where songs.SID='"+songid+"' and songartistlist.SID=songs.SID";

Class.forName("oracle.jdbc.driver.OracleDriver");         
Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl","anjali","250792lt26i");
ResultSet rs=connection.prepareStatement(query).executeQuery();
String title=null;  
int length=0;
String genre=null;
int visits=0;
int dislikes=0;
int likes=0;
String web=null;

String thumb=null;
int year=0;
String artistnames=null;
String artistids=null;
String albumnames=null;
String albumids=null;

 
while (rs.next())
{
    
    title=rs.getString("songtitle");

length=rs.getInt("length");
genre=rs.getString("genre");
visits=rs.getInt("visits");
dislikes=rs.getInt("dislikes");
likes=rs.getInt("likes");
web=rs.getString("URL");
//System.out.println("URL" +web);
thumb=rs.getString("thumbnail");
year=rs.getInt("year");
//////////DEAL WITH THE LYRICS PART OVER HERE
artistnames = rs.getString("artistnames");
artistids=rs.getString("ids");
albumnames=rs.getString("albumnames");
albumids=rs.getString("albumIDss");
}


//System.out.println("web" + web);



/////////////////////////////////////////UPDATE THE VISITS//////////////////////////////////////////
String vis="select visits from songs where sid='"+songid+"'";
ResultSet rsv= connection.prepareStatement(vis).executeQuery();
int vi=0;
while(rsv.next())
{
    vi=rsv.getInt("visits");
    
}

vi=vi+1;
String up="update songs set visits='"+vi+"'where sid='"+songid+"'";
int x=connection.prepareStatement(up).executeUpdate();




%>

<!DOCTYPE HTML>
<html>
<head>
<title>song profile</title>
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="css/style.css" rel='stylesheet' type='text/css' />
<link href="css/style_1.css" rel='stylesheet' type='text/css' />
<link href="css/styleold.css" rel='stylesheet' type='text/css' />
<link href="css/av.css" rel='stylesheet' type='text/css' />
<link href="css/swati.css" rel='stylesheet' type='text/css' />
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="js/jquery.js"></script>
<script src="js/tabs.js"></script>

<script type="text/javascript" src="js/login.js"></script>
<script src="js/jquery.easydropdown.js"></script>
<!--Animation-->
<script src="js/wow.min.js"></script>
<link href="css/animate.css" rel='stylesheet' type='text/css' />
<script>
new WOW().init();
</script>
<!--star-->
    <link href="css/star-rating.css" media="all" rel="stylesheet" type="text/css"/>
    <script src="js/star-rating.js" type="text/javascript"></script>
</head>
<body>
<div class="header">
  <div class="col-sm-8 header-left">
<div class="logo">
<a href="index.html"><img src="images/logo.png" style="height:auto; width:auto;" alt=""/></a>
</div>
<div class="menu">
 <a class="toggleMenu" href="#"><img src="images/nav.png" alt="" /></a>
                                                  <ul class="nav" id="nav" style="display: inline-block;">
    <li class="active"><a href="index.html">Dashboard</a></li>
    <li><a href="living.html">Playlist</a></li>
    <li><a href="education.html">Favorites</a></li>
    <li><a href="entertain.html">Requests</a></li>
    <li><a href="404.html">Settings</a></li>
<div class="clearfix"> </div>
</ul>
<script type="text/javascript" src="js/responsive-nav.js"></script>
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
                    System.out.println("Name is right?"+s);
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
                                + "<span><a href='Reg.html'>register</a></span>"
                                + "<span><a href='changePsd.jsp'>Forgot your password?</a></span>"
                                
                                + "</form>"
                                + "</div>"
                                + "</div>");
                    }
                %>
		                 <div class="clearfix"></div>
	                 </div>
               <div class="clearfix"></div>
   </div>
    <!--  SEARCH BAR  -->
    
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
            <div class="banner1" style = "width:auto; margin-bottom : 0">
           
        </div>
    


    
    
    
    <!--  SEARCH BAR  -->
    <div class="banner" style="height: 600px; width:100%; text-align: left; ">
        <div class="container_wrap" style="margin-top:  20px">
     
            <%

            String g=web;

            g=g.replace("watch?v=","embed/");


            g=g.concat("?autoplay=1");
        
            %>
            <iframe src="<%out.println(g);%>" frameborder="0" width="720" height="440" style="margin-left: 15px;" allowfullscreen></iframe>
                    <div id="boxbar">
                     
                                 <br>
                                
                                        <%
            
            ResultSet ak=connection.prepareStatement("select rating, count from rating where songid='"+songid+"'").executeQuery();
            if(ak.next())
            {}
            else
            {
                out.println(" <a href='#' class='post-desc cattegory' style='font-size: small'><img src='images/rating.png' style='width: 20px; height:20px;'></a>");
            }
            while(ak.next())
            {
            for(int k=0; k<ak.getInt("rating");k++)
                                         out.println(" <a href='#' class='post-desc cattegory' style='font-size: small'><img src='images/rating.png' style='width: 20px; height:20px;'></a>");
            out.println("<B style='float:left'>("+ak.getInt("count")+")</B>");
            }
            
                               %>
                               
                               <% 
            System.out.println("av");
                               String que="select * from user_rating where userid="+t+" and songid='"+songid+"'";
                               ResultSet ur=connection.prepareStatement(que).executeQuery();
                               if(ur.next())
                               {
                               
                                   
                                   
                                  for(int k=0; k<ur.getInt("rating");k++)
                                         out.println(" <a href='#' style='font-size: small'><img src='images/rating.png' style='width: 20px; height:20px;'></a>");
                          
                               
                               }
                             else
                               {
                                   out.println("av");
                                   out.println("<b style='float:left'></b>");
                                   out.println("<form style='display:inline-block; width:700; margin-left:35%;' method='post' action= 'star.jsp?id=" + songid + "'>"
                                         +"<div class='dropdown-button1' style=' height: 100px; width:600px;'>"
                              //                       +"<label style='display:inline-block; color:#fff; float:left;' for='UserRating' value='Rate the song'> Rate the song</label>"
  +"<select  style='display:inline-block; float:left;' name='UserRating'>"
+"<option selected='' tabindex='4' style='display:none;color:#eee;' name='UserRating'>Rate:</option>"
                                                        +"<option value='5'>5</option>"
                                                        +"<option value='4'>4</option>"
                                                        +"<option value='3'>3</option>"
                                                        +"<option value='2'>2</option>"
                                                        +"<option value='1'>1</option>"                                                     
                                                      +"   </select>"
                                                      +"<input  style='display:inline-block; float:left;' type='submit' name='SubmitRating' value='Submit Rating' class='button' style='margin-left: 80%; display: inline;' ></div></form>");
                               }
                                   
                                   
                                   
                                   
                                  System.out.println("av"); 
                                   

                               %>
                                                            
                               
                                                               
                                                                  
                                                         </div>
                                      

       <div class="clearfix"></div>      
         </div>
<div class="container_wrap_right">
      <div class="container_wrap_right_top" >
                           <p class="songpage_info_title"  >
                                 <h1 style="text-align: center; text-decoration: #ffffff"><%out.println(title);%></h1>
                                 
                                 
                               
                                 
                                 
                                 
                                 
                                 
                                 
<p class="songpage_info_content" style='text-decoration: #ffffff; '>
                                     <b>Artists</b>
                                     
                                     <% 
                                 int l=artistnames.indexOf(",");
                                 
                                 if(l<2)
                                 {
                                     String arn=artistnames;
                                     String ari=artistids;
                                      out.println("     "+"<a href='artist.jsp?id="+ari+"' style='color: #ffffff;'>");
                                         out.println(arn+"</a>");
                                 }
                                 else
                                 {
                                     String arn[]=artistnames.split(","); 
                                     String ari[]=artistids.split(",");
                                     int w=arn.length;
                                     if(w>7)
                                         w=6;
                                          for(int i=0;i<w;i++)
                                     {
                                         if(i==0)
                                         {
                                         out.println("     "+"<a href='artist.jsp?id="+ari[i]+"' style='color: #ffffff;'>");
                                         out.println(arn[i]+"</a>");
                                         }
                                         else
                                         {
                                         out.println(", "+"<a href='artist.jsp?id="+ari[i]+"' style='color: #ffffff;'>");
                                         out.println(arn[i]+"</a>");
                                         
                                     }}
                                                                          
                                 }   %>
                                     <br><br>
                                     <b>Length    :&nbsp;&nbsp;&nbsp;&nbsp;</b><%out.println(""+length);%><b style="margin-left: 35px"><br>
                                         Genre   :&nbsp;&nbsp;&nbsp;&nbsp;</b><%out.println(""+genre);%><br>                                     
                               <b>Year    :&nbsp;&nbsp;&nbsp;&nbsp;</b><%out.println(year);%>
                                     <br><br>
                                     
                                     <b>Albums</b>
                                     <% 
                                  int p=albumnames.indexOf(",");
                                  
                                  if(p>7)
                                  {                                     p=7;
                                  
                                  }
                                 if(p<2)
                                 {
                                     String aln=albumnames;
                                     String ali=albumids;
                                      out.println("     "+"<a href='album.jsp?id="+ali+"' style='color: #ffffff;'>");
                                         out.println(aln+"</a>");
                                 }
                                 else
                                 {   
                                     String aln[]=albumnames.split(",");
                                     String ali[]=albumids.split(",");
                                     int q=aln.length;
                                     if(q>7)
                                         q=6;
                                     for(int i=0;i<q;i++)
                                     {
                                         if(i==0)
                                         {
                                         out.println("<a href='album.jsp?id="+ali[i]+"' style='color: #ffffff;'>");
                                         out.println(aln[i]+"</a>");
                                         }
                                         else
                                         {
                                         out.println(", "+"<a href='album.jsp?id="+ali[i]+"' style='color: #ffffff; font-size:'>");
                                         out.println(aln[i]+"</a>");
                                         }
                                     }
                                 }   %>

                                     <br><br><br><br>
                                                                      </p>
  </div>
  <div class="container_wrap_right_bot" style="border-style: none;">
    <div class="container_wrap_right_bot_top" style="border-style: none;">
                                 <form class='songpage_add_playlist' method="post" action="<%out.println("addtoplay.jsp?id="+songid);%>"  style="border-style: none;">
                                     <div class="songpage_dropdown" >
<select name="one" class="dropdown-select">
                                        <%
                                        String pl="select * from playlists where userid="+t+" and playlistid not IN(select playlistid from playlist_info where songid ='"+songid+"')";
                                       ResultSet rpl=connection.prepareCall(pl).executeQuery();
                                       while(rpl.next())
                                       {
                                           out.println("<option value='"+rpl.getString("playlistid")+"'>"+rpl.getString("name")+"</option>");
                                       }
                                        %>
</select>
</div>
                                        
                                        <div class="songpage_a_button" style="display: inline-block; margin-left: 0px;">
                                            
                                            <input  type="submit" style=" color: white;" value="Add to Playlist" >
</div>
                                        </form>
                                        <%
                                       ResultSet fav=connection.prepareStatement("select * from favorites where songid='"+songid+"' and userid="+t).executeQuery();
                                       if(fav.next())
                                       {}
                                       else
                                       {
                                      out.println("<form class='songpage_add_playlist' method='post' action='addtofav.jsp?id="+songid+"'>"
                                        +"<div class='songpage_a_button'>"
  +"<input  type='submit' value='Add to Favorites' style=' color: white;' >"
+"</div>    "
+"</form>");
                                       }
                                        %>
                                        
</div>
                                        <br><br><br><br>
<div class="container_wrap_right_bot_top_bot">
                                     <p class="show_likes" style="text-align: center; color: #ffffff; font-size: x-large;">
                                         <% out.println(likes
                                                 +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                                                 visits+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                                                 +                                                 dislikes);%> 
                                     </p>
                                     
   <p class="show_likes" style="text-align: center; color: #ffffff; font-size: x-large; margin-top: 20px; ">
                                       <a  name="like" href='<%out.println("likes.jsp?id="+songid);%>'><img src="images/like-icon.png" height="35" width="35"></a>
  <a  name="dislike" href='<%out.println("dislikes.jsp?id="+songid);%>'><img src="images/dislike-icon.png" height="35" width="35"></a>
                                   </p>
  </div>
 <div class='star_left post-desc star_filed'>
                                      <!--  Total Rating Star  Total.jsp-->
                                      
                                      <%
                                   ResultSet rv=connection.prepareStatement("select rating from rating where songid='"+songid+"'").executeQuery();
                                   int rating;
                                   while(rv.next())
                                       rating=rv.getInt(1);
                                      %>
                                      </div>
  </div>
</div>
  </div>
   <div class="content_top">
     <div class="container">
    <div class="col-md-4 bottom_nav">
      <div class="content_menu" style="width:700px;">
</div>
</div>
    </div>
   </div>
   <div class="content_middle">
     <div class="container">
       <div class="content_middle_box">
          
              
    
    
                
                
                
                <br>
                <br>
                <br>
                <br>
                <br>
                
                
                
<div id="page-frame">
<div id="page">
<div id="sidebar">
<ul class="tabs" id="tabs">
<li style="width:200px;"><a href="#Lyrics">Lyrics</a></li>
<li style="width:200px;"><a href="#Reviews">Reviews</a></li>
<li style="width:200px;"><a href="#Comments">Comments</a></li>

</ul>

                        </div>
                   <div id="content">
                       
                       
                       
<div id="Lyrics" class="tab-section dashboard"> 
<div class="recent-post-dashboard">
<h1 class="main-heading">Lyrics</h1>
                                                <form method="post" action="<%out.println("lyric.jsp?id="+songid);%>"> 
<div class="last-post-grid">
              
                                                              

                <%
               ResultSet lyric=connection.prepareStatement("select lyrics from songs where sid='"+songid+"'").executeQuery();
                %>                          
                                                            

                <h3><textarea id="interests" placeholder="Review......." rows="60" cols="134" name="content" style="font-size: small;">  
            <%
               while (lyric.next())
               {
                   Clob c=lyric.getClob("lyrics");
                   if(c!=null)
        out.println(c.getSubString(1, (int) c.length()));
    }
                %></textarea></h3>

    <input type="submit" name="SubmitReview" value="Change Lyrics" class="button" style='margin-left: 40%;' > 

<div class='clearfix'></div>
</div>

         </form>                                  
                                                
<div class="clearfix"></div>
</div> 
</div>
<div id="Reviews" class="tab-section dashboard">
                                    <h1 class="main-heading">Reviews</h1>
<div class="last-post-grid">

                                             
                                             <form class="song_review_submit" method="post" action="<% out.println("insertreview.jsp?id="+songid+""); %>">
                                                                               
                                               
                                               
                                                 <input type="text" id="interests" placeholder="   Title............." name="title" style='width:836px;'><br>
                                                                                    <textarea id="interests" placeholder="Review......." rows="6" cols="100" name="content">                   
                                                                                    </textarea>
                                                 <div class="dropdown-button1" style=" height: 10px; widht:20px;">
                                                     <label for='ReviewRating' value='Rate the song'> Rate the song</label>
  <select name="ReviewRating">
<option selected="" tabindex="4" style="display:none;color:#eee;" name="ReviewRating">Rate:</option>
                                                        <option value="5">5</option>
                                                        <option value="4">4</option>
                                                        <option value="3">3</option>
                                                        <option value="2">2</option>
                                                        <option value="1">1</option>
                                                        
                                                      
         </select>
                                                      <input type="submit" name="SubmitReview" value="Submit Review" class="button" style='margin-left: 80%; display: inline;' >    
</div>
 
                                                  
 
  </form>
</div>
                                             <br><br>
                                               <% 
                                ResultSet getReviews=connection.prepareStatement("select content, timestamp, first_name, last_name, title, stars  from reviews, users where reviews.userid=users.userid and reviews.sid='"+songid+"' order by timestamp desc").executeQuery();
                               while(getReviews.next())
                               {
                             %> 
    <div class="last-post-grid">
<ul>
                                                            
                            
                                                
                             
                                <li><h3><b><%=getReviews.getString("title")%></b><br><%=getReviews.getString("content")%></h3>
                                            <div class='last-post-grid-meta'>
                                    <a href='#' class='post-desc author-details'>
                                        <%out.println(getReviews.getString("first_name")+" "+getReviews.getString("last_name"));%>
                                    </a>
                                          <a href='#' class='post-desc category' style='font-size: small'><%= getReviews.getString("timestamp") %></a>
                                          <div style="display: compact">
                                          <%  for(int k=0; k<getReviews.getInt("stars");k++)
                                         out.println(" <a href='#' class='post-desc cattegory' style='font-size: small'><img src='images/rating.png' style='width: 20px; height:20px;'></a>");
                               %></div><div class='clearfix'></div>  
                               <% }%>
                                          
                                 </div>              
                                  </li>               
                                                  
                                                       </ul>
<div class="clearfix"></div>
</div> 
</div>
<div id="Comments" class="tab-section dashboard">
                                    <h1 class="main-heading">Comments</h1>
<div class="last-post-grid">
  
                                            <form class="song_comment_submit" method="post" action="<% out.println("insertcomment.jsp?id="+songid+""); %>">
  <textarea id="interest" placeholder="       Please write your comment here" rows="6" cols="100" name="content"></textarea>
   <div class="k1">  
    <input type="submit" value="submit" class="button" style='margin-left: 40%;'>
   </div>
  </form>
                                            
</div>
                                                <br><br>
                                    <div class="last-post-grid">
<ul>
<!-- COMMENTS  -->
                                <% 
                               
                                ResultSet getComments=connection.prepareStatement("select content, timestamp, first_name, last_name from comments, users where comments.userid=users.userid and comments.songid='"+songid+"' order by timestamp desc").executeQuery();
                                while(getComments.next())
                                {
                                    
                                out.println("       <li>"
                            +"                      <h3>"+getComments.getString("content")+"</h3>"
                            +"                      <div class='last-post-grid-meta'>"
                            +"                      <a href='#' class='post-desc author-details'>"+getComments.getString("first_name")+"  "+getComments.getString("last_name")+"</a>"
                            +"                      <a href='#' class='post-desc category'>"+getComments.getDate("timestamp")+"</a>"
                            +"                      <div class='clearfix'></div>"
                            +"                      </div>"
                            +"                      </li>");
                                }
                                %>
                                              
</ul>
<div class="clearfix"></div>
</div> 
</div>
                                                
                                                
                                                
                                                
                                                
</div>
<div style="clear:both;"></div>
</div>
</div>                
                
                
                
    
    
    <div class="clearfix"> </div>
    
    
    
    
    
     
     </div>
     </div>
   </div>
   <div class="footer">
    <div class="container">
    <div class="footer_top">
      <h3>Subscribe to our newsletter</h3>
           <form method="post" action='Subscription.jsp'>
<span>
<i><img src="images/mail.png" alt=""></i>
   <input type="text" value="Enter your email" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Enter your email';}">
   <label class="btn1 btn2 btn-2 btn-2g"> <input name="submit" type="submit" id="submit" value="Subscribe"> </label>
   <div class="clearfix"> </div>
</span>    
  </form>
 </div>
      </div>
   </div>
    
    
  <%
  Class.forName("oracle.jdbc.driver.OracleDriver"); 
java.sql.Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl","anjali","250792lt26i"); 
Statement st= con.createStatement(); 
PreparedStatement cs = con.prepareCall("{call UPDATELOG(?,?)}");
cs.setString(1,t);
cs.setString(2,songid);
cs.executeUpdate();
con.commit();
con.close();

  %>  
    
    
    
    
    
    
    
    
<!--script>
$(document).ready(
$('#srch-max').click(function(){
       $('.srch-box').css({
       'width':"auto", 
       'height':"auto",
       'min-height':"50px", 
       'background':"transparent",
       'margin':"0px",
       'box-shadow':"0px 0px rgba(0,0,0,0)",
       'border':"0px"
       });
       
       $('.kwd-set').css({
       'float':"right", 
       'clear':"both",
       'margin':"10px",
       'width':"auto",
       'height':"auto",
       });
       
       $('.srch-box h3').css({
       'display':"none" 
       });
       
       $('.srch-results').css({
       'display':"block",
       'opacity':"1",
       'height':"1000px"
       });
       
}) 
) 
</script-->
    
    
    
    
    
    
    
    
    
    
    
</body>
</html> 







































































