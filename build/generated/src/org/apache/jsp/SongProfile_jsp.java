package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import jdk.nashorn.internal.ir.Symbol;
import java.util.*;
import java.sql.*;
import java.text.*;
import java.lang.*;

public final class SongProfile_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");

    String t=null;
   if(session.getAttribute("uid")!=null)
  t=session.getAttribute("uid").toString();
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





      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE HTML>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<title>song profile</title>\n");
      out.write("<link href=\"http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("<link href=\"css/bootstrap.css\" rel='stylesheet' type='text/css' />\n");
      out.write("<link href=\"css/style.css\" rel='stylesheet' type='text/css' />\n");
      out.write("<link href=\"css/style_1.css\" rel='stylesheet' type='text/css' />\n");
      out.write("<link href=\"css/styleold.css\" rel='stylesheet' type='text/css' />\n");
      out.write("<link href=\"css/av.css\" rel='stylesheet' type='text/css' />\n");
      out.write("<link href=\"css/swati.css\" rel='stylesheet' type='text/css' />\n");
      out.write("<!-- Custom Theme files -->\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("\n");
      out.write("<script src=\"js/jquery.js\"></script>\n");
      out.write("<script src=\"js/tabs.js\"></script>\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\" src=\"js/login.js\"></script>\n");
      out.write("<script src=\"js/jquery.easydropdown.js\"></script>\n");
      out.write("<!--Animation-->\n");
      out.write("<script src=\"js/wow.min.js\"></script>\n");
      out.write("<link href=\"css/animate.css\" rel='stylesheet' type='text/css' />\n");
      out.write("<script>\n");
      out.write("new WOW().init();\n");
      out.write("</script>\n");
      out.write("<!--star-->\n");
      out.write("    <link href=\"css/star-rating.css\" media=\"all\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("    <script src=\"js/star-rating.js\" type=\"text/javascript\"></script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div class=\"header\">\n");
      out.write("  <div class=\"col-sm-8 header-left\">\n");
      out.write("<div class=\"logo\">\n");
      out.write("<a href=\"index.html\"><img src=\"images/logo.png\" style=\"height:auto; width:auto;\" alt=\"\"/></a>\n");
      out.write("</div>\n");
      out.write("<div class=\"menu\">\n");
      out.write(" <a class=\"toggleMenu\" href=\"#\"><img src=\"images/nav.png\" alt=\"\" /></a>\n");
      out.write("                                                  <ul class=\"nav\" id=\"nav\" style=\"display: inline-block;\">\n");
      out.write("    <li class=\"active\"><a href=\"index.html\">Dashboard</a></li>\n");
      out.write("    <li><a href=\"living.html\">Playlist</a></li>\n");
      out.write("    <li><a href=\"education.html\">Favorites</a></li>\n");
      out.write("    <li><a href=\"entertain.html\">Requests</a></li>\n");
      out.write("    <li><a href=\"404.html\">Settings</a></li>\n");
      out.write("<div class=\"clearfix\"> </div>\n");
      out.write("</ul>\n");
      out.write("<script type=\"text/javascript\" src=\"js/responsive-nav.js\"></script>\n");
      out.write("   </div> \n");
      out.write("   \n");
      out.write("       <div class=\"clearfix\"></div>\n");
      out.write("       </div>\n");
      out.write("           <div class=\"col-sm-4 header_right\">\n");
      out.write("\t    \t\t       ");
                           String s = null;
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
                
      out.write("\n");
      out.write("\t\t                 <div class=\"clearfix\"></div>\n");
      out.write("\t                 </div>\n");
      out.write("               <div class=\"clearfix\"></div>\n");
      out.write("   </div>\n");
      out.write("    <!--  SEARCH BAR  -->\n");
      out.write("    \n");
      out.write("    <div class=\"banner1\" style = \"width:auto; margin-bottom : 0\">\n");
      out.write("           \n");
      out.write("        </div>\n");
      out.write("        <div class=\"content_topss\" style = \"width:auto; margin-top : 0 ;border:none\"  >\n");
      out.write("            <div class=\"container\" style = \"width:auto; margin-top : 0;border:none\">\n");
      out.write("                <div class =\"col-md-2 wrapper\" style = \"width:auto; margin-top : 0 \">\n");
      out.write("                    <ul style=\"list-style-type: none\">\n");
      out.write("                        <li> \n");
      out.write("\n");
      out.write("                            <form name=\"Search\" action=\"SearchResult.jsp\" method=\"POST\" style=\"display:inline\" >\n");
      out.write("\n");
      out.write("                               <input type=\"text\"  name = \"Keyword\" placeholder=\"What are you looking for ?\" class=\"textox\"  >\n");
      out.write("                                    <input name=\"submit\" type=\"submit\" id=\"submit\" value=\"Search\" class=\"Button\" >\n");
      out.write("                                    \n");
      out.write("                                \n");
      out.write("                                <span class=\"content_dropdown2\">\n");
      out.write("                                    <select name=\"SearchBy\">\n");
      out.write("                                        <option selected tabindex=\"9\" style=\"display:list-item;color:#e94c38;\">Title</option> \n");
      out.write("                                        <option>Album</option>\n");
      out.write("                                        <option>Artist </option>\n");
      out.write("                                    </select>\n");
      out.write("                                    <select name = \"SortBy\">\n");
      out.write("                                        <option selected tabindex=\"9\" style=\"display:list-item;color:#e94c38;\">Rating</option> \n");
      out.write("                                        <option>Popularity</option>\n");
      out.write("                                        <option>Newest</option>\n");
      out.write("                                    </select>\n");
      out.write("                                </span>\n");
      out.write("                                \n");
      out.write("                                    \n");
      out.write("                                    \n");
      out.write("                                \n");
      out.write("\n");
      out.write("                                                  \n");
      out.write("\n");
      out.write("                            </form> </li> \n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("            <div class=\"banner1\" style = \"width:auto; margin-bottom : 0\">\n");
      out.write("           \n");
      out.write("        </div>\n");
      out.write("    \n");
      out.write("\n");
      out.write("\n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    <!--  SEARCH BAR  -->\n");
      out.write("    <div class=\"banner\" style=\"height: 600px; width:100%; text-align: left; \">\n");
      out.write("        <div class=\"container_wrap\" style=\"margin-top:  20px\">\n");
      out.write("     \n");
      out.write("            ");


            String g=web;

            g=g.replace("watch?v=","embed/");


            g=g.concat("?autoplay=0");
        
            
      out.write("\n");
      out.write("            <iframe src=\"");
out.println(g);
      out.write("\" frameborder=\"0\" width=\"720\" height=\"440\" style=\"margin-left: 15px;\" allowfullscreen></iframe>\n");
      out.write("                    <div id=\"boxbar\">\n");
      out.write("                     \n");
      out.write("                                 <br>\n");
      out.write("                                \n");
      out.write("                                        ");

            
            ResultSet ak=connection.prepareStatement("select rating, count from rating where songid='"+songid+"'").executeQuery();
            while(ak.next())
            {
            for(int k=0; k<ak.getInt("rating");k++)
                                         out.println(" <a href='#' class='post-desc cattegory' style='font-size: small'><img src='images/rating.png' style='width: 20px; height:20px;'></a>");
            out.println("<B style='float:left'>("+ak.getInt("count")+")</B>");
            }
            
                               
      out.write("\n");
      out.write("                               \n");
      out.write("                               ");
 
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
                                   out.println("<form style='display:inline-block; width:700;' method='post' action= 'star.jsp?id=" + songid + "'>"
                                         +"<div class='dropdown-button1' style=' height: 100px; width:600px;'>"
                                                     +"<label style='display:inline-block; color:#fff; float:left;' for='UserRating' value='Rate the song'> Rate the song</label>"
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
                                   

                               
      out.write("\n");
      out.write("                                                            \n");
      out.write("                               \n");
      out.write("                                                               \n");
      out.write("                                                                  \n");
      out.write("                                                         </div>\n");
      out.write("                                      \n");
      out.write("</div>\n");
      out.write("       <div class=\"clearfix\"></div>      \n");
      out.write("         </div>\n");
      out.write("<div class=\"container_wrap_right\" >\n");
      out.write("      <div class=\"container_wrap_right_top\" >\n");
      out.write("                           <p class=\"songpage_info_title\"  >\n");
      out.write("                                 <h1 style=\"text-align: center; text-decoration: #ffffff\">");
out.println(title);
      out.write("</h1>\n");
      out.write("                                 \n");
      out.write("                                 \n");
      out.write("                               \n");
      out.write("                                 \n");
      out.write("                                 \n");
      out.write("                                 \n");
      out.write("                                 \n");
      out.write("                                 \n");
      out.write("                                 \n");
      out.write("<p class=\"songpage_info_content\" style='text-decoration: #ffffff; '>\n");
      out.write("                                     <b>Artists</b>\n");
      out.write("                                     \n");
      out.write("                                     ");
 
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
                                          for(int i=0;i<arn.length;i++)
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
                                                                          
                                 }   
      out.write("\n");
      out.write("                                     <br><br>\n");
      out.write("                                     <b>Length    :&nbsp;&nbsp;&nbsp;&nbsp;</b>");
out.println(""+length);
      out.write("<b style=\"margin-left: 35px\"><br>\n");
      out.write("                                         Genre   :&nbsp;&nbsp;&nbsp;&nbsp;</b>");
out.println(""+genre);
      out.write("<br>                                     \n");
      out.write("                               <b>Year    :&nbsp;&nbsp;&nbsp;&nbsp;</b>");
out.println(year);
      out.write("\n");
      out.write("                                     <br><br>\n");
      out.write("                                     \n");
      out.write("                                     <b>Albums</b>\n");
      out.write("                                     ");
 
                                  int p=albumnames.indexOf(",");
                                 if(l<2)
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
                                     for(int i=0;i<aln.length;i++)
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
                                 }   
      out.write("\n");
      out.write("\n");
      out.write("                                     <br><br><br><br>\n");
      out.write("                                                                      </p>\n");
      out.write("  </div>\n");
      out.write("  <div class=\"container_wrap_right_bot\" style=\"border-style: none;\">\n");
      out.write("    <div class=\"container_wrap_right_bot_top\" style=\"border-style: none;\">\n");
      out.write("                                 <form class='songpage_add_playlist' method=\"post\" action=\"");
out.println("addtoplay.jsp?id="+songid);
      out.write("\"  style=\"border-style: none;\">\n");
      out.write("                                     <div class=\"songpage_dropdown\" >\n");
      out.write("<select name=\"one\" class=\"dropdown-select\">\n");
      out.write("                                        ");

                                        String pl="select * from playlists where userid="+t+" and playlistid not IN(select playlistid from playlist_info where songid ='"+songid+"')";
                                       ResultSet rpl=connection.prepareCall(pl).executeQuery();
                                       while(rpl.next())
                                       {
                                           out.println("<option value='"+rpl.getString("playlistid")+"'>"+rpl.getString("name")+"</option>");
                                       }
                                        
      out.write("\n");
      out.write("</select>\n");
      out.write("</div>\n");
      out.write("                                        \n");
      out.write("                                        <div class=\"songpage_a_button\" style=\"display: inline-block; margin-left: 0px;\">\n");
      out.write("                                            \n");
      out.write("                                            <input  type=\"submit\" style=\" color: white;\" value=\"Add to Playlist\" >\n");
      out.write("</div>\n");
      out.write("                                        </form>\n");
      out.write("                                        ");

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
                                        
      out.write("\n");
      out.write("                                        \n");
      out.write("</div>\n");
      out.write("                                        <br><br><br><br>\n");
      out.write("<div class=\"container_wrap_right_bot_top_bot\">\n");
      out.write("                                     <p class=\"show_likes\" style=\"text-align: center; color: #ffffff; font-size: x-large;\">\n");
      out.write("                                         ");
 out.println(likes
                                                 +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                                                 visits+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                                                 +                                                 dislikes);
      out.write(" \n");
      out.write("                                     </p>\n");
      out.write("                                     \n");
      out.write("   <p class=\"show_likes\" style=\"text-align: center; color: #ffffff; font-size: x-large; margin-top: 20px; \">\n");
      out.write("                                       <a  name=\"like\" href='");
out.println("likes.jsp?id="+songid);
      out.write("'><img src=\"images/like-icon.png\" height=\"35\" width=\"35\"></a>\n");
      out.write("  <a  name=\"dislike\" href='");
out.println("dislikes.jsp?id="+songid);
      out.write("'><img src=\"images/dislike-icon.png\" height=\"35\" width=\"35\"></a>\n");
      out.write("                                   </p>\n");
      out.write("  </div>\n");
      out.write(" <div class='star_left post-desc star_filed'>\n");
      out.write("                                      <!--  Total Rating Star  Total.jsp-->\n");
      out.write("                                      \n");
      out.write("                                      ");

                                   ResultSet rv=connection.prepareStatement("select rating from rating where songid='"+songid+"'").executeQuery();
                                   int rating;
                                   while(rv.next())
                                       rating=rv.getInt(1);
                                      
      out.write("\n");
      out.write("                                      </div>\n");
      out.write("  </div>\n");
      out.write("</div>\n");
      out.write("  </div>\n");
      out.write("   </div> </div>\n");
      out.write("   <div class=\"content_top\">\n");
      out.write("     <div class=\"container\">\n");
      out.write("    <div class=\"col-md-4 bottom_nav\">\n");
      out.write("      <div class=\"content_menu\" style=\"width:700px;\">\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("    </div>\n");
      out.write("   </div>\n");
      out.write("   <div class=\"content_middle\">\n");
      out.write("     <div class=\"container\">\n");
      out.write("       <div class=\"content_middle_box\">\n");
      out.write("          \n");
      out.write("              \n");
      out.write("    \n");
      out.write("    \n");
      out.write("                \n");
      out.write("                \n");
      out.write("                \n");
      out.write("                <br>\n");
      out.write("                <br>\n");
      out.write("                <br>\n");
      out.write("                <br>\n");
      out.write("                <br>\n");
      out.write("                \n");
      out.write("                \n");
      out.write("                \n");
      out.write("<div id=\"page-frame\">\n");
      out.write("<div id=\"page\">\n");
      out.write("<div id=\"sidebar\">\n");
      out.write("<ul class=\"tabs\" id=\"tabs\">\n");
      out.write("<li style=\"width:200px;\"><a href=\"#Lyrics\">Lyrics</a></li>\n");
      out.write("<li style=\"width:200px;\"><a href=\"#Reviews\">Reviews</a></li>\n");
      out.write("<li style=\"width:200px;\"><a href=\"#Comments\">Comments</a></li>\n");
      out.write("\n");
      out.write("</ul>\n");
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                   <div id=\"content\">\n");
      out.write("                       \n");
      out.write("                       \n");
      out.write("                       \n");
      out.write("<div id=\"Lyrics\" class=\"tab-section dashboard\"> \n");
      out.write("<div class=\"recent-post-dashboard\">\n");
      out.write("<h1 class=\"main-heading\">Lyrics</h1>\n");
      out.write("                                                <form method=\"post\" action=\"");
out.println("lyric.jsp?id="+songid);
      out.write("\"> \n");
      out.write("<div class=\"last-post-grid\">\n");
      out.write("              \n");
      out.write("                                                              \n");
      out.write("\n");
      out.write("                ");

               ResultSet lyric=connection.prepareStatement("select lyrics from songs where sid='"+songid+"'").executeQuery();
                
      out.write("                          \n");
      out.write("                                                            \n");
      out.write("\n");
      out.write("                <h3><textarea id=\"interests\" placeholder=\"Review.......\" rows=\"60\" cols=\"134\" name=\"content\" style=\"font-size: small;\">  \n");
      out.write("            ");

               while (lyric.next())
               {
                   Clob c=lyric.getClob("lyrics");
                   if(c!=null)
        out.println(c.getSubString(1, (int) c.length()));
    }
                
      out.write("</textarea></h3>\n");
      out.write("\n");
      out.write("    <input type=\"submit\" name=\"SubmitReview\" value=\"Change Lyrics\" class=\"button\" style='margin-left: 40%;' > \n");
      out.write("\n");
      out.write("<div class='clearfix'></div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("         </form>                                  \n");
      out.write("                                                \n");
      out.write("<div class=\"clearfix\"></div>\n");
      out.write("</div> \n");
      out.write("</div>\n");
      out.write("<div id=\"Reviews\" class=\"tab-section dashboard\">\n");
      out.write("                                    <h1 class=\"main-heading\">Reviews</h1>\n");
      out.write("<div class=\"last-post-grid\">\n");
      out.write("\n");
      out.write("                                             \n");
      out.write("                                             <form class=\"song_review_submit\" method=\"post\" action=\"");
 out.println("insertreview.jsp?id="+songid+""); 
      out.write("\">\n");
      out.write("                                                                               \n");
      out.write("                                               \n");
      out.write("                                               \n");
      out.write("                                                 <input type=\"text\" id=\"interests\" placeholder=\"   Title.............\" name=\"title\" style='width:836px;'><br>\n");
      out.write("                                                                                    <textarea id=\"interests\" placeholder=\"Review.......\" rows=\"6\" cols=\"100\" name=\"content\">                   \n");
      out.write("                                                                                    </textarea>\n");
      out.write("                                                 <div class=\"dropdown-button1\" style=\" height: 10px; widht:20px;\">\n");
      out.write("                                                     <label for='ReviewRating' value='Rate the song'> Rate the song</label>\n");
      out.write("  <select name=\"ReviewRating\">\n");
      out.write("<option selected=\"\" tabindex=\"4\" style=\"display:none;color:#eee;\" name=\"ReviewRating\">Rate:</option>\n");
      out.write("                                                        <option value=\"5\">5</option>\n");
      out.write("                                                        <option value=\"4\">4</option>\n");
      out.write("                                                        <option value=\"3\">3</option>\n");
      out.write("                                                        <option value=\"2\">2</option>\n");
      out.write("                                                        <option value=\"1\">1</option>\n");
      out.write("                                                        \n");
      out.write("                                                      \n");
      out.write("         </select>\n");
      out.write("                                                      <input type=\"submit\" name=\"SubmitReview\" value=\"Submit Review\" class=\"button\" style='margin-left: 80%; display: inline;' >    \n");
      out.write("</div>\n");
      out.write(" \n");
      out.write("                                                  \n");
      out.write(" \n");
      out.write("  </form>\n");
      out.write("</div>\n");
      out.write("                                             <br><br>\n");
      out.write("                                               ");
 
                                ResultSet getReviews=connection.prepareStatement("select content, timestamp, first_name, last_name, title, stars  from reviews, users where reviews.userid=users.userid and reviews.sid='"+songid+"' order by timestamp desc").executeQuery();
                               while(getReviews.next())
                               {
                             
      out.write(" \n");
      out.write("    <div class=\"last-post-grid\">\n");
      out.write("<ul>\n");
      out.write("                                                            \n");
      out.write("                            \n");
      out.write("                                                \n");
      out.write("                             \n");
      out.write("                                <li><h3><b>");
      out.print(getReviews.getString("title"));
      out.write("</b><br>");
      out.print(getReviews.getString("content"));
      out.write("</h3>\n");
      out.write("                                            <div class='last-post-grid-meta'>\n");
      out.write("                                    <a href='#' class='post-desc author-details'>\n");
      out.write("                                        ");
out.println(getReviews.getString("first_name")+" "+getReviews.getString("last_name"));
      out.write("\n");
      out.write("                                    </a>\n");
      out.write("                                          <a href='#' class='post-desc category' style='font-size: small'>");
      out.print( getReviews.getString("timestamp") );
      out.write("</a>\n");
      out.write("                                          <div style=\"display: compact\">\n");
      out.write("                                          ");
  for(int k=0; k<getReviews.getInt("stars");k++)
                                         out.println(" <a href='#' class='post-desc cattegory' style='font-size: small'><img src='images/rating.png' style='width: 20px; height:20px;'></a>");
                               
      out.write("</div><div class='clearfix'></div>  \n");
      out.write("                               ");
 }
      out.write("\n");
      out.write("                                          \n");
      out.write("                                 </div>              \n");
      out.write("                                  </li>               \n");
      out.write("                                                  \n");
      out.write("                                                       </ul>\n");
      out.write("<div class=\"clearfix\"></div>\n");
      out.write("</div> \n");
      out.write("</div>\n");
      out.write("<div id=\"Comments\" class=\"tab-section dashboard\">\n");
      out.write("                                    <h1 class=\"main-heading\">Comments</h1>\n");
      out.write("<div class=\"last-post-grid\">\n");
      out.write("  \n");
      out.write("                                            <form class=\"song_comment_submit\" method=\"post\" action=\"");
 out.println("insertcomment.jsp?id="+songid+""); 
      out.write("\">\n");
      out.write("  <textarea id=\"interest\" placeholder=\"       Please write your comment here\" rows=\"6\" cols=\"100\" name=\"content\"></textarea>\n");
      out.write("   <div class=\"k1\">  \n");
      out.write("    <input type=\"submit\" value=\"submit\" class=\"button\" style='margin-left: 40%;'>\n");
      out.write("   </div>\n");
      out.write("  </form>\n");
      out.write("                                            \n");
      out.write("</div>\n");
      out.write("                                                <br><br>\n");
      out.write("                                    <div class=\"last-post-grid\">\n");
      out.write("<ul>\n");
      out.write("<!-- COMMENTS  -->\n");
      out.write("                                ");
 
                               
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
                                
      out.write("\n");
      out.write("                                              \n");
      out.write("</ul>\n");
      out.write("<div class=\"clearfix\"></div>\n");
      out.write("</div> \n");
      out.write("</div>\n");
      out.write("                                                \n");
      out.write("                                                \n");
      out.write("                                                \n");
      out.write("                                                \n");
      out.write("                                                \n");
      out.write("</div>\n");
      out.write("<div style=\"clear:both;\"></div>\n");
      out.write("</div>\n");
      out.write("</div>                \n");
      out.write("                \n");
      out.write("                \n");
      out.write("                \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    <div class=\"clearfix\"> </div>\n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("     \n");
      out.write("     </div>\n");
      out.write("     </div>\n");
      out.write("   </div>\n");
      out.write("   <div class=\"footer\">\n");
      out.write("    <div class=\"container\">\n");
      out.write("    <div class=\"footer_top\">\n");
      out.write("      <h3>Subscribe to our newsletter</h3>\n");
      out.write("           <form method=\"post\" action='Subscription.jsp'>\n");
      out.write("<span>\n");
      out.write("<i><img src=\"images/mail.png\" alt=\"\"></i>\n");
      out.write("   <input type=\"text\" value=\"Enter your email\" onfocus=\"this.value = '';\" onblur=\"if (this.value == '') {this.value = 'Enter your email';}\">\n");
      out.write("   <label class=\"btn1 btn2 btn-2 btn-2g\"> <input name=\"submit\" type=\"submit\" id=\"submit\" value=\"Subscribe\"> </label>\n");
      out.write("   <div class=\"clearfix\"> </div>\n");
      out.write("</span>    \n");
      out.write("  </form>\n");
      out.write(" </div>\n");
      out.write("      </div>\n");
      out.write("   </div>\n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("<!--script>\n");
      out.write("$(document).ready(\n");
      out.write("$('#srch-max').click(function(){\n");
      out.write("       $('.srch-box').css({\n");
      out.write("       'width':\"auto\", \n");
      out.write("       'height':\"auto\",\n");
      out.write("       'min-height':\"50px\", \n");
      out.write("       'background':\"transparent\",\n");
      out.write("       'margin':\"0px\",\n");
      out.write("       'box-shadow':\"0px 0px rgba(0,0,0,0)\",\n");
      out.write("       'border':\"0px\"\n");
      out.write("       });\n");
      out.write("       \n");
      out.write("       $('.kwd-set').css({\n");
      out.write("       'float':\"right\", \n");
      out.write("       'clear':\"both\",\n");
      out.write("       'margin':\"10px\",\n");
      out.write("       'width':\"auto\",\n");
      out.write("       'height':\"auto\",\n");
      out.write("       });\n");
      out.write("       \n");
      out.write("       $('.srch-box h3').css({\n");
      out.write("       'display':\"none\" \n");
      out.write("       });\n");
      out.write("       \n");
      out.write("       $('.srch-results').css({\n");
      out.write("       'display':\"block\",\n");
      out.write("       'opacity':\"1\",\n");
      out.write("       'height':\"1000px\"\n");
      out.write("       });\n");
      out.write("       \n");
      out.write("}) \n");
      out.write(") \n");
      out.write("</script-->\n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("</body>\n");
      out.write("</html> \n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
