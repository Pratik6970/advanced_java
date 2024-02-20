package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDao;
import pojos.Book;

/**
 * Servlet implementation class ShowCartServlet
 */
@WebServlet("/showcart")
public class ShowCartServlet extends HttpServlet {
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
		// TODO Auto-generated method stub
		
		//Business logic
		
		HttpSession session = request.getSession();
		List<Integer>cart=(List<Integer>)session.getAttribute("cart");
		
		
		//Presntation logic
		//out.println("<h1>Show Books</h1>");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<h1 align='center'>Show Books</h1></hr>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title><h1>Show Books</h1></title>");
		out.println("</head>");
		out.println("<body>");
		
		 ServletContext ctx=this.getServletContext();
		 String greet=ctx.getInitParameter("greet");
		Cookie []arr=request.getCookies();	
		if(arr!=null) {
		for (Cookie c : arr) {
			if(c.getName().equals("uname"))
			{
				String name=c.getValue();
				//out.printf("<h1>Hello, %s<hr/></h1>","<h1>"+name+"</h1>");
				out.printf("<h1 align='center'>%s, %s<hr/></h1>",greet, name);

				break;
			}
		}
		}
		
		double total = 0.0;
		out.println("<table border='1' align='center'>");
		out.println("<thead>");
		out.println("<tr>");
		out.println("<th>Id</th>");
		out.println("<th>Subject_name</th>");
		out.println("<th>Book_name</th>");
		out.println("<th>Author_name</th>");
		out.println("<th>Price</th>");
		out.println("</tr>");
		out.println("</thead>");
		out.println("<tbody>");
		//11 display all the books from the cart
		try(BookDao dao=new BookDao()){
			for (Integer id : cart ) {
				Book b = dao.getBook(id);
				total=total+b.getPrice();
				out.println("<tr>");
				out.printf("<td>%d</td>\r\n",b.getBook_id());
				out.printf("<td>%s</td>\r\n",b.getSubject_name());
				out.printf("<td>%s</td>\r\n",b.getBook_name());
				out.printf("<td>%s</td>\r\n",b.getAuthor_name());
				out.printf("<th>%.2f</th>",b.getPrice());
				out.println("</tr>");
				//out.println(b.toString() +"<br/>" );
			
		}
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		out.println("</tbody>");
		out.println("</table>");
		out.printf("<div align='center'><h1>Total bill: RS %.2f/-</h1></div>\r\n", total);

		//out.printf("<div class='text-center'>Total bill: RS %.2f/-</div>\r\n",total);
		//out.println("<h1>Cart is empty</h1>");
		out.println("<div align = 'center'><a href='logout'><h1>Sign out!!!</h1></a></div>");

		//out.println("<a href='logout'><h1>Sign out!!!</h1></a>");
		out.println("</body>");
		out.println("</html>");
		
	}

}
