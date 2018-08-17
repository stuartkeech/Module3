package claims;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Controller")
public class Controller extends HttpServlet {	
	private Database db=new Database();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession s=request.getSession();
		s.setAttribute("managerPower",1000000);
		String url=request.getHeader("referer");
		String ref=url.substring(url.lastIndexOf("/")+1,url.lastIndexOf(".jsp"));
		/*if(request.getParameter("submit").equals("claims")) {
			db.createConnection();
			s.setAttribute("claims",db.getClaims(null,(int)s.getAttribute("managerPower")));
			db.destroyConnection();
			response.sendRedirect("PolicyClosing.jsp");
		}else if(request.getParameter("submit").equals("policies")) {
			db.createConnection();
			s.setAttribute("policies",db.getPolicies(null));
			db.destroyConnection();
			response.sendRedirect("PolicyReview.jsp");
		}*/
		if(ref.equals("RegularClosing")) {
			if(request.getParameter("info").equals("Pending")) {
				db.createConnection();
				s.setAttribute("claims",db.getClaims(null,(int)s.getAttribute("managerPower")));
				db.destroyConnection();
				response.sendRedirect("PolicyClosing.jsp");
			} else if(request.getParameter("info").equals("Approved")) {
				db.createConnection();
				s.setAttribute("claims",db.getClaims(1,(int)s.getAttribute("managerPower")));
				db.destroyConnection();
				response.sendRedirect("PolicyClosing.jsp");
			} else if(request.getParameter("info").equals("Rejected")){
				db.createConnection();
				s.setAttribute("claims",db.getClaims(0,(int)s.getAttribute("managerPower")));
				db.destroyConnection();
				response.sendRedirect("PolicyClosing.jsp");
			}
		}else if(ref.equals("IntermitentClosing")) {
			if(request.getParameter("info").equals("Pending")) {
				db.createConnection();
				s.setAttribute("claims",db.getClaims(null,(int)s.getAttribute("managerPower")));
				db.destroyConnection();
				response.sendRedirect("PolicyClosing.jsp");
			} else if(request.getParameter("info").equals("Approved")) {
				db.createConnection();
				s.setAttribute("claims",db.getClaims(1,(int)s.getAttribute("managerPower")));
				db.destroyConnection();
				response.sendRedirect("PolicyClosing.jsp");
			} else if(request.getParameter("info").equals("Rejected")){
				db.createConnection();
				s.setAttribute("claims",db.getClaims(0,(int)s.getAttribute("managerPower")));
				db.destroyConnection();
				response.sendRedirect("PolicyClosing.jsp");
			}
		}else if(ref.equals("PolicyAproval")) {
			if(request.getParameter("info").equals("Pending")) {
				db.createConnection();
				s.setAttribute("policies",db.getPolicies(null));
				db.destroyConnection();
				response.sendRedirect("PolicyReview.jsp");
			} else if(request.getParameter("info").equals("Approved")) {
				db.createConnection();
				s.setAttribute("policies",db.getPolicies(1));
				db.destroyConnection();
				response.sendRedirect("PolicyReview.jsp");
			} else if(request.getParameter("info").equals("Rejected")){
				db.createConnection();
				s.setAttribute("policies",db.getPolicies(0));
				db.destroyConnection();
				response.sendRedirect("PolicyReview.jsp");
			}
		}
	}
}
