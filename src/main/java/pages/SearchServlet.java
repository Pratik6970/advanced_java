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
 * Servlet implementation class SearchServlet
 */
@WebServlet("/searching")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
		
	}
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String id=request.getParameter("bookid");
		int ids=Integer.parseInt(id);
		
		Book b=null;
		
		try(BookDao dao= new BookDao())
		{
			b=dao.getBook(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1 align='center'>Welcome To Serach Book Page</h1></hr>");
		out.println("<html>");
		out.println("<head>");
		
		out.println("<title>BookShop Result</title>");
		
		out.println("</head>");
		out.println("<body>");
		if(b==null)
		{
			out.println("<h1>Sorry Book is not found</h1>");
			out.println("<a href='loginer.html'><h1>Please go to Login Page!!!!!</h1></a>");
			
		}
		else
		{
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Find Book</title>");
			out.println("<body>");
			out.println("<table>");
		
			out.print("<h1>Bookid :"+b.getBook_id()+"</h1></br>");
			out.print("<h1>Subject Name :"+b.getSubject_name()+"</h1></br>");
			out.print("<h1>BookName:"+b.getBook_name()+"</h1></br>");
			out.print("<h1>Author_name :"+b.getAuthor_name()+"</h1></br>");
			out.print("<h1>BookPrice :"+b.getPrice()+"</h1></br>");
			
			out.print("<h1>Thanks!!!!!</h1>");
			out.println("</table>");
			out.println("</body>");
			out.println("</head>");
			out.println("</html>");
			
		
			
			
		}
		out.println("<a href='subjectd'><h4>Welcome To Subject</h1></a>");
		out.println("</body>");
		out.println("</html>");
		
	}
    

}
