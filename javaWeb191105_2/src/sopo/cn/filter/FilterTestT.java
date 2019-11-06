package sopo.cn.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import sopo.cn.controller.UserController;

/**
 * Servlet Filter implementation class FilterTestT
 */
@WebFilter("/*")
public class FilterTestT implements Filter {

    /**
     * Default constructor. 
     */
    public FilterTestT() {
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
		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpServletResponse hResponse = (HttpServletResponse) response;
		HttpSession session = hRequest.getSession();
		
		
		String username = (String) session.getAttribute("user");
		if (username != null) {
			hResponse.sendRedirect(hRequest.getContextPath() + "/main.jsp");
		} else {
			Cookie[] cookies = hRequest.getCookies();
			String userKey = null;
			String ssid = null;
			
			if (cookies != null && cookies.length > 0) {
				for ( Cookie c : cookies) {
					if (c.getName().equals("userKey")) {
						userKey = c.getValue();
					}
					if (c.getName().equals("ssid")) {
						ssid = c.getValue();
					}
				}
			}
			
			if (userKey != null && ssid != null) {
				new UserController().login();
				return;
			} else {
				chain.doFilter(request, response);
			}
		}
		// pass the request along the filter chain
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
