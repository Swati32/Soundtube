package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.sql.*;
import java.text.*;
import java.lang.*;

public final class Dashboard_jsp extends org.apache.jasper.runtime.HttpJspBase
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
     


      out.write("\n");
      out.write("<!DOCTYPE HTML>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<title>Dashboard</title>\n");
      out.write("<link href=\"css/bootstrap.css\" rel='stylesheet' type='text/css' />\n");
      out.write("<link href=\"css/style.css\" rel='stylesheet' type='text/css' />\n");
      out.write("<!-- Custom Theme files -->\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("<script type=\"application/x-javascript\"> addEventListener(\"load\", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>\n");
      out.write("<!--webfont-->\n");
      out.write("<link href='//fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>\n");
      out.write("<script type=\"text/javascript\" src=\"js/jquery-1.11.1.min.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"js/login.js\"></script>\n");
      out.write("<script src=\"js/jquery.easydropdown.js\"></script>\n");
      out.write("<!--Animation-->\n");
      out.write("<script src=\"js/wow.min.js\"></script>\n");
      out.write("<link href=\"css/animate.css\" rel='stylesheet' type='text/css' />\n");
      out.write("<script>\n");
      out.write("\tnew WOW().init();\n");
      out.write("</script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div class=\"header\">\n");
      out.write("\t\t   <div class=\"col-sm-8 header-left\">\n");
      out.write("\t\t\t\t\t <div class=\"logo\">\n");
      out.write("\t\t\t\t\t\t<a href=\"print.jsp\"><img src=\"images/logo.png\" style=\"height:auto; width:auto;\"alt=\"\"/></a>\n");
      out.write("\t\t\t\t\t </div>\n");
      out.write("\t\t\t\t\t <div class=\"menu\">\n");
      out.write("\t\t\t\t\t\t  <a class=\"toggleMenu\" href=\"#\"><img src=\"images/nav.png\" alt=\"\" /></a>\n");
      out.write("\t\t\t\t\t\t    <ul class=\"nav\" id=\"nav\">\n");
      out.write("                                                        \n");
      out.write("\t\t\t\t\t\t    \t<li class=\"active\"><a href=\"Dashboard.jsp\">Dashboard</a></li>\n");
      out.write("                                                        ");
 if(session.getAttribute("uid")!=null)
                                                        {
      out.write("\n");
      out.write("\t\t\t\t\t\t      \n");
      out.write("\t\t\t\t\t\t    \t<li><a href=\"playlist.jsp\">Playlists</a></li>\n");
      out.write("\t\t\t\t\t\t    \t<li><a href=\"favorite.jsp\">Favorites</a></li>\n");
      out.write("\t\t\t\t\t\t    \t<li><a href=\"request.html\">Requests</a></li>\n");
      out.write("\t\t\t\t\t\t    \t\n");
      out.write("                                                        ");
}
						    	
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"clearfix\"></div>\n");
      out.write("\t\t\t\t\t\t\t</ul>\n");
      out.write("\t\t\t\t\t\t\t<script type=\"text/javascript\" src=\"js/responsive-nav.js\"></script>\n");
      out.write("\t\t\t\t    </div>\t\n");
      out.write("\t\t\t\t     <!-- start search-->\n");
      out.write("\t\t\t\t    \n");
      out.write("\t\t\t\t\t\t<!----search-scripts---->\n");
      out.write("\t\t\t\t\t\t<script src=\"js/classie.js\"></script>\n");
      out.write("\t\t\t\t\t\t<script src=\"js/uisearch.js\"></script>\n");
      out.write("\t\t\t\t\t\t<script>\n");
      out.write("\t\t\t\t\t\t\tnew UISearch( document.getElementById( 'sb-search' ) );\n");
      out.write("\t\t\t\t\t\t</script>\n");
      out.write("\t\t\t\t\t\t<!----//search-scripts---->\t\t\t\t\t\t\n");
      out.write("\t    \t\t    <div class=\"clearfix\"></div>\n");
      out.write("\t    \t    </div>\n");
      out.write("    \n");
      out.write("\t            <div class=\"col-sm-4 header_right\">\n");
      out.write("       ");
 
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
                                  
      out.write("\n");
      out.write("\t    \t\t      \n");
      out.write("\t\t                 <div class=\"clearfix\"></div>\n");
      out.write("\t                 </div>\n");
      out.write("\t                <div class=\"clearfix\"></div>\n");
      out.write("   </div>\n");
      out.write("                                  \n");
      out.write("   <div class=\"banner\">\n");
      out.write("       <div class=\"container_wrap\" style=\"margin-top: 1%; margin-left: 25%;\">\n");
      out.write("   \t\t<h1>Looking for Something?</h1>\n");
      out.write("                `<form style=\"text-align: center\" method=\"post\" action='SearchResult.jsp'>\n");
      out.write("   \t       <div class=\"dropdown-buttons\">   \n");
      out.write("            \t\t  \n");
      out.write("\t\t\t\t     <div class=\"dropdown-button\">\n");
      out.write("\t\t\t\t\t   <select>\n");
      out.write("\t\t\t\t\t\t\t<option selected=\"\" tabindex=\"9\" style=\"display:none;color:#eee;\" name=\"SearchBy\">Search By</option>\n");
      out.write("                                                        <option value=\"Title\">Title</option>\n");
      out.write("                                                        <option value=\"Artist\">Artist</option>\n");
      out.write("                                                        <option value=\"Album\">Album</option>\n");
      out.write("\t\t\t\t\t          </select>\n");
      out.write("\t\t\t\t\t </div>\n");
      out.write("\t\t\t\t   </div>  \n");
      out.write("                \n");
      out.write("\t\t\t\t<input type=\"text\" value=\"Keyword, name, date, ...\" onfocus=\"this.value = '';\" onblur=\"if (this.value == '') {this.value = 'Keyword, name, date, ...';}\" name='Keyword'>\n");
      out.write("\t\t\t    <div class=\"contact_btn\">\n");
      out.write("\t               <label class=\"btn1 btn-2 btn-2g\"><input name=\"submit\" type=\"submit\" id=\"submit\" value=\"Search\"></label>\n");
      out.write("\t            </div>\n");
      out.write("\t\t\t</form>        \t\t\n");
      out.write("   \t\t    <div class=\"clearfix\"></div>\n");
      out.write("         </div>\n");
      out.write("   </div>\n");
      out.write("   <div class=\"content_top\">\n");
      out.write("   \t  <div class=\"container\">\n");
      out.write("   \t\t<div class=\"col-md-4 bottom_nav\">\n");
      out.write("   \t\t   <div class=\"content_menu\" style=\"width:700px; color: red;\">\n");
      out.write("\t\t\t\t<ul>\n");
      out.write("                                    \n");
      out.write("                                    ");

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
                                       
                                        
      out.write("\n");
      out.write("                                     \n");
      out.write("                                        \n");
      out.write("                                        \n");
      out.write("\t\t\t\t</ul>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t\n");
      out.write("\t\t\n");
      out.write("   \t</div>\n");
      out.write("   </div>\n");
      out.write("   <div class=\"content_middle\">\n");
      out.write("   \t  <div class=\"container\">\n");
      out.write("   \t    <div class=\"content_middle_box\">\n");
      out.write("          \n");
      out.write("              \n");
      out.write("   \t\t\t<div class=\"top_grid wow fadeInRight\" data-wow-delay=\"0.4s\">\n");
      out.write("   \t\t\t<div class=\"clearfix\">\n");
      out.write("   \t\t\t   \t\t   \t\n");
      out.write("   \t\t\t\n");
      out.write("   \t\t\t</div>\n");
      out.write("   \t\t\t</div>\n");
      out.write("   \t\t\n");
      out.write("                \n");
      out.write("                \n");
      out.write("                \n");
      out.write("                \n");
      out.write("                \n");
      out.write("                <div class=\"top_grid wow fadeInRight\" data-wow-delay=\"0.4s\" style=\"margin-top:50px;\">\n");
      out.write("   \t\t\t<div class=\"col-md-3\">\n");
      out.write("   \t\t\t  <div class=\"grid1\">\n");
      out.write("   \t\t\t\t<div class=\"view view-first\">\n");
      out.write("                  <div class=\"index_img\"><img src=");
out.println("\""+thumb[0]+"\"");
      out.write(" class='img-responsive' style=\"height:200px; width: 260px;\" alt=\"\"/></div>\n");
      out.write("\n");
      out.write("   \t\t\t          <div class=\"mask\">\n");
      out.write("                                      <div class=\"info\"><i class=\"search\"> </i><a href='");
 out.println("SongProfile.jsp?id="+SID[0]);
      out.write("'>Play</a></div>\n");
      out.write("                        <ul class=\"mask_img\">\n");
      out.write("                        \t<li class=\"star\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                        \t<li class=\"set\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                        \t<div class=\"clearfix\"> </div>\n");
      out.write("                        </ul>\n");
      out.write("                        </div>\n");
      out.write("                    </div> \n");
      out.write("                    <i class=\"home1\"></i>\n");
      out.write("   \t\t\t\t <div class=\"inner_wrap\">\n");
      out.write("   \t\t\t\t \t<h3>");
out.println(name[0]);
      out.write("</h3>\n");
      out.write("   \t\t\t\t \t<ul class=\"star1\">\n");
      out.write("                                            <h4 class='green'>");
 out.println(artist_name[0]); 
      out.write("</h4>\n");
      out.write("   \t\t\t\t \t  <li><a href=\"#\"> <img src=\"images/star1.png\" alt=\"\">1</a></li>\n");
      out.write("   \t\t\t\t \t</ul>\n");
      out.write("   \t\t\t\t </div>\n");
      out.write("   \t\t\t   </div>\n");
      out.write("   \t\t\t</div>\n");
      out.write("                                          \n");
      out.write("   \t\t\t<div class=\"col-md-3\">\n");
      out.write("   \t\t\t  <div class=\"grid1\">\n");
      out.write("   \t\t\t\t<div class=\"view view-first\">\n");
      out.write("                  <div class=\"index_img1\"><img src=");
out.println("\""+thumb[1]+"\"");
      out.write(" class=\"img-responsive\" style=\"height:200px; width: 260px;\" alt=\"\"/></div>\n");
      out.write("   \t\t\t\t     <div class=\"mask\">\n");
      out.write("                        <div class=\"info\"><i class=\"search\"> </i><a href='");
 out.println("SongProfile.jsp?id="+SID[1]);
      out.write("'>Play</a></div>\n");
      out.write("                        <ul class=\"mask_img\">\n");
      out.write("                        \t<li class=\"star\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                        \t<li class=\"set\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                        \t<div class=\"clearfix\"> </div>\n");
      out.write("                        </ul>\n");
      out.write("                      </div>\n");
      out.write("                  </div> \n");
      out.write("                 <i class=\"home1\"> </i>\n");
      out.write("   \t\t\t\t <div class=\"inner_wrap\">\n");
      out.write("   \t\t\t\t \t<h3>");
out.println(name[1]);
      out.write("</h3>\n");
      out.write("   \t\t\t\t \t<ul class=\"star1\">\n");
      out.write("   \t\t\t\t \t  <h4 class=\"green\">");
 out.println(artist_name[1]); 
      out.write("</h4>\n");
      out.write("   \t\t\t\t \t  <li><a href=\"#\"> <img src=\"images/star2.png\" alt=\"\">2</a></li>\n");
      out.write("   \t\t\t\t \t</ul>\n");
      out.write("   \t\t\t\t </div>\n");
      out.write("   \t\t\t   </div>\n");
      out.write("   \t\t\t</div>\n");
      out.write("                    \n");
      out.write("                    \n");
      out.write("                    \n");
      out.write("   \t\t\t<div class=\"col-md-3\">\n");
      out.write("   \t\t\t  <div class=\"grid1\">\n");
      out.write("   \t\t\t\t<div class=\"view view-first\">\n");
      out.write("                  <div class=\"index_img2\"><img src=");
out.println("\""+thumb[2]+"\"");
      out.write(" class=\"img-responsive\" style=\"height:200px; width: 260px;\" alt=\"\"/></div>\n");
      out.write("   \t\t\t\t     <div class=\"mask\">\n");
      out.write("                        <div class=\"info\"><i class=\"search\"> </i><a href='");
 out.println("SongProfile.jsp?id="+SID[2]);
      out.write("'>Play</a></div>\n");
      out.write("                        <ul class=\"mask_img\">\n");
      out.write("                        \t<li class=\"star\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                        \t<li class=\"set\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                        \t<div class=\"clearfix\"> </div>\n");
      out.write("                        </ul>\n");
      out.write("                      </div>\n");
      out.write("                  </div> \n");
      out.write("                 <i class=\"home1\"> </i>\n");
      out.write("   \t\t\t\t <div class=\"inner_wrap\">\n");
      out.write("   \t\t\t\t \t<h3>");
out.println(name[2]);
      out.write("</h3>\n");
      out.write("   \t\t\t\t \t<ul class=\"star1\">\n");
      out.write("   \t\t\t\t \t  <h4 class=\"blue\">");
 out.println(artist_name[2]); 
      out.write("</h4>\n");
      out.write("   \t\t\t\t \t  <li><a href=\"#\"> <img src=\"images/star2.png\" alt=\"\">3</a></li>\n");
      out.write("   \t\t\t\t \t</ul>\n");
      out.write("   \t\t\t\t </div>\n");
      out.write("   \t\t\t   </div>\n");
      out.write("   \t\t\t</div>\n");
      out.write("   \t\t\t<div class=\"col-md-3\">\n");
      out.write("   \t\t\t  <div class=\"grid1\">\n");
      out.write("   \t\t\t\t<div class=\"view view-first\">\n");
      out.write("                  <div class=\"index_img\"><img src=");
out.println("\""+thumb[3]+"\"");
      out.write(" class=\"img-responsive\" style=\"height:200px; width: 260px;\" alt=\"\"/></div>\n");
      out.write("\n");
      out.write("   \t\t\t          <div class=\"mask\">\n");
      out.write("                      <div class=\"info\"><i class=\"search\"> </i><a href='");
 out.println("SongProfile.jsp?id="+SID[3]);
      out.write("'>Play</a></div>\n");
      out.write("                        <ul class=\"mask_img\">\n");
      out.write("                        \t<li class=\"star\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                        \t<li class=\"set\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                        \t<div class=\"clearfix\"> </div>\n");
      out.write("                        </ul>\n");
      out.write("                      </div>\n");
      out.write("                  </div> \n");
      out.write("                  <i class=\"home1\"></i>\n");
      out.write("   \t\t\t\t  <div class=\"inner_wrap\">\n");
      out.write("   \t\t\t\t \t<h3>");
out.println(name[3]);
      out.write("</h3>\n");
      out.write("   \t\t\t\t \t<ul class=\"star1\">\n");
      out.write("   \t\t\t\t \t  <h4 class=\"green\">");
 out.println(artist_name[3]); 
      out.write("</h4>\n");
      out.write("   \t\t\t\t \t  <li><a href=\"#\"> <img src=\"images/star1.png\" alt=\"\">4</a></li>\n");
      out.write("   \t\t\t\t \t</ul>\n");
      out.write("   \t\t\t\t  </div>\n");
      out.write("   \t\t\t   </div>\n");
      out.write("   \t\t\t</div>\n");
      out.write("   \t\t\t<div class=\"clearfix\"> </div>\n");
      out.write("   \t\t</div>\n");
      out.write("                \n");
      out.write("                \n");
      out.write("                \n");
      out.write("                \n");
      out.write("                \n");
      out.write("                \n");
      out.write("                \n");
      out.write("                \n");
      out.write("                \n");
      out.write("                \n");
      out.write("   \t\t<div class=\"top_grid wow fadeInRight\" data-wow-delay=\"0.4s\" style=\"margin-top:50px;\">\n");
      out.write("   \t\t\t<div class=\"col-md-3\">\n");
      out.write("   \t\t\t  <div class=\"grid1\">\n");
      out.write("   \t\t\t\t<div class=\"view view-first\">\n");
      out.write("                  <div class=\"index_img\"><img src=");
out.println("\""+thumb[4]+"\"");
      out.write(" class=\"img-responsive\" style=\"height:200px; width: 260px;\" alt=\"\"/></div>\n");
      out.write("\n");
      out.write("   \t\t\t          <div class=\"mask\">\n");
      out.write("                       <div class=\"info\"><i class=\"search\"> </i><a href='");
 out.println("SongProfile.jsp?id="+SID[4]);
      out.write("'>Play</a></div>\n");
      out.write("                        <ul class=\"mask_img\">\n");
      out.write("                        \t<li class=\"star\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                        \t<li class=\"set\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                        \t<div class=\"clearfix\"> </div>\n");
      out.write("                        </ul>\n");
      out.write("                        </div>\n");
      out.write("                    </div> \n");
      out.write("                    <i class=\"home1\"></i>\n");
      out.write("   \t\t\t\t <div class=\"inner_wrap\">\n");
      out.write("   \t\t\t\t \t<h3>");
out.println(name[4]);
      out.write("</h3>\n");
      out.write("   \t\t\t\t \t<ul class=\"star1\">\n");
      out.write("   \t\t\t\t \t  <h4 class=\"green\">");
 out.println(artist_name[4]); 
      out.write("</h4>\n");
      out.write("   \t\t\t\t \t  <li><a href=\"#\"> <img src=\"images/star1.png\" alt=\"\">5</a></li>\n");
      out.write("   \t\t\t\t \t</ul>\n");
      out.write("   \t\t\t\t </div>\n");
      out.write("   \t\t\t   </div>\n");
      out.write("   \t\t\t</div>\n");
      out.write("   \t\t\t<div class=\"col-md-3\">\n");
      out.write("   \t\t\t  <div class=\"grid1\">\n");
      out.write("   \t\t\t\t<div class=\"view view-first\">\n");
      out.write("                  <div class=\"index_img1\"><img src=");
out.println("\""+thumb[5]+"\"");
      out.write(" class=\"img-responsive\" style=\"height:200px; width: 260px;\" alt=\"\"/></div>\n");
      out.write("   \t\t\t\t     <div class=\"mask\">\n");
      out.write("                        <div class=\"info\"><i class=\"search\"> </i><a href='");
 out.println("SongProfile.jsp?id="+SID[5]);
      out.write("'>Play</a></div>\n");
      out.write("                        <ul class=\"mask_img\">\n");
      out.write("                        \t<li class=\"star\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                        \t<li class=\"set\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                        \t<div class=\"clearfix\"> </div>\n");
      out.write("                        </ul>\n");
      out.write("                      </div>\n");
      out.write("                  </div> \n");
      out.write("                 <i class=\"home1\"> </i>\n");
      out.write("   \t\t\t\t <div class=\"inner_wrap\">\n");
      out.write("   \t\t\t\t \t<h3>");
out.println(name[5]);
      out.write("</h3>\n");
      out.write("   \t\t\t\t \t<ul class=\"star1\">\n");
      out.write("   \t\t\t\t \t  <h4 class=\"green\">");
 out.println(artist_name[5]); 
      out.write("</h4>\n");
      out.write("   \t\t\t\t \t  <li><a href=\"#\"> <img src=\"images/star2.png\" alt=\"\">6</a></li>\n");
      out.write("   \t\t\t\t \t</ul>\n");
      out.write("   \t\t\t\t </div>\n");
      out.write("   \t\t\t   </div>\n");
      out.write("   \t\t\t</div>\n");
      out.write("   \t\t\t<div class=\"col-md-3\">\n");
      out.write("   \t\t\t  <div class=\"grid1\">\n");
      out.write("   \t\t\t\t<div class=\"view view-first\">\n");
      out.write("                  <div class=\"index_img2\"><img src=");
out.println("\""+thumb[6]+"\"");
      out.write(" class=\"img-responsive\" style=\"height:200px; width: 260px;\" alt=\"\"/></div>\n");
      out.write("   \t\t\t\t     <div class=\"mask\">\n");
      out.write("                        <div class=\"info\"><i class=\"search\"> </i><a href='");
 out.println("SongProfile.jsp?id="+SID[6]);
      out.write("'>Play</a></div>\n");
      out.write("                        <ul class=\"mask_img\">\n");
      out.write("                        \t<li class=\"star\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                        \t<li class=\"set\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                        \t<div class=\"clearfix\"> </div>\n");
      out.write("                        </ul>\n");
      out.write("                      </div>\n");
      out.write("                  </div> \n");
      out.write("                 <i class=\"home1\"> </i>\n");
      out.write("   \t\t\t\t <div class=\"inner_wrap\">\n");
      out.write("   \t\t\t\t \t<h3>");
out.println(name[6]);
      out.write("</h3>\n");
      out.write("   \t\t\t\t \t<ul class=\"star1\">\n");
      out.write("   \t\t\t\t \t  <h4 class=\"blue\">");
 out.println(artist_name[6]); 
      out.write("</h4>\n");
      out.write("   \t\t\t\t \t  <li><a href=\"#\"> <img src=\"images/star2.png\" alt=\"\">7</a></li>\n");
      out.write("   \t\t\t\t \t</ul>\n");
      out.write("   \t\t\t\t </div>\n");
      out.write("   \t\t\t   </div>\n");
      out.write("   \t\t\t</div>\n");
      out.write("   \t\t\t<div class=\"col-md-3\">\n");
      out.write("   \t\t\t  <div class=\"grid1\">\n");
      out.write("   \t\t\t\t<div class=\"view view-first\">\n");
      out.write("                  <div class=\"index_img\"><img src=");
out.println("\""+thumb[7]+"\"");
      out.write(" class=\"img-responsive\" style=\"height:200px; width: 260px;\" alt=\"\"/></div>\n");
      out.write("\n");
      out.write("   \t\t\t          <div class=\"mask\">\n");
      out.write("                      <div class=\"info\"><i class=\"search\"> </i><a href='");
 out.println("SongProfile.jsp?id="+SID[7]);
      out.write("'>Play</a></div>\n");
      out.write("                        <ul class=\"mask_img\">\n");
      out.write("                        \t<li class=\"star\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                        \t<li class=\"set\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                        \t<div class=\"clearfix\"> </div>\n");
      out.write("                        </ul>\n");
      out.write("                      </div>\n");
      out.write("                  </div> \n");
      out.write("                  <i class=\"home1\"></i>\n");
      out.write("   \t\t\t\t  <div class=\"inner_wrap\">\n");
      out.write("   \t\t\t\t \t<h3>");
out.println(name[7]);
      out.write("</h3>\n");
      out.write("   \t\t\t\t \t<ul class=\"star1\">\n");
      out.write("   \t\t\t\t \t  <h4 class=\"green\">");
 out.println(artist_name[7]); 
      out.write("</h4>\n");
      out.write("   \t\t\t\t \t  <li><a href=\"#\"> <img src=\"images/star1.png\" alt=\"\">8</a></li>\n");
      out.write("   \t\t\t\t \t</ul>\n");
      out.write("   \t\t\t\t  </div>\n");
      out.write("   \t\t\t   </div>\n");
      out.write("   \t\t\t</div>\n");
      out.write("   \t\t\t<div class=\"clearfix\"> </div>\n");
      out.write("   \t\t</div>\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t<div class=\"top_grid wow fadeInRight\" data-wow-delay=\"0.4s\" style=\"margin-top:50px;\">\n");
      out.write("   \t\t\t<div class=\"col-md-3\">\n");
      out.write("   \t\t\t  <div class=\"grid1\">\n");
      out.write("   \t\t\t\t<div class=\"view view-first\">\n");
      out.write("                  <div class=\"index_img\"><img src=");
out.println("\""+thumb[8]+"\"");
      out.write(" class=\"img-responsive\" style=\"height:200px; width: 260px;\" alt=\"\"/></div>\n");
      out.write("\n");
      out.write("   \t\t\t          <div class=\"mask\">\n");
      out.write("                       <div class=\"info\"><i class=\"search\"> </i><a href='");
 out.println("SongProfile.jsp?id="+SID[8]);
      out.write("'>Play</a></div>\n");
      out.write("                        <ul class=\"mask_img\">\n");
      out.write("                        \t<li class=\"star\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                        \t<li class=\"set\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                        \t<div class=\"clearfix\"> </div>\n");
      out.write("                        </ul>\n");
      out.write("                        </div>\n");
      out.write("                    </div> \n");
      out.write("                    <i class=\"home1\"></i>\n");
      out.write("   \t\t\t\t <div class=\"inner_wrap\">\n");
      out.write("   \t\t\t\t \t<h3>");
out.println(name[8]);
      out.write("</h3>\n");
      out.write("   \t\t\t\t \t<ul class=\"star1\">\n");
      out.write("   \t\t\t\t \t  <h4 class=\"green\">");
 out.println(artist_name[8]); 
      out.write("</h4>\n");
      out.write("   \t\t\t\t \t  <li><a href=\"#\"> <img src=\"images/star1.png\" alt=\"\">9</a></li>\n");
      out.write("   \t\t\t\t \t</ul>\n");
      out.write("   \t\t\t\t </div>\n");
      out.write("   \t\t\t   </div>\n");
      out.write("   \t\t\t</div>\n");
      out.write("   \t\t\t<div class=\"col-md-3\">\n");
      out.write("   \t\t\t  <div class=\"grid1\">\n");
      out.write("   \t\t\t\t<div class=\"view view-first\">\n");
      out.write("                  <div class=\"index_img1\"><img src=");
out.println("\""+thumb[9]+"\"");
      out.write(" class=\"img-responsive\" style=\"height:200px; width: 260px;\" alt=\"\"/></div>\n");
      out.write("   \t\t\t\t     <div class=\"mask\">\n");
      out.write("                        <div class=\"info\"><i class=\"search\"> </i><a href='");
 out.println("SongProfile.jsp?id="+SID[9]);
      out.write("'>Play</a></div>\n");
      out.write("                        <ul class=\"mask_img\">\n");
      out.write("                        \t<li class=\"star\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                        \t<li class=\"set\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                        \t<div class=\"clearfix\"> </div>\n");
      out.write("                        </ul>\n");
      out.write("                      </div>\n");
      out.write("                  </div> \n");
      out.write("                 <i class=\"home1\"> </i>\n");
      out.write("   \t\t\t\t <div class=\"inner_wrap\">\n");
      out.write("   \t\t\t\t \t<h3>");
out.println(name[9]);
      out.write("</h3>\n");
      out.write("   \t\t\t\t \t<ul class=\"star1\">\n");
      out.write("   \t\t\t\t \t  <h4 class=\"green\">");
 out.println(artist_name[9]); 
      out.write("</h4>\n");
      out.write("   \t\t\t\t \t  <li><a href=\"#\"> <img src=\"images/star2.png\" alt=\"\">10</a></li>\n");
      out.write("   \t\t\t\t \t</ul>\n");
      out.write("   \t\t\t\t </div>\n");
      out.write("   \t\t\t   </div>\n");
      out.write("   \t\t\t</div>\n");
      out.write("   \t\t\t<div class=\"col-md-3\">\n");
      out.write("   \t\t\t  <div class=\"grid1\">\n");
      out.write("   \t\t\t\t<div class=\"view view-first\">\n");
      out.write("                                    <div class=\"index_img2\"><img src=");
out.println("\""+thumb[10]+"\"");
      out.write(" class=\"img-responsive\" style=\"height:200px; width: 260px;\" alt=\"\"/></div>\n");
      out.write("   \t\t\t\t     <div class=\"mask\">\n");
      out.write("                        <div class=\"info\"><i class=\"search\"> </i><a href='");
 out.println("SongProfile.jsp?id="+SID[10]);
      out.write("'>Play</a></div>\n");
      out.write("                        <ul class=\"mask_img\">\n");
      out.write("                        \t<li class=\"star\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                        \t<li class=\"set\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                        \t<div class=\"clearfix\"> </div>\n");
      out.write("                        </ul>\n");
      out.write("                      </div>\n");
      out.write("                  </div> \n");
      out.write("                 <i class=\"home1\"> </i>\n");
      out.write("   \t\t\t\t <div class=\"inner_wrap\">\n");
      out.write("   \t\t\t\t \t<h3>");
out.println(name[10]);
      out.write("</h3>\n");
      out.write("   \t\t\t\t \t<ul class=\"star1\">\n");
      out.write("   \t\t\t\t \t  <h4 class=\"blue\">");
 out.println(artist_name[10]); 
      out.write("</h4>\n");
      out.write("   \t\t\t\t \t  <li><a href=\"#\"> <img src=\"images/star2.png\" alt=\"\">11</a></li>\n");
      out.write("   \t\t\t\t \t</ul>\n");
      out.write("   \t\t\t\t </div>\n");
      out.write("   \t\t\t   </div>\n");
      out.write("   \t\t\t</div>\n");
      out.write("   \t\t\t<div class=\"col-md-3\">\n");
      out.write("   \t\t\t  <div class=\"grid1\">\n");
      out.write("   \t\t\t\t<div class=\"view view-first\">\n");
      out.write("                  <div class=\"index_img\"><img src=");
out.println("\""+thumb[11]+"\"");
      out.write(" class=\"img-responsive\" style=\"height:200px; width: 260px;\" alt=\"\"/></div>\n");
      out.write("\n");
      out.write("   \t\t\t          <div class=\"mask\">\n");
      out.write("                      <div class=\"info\"><i class=\"search\"> </i><a href='");
 out.println("SongProfile.jsp?id="+SID[11]);
      out.write("'>Play</a></div>\n");
      out.write("                        <ul class=\"mask_img\">\n");
      out.write("                        \t<li class=\"star\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                        \t<li class=\"set\"><img src=\"\" alt=\"\"/></li>\n");
      out.write("                        \t<div class=\"clearfix\"> </div>\n");
      out.write("                        </ul>\n");
      out.write("                      </div>\n");
      out.write("                  </div> \n");
      out.write("                  <i class=\"home1\"></i>\n");
      out.write("   \t\t\t\t  <div class=\"inner_wrap\">\n");
      out.write("   \t\t\t\t \t<h3>");
out.println(name[11]);
      out.write("</h3>\n");
      out.write("   \t\t\t\t \t<ul class=\"star1\">\n");
      out.write("   \t\t\t\t \t  <h4 class=\"green\">");
 out.println(artist_name[10]); 
      out.write("</h4>\n");
      out.write("   \t\t\t\t \t  <li><a href=\"#\"> <img src=\"images/star1.png\" alt=\"\">12</a></li>\n");
      out.write("   \t\t\t\t \t</ul>\n");
      out.write("   \t\t\t\t  </div>\n");
      out.write("   \t\t\t   </div>\n");
      out.write("   \t\t\t</div>\n");
      out.write("   \t\t\t<div class=\"clearfix\"> </div>\n");
      out.write("   \t\t</div>\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t");
connection.close();
      out.write("\n");
      out.write("   \t\t\n");
      out.write("   \t\t <div class=\"clearfix\"> </div>\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t\n");
      out.write("   \t\t  \n");
      out.write("   \t\t  </div>\n");
      out.write("   \t  </div>\n");
      out.write("   </div>\n");
      out.write("\n");
      out.write("</body>\n");
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
