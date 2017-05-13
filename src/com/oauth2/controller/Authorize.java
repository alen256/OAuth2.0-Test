package com.oauth2.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oauth2.bll.SearchModel;
import com.oauth2.model.App;

/**
 * Servlet implementation class Authorize
 */
@WebServlet("/authorize")
public class Authorize extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authorize() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirect_uri = request.getParameter("redirect_uri");
		String appid = request.getParameter("appid");
		String state = request.getParameter("state");
		String response_type = request.getParameter("response_type");
		
		if (redirect_uri == null || appid == null || state == null || response_type == null){
			request.setAttribute("message", "参数不完整");
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
			return;
		}
		App app = SearchModel.getApp(appid);
		if (app == null){
			request.setAttribute("message", "App不在信任名单中");
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
			return;
		}
		HttpSession session = request.getSession();
		session.setAttribute("redirect_uri", URLEncoder.encode(redirect_uri, "UTF-8"));
		session.setAttribute("app_domain", app.getDomain());
		session.setAttribute("app_name", app.getName());
		session.setAttribute("appid", appid);
		session.setAttribute("state", state);
		session.setAttribute("response_type", response_type);

		if (session.getAttribute("username") == null || session.getAttribute("username") == ""){
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
		else{
			request.getRequestDispatcher("/WEB-INF/confirm.jsp").forward(request, response);
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
