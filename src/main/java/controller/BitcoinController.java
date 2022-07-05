package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.dto.MemberDTO;

/**
 * Servlet implementation class BitcoinController
 */
@WebServlet("/cointracker")
public class BitcoinController extends HttpServlet {
	
	private String url = "showError.jsp";
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String command = request.getParameter("command");
		System.out.println(command);
		try{
			if(command.equals("login")){
				login(request, response);
			}else if(command.equals("logout")) {
				logout(request, response);
			}else if(command.equals("signup")) {
				signup(request, response);
			}else if(command.equals("addbookmark")) {
				addBookmark(request, response);
			}
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			request.getRequestDispatcher("showError.jsp").forward(request, response);
			s.printStackTrace();
		}
	}

	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			
			String id = request.getParameter("id");
			String psw = request.getParameter("psw");
			
			
			MemberDTO member = MemberDAO.getInstance().getMember(id);
				
			if (id.equals(member.getId()) && psw.equals(member.getPassword())) {
				url = "index.html";
				Cookie cookie = new Cookie("userId", id);
			    cookie.setMaxAge(60*60*24); 
			    cookie.setPath("/"); 
			    response.addCookie(cookie);
				response.sendRedirect(url);
			}else {
				response.sendRedirect(url);
				}
		}
	
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		url = "index.html";
		Cookie cookie = new Cookie("userId", null); // 삭제할 쿠키에 대한 값을 null로 지정
	    cookie.setMaxAge(0); // 유효시간을 0으로 설정해서 바로 만료시킨다.
	    cookie.setPath("/"); 
	    response.addCookie(cookie); // 응답에 추가해서 없어지도록 함
	    response.sendRedirect(url);
	}
	
	protected void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String psw = request.getParameter("psw");

		System.out.println(request.getParameter("email"));
		MemberDTO newmember = MemberDTO.builder().id(id).name(name).password(psw).build();
		try {
			url = "index.html";
			MemberDAO.getInstance().addMember(newmember);
			response.sendRedirect(url);
		} catch (RollbackException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", "중복된 e-mail 입니다.");
			request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	protected void addBookmark(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("id");
		
		System.out.println(id);
		
	}
}
