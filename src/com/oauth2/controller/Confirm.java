package com.oauth2.controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oauth2.bll.Auth;

/**
 * Servlet implementation class Confirm
 */
@WebServlet("/confirm")
public class Confirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Confirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String redirect_uri = (String)session.getAttribute("redirect_uri");
		if (redirect_uri == null || redirect_uri == ""){
			request.setAttribute("message", "无效跳转");
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
			return;
		}
		String username = (String)session.getAttribute("username");
		if (username == null || username == ""){
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			return;
		}
		String app_domain = (String)session.getAttribute("app_domain");
		String state = (String)session.getAttribute("state");
		String appid = (String)session.getAttribute("appid");
		String code = Auth.makeToken(username, appid, redirect_uri);

		session.removeAttribute("redirect_uri");
		session.removeAttribute("state");
		session.removeAttribute("appid");
		session.removeAttribute("app_name");
		session.removeAttribute("response_type");
		response.sendRedirect(URLDecoder.decode(app_domain + redirect_uri, "UTF-8") + "?code=" + code + "&state=" + state);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
