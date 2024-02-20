package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import pojos.Book;

/**
 * Servlet implementation class BooksServlet
 */
@WebServlet("/books")
public class BooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processReuest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processReuest(request, response);
	}
	protected void processReuest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String subject=request.getParameter("subject");
		
		List<Book>list=new ArrayList<>();
		
		try(BookDao dao = new BookDao()){
			list=dao.getBooks(subject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Subject_name</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form method='post' action='addcart'>");
		
		for(Book b:list)
		{
			out.printf("<input type='checkbox' name='book' value='%d'/>%s"  +"<a href='Detailsd?id=%d&subject=%s'>  Details  </a><br/>\r\n",b.getBook_id(),b.getBook_name(),b.getBook_id(),subject);
		}
		
		out.println("<input type='submit' value='Addcart'/>");
		out.println("<a href='AddCart'><h1>Addcarts</h1></a>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}
	
	

}
