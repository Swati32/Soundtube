package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import javax.sql.*;
import java.text.*;
import java.util.*;

public final class request_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html; charset=ISO-8859-1");
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
      out.write("\n");
      out.write("\n");
      out.write("\n");

String remail=request.getParameter("email");
String rtitle=request.getParameter("title");
String rinformation=request.getParameter("information");
Calendar calendar = Calendar.getInstance();
java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl","anjali","250792lt26i");
            out.println(connection);
            
            
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


      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE HTML>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<title>Requests</title>\n");
      out.write("<link href=\"css/bootstrap.css\" rel='stylesheet' type='text/css' />\n");
      out.write("<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->\n");
      out.write("<!-- Custom Theme files -->\n");
      out.write("<link href=\"css/style.css\" rel='stylesheet' type='text/css' />\n");
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
      out.write("\t\t\t\t\t\t    \t\n");
      out.write("\t\t\t\t\t\t    \t<li><a href=\"Dashboard.jsp\">Dashboard</a></li>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t    \t<li><a href=\"playlist.jsp\">Playlists</a></li>\n");
      out.write("\t\t\t\t\t\t    \t<li><a href=\"favorite.jsp\">Favorites</a></li>\n");
      out.write("\t\t\t\t\t\t    \t<li><a href=\"request.html\">Requests</a></li>\n");
      out.write("\t\t\t\t\t\t    \t<li class=\"active\"><a href=\"setting.html\">Settings</a></li>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t\t</ul>\n");
      out.write("\t\t\t\t    </div>\t\t\t\t\t\t\t\t\n");
      out.write("\t    \t\t    <div class=\"clearfix\"></div>\n");
      out.write("\t    \t    </div>\n");
      out.write("\t            <div class=\"col-sm-4 header_right\">\n");
      out.write("\t    \t\t     <div id=\"loginContainer\"><a href=\"Dashboard.jsp\"><img src=\"images/login.png\"><span>Back to Home</span></a>\n");
      out.write("\t\t\t\t\t\t  \n");
      out.write("\t\t\t             </div>\n");
      out.write("\t\t                 <div class=\"clearfix\"></div>\n");
      out.write("\t                 </div>\n");
      out.write("\t                <div class=\"clearfix\"></div>\n");
      out.write("   </div>\n");
      out.write("   <div class=\"album_banner\">\n");
      out.write("   \t  \n");
      out.write("   </div>\n");
      out.write("   <div class=\"content_top\">\n");
      out.write("   \t  <div class=\"container main\">\n");
      out.write("   \t\t   \n");
      out.write("\t     <div class=\"request-middle\">\n");
      out.write("\t       <p class=\"request-middle-logo\">Request Your Song</p>\n");
      out.write("\t     </div>\n");
      out.write("\t\t <div class=\"request-bottom\">\n");
      out.write("\t\t    <p>Your Request has been submitted!</p>\n");
      out.write("\t\t </div>\n");
      out.write("\t\t </div>\n");
      out.write("\t  \n");
      out.write("  </div>\n");
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
