package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.sql.*;
import java.text.*;
import java.lang.*;

public final class SearchResult_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write('\n');
  
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

                out.println(SQL);
                
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


      out.write(" \n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("\n");
      out.write("        <title>Search Result | Living :: w3layouts</title>\n");
      out.write("        <link href=\"css/bootstrap.css\" rel='stylesheet' type='text/css' />\n");
      out.write("        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->\n");
      out.write("        <!-- Custom Theme files -->\n");
      out.write("        <link href=\"css/style.css\" rel='stylesheet' type='text/css' />\n");
      out.write("        <link href=\"css/swati.css\" rel='stylesheet' type='text/css' />\n");
      out.write("        <!-- Custom Theme files -->\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("        <script type=\"application/x-javascript\"> addEventListener(\"load\", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>\n");
      out.write("        <!--webfont-->\n");
      out.write("        <link href='//fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>\n");
      out.write("        <script type=\"text/javascript\" src=\"js/jquery-1.11.1.min.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"js/login.js\"></script>\n");
      out.write("        <script src=\"js/jquery.easydropdown.js\"></script>\n");
      out.write("        <script src=\"js/wow.min.js\"></script>\n");
      out.write("        <link href=\"css/animate.css\" rel='stylesheet' type='text/css' />\n");
      out.write("        <script>\n");
      out.write("            new WOW().init();\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        <div class=\"header\">\n");
      out.write("            <div class=\"col-sm-8 header-left\">\n");
      out.write("                <div class=\"logo\">\n");
      out.write("                    <a href=\"index.html\"><img src=\"images/logo.png\" style=\"height:auto; width:auto;\" alt=\"\"/></a>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"menu\">\n");
      out.write("                    <a class=\"toggleMenu\" href=\"#\"><img src=\"images/nav.png\" alt=\"\" /></a>\n");
      out.write("                    <ul class=\"nav\" id=\"nav\">\n");
      out.write("                         \t<li><a href=\"Dashboard.jsp\">Dashboard</a></li>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t    \t<li><a href=\"playlist.jsp\">Playlists</a></li>\n");
      out.write("\t\t\t\t\t\t    \t<li><a href=\"favorite.jsp\">Favorites</a></li>\n");
      out.write("\t\t\t\t\t\t    \t<li><a href=\"request.html\">Requests</a></li>\n");
      out.write("\t\t\t\t\t\t    \t<li><a href=\"setting.html\">Settings</a></li>\n");
      out.write("\n");
      out.write("                    </ul>\n");
      out.write("                    <script type=\"text/javascript\" src=\"js/responsive-nav.js\"></script>\n");
      out.write("                </div>\t\n");
      out.write("\n");
      out.write("                <div class=\"clearfix\"></div>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            \n");
      out.write("            \n");
      out.write("            \n");
      out.write("            \n");
      out.write("            <div class=\"col-sm-4 header_right\">\n");
      out.write("                ");
                           String s = null;
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
                
      out.write("\n");
      out.write("                <div class=\"clearfix\"></div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"clearfix\"></div>\n");
      out.write("        </div>\n");
      out.write("                \n");
      out.write("                \n");
      out.write("                \n");
      out.write("                \n");
      out.write("                \n");
      out.write("        <div class=\"banner1\" style = \"width:auto; margin-bottom : 0\">\n");
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
      out.write("                                        <option selected tabindex=\"9\" style=\"display:list-item;color:#e94c38;\">Title</option>\t\n");
      out.write("                                        <option>Album</option>\n");
      out.write("                                        <option>Artist </option>\n");
      out.write("                                    </select>\n");
      out.write("                                    <select name = \"SortBy\">\n");
      out.write("                                        <option selected tabindex=\"9\" style=\"display:list-item;color:#e94c38;\">Rating</option>\t\n");
      out.write("                                        <option>Popularity</option>\n");
      out.write("                                        <option>Newest</option>\n");
      out.write("                                    </select>\n");
      out.write("                                </span>\n");
      out.write("                           </form> </li> \n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("                \n");
      out.write("                \n");
      out.write("                \n");
      out.write("\n");
      out.write("\n");
      out.write("        <div class=\"living_middle\">\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <h2 class=\"title block-title1\">Search Results</h2>\n");
      out.write("                ");
if (name[0] != null) { 
      out.write(" \n");
      out.write("                ");
for (int j = 0; j < i; j++) { 
      out.write("    \n");
      out.write("\n");
      out.write("                <div class=\"content_middle_box\">\n");
      out.write("                    <div class=\"top_grid wow fadeInRight\" data-wow-delay=\"0.4s\">\n");
      out.write("                        <div class=\"clearfix\">\n");
      out.write("\n");
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div class=\"top_grid wow fadeInRight\" data-wow-delay=\"0.4s\" style=\"margin-top:50px;\">\n");
      out.write("                        ");
  
                            if (name[j] != null){
      out.write("\n");
      out.write("                         <div class=\"col-md-3\">\n");
      out.write("                            <div class=\"grid1\">\n");
      out.write("                                <div class=\"view view-first\">\n");
      out.write("                                    <div class=\"index_img1\"><img src=");
out.println("\"" + thumb[j] + "\"");
      out.write(" class=\"img-responsive\" style=\"height:200px; width: 260px;\" alt=\"\"/></div>\n");
      out.write("                                    <div class=\"mask\">\n");
      out.write("                                         <div class=\"info\"><i class=\"search\"> </i><a href='");
 out.println("SongProfile.jsp?id="+songid[j]);
      out.write("'>Play</a></div>\n");
      out.write("                                        <ul class=\"mask_img\">\n");
      out.write("                                            <li class=\"star\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                                            <li class=\"set\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                                            <div class=\"clearfix\"> </div>\n");
      out.write("                                        </ul>\n");
      out.write("                                    </div>\n");
      out.write("                                </div> \n");
      out.write("                                <i class=\"home1\"> </i>\n");
      out.write("                                <div class=\"inner_wrap\">\n");
      out.write("                                    <h3>");
out.println(name[j]);
      out.write("</h3>\n");
      out.write("                                    <ul class=\"star1\">\n");
      out.write("                                        <h4 class=\"green1\">");
 out.println(artist_name[j]); 
      out.write("</h4>\n");
      out.write("                                        <li><a href=\"#\"> <img src=\"images/star2.png\" alt=\"\">2</a></li>\n");
      out.write("                                    </ul>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        ");
}
      out.write("               \n");
      out.write("                        ");
j++;
      out.write("\n");
      out.write("                        ");
if (name[j] != null){
      out.write("\n");
      out.write("                        <div class=\"col-md-3\">\n");
      out.write("                            <div class=\"grid1\">\n");
      out.write("                                <div class=\"view view-first\">\n");
      out.write("                                    <div class=\"index_img1\"><img src=");
out.println("\"" + thumb[j] + "\"");
      out.write(" class=\"img-responsive\" style=\"height:200px; width: 260px;\" alt=\"\"/></div>\n");
      out.write("                                    <div class=\"mask\">\n");
      out.write("                                          <div class=\"info\"><i class=\"search\"> </i><a href='");
 out.println("SongProfile.jsp?id="+songid[j]);
      out.write("'>Play</a></div>\n");
      out.write("                                        <ul class=\"mask_img\">\n");
      out.write("                                            <li class=\"star\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                                            <li class=\"set\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                                            <div class=\"clearfix\"> </div>\n");
      out.write("                                        </ul>\n");
      out.write("                                    </div>\n");
      out.write("                                </div> \n");
      out.write("                                <i class=\"home1\"> </i>\n");
      out.write("                                <div class=\"inner_wrap\">\n");
      out.write("                                    <h3>");
out.println(name[j]);
      out.write("</h3>\n");
      out.write("                                    <ul class=\"star1\">\n");
      out.write("                                        <h4 class=\"green1\">");
 out.println(artist_name[j]); 
      out.write("</h4>\n");
      out.write("                                        <li><a href=\"#\"> <img src=\"images/star2.png\" alt=\"\">2</a></li>\n");
      out.write("                                    </ul>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        ");
}
      out.write("                \n");
      out.write("                        ");
j++;
      out.write("\n");
      out.write("\n");
      out.write("                        ");
if (name[j] != null){
      out.write("\n");
      out.write("                        <div class=\"col-md-3\">\n");
      out.write("                            <div class=\"grid1\">\n");
      out.write("                                <div class=\"view view-first\">\n");
      out.write("                                    <div class=\"index_img2\"><img src=");
out.println("\"" + thumb[j] + "\"");
      out.write(" class=\"img-responsive\" style=\"height:200px; width: 260px;\" alt=\"\"/></div>\n");
      out.write("                                    <div class=\"mask\">\n");
      out.write("                                        <div class=\"info\"><i class=\"search\"> </i><a href='");
 out.println("SongProfile.jsp?id="+songid[j]);
      out.write("'>Play</a></div>\n");
      out.write("                                        <ul class=\"mask_img\">\n");
      out.write("                                            <li class=\"star\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                                            <li class=\"set\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                                            <div class=\"clearfix\"> </div>\n");
      out.write("                                        </ul>\n");
      out.write("                                    </div>\n");
      out.write("                                </div> \n");
      out.write("                                <i class=\"home1\"> </i>\n");
      out.write("                                <div class=\"inner_wrap\">\n");
      out.write("                                    <h3>");
out.println(name[j]);
      out.write("</h3>\n");
      out.write("                                    <ul class=\"star1\">\n");
      out.write("                                        <h4 class=\"blue1\">");
 out.println(artist_name[j]); 
      out.write("</h4>\n");
      out.write("                                        <li><a href=\"#\"> <img src=\"images/star2.png\" alt=\"\">3</a></li>\n");
      out.write("                                    </ul>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        ");
}
      out.write("                \n");
      out.write("                        ");
j++;
      out.write("\n");
      out.write("                        ");
if (name[j] != null){
      out.write("\n");
      out.write("                        <div class=\"col-md-3\">\n");
      out.write("                            <div class=\"grid1\">\n");
      out.write("                                <div class=\"view view-first\">\n");
      out.write("                                    <div class=\"index_img\"><img src=");
out.println("\"" + thumb[j] + "\"");
      out.write(" class=\"img-responsive\" style=\"height:200px; width: 260px;\" alt=\"\"/></div>\n");
      out.write("\n");
      out.write("                                    <div class=\"mask\">\n");
      out.write("                                        <div class=\"info\"><i class=\"search\"> </i><a href='");
 out.println("SongProfile.jsp?id="+songid[j]);
      out.write("'>Play</a></div>\n");
      out.write("                                        <ul class=\"mask_img\">\n");
      out.write("                                            <li class=\"star\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                                            <li class=\"set\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                                            <div class=\"clearfix\"> </div>\n");
      out.write("                                        </ul>\n");
      out.write("                                    </div>\n");
      out.write("                                </div> \n");
      out.write("                                <i class=\"home1\"></i>\n");
      out.write("                                <div class=\"inner_wrap\">\n");
      out.write("                                    <h3>");
out.println(name[j]);
      out.write("</h3>\n");
      out.write("                                    <ul class=\"star1\">\n");
      out.write("                                        <h4 class=\"green1\">");
 out.println(artist_name[j]); 
      out.write("</h4>\n");
      out.write("                                        <li><a href=\"#\"> <img src=\"images/star1.png\" alt=\"\">4</a></li>\n");
      out.write("                                    </ul>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        ");
}
      out.write("                \n");
      out.write("                                   \n");
      out.write("                        <div class=\"clearfix\"> </div>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    ");
}
                    } else { 
      out.write("\n");
      out.write("\n");
      out.write("                    <div style=\"text-align: center\"><img src=\"images/NoVideosFound.png\" width=\"100\" /></div>\n");
      out.write("\n");
      out.write("\n");
      out.write("                    ");
}
      out.write("\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\t\t");
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
