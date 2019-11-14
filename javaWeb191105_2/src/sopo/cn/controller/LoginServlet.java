package sopo.cn.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/jsp;charset=UTF-8");
		response.getWriter().append("Served at: ").println(request.getContextPath());
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		HttpSession session = request.getSession();
		
		session.setAttribute("user", user);
		session.setAttribute("pass", pass);
		ServletContext application = session.getServletContext();
		@SuppressWarnings("unchecked")
		HashMap< String, String> dataMap = (HashMap<String, String>) application.getAttribute("online");
		if (dataMap ==  null) {
			dataMap = new HashMap< String, String>();
		}
		dataMap.put( session.getId(), user);
		application.setAttribute("online", dataMap);
		
		try {
			//转发
			// request.getRequestDispatcher("/shop.jsp").forward(request, response);
			// 重定向
			System.out.println("user1:" + session.getAttribute("user"));
			response.sendRedirect("Count163/shop.jsp");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
