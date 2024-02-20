package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import pojos.Book;

/**
 * Servlet implementation class DetailsServlet
 */
@WebServlet("/Detailsd")
public class DetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 processRequest(request, response);
	}
	
	
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Subject = request.getParameter("subject");
		int id=Integer.parseInt(request.getParameter("id"));
		Book book=null;
		//Business Logic
		try(BookDao dao = new BookDao())
		{
			dao.getBook(id);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Presentation Logic
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Details of book</title>");
		out.println("</head>");
		out.println("<body>");
		if(book==null)
		
			out.println("Book is not found");
	
		else {
		    out.println("subject_name" + book.getSubject_name() + "<br/>");
		    out.println("Book_name" + book.getBook_name() + "<br/>");
		    out.println("Author_name" + book.getAuthor_name() + "<br/>");
		    out.println("Price " + book.getPrice() + "<br/>");
		}
			
		
		
		out.println("</body>");
		out.println("</html>");
		
		
	}

}






