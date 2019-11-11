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
 * ʹ��Filterʵ�ֵĵ�ҳ��"/FilterTest/login.jsp"��¼�߼�
 * ���������������������ڣ�����ڡ���������������
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
		
		// System.out.println("filterִ����");
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
		
		//����ѵ�¼������ת��¼�ɹ�ҳ��
		if (session.getAttribute("sUsername") != null) {
			if (session.getAttribute("sUsername").equals("ADMIN") && session.getAttribute("sPwd").equals("0000")) {
				session.setAttribute("flag", "ok");
				hRequest.getRequestDispatcher("success.jsp").forward(request, response);
			} else {
				session.setAttribute( "flag", "�û������������");
				hRequest.getRequestDispatcher("login.jsp").forward(request, response);
				// hResponse.sendRedirect("login.jsp");
				return;
			}
		} else {
			session.setAttribute( "flag", "��½ʧ��");
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
