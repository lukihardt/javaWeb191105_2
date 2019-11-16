package sopo.cn.controller.lession45FromInternet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sopo.cn.dao.lession45FromInternet.ImageDao;
import sopo.cn.dao.lession45FromInternet.JdbcImageDao;
import sopo.cn.model.lession45FromInternet.Image;

/**
 * Servlet implementation class ViewServlet
 */
@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").println(request.getContextPath());
		// 调用ImageDao查询
		ImageDao imageDao = new JdbcImageDao();
		List<Image> list = imageDao.finaAll();
		// 将集合放入request
		request.setAttribute( "imagesReq", list);
		// System.out.println(list);
		// 跳转到view.jsp
		request.getRequestDispatcher("/lession45FromInternet/view.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
