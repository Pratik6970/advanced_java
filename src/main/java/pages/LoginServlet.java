package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDaoImpl;
import pojos.User;

/**
 * Servlet implementation class LoginServlet
 * @param <cookie>
 */
@WebServlet("/logsd")
public class LoginServlet<cookie> extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDaoImpl userDao;
	//User cust=null;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

    public void init() throws ServletException {
		try {
			userDao = new UserDaoImpl();
		
			//tutdao=new TutorialDaoImpl();

		} catch (Exception e) {
			// To inform WC that init() has failed : re throw the exception , wrapped in
			// ServletException
			// ServletException(String errMesg,Throwable e)
			throw new ServletException("err in init of " + getClass(), e);
		}

	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			userDao.cleanUp();
			//topicDao.cleanUp();
			//tutdao.cleanUp();
		} catch (SQLException e) {
			// how to tell WC that destroy method failed ?
			throw new RuntimeException("err in destroy of " + getClass().getName(), e);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");	
		
		try(PrintWriter pw = response.getWriter()){
			String email=request.getParameter("em");
			String pwd=request.getParameter("pass");
			
			
			User user = userDao.validateUser(email, pwd);
			// chk null => invalid login --send retry link
			if(user==null)
			{
				pw.println("<h1>Sorry Invalid User Detail!!!</h1></br>");
				pw.println("<a href='loginer.html'><h1>Sorry Go To Login Again!!!</h1></a>");
			}
			else
			{
				//10 step
				Cookie c=new Cookie("uname",user.getName());
				c.setMaxAge(3600);
				response.addCookie(c);
				//11 step
				HttpSession session = request.getSession();
				session.setAttribute("cid", user.getId());
				session.setAttribute("cart", new ArrayList<Integer>());
				//response.sendRedirect("subjectd");
				
				pw.println("<h1>Welcome To BookShop!!!!!</h1></br>");
				
				pw.println("<h1>Login Successful!!!!"+user+"</h1>");
				
				pw.print("<a href='Search.html'><h1>Register successfully</h1></a>");
			}
			
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


}
}



