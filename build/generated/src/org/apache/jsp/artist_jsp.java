package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.sql.*;
import java.text.*;
import java.lang.*;

public final class artist_jsp extends org.apache.jasper.runtime.HttpJspBase
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
  String keyword = (String) request.getParameter("id");
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


      out.write(" \n");
      out.write("\n");
      out.write("<!DOCTYPE HTML>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>playlist</title>\n");
      out.write("        <link href=\"http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("        <link href=\"css/bootstrap.css\" rel='stylesheet' type='text/css' />\n");
      out.write("        <link href=\"css/style.css\" rel='stylesheet' type='text/css' />\n");
      out.write("        <link href=\"css/style_1.css\" rel='stylesheet' type='text/css' />\n");
      out.write("        <link href=\"css/swati.css\" rel='stylesheet' type='text/css' />\n");
      out.write("\n");
      out.write("        <!-- Custom Theme files -->\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("\n");
      out.write("        <script src=\"js/jquery.js\"></script>\n");
      out.write("        <script src=\"js/tabs.js\"></script>\n");
      out.write("\n");
      out.write("        <script type=\"text/javascript\" src=\"js/login.js\"></script>\n");
      out.write("        <script src=\"js/jquery.easydropdown.js\"></script>\n");
      out.write("        <!--Animation-->\n");
      out.write("        <script src=\"js/wow.min.js\"></script>\n");
      out.write("        <link href=\"css/animate.css\" rel='stylesheet' type='text/css' />\n");
      out.write("        <script>\n");
      out.write("            new WOW().init();\n");
      out.write("        </script>\n");
      out.write("        <!--star-->\n");
      out.write("        <link href=\"css/star-rating.css\" media=\"all\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <script src=\"js/star-rating.js\" type=\"text/javascript\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"header\">\n");
      out.write("            <div class=\"col-sm-8 header-left\">\n");
      out.write("                <div class=\"logo\">\n");
      out.write("                    <a href=\"index.html\"><img src=\"images/logo.png\" style=\"height:auto; width:auto;\" alt=\"\"/></a>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"menu\">\n");
      out.write("                    <a class=\"toggleMenu\" href=\"#\"><img src=\"images/nav.png\" alt=\"\" /></a>\n");
      out.write("                    <ul class=\"nav\" id=\"nav\">\n");
      out.write("                        \n");
      out.write("                     \t<li><a href=\"Dashboard.jsp\">Dashboard</a></li>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t    \t<li class=\"active\"><a href=\"playlist.jsp\">Playlists</a></li>\n");
      out.write("\t\t\t\t\t\t    \t<li><a href=\"favorite.jsp\">Favorites</a></li>\n");
      out.write("\t\t\t\t\t\t    \t<li><a href=\"request.html\">Requests</a></li>\n");
      out.write("\t\t\t\t\t\t    \t<li><a href=\"setting.html\">Settings</a></li>\n");
      out.write("\n");
      out.write("                        <div class=\"clearfix\"></div>\n");
      out.write("                    </ul>\n");
      out.write("                </div>\t\t\t\t\t\t\t\t\n");
      out.write("                <div class=\"clearfix\"></div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-sm-4 header_right\">\n");
      out.write("\t    \t\t       ");
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
                                + "<span><a href='Reg.html'>register</a></span>"
                                + "<span><a href='changePsd.jsp'>Forgot your password?</a></span>"
                                
                                + "</form>"
                                + "</div>"
                                + "</div>");
                    }
                
      out.write("\n");
      out.write("\t\t                 <div class=\"clearfix\"></div>\n");
      out.write("\t                 </div>\n");
      out.write("            <div class=\"clearfix\"></div>\n");
      out.write("        </div>\n");
      out.write("         <div class=\"banner1\" style = \"width:auto; margin-bottom : 0\">\n");
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
      out.write("\n");
      out.write("        <div class=\"content_middle\">\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <div class=\"content_middle_box\">\n");
      out.write("\n");
      out.write("                    <div class=\"Album-bot\">\n");
      out.write("                        <div class=\"Album-bot-left\">\n");
      out.write("                            <div class=\"Album-img\">\n");
      out.write("                                <img src=\"");
out.println(thumb[0]);
      out.write("\" alt=\"Album-img\" />\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"Album-info\">\n");
      out.write("                                <h1>");
out.println(artistname);
      out.write("</h1>\n");
      out.write("                                <p>Years Active :");
out.println(yearsactive);
      out.write(" </p>\n");
      out.write("                                <p>Genre :");
out.println(genre);
      out.write("</p>\n");
      out.write("                                <a href =\"https://");
out.println(website);
      out.write("\"></a>\n");
      out.write("                                <p>Website :");
out.println(website);
      out.write("</p>\n");
      out.write("                                <br/>\n");
      out.write("\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"Album-bot-right\">\n");
      out.write("                            <div class=\"Album-bot-right-Tracks-top\">\n");
      out.write("                                <p class=\"Album-bot-right-Tracks-top-logo\">Tracks</p>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"Album-bot-right-Tracks_ss\">\n");
      out.write("\n");
      out.write("                                <div class=\"middle_grid wow fadeInUp\" data-wow-delay=\"0.4s\">\n");
      out.write("                                    ");
for (int j = 0; j < i; j++) {
      out.write("\n");
      out.write("                                    <div class=\"col-md-6\">\n");
      out.write("                                        <div class=\"grid1\">\n");
      out.write("                                               \n");
      out.write("                                                 <div class=\"view view-first\">\n");
      out.write("                                                  \n");
      out.write("                                    <div class=\"index_imgss\"><img src=");
out.println("\"" + thumb[j] + "\"");
      out.write(" class=\"img-responsive\"  alt=\"\"/></div>\n");
      out.write("                                   \n");
      out.write("                                    <div class=\"mask\">\n");
      out.write("                                        <div class=\"info\"><i class=\"search\"> </i>  \n");
      out.write("                                            <a href ='");
 out.println("SongProfile.jsp?id="+songid[j]); 
      out.write("'>Play</a></div>\n");
      out.write("                                        <ul class=\"mask_img\">\n");
      out.write("                                            <li class=\"star\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                                            <li class=\"set\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                                            <div class=\"clearfix\"> </div>\n");
      out.write("                                        </ul>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                                  \n");
      out.write("                                            <div class=\"clearfix\"> </div>\n");
      out.write("                                            <i class=\"home1\"> </i>\n");
      out.write("                                            <ul class=\"vision\">\n");
      out.write("                                                <li>");
out.println(songname[j]);
      out.write("</li>\n");
      out.write("\n");
      out.write("                                            </ul>\n");
      out.write("                                            <div class=\"inner_wrap1\">\n");
      out.write("                                                <ul class=\"item_module\">\n");
      out.write("                                                    <li class=\"module_left\"><img src=\"images/m1.jpg\" class=\"img-responsive\" alt=\"\"/></li>\n");
      out.write("                                                    <li class=\"module_right\"><h5>");
out.println(artist_name[j]);
      out.write("</h5></li>\n");
      out.write("                                                    <div class='post-desc star_filed'>\n");
      out.write("                                                        <form>\n");
      out.write("                                                            <input id=\"input-21e\" value=\"0\" type=\"number\" class=\"rating\" min=0 max=5 step=0.5 data-size=\"xs\" >\n");
      out.write("                                                        </form>\n");
      out.write("                                                        <script>\n");
      out.write("            jQuery(document).ready(function () {\n");
      out.write("                $(\".rating-kv\").rating();\n");
      out.write("            });\n");
      out.write("                                                        </script>\n");
      out.write("                                                    </div>  \n");
      out.write("                                                    <div class=\"clearfix\"> </div>\n");
      out.write("                                                </ul>\n");
      out.write("                                            </div></br>\n");
      out.write("                                                    <div class=\"clearfix\"></div>\n");
      out.write("                                        </div>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"clearfix\"></div>\n");
      out.write("                                    ");
}
      out.write("\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"clearfix\"> </div>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"clearfix\"> </div>\n");
      out.write("    </body>\n");
      out.write("</html>\t");
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
