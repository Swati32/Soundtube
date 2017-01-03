<%@page import="java.util.*" import="java.sql.*" import="java.text.*" import="java.lang.*"%>
<%  
    String keyword=null;
    String searchby=null;
    String sortby=null;
    
    int i = 0;
    
    if(request.getParameter("Keyword") != null)
    keyword = request.getParameter("Keyword").toString();
    else
    keyword = "";
    
    if(request.getParameter("SearchBy") != null)
    searchby = request.getParameter("SearchBy").toString();
    else
    searchby = "Title";

    
     if(request.getParameter("SortBy") != null)
    sortby = request.getParameter("SortBy").toString();
    else
    sortby = "Rating";

      System.out.println("K"+keyword);
        System.out.println("K"+searchby);
          System.out.println("K"+sortby);
    
    String name[] = new String[100];
    String thumb[] = new String[100];
    String artist_name[] = new String[100];
    String rating[] = new String[100];
    String songid[] = new String[100];
    
    String SQL = "";
    try {
        if (searchby.equals("Title")) {
                SQL = "SELECT distinct songs.sid as songid,songtitle,thumbnail,artistnames,year as Newest,rating as Rating, "
                        + " count AS Popularity , UTL_MATCH.edit_distance(lower(songtitle),lower('" + keyword.toLowerCase() + "')) as ed "
                        + " FROM (SELECT * FROM (SELECT distinct sid FROM songs WHERE UTL_MATCH.edit_distance(lower(songtitle),lower('" + keyword.toLowerCase() + "'))<10 "
                        + " ORDER BY UTL_MATCH.edit_distance(lower(songtitle),lower('" + keyword.toLowerCase() + "')) ) WHERE ROWNUM<20) ss "
                        + " LEFT JOIN songs on ss.sid=songs.sid LEFT JOIN songartistlist ON ss.sid =songartistlist.sid"
                        + " LEFT JOIN artist ON songartistlist.artistid = artist.artistid LEFT JOIN (SELECT sid ,wm_concat(name) as artistnames "
                        + " FROM (Select distinct(name),sid FROM (SELECT a.artistid,a.name,s.sid as sid FROM ARTIST a, songartistlist s "
                        + " where s.artistid = a.artistid)) group by sid) aa ON aa.sid = songartistlist.sid LEFT JOIN rating ON "
                        + " songartistlist.sid =rating.SONGID ORDER BY ed ," + sortby + " desc";

              
                
        } else if (searchby.equals("Artist")) {
            SQL = "SELECT ss.artistid,songs.sid as songid,songtitle,thumbnail,artistnames,year as Newest,rating as Rating,count AS Popularity ,"
                    + " UTL_MATCH.edit_distance(lower(ss.NAME),('" + keyword.toLowerCase() + "')) as ed"
                    + " FROM (SELECT * FROM (SELECT artistid,name,UTL_MATCH.edit_distance(lower(NAME),('" + keyword.toLowerCase() + "')) as ed"
                    + " FROM ARTIST WHERE UTL_MATCH.edit_distance(lower(NAME),('" + keyword.toLowerCase() + "'))<3 "
                    + " ORDER BY ed ) WHERE ROWNUM<5) ss "
                    + " LEFT JOIN songartistlist ON ss.artistid =songartistlist.ARTISTID"
                    + " LEFT JOIN songs ON songartistlist.sid= songs.sid"
                    + " LEFT JOIN (SELECT sid ,wm_concat(name) as artistnames FROM "
                    + " (Select distinct(name),sid  FROM  (SELECT a.artistid,a.name,s.sid as sid FROM ARTIST a,"
                    + " songartistlist s where s.artistid = a.artistid)) group by sid) aa ON aa.sid = songartistlist.sid"
                    + " LEFT JOIN rating ON songs.sid =rating.SONGID ORDER BY ed," + sortby + " desc";
        } else if (searchby.equals("Album")) {

            SQL = "SELECT songs.sid AS songid,songtitle,thumbnail,artistnames,year as Newest,rating as Rating,count AS Popularity, alb.ed as ed"
                    + " FROM (SELECT * FROM (SELECT albumid,UTL_MATCH.edit_distance(lower(albumname),('" + keyword.toLowerCase() + "')) as ed,albumname "
                    + " FROM album WHERE UTL_MATCH.edit_distance(lower(albumname),('" + keyword.toLowerCase() + "'))<2 ORDER BY ed) WHERE ROWNUM<5) alb"
                    + " LEFT JOIN albumsonglist asl ON alb.albumid=asl.albumid"
                    + " LEFT JOIN songs ON asl.songid = songs.sid"
                    + " LEFT JOIN Rating r ON asl.songid = r.songid"
                    + " LEFT JOIN (SELECT sid ,wm_concat(name) as artistnames"
                    + " FROM (SELECT distinct(name),sid FROM (SELECT a.artistid,a.name,s.sid as sid"
                    + " FROM ARTIST a, songartistlist s"
                    + " WHERE s.artistid = a.artistid)) GROUP BY sid) aa ON asl.songid =aa.sid"
                    + " WHERE SONGS.SID IS NOT NULL"
                    + " ORDER BY alb.ed," + sortby + " desc";

           
        }
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl", "anjali", "250792lt26i");

        PreparedStatement stmt = connection.prepareStatement(SQL);

        ResultSet k = stmt.executeQuery();
        
        while (k.next()) {
            name[i] = k.getString("songtitle");
            songid[i]=k.getString("songid");
            thumb[i] = k.getString("thumbnail");
            if (thumb[i] == null) {
                thumb[i] = "p2.png";
            } else if (thumb[i].length() < 10) {
                thumb[i] = "p2.png";
            }
            artist_name[i] = k.getString("artistnames");
            if (artist_name[i] == null) {
                artist_name[i] = "unknown";
            }
             if (artist_name[i].length()>25) {
                artist_name[i] = artist_name[i].substring(0, 25);
            }
            
            i = i + 1;
        }
        
        connection.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

%> 

<!DOCTYPE html>
<html>
    <head>

        <title>Search Result | Living :: w3layouts</title>
        <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <!-- Custom Theme files -->
        <link href="css/style.css" rel='stylesheet' type='text/css' />
        <link href="css/swati.css" rel='stylesheet' type='text/css' />
        <!-- Custom Theme files -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!--webfont-->
        <link href='//fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
        <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="js/login.js"></script>
        <script src="js/jquery.easydropdown.js"></script>
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
                    <a href="index.html"><img src="images/logo.png" style="height:auto; width:auto;" alt=""/></a>
                </div>
                <div class="menu">
                    <a class="toggleMenu" href="#"><img src="images/nav.png" alt="" /></a>
                    <ul class="nav" id="nav">
                         	<li><a href="Dashboard.jsp">Dashboard</a></li>

						    	<li><a href="playlist.jsp">Playlists</a></li>
						    	<li><a href="favorite.jsp">Favorites</a></li>
						    	<li><a href="request.html">Requests</a></li>
						    	<li><a href="setting.html">Settings</a></li>

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
                                 + "<span><a href='Reg.html'>Register</a></span>"
                                 + "<span><a href='setting.html'>Forgot your password?</a></span>"


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
                
                
                


        <div class="living_middle">
            <div class="container">
                <h2 class="title block-title1">Search Results</h2>
                <%if (name[0] != null) { %> 
                <%for (int j = 0; j < i; j++) { %>    

                <div class="content_middle_box">
                    <div class="top_grid wow fadeInRight" data-wow-delay="0.4s">
                        <div class="clearfix">


                        </div>
                    </div>

                    <div class="top_grid wow fadeInRight" data-wow-delay="0.4s" style="margin-top:50px;">
                        <%  
                            if (name[j] != null){%>
                         <div class="col-md-3">
                            <div class="grid1">
                                <div class="view view-first">
                                    <div class="index_img1"><img src=<%out.println("\"" + thumb[j] + "\"");%> class="img-responsive" style="height:200px; width: 260px;" alt=""/></div>
                                    <div class="mask">
                                         <div class="info"><i class="search"> </i><a href='<% out.println("SongProfile.jsp?id="+songid[j]);%>'>Play</a></div>
                                        <ul class="mask_img">
                                            <li class="star"><img src="" alt=""/></li>
                                            <li class="set"><img src="" alt=""/></li>
                                            <div class="clearfix"> </div>
                                        </ul>
                                    </div>
                                </div> 
                                <i class="home1"> </i>
                                <div class="inner_wrap">
                                    <h3><%out.println(name[j]);%></h3>
                                    <ul class="star1">
                                        <h4 class="green1"><% out.println(artist_name[j]); %></h4>
                                        <li><a href="#"> <img src="images/star2.png" alt="">2</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <%}%>               
                        <%j++;%>
                        <%if (name[j] != null){%>
                        <div class="col-md-3">
                            <div class="grid1">
                                <div class="view view-first">
                                    <div class="index_img1"><img src=<%out.println("\"" + thumb[j] + "\"");%> class="img-responsive" style="height:200px; width: 260px;" alt=""/></div>
                                    <div class="mask">
                                          <div class="info"><i class="search"> </i><a href='<% out.println("SongProfile.jsp?id="+songid[j]);%>'>Play</a></div>
                                        <ul class="mask_img">
                                            <li class="star"><img src="" alt=""/></li>
                                            <li class="set"><img src="" alt=""/></li>
                                            <div class="clearfix"> </div>
                                        </ul>
                                    </div>
                                </div> 
                                <i class="home1"> </i>
                                <div class="inner_wrap">
                                    <h3><%out.println(name[j]);%></h3>
                                    <ul class="star1">
                                        <h4 class="green1"><% out.println(artist_name[j]); %></h4>
                                        <li><a href="#"> <img src="images/star2.png" alt="">2</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <%}%>                
                        <%j++;%>

                        <%if (name[j] != null){%>
                        <div class="col-md-3">
                            <div class="grid1">
                                <div class="view view-first">
                                    <div class="index_img2"><img src=<%out.println("\"" + thumb[j] + "\"");%> class="img-responsive" style="height:200px; width: 260px;" alt=""/></div>
                                    <div class="mask">
                                        <div class="info"><i class="search"> </i><a href='<% out.println("SongProfile.jsp?id="+songid[j]);%>'>Play</a></div>
                                        <ul class="mask_img">
                                            <li class="star"><img src="" alt=""/></li>
                                            <li class="set"><img src="" alt=""/></li>
                                            <div class="clearfix"> </div>
                                        </ul>
                                    </div>
                                </div> 
                                <i class="home1"> </i>
                                <div class="inner_wrap">
                                    <h3><%out.println(name[j]);%></h3>
                                    <ul class="star1">
                                        <h4 class="blue1"><% out.println(artist_name[j]); %></h4>
                                        <li><a href="#"> <img src="images/star2.png" alt="">3</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <%}%>                
                        <%j++;%>
                        <%if (name[j] != null){%>
                        <div class="col-md-3">
                            <div class="grid1">
                                <div class="view view-first">
                                    <div class="index_img"><img src=<%out.println("\"" + thumb[j] + "\"");%> class="img-responsive" style="height:200px; width: 260px;" alt=""/></div>

                                    <div class="mask">
                                        <div class="info"><i class="search"> </i><a href='<% out.println("SongProfile.jsp?id="+songid[j]);%>'>Play</a></div>
                                        <ul class="mask_img">
                                            <li class="star"><img src="" alt=""/></li>
                                            <li class="set"><img src="" alt=""/></li>
                                            <div class="clearfix"> </div>
                                        </ul>
                                    </div>
                                </div> 
                                <i class="home1"></i>
                                <div class="inner_wrap">
                                    <h3><%out.println(name[j]);%></h3>
                                    <ul class="star1">
                                        <h4 class="green1"><% out.println(artist_name[j]); %></h4>
                                        <li><a href="#"> <img src="images/star1.png" alt="">4</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <%}%>                
                                   
                        <div class="clearfix"> </div>
                    </div>

                    <%}
                    } else { %>

                    <div style="text-align: center"><img src="images/NoVideosFound.png" width="100" /></div>


                    <%}%>

                </div>
            </div>

    </body>
</html>		