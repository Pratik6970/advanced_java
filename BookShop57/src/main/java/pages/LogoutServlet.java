package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void dopost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<h1 align ='center'>Logout page</h1>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title><h1>Thanks!!!</h1></title>");
		out.println("</head>");
		out.println("<body>");
		
		//context-param used
		
		ServletContext ctx=this.getServletContext();
		String greet = ctx.getInitParameter("greet");
		Cookie []arr=request.getCookies();
		if(arr!=null) {
		for (Cookie c : arr) {
			if(c.getName().equals("uname"))
			{
				String name=c.getValue();
				//out.printf("Hello, %s<hr/>\r\n",name);
				out.printf("<h1 align='center'>%s, %s<hr/></h1>",greet, name);

				break;
			}
		}
		}
		out.println("<h1 align ='center'>Thanks to visit our bookshop application!!!!</h1>");
		out.println("\"<div align = 'center'><a href='loginer.html'><h1>Login Again!!!</h1></a></div>");
		out.println("</body>");
		out.println("</html>");
	
	}

}
