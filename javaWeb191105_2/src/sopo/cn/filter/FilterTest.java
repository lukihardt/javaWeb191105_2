package sopo.cn.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 使用Filter实现的单页面"/FilterTest/login.jsp"登录逻辑
 * ★★★★★★★★★★★★★★★★★多入口，多出口★★★★★★★★★★★★★★★
 * @author Administrator
 *
 */
public class FilterTest implements Filter {

    /**
     * Default constructor. 
     */
    public FilterTest() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		
		// System.out.println("filter执行了");
		HttpServletRequest hRequest = (HttpServletRequest) request;
		// System.out.println(hRequest.getParameter("username"));
		String usrname = hRequest.getParameter("username");
		String pwd = hRequest.getParameter("password");
		
		HttpSession session = hRequest.getSession();
		if (session.getAttribute("flag") != null) {
			if (session.getAttribute("flag").equals("ok")) {
				hRequest.getRequestDispatcher("success.jsp").forward(request, response);
				// hResponse.sendRedirect("");
				return;
			}
		}
		
		
		if (usrname != null && pwd != null) {
			session.setAttribute( "sUsername", usrname);
			session.setAttribute( "sPwd", pwd);
		} 
		
		//如果已登录过，跳转登录成功页面
		if (session.getAttribute("sUsername") != null) {
			if (session.getAttribute("sUsername").equals("ADMIN") && session.getAttribute("sPwd").equals("0000")) {
				session.setAttribute("flag", "ok");
				hRequest.getRequestDispatcher("success.jsp").forward(request, response);
			} else {
				session.setAttribute( "flag", "用户名或密码错误");
				hRequest.getRequestDispatcher("login.jsp").forward(request, response);
				// hResponse.sendRedirect("login.jsp");
				return;
			}
		} else {
			session.setAttribute( "flag", "登陆失败");
			hRequest.getRequestDispatcher("login.jsp").forward(request, response);
			// hResponse.sendRedirect("login.jsp");
			return;
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
