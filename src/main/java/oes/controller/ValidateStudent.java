package oes.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oes.db.Students;
import oes.model.StudentsDao;
//importing all packages

/**
 * Servlet implementation class ValidateStudent
 */
@WebServlet({ "/ValidateStudent", "/oes.controller.ValidateStudent" })
public class ValidateStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String username = request.getParameter("uname");
		String password = request.getParameter("pass");
		Students sd = new Students();
		sd.setUsername(username);
		sd.setPassword(password);
		//
		boolean status = StudentsDao.doValidate(sd);
		if(status) {
			//Provides a way to identify a user across more than one pagerequest 
			HttpSession studentSession = request.getSession();
			studentSession.setAttribute("username", sd.getUsername());
			studentSession.setAttribute("name", sd.getName());
			response.sendRedirect("StudentInstructions.jsp");
		}else {
			String msg = "Invalid username or password";
			response.sendRedirect("StudentLogin.jsp?msg="+msg);
		}//
	}

}
