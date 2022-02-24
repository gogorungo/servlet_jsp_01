package org.comstudy21.myapp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {

	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); // 요청할때
		resp.setCharacterEncoding("UTF-8"); // 요청을 돌려줄 때
		resp.setContentType("text/html; charset=UTF-8");
		
		String ctxPath = req.getContextPath();
		String reqUri = req.getRequestURI();
		
		int beginIndex = ctxPath.length();
		int endIndex = reqUri.lastIndexOf(".");
		
		String path = reqUri.substring(beginIndex, endIndex);
		endIndex = path.lastIndexOf("/");
		String dirPath = endIndex == 0 ? "/home" : path.substring(0, endIndex);
		
		String viewName = "/WEB-INF/views/";
		if("/home".equals(path)) {
			viewName += "home.jsp";
		} else if("/member/list".equals(path)) {
			viewName += "member/list.jsp";
		} else if("/board/list".equals(path)) {
			viewName += "bbs/list.jsp";
		}
		
		req.setAttribute("path", path);
		
		//forward 하기
		RequestDispatcher view = req.getRequestDispatcher(viewName);
		view.forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	public void destroy() {
		System.out.println("destroy - DispatcherServlet");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("init - DispatcherServlet");
	}

}
