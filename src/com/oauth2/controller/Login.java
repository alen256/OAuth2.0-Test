package com.oauth2.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oauth2.bll.Auth;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username == null || password == null){
			request.setAttribute("error_msg", "用户名或密码为空");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			return;
		}
		HttpSession session = request.getSession();
		String app_name = (String)session.getAttribute("app_name");
		if (app_name == null || app_name == ""){
			request.setAttribute("message", "无效跳转");
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
			return;
		}
		if (Auth.check(username, password)){
			session.setAttribute("username", username);
			request.setAttribute("app_name", app_name);
			request.setAttribute("redirect_uri", (String)session.getAttribute("redirect_uri"));
			request.getRequestDispatcher("/WEB-INF/confirm.jsp").forward(request, response);
		}
		else{
			request.setAttribute("error_msg", "用户名或密码不正确");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
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
