package pages;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddCart
 */
@WebServlet("/addcart")
public class AddCart extends HttpServlet {
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
		
		String[]ids=request.getParameterValues("book");
		HttpSession session = request.getSession();
		List<Integer>cart=(List<Integer>)session.getAttribute("cart");
		for (int i = 0; i < ids.length; i++) {
			int id=Integer.parseInt(ids[i]);
			System.out.println("Book added in cart "+id);
			if(!cart.contains(id))
			cart.add(id);
			
		}
		String message="<h1>"+"New" +ids.length+ "books added in the cart"+"</h1>";
		request.setAttribute("message", message);
		RequestDispatcher rd=request.getRequestDispatcher("subjectd");
		rd.forward(request, response);
	}


}
