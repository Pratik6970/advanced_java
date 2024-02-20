package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;

/**
 * Servlet implementation class SubjectdServlet
 */
@WebServlet("/subjectd")
public class SubjectdServlet extends HttpServlet {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<String>list=new ArrayList<>();
		
		try(BookDao dao=new BookDao()){
			list=dao.getSubject_name();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1 align ='center>Welcome To Subject Page</h1></hr>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Subject_name</title>");
		out.println("</head>");
		out.println("<body>");
		
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
		
		
		out.println("<form method='post' action='books'>");
		out.println("<table align='center'>");
		for(String subjectstring:list)
		{
			out.printf("<input type='radio' name='subject' value='%s'/>%s<br/>\r\n",subjectstring,subjectstring);
		}
		out.println("</table>");
		//response.sendRedirect("books");
		out.println("<input type='submit' value='Show Books'/>");
		//response.sendRedirect("books");
		out.println("<a href='showcart'><h1>Show Cart</h1></a>");
		out.println("</form>");
		
		String message=(String)request.getAttribute("message");
		if(message!=null)
		{
			out.println(message+"<br/>");
		}
		out.println("</body>");
		out.println("</html>");
	}
	
	

}
