package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.sql.*;
import java.text.*;
import java.lang.*;

public final class playlist_jsp extends org.apache.jasper.runtime.HttpJspBase
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
   String playlist_name[] = new String[100];
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

      out.write(" \n");
      out.write("\n");
      out.write("<!DOCTYPE HTML>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<title>Duhoot Bootstarp Website Template | Home :: w3layouts</title>\n");
      out.write("<link href=\"css/bootstrap.css\" rel='stylesheet' type='text/css' />\n");
      out.write("<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->\n");
      out.write("<!-- Custom Theme files -->\n");
      out.write("<link href=\"css/style.css\" rel='stylesheet' type='text/css' />\n");
      out.write(" <link href=\"css/swati.css\" rel='stylesheet' type='text/css' />\n");
      out.write("<!-- Custom Theme files -->\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("<script type=\"application/x-javascript\"> addEventListener(\"load\", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>\n");
      out.write("<!--webfont-->\n");
      out.write("<script type=\"text/javascript\" src=\"js/jquery-1.11.1.min.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"js/login.js\"></script>\n");
      out.write("<script src=\"js/jquery.easydropdown.js\"></script>\n");
      out.write("<!--Animation-->\n");
      out.write("<script src=\"js/wow.min.js\"></script>\n");
      out.write("<script>\n");
      out.write("\tnew WOW().init();\n");
      out.write("</script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div class=\"header\">\n");
      out.write("\t\t   <div class=\"col-sm-8 header-left\">\n");
      out.write("\t\t\t\t\t <div class=\"logo\">\n");
      out.write("\t\t\t\t\t\t<a href=\"index.html\"><img src=\"images/logo.png\" style=\"height:auto; width:auto;\" alt=\"logo\"/></a>\n");
      out.write("\t\t\t\t\t </div>\n");
      out.write("\t\t\t\t\t <div class=\"menu\">\n");
      out.write("\t\t\t\t\t\t  <a class=\"toggleMenu\" href=\"#\"><img src=\"images/nav.png\" alt=\"\" /></a>\n");
      out.write("\t\t\t\t\t\t    <ul class=\"nav\" id=\"nav\">\n");
      out.write("\t\t\t\t\t\t    \t<li><a href=\"Dashboard.jsp\">Dashboard</a></li>\n");
      out.write("\t\t\t\t\t\t    \t<li class=\"active\"><a href=\"playlist.jsp\">Playlists</a></li>\n");
      out.write("\t\t\t\t\t\t    \t<li><a href=\"favorite.jsp\">Favorites</a></li>\n");
      out.write("\t\t\t\t\t\t    \t<li><a href=\"request.html\">Requests</a></li>\n");
      out.write("\t\t\t\t\t\t    \t<li><a href=\"setting.html\">Settings</a></li>\n");
      out.write("\t\t\t\t\t\t\t</ul>\n");
      out.write("\t\t\t\t    </div>\t\t\t\t\t\t\t\t\n");
      out.write("\t    \t\t    <div class=\"clearfix\"></div>\n");
      out.write("\t    \t    </div>\n");
      out.write("\t            <div class=\"col-sm-4 header_right\">\n");
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
      out.write("\t                <div class=\"clearfix\"></div>\n");
      out.write("   </div>\n");
      out.write(" \n");
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
      out.write("   <div class=\"content_top\">\n");
      out.write("   \t  <div class=\"container\">\n");
      out.write("   \t\t<div class=\"col-md-8 wow fadeInRight playlist-window\" data-wow-delay=\"0.4s\" >\n");
      out.write("                   \n");
      out.write("                    \n");
      out.write("          <div class=\"educate_grid\">\n");
      out.write("               <div class=\"createplaylist_button\"><h3><a href=\"createplaylist.jsp\">create playlist</a></h3></div>                 \n");
      out.write("                ");
for (int j = 0; j < i; j++) { 
      out.write("    \n");
      out.write(" ");
if(playlist_name[j] !=null){
      out.write("\n");
      out.write("            <div class=\"col-md-6\">\n");
      out.write("   \t    \t  <div class=\"living_box\">\n");
      out.write("\t\t\t   <a href=\"subplaylist.jsp?playlistid=");
out.println(playlist_id[j]);
      out.write("\"><img style=\"width:367px\" src=");
out.println("\"" + thumb[j] + "\"");
      out.write("  class=\"img-responsive\" alt=\"\"/></a>\n");
      out.write("\t\t\t\t<div class=\"living_desc desc1 playlist-function\">\n");
      out.write("      \n");
      out.write("\t\t\t\t<h3 class=\"playlist-function-song\"><a href=\"subplaylist.jsp?playlistid=");
out.println(playlist_id[j]);
      out.write('"');
      out.write('>');
out.println(playlist_name[j]);
      out.write("</a></h3>\n");
      out.write("                                <h6 class=\"playlist-function-add\"><a href=\"deleteplaylist.jsp?playlistid=");
out.println(playlist_id[j]);
      out.write("\">delete</a></h3>\n");
      out.write("                                <h6 class=\"playlist-function-delete\"><a href=\"renameplaylist.jsp?playlistid=");
out.println(playlist_id[j]);
      out.write("\">rename</a></h3>\n");
      out.write("                                \n");
      out.write("\t\t\t\t<div class=\"clearfix\"></div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t  </div>\n");
      out.write("\t\t    </div>\n");
      out.write("                                 ");
}else{ 
      out.write("\n");
      out.write("                      \n");
      out.write("                    <div style=\"text-align: center\"><img src=\"images/NoVideosFound.png\" width=\"100\" /></div>\n");
      out.write("                       \n");
      out.write("                      \n");
      out.write("                    ");
}
      out.write("\n");
      out.write("\t\t");
}
      out.write("\n");
      out.write("                \n");
      out.write("\t\t    <div class=\"clearfix\"></div>\n");
      out.write("\t\t   </div>\n");
      out.write("                \n");
      out.write("\t\t   <div class=\"educate_grid1\">\n");
      out.write("           \n");
      out.write("\t\t    \n");
      out.write("\t\t    <div class=\"clearfix\"></div>\n");
      out.write("\t\t   </div>\n");
      out.write("\t\t </div>\n");
      out.write("   \t  </div>\t  \n");
      out.write("   </div>\n");
      out.write("   \n");
      out.write("   <div class=\"content_middle\">\n");
      out.write("   \t  <div class=\"container\">\n");
      out.write("   \t    <div class=\"content_middle_box\">\n");
      out.write("\t\t\n");
      out.write("          \n");
      out.write("   \t\t   \n");
      out.write("   \t\t  \n");
      out.write("   \t\t  </div>\n");
      out.write("   \t  </div>\n");
      out.write("   </div>\n");
      out.write("\n");
      out.write("</body>\n");
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
