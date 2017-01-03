<%@page import="java.util.*" import="java.sql.*" import="java.text.*" import="java.lang.*"%>
<%  String keyword = (String) request.getParameter("id");
    int i = 0;
    if (keyword == null) {
        keyword = "";
    }
    int t = 0;
    
    String songname[] = new String[100];
    String songid[] = new String[100];
    String thumb[] = new String[100];
    String artist_name[] = new String[100];
    String rating[] = new String[100];
    String artistname = "Unknown";
    String yearsactive = "Unknown";
    String genre = "Unknown";
    String website = "Unknown";
    String SQL = "";

    String SQL1 = "SELECT * FROM ARTIST WHERE ARTISTID =\'" + keyword + "\'";
    SQL = " select * from songartistlist ASL "
            + " LEFT JOIN songs  ON asL.sid= songs.sid "
            + " LEFT JOIN rating on songs.sid = rating.songid"
            + " LEFT JOIN (SELECT sid ,wm_concat(name) as artistnames FROM (Select distinct(name),sid  "
            + " FROM (SELECT a.artistid,a.name,s.sid as sid FROM ARTIST a, songartistlist s where s.artistid = a.artistid)) group by sid) aa on aa.sid = songs.sid "
            + " WHERE asl.artistid=\'" + keyword + "\'";
    try {
      

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl", "anjali", "250792lt26i");
        //out.println("Here fething album details");
        PreparedStatement stmt = connection.prepareStatement(SQL);
        PreparedStatement stmt1 = connection.prepareStatement(SQL1);
        ResultSet l = stmt1.executeQuery();
        ResultSet k = stmt.executeQuery();
        while (l.next()) {

            artistname = l.getString("name");
            yearsactive = l.getString("yearsactive");
            genre = l.getString("genre");
            website = l.getString("website");
            if (genre !=null && genre.length()>20) {
                genre = genre.substring(0, 26);
            }
            
        }
        while (k.next()) {
            out.println("Here fething album song details1");
            songname[i] = k.getString("songtitle");
            songid[i]= k.getString("sid");
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

        while (l.next()) {

        }
        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

%> 

<!DOCTYPE HTML>
<html>
    <head>
        <title>playlist</title>
        <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
        <link href="css/style.css" rel='stylesheet' type='text/css' />
        <link href="css/style_1.css" rel='stylesheet' type='text/css' />
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
                    <ul class="nav" id="nav">
                        
                     	<li><a href="Dashboard.jsp">Dashboard</a></li>

						    	<li class="active"><a href="playlist.jsp">Playlists</a></li>
						    	<li><a href="favorite.jsp">Favorites</a></li>
						    	<li><a href="request.html">Requests</a></li>
						    	<li><a href="setting.html">Settings</a></li>

                        <div class="clearfix"></div>
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

        <div class="content_middle">
            <div class="container">
                <div class="content_middle_box">

                    <div class="Album-bot">
                        <div class="Album-bot-left">
                            <div class="Album-img">
                                <img src="<%out.println(thumb[0]);%>" alt="Album-img" />
                            </div>
                            <div class="Album-info">
                                <h1><%out.println(artistname);%></h1>
                                <p>Years Active :<%out.println(yearsactive);%> </p>
                                <p>Genre :<%out.println(genre);%></p>
                                <a href ="https://<%out.println(website);%>"></a>
                                <p>Website :<%out.println(website);%></p>
                                <br/>

                            </div>
                        </div>
                        <div class="Album-bot-right">
                            <div class="Album-bot-right-Tracks-top">
                                <p class="Album-bot-right-Tracks-top-logo">Tracks</p>
                            </div>
                            <div class="Album-bot-right-Tracks_ss">

                                <div class="middle_grid wow fadeInUp" data-wow-delay="0.4s">
                                    <%for (int j = 0; j < i; j++) {%>
                                    <div class="col-md-6">
                                        <div class="grid1">
                                               
                                                 <div class="view view-first">
                                                  
                                    <div class="index_imgss"><img src=<%out.println("\"" + thumb[j] + "\"");%> class="img-responsive"  alt=""/></div>
                                   
                                    <div class="mask">
                                        <div class="info"><i class="search"> </i>  
                                            <a href ='<% out.println("SongProfile.jsp?id="+songid[j]); %>'>Play</a></div>
                                        <ul class="mask_img">
                                            <li class="star"><img src="" alt=""/></li>
                                            <li class="set"><img src="" alt=""/></li>
                                            <div class="clearfix"> </div>
                                        </ul>
                                    </div>
                                </div>
                                  
                                            <div class="clearfix"> </div>
                                            <i class="home1"> </i>
                                            <ul class="vision">
                                                <li><%out.println(songname[j]);%></li>

                                            </ul>
                                            <div class="inner_wrap1">
                                                <ul class="item_module">
                                                    <li class="module_left"><img src="images/m1.jpg" class="img-responsive" alt=""/></li>
                                                    <li class="module_right"><h5><%out.println(artist_name[j]);%></h5></li>
                                                    <div class='post-desc star_filed'>
                                                        <form>
                                                            <input id="input-21e" value="0" type="number" class="rating" min=0 max=5 step=0.5 data-size="xs" >
                                                        </form>
                                                        <script>
            jQuery(document).ready(function () {
                $(".rating-kv").rating();
            });
                                                        </script>
                                                    </div>  
                                                    <div class="clearfix"> </div>
                                                </ul>
                                            </div></br>
                                                    <div class="clearfix"></div>
                                        </div>
                                    </div>
                                    <div class="clearfix"></div>
                                    <%}%>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
            <div class="clearfix"> </div>
        </div>
        <div class="clearfix"> </div>
    </body>
</html>	