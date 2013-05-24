package com.atoz.survey.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atoz.survey.dao.mysqlimpl.PaperDaoImpl;
import com.atoz.survey.po.User;
import com.atoz.survey.service.PaperService;
import com.atoz.survey.service.UserService;
import com.atoz.survey.service.impl.UserServiceImpl;
import com.sun.xml.internal.bind.v2.model.core.ID;

public class AdminServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AdminServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserService userService = new UserServiceImpl();

		// 查找所有用户
		String action = request.getParameter("action");
		if (action.equals("findAllUsers")) {
			List<User> users = userService.findAllUsers();
			HttpSession session = request.getSession();
			session.setAttribute("users", users);
			response.sendRedirect("iframe.jsp");
		}
		// 查找用户，返回查找表单页面
		else if (action.equals("findUser")) {
			HttpSession session = request.getSession();
			session.setAttribute("findUser", "findUser");
			response.sendRedirect("iframe.jsp");
		}
		else if (action.equals("addUser")) {
			HttpSession session = request.getSession();
			session.setAttribute("addUser", "addUser");
			response.sendRedirect("iframe.jsp");
		}
		// 根据用户ID查找用户信息
		String userIdString = request.getParameter("findUserByUserId");
		if (userIdString != null) {
			int userId = Integer.parseInt(userIdString);
			if (action != null) {
				User user = userService.findUserByUserId(userId);
				HttpSession session = request.getSession();
				session.setAttribute("findUserResult", user);
				response.sendRedirect("iframe.jsp");
			}
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}