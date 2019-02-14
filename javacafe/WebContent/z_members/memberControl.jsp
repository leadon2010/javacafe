<%@page import="java.util.ArrayList"%>
<%@page import="jdbc.MembersDO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="usrdo" class="jdbc.MembersDO" />
<jsp:useBean id="usrdao" class="jdbc.MembersDAO" />
<jsp:setProperty name="usrdo" property="*" />

<%
	String action = request.getParameter("action");

	if (action == null) {
		out.println("action null");

	} else if (action.equals("list")) {
		ArrayList<MembersDO> usrlist = usrdao.selectAll();
		request.setAttribute("usrdo", usrlist);

		pageContext.forward("userAll.jsp");

	} else if (action.equals("search")) {
		MembersDO data = usrdao.selectOne(usrdo.getUser_no());
		request.setAttribute("usrdo", data);

		pageContext.forward("userSearch.jsp");

	} else if (action.equals("home")) {
		MembersDO newusr = usrdao.selectOne(usrdo.getUser_no());
		if (newusr.getUser_no() != null) {
			request.setAttribute("usrdo", newusr);
			pageContext.forward("home.jsp");
		} else {
			response.sendRedirect("home.jsp");
		}

	} else if (action.equals("login")) {
		if (usrdao.login(usrdo.getUser_no(), usrdo.getPassword())) {
			response.sendRedirect("memberControl.jsp?action=home&user_no=" + usrdo.getUser_no());

		} else {
			response.sendRedirect("login.jsp");
		}

	} else if (action.equals("insert")) {
		if (usrdao.insert(usrdo)) {
			response.sendRedirect("memberControl.jsp?action=home&user_no=" + usrdo.getUser_no());

		} else {
			out.println("login.jsp");

		}
	} else if (action.equals("logout")) {
		session.invalidate();
		response.sendRedirect("index.jsp");

	} else {
		out.println("error action.");
	}
%>