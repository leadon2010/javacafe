<%@page import="java.util.ArrayList"%>
<%@page import="zjdbc.GoodsDO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="gdsdo" class="zjdbc.GoodsDO" />
<jsp:useBean id="gdsdao" class="zjdbc.GoodsDAO" />
<jsp:setProperty name="gdsdo" property="*" />

<%
	String action = request.getParameter("action");

	if (action == null) {
		out.println("action null");

	} else if (action.equals("list")) {
		ArrayList<GoodsDO> gdslist = gdsdao.selectAll();
		request.setAttribute("gdsdao", gdslist);

		pageContext.forward("prodAll.jsp");

	} else if (action.equals("insert")) {
		if (gdsdao.insertGood(gdsdo)) {
			response.sendRedirect("prodControl.jsp?action=list");

		} else {
			out.println("login.jsp");

		}

	} else if (action.equals("search")) {
		GoodsDO gds = gdsdao.selectOne(gdsdo.getProd_no());
		request.setAttribute("gdsdo", gds);

		pageContext.forward("prodSearch.jsp");

	} else if (action.equals("home")) {
		GoodsDO newusr = gdsdao.selectOne(gdsdo.getProd_no());
		if (newusr.getProd_no() != null) {
			request.setAttribute("usrdo", newusr);
			pageContext.forward("home.jsp");
		} else {
			response.sendRedirect("home.jsp");
		}

	} else if (action.equals("login")) {
		if (action.equals("null")) {
			response.sendRedirect("memberControl.jsp?action=home&user_no=" + gdsdo.getProd_no());

		} else {
			response.sendRedirect("../members/login.jsp");
		}

	} else if (action.equals("logout")) {
		session.invalidate();
		response.sendRedirect("index.jsp");

	} else {
		out.println("error action.");
	}
%>