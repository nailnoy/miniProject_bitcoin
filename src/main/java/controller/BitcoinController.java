package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class BitcoinController
 */
@WebServlet("/cointracker")
public class BitcoinController extends HttpServlet {
	
	private String url = "showError.jsp";
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String command = request.getParameter("command");
		try{
			if(command.equals("login")){
				login(request, response);
			}else if(command.equals("chart")) {
				chart(request, response);
			}
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			request.getRequestDispatcher("showError.jsp").forward(request, response);
			s.printStackTrace();
		}
	}

	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			response.setContentType("text/html;charset=utf-8");
			
			String email = request.getParameter("email");
			String psw = request.getParameter("psw");
			String myEmail = "omomo010@gmail.com";
			String myPsw = "1234";
				
			if (email.equals(myEmail) && psw.equals(myPsw)) {
				request.setAttribute("name", "이관용");
				request.setAttribute("dept", "홍보");
				request.getRequestDispatcher("profile").forward(request, response);
			}else {
				response.sendRedirect("fail-login");
				}
		}
	
	protected void chart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		String email = request.getParameter("email");
		String psw = request.getParameter("psw");
		
		
	}
}
