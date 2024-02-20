package sunbeam.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class securitsFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Security.init() filter is called!!!");
	}
	
	
	@Override
	public void destroy() {
		System.out.println("Security.destroy()is called!!!!");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)resp;
		String curPath=request.getServletPath();
	
		
		System.out.println("Security.dofilter is called!!!!"+curPath);
		
		List<String>paths=new ArrayList<String>();
		Collections.addAll(paths,"/subjectd","/showcart","/books","/addcart","/logout");
		
		if(paths.contains(curPath))
		{
			HttpSession session=request.getSession();
			Integer cid=(Integer)session.getAttribute("cid");
			
			if(cid == null)
			
				response.sendRedirect("loginer.html");
				
				else
					chain.doFilter(req, resp);
		}			

		else
			chain.doFilter(req, resp);
		
	}

}
				
			
			
		
		
		
		
		

