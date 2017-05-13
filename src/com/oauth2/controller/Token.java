package com.oauth2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Token
 */
@WebServlet("/token")
public class Token extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Token() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String code = request.getParameter("code");
		String redirect_uri = request.getParameter("redirect_uri");
		String appid = request.getParameter("appid");
		String secret = request.getParameter("secret");
		String grant_type = request.getParameter("grant_type");
		if(code == null || redirect_uri == null || appid == null || secret == null/* || grant_type == null*/){
			out.print("{\"info\": \"false\", \"msg\": \"缺少参数\"}");
			return;
		}
		redirect_uri = URLEncoder.encode(redirect_uri, "UTF-8");
		String token = com.oauth2.bll.Token.check(code, redirect_uri, appid, secret);
		if (token == ""){
			out.print("{\"info\": \"false\", \"msg\": \"code无效\"}");
		}
		else{
			out.print("{\"info\": \"true\", \"token\": \"" + token + "\", \"token_type\": \"Bearer\", \"expires_in\": 3600}");
		}
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
